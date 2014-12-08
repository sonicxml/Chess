import java.util.HashSet;
import java.util.Set;

public class BoardState {
    static final ChessPiece[][] chessPieces = new ChessPiece[8][8];
    static Coords blackKing = new Coords(0, 4);
    static Coords whiteKing = new Coords(7, 4);
    static boolean whiteInCheck = false;
    static boolean blackInCheck = false;
    private static int whiteScore = 0;
    private static int blackScore = 0;

    private static enum Piece {
        Pawn,
        Knight,
        Bishop,
        Rook,
        Queen,
        King,
    }

    public static void resetPieces() {
        blackKing = new Coords(0, 4);
        whiteKing = new Coords(7, 4);
        whiteInCheck = false;
        blackInCheck = false;
        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                if (i < 2 || i > 5) {
                    Coords location = new Coords(i, j);
                    boolean isWhite = (i > 5);
                    if (i == 1 || i == 6) {
                        // Pawn
                        chessPieces[i][j] = (new Pawn(location, isWhite));
                    } else if (j == 0 || j == 7) {
                        // Rook
                        chessPieces[i][j] = (new Rook(location, isWhite));
                    } else if (j == 1 || j == 6) {
                        // Knight
                        chessPieces[i][j] = (new Knight(location, isWhite));
                    } else if (j == 2 || j == 5) {
                        // Bishop
                        chessPieces[i][j] = (new Bishop(location, isWhite));
                    } else if (j == 3) {
                        // Queen
                        chessPieces[i][j] = (new Queen(location, isWhite));
                    } else if (j == 4) {
                        // King
                        chessPieces[i][j] = (new King(location, isWhite));
                    }
                } else {
                    // Blank
                    chessPieces[i][j] = null;
                }
            }
        }
    }

    public static void movePiece(Coords oldLoc, Coords newLoc) {
        int i1 = oldLoc.getfst();
        int j1 = oldLoc.getlst();
        int i2 = newLoc.getfst();
        int j2 = newLoc.getlst();

        ChessPiece temp = chessPieces[i1][j1];

        if (temp.getClass().getName().equals("King")) {
            if (temp.isWhite()) {
                whiteKing = newLoc;
            } else {
                blackKing = newLoc;
            }
        }

        chessPieces[i1][j1] = null;
        if (chessPieces[i2][j2] != null) {
            int score = getRelativeValue(
                    Piece.valueOf(chessPieces[i2][j2].getClass().getName()));
            if (temp.isWhite()) {
                whiteScore += score;
            } else {
                blackScore += score;
            }
        }
        chessPieces[i2][j2] = temp;
    }

    public static int getWhiteScore() {
        return whiteScore;
    }

    public static int getBlackScore() {
        return blackScore;
    }

    public static void resetScores() {
        whiteScore = 0;
        blackScore = 0;
    }

    public static boolean isInCheck(Coords init, Coords loc, Coords move, boolean white) {
        ChessPiece temp1 = new Pawn(new Coords(0, 0), true);
        ChessPiece temp2 = new Pawn(new Coords(0, 0), true);
        if (init != null && move != null) {
            temp1 = chessPieces[init.getfst()][init.getlst()];
            temp2 = chessPieces[move.getfst()][move.getlst()];
            chessPieces[init.getfst()][init.getlst()] = null;
            chessPieces[move.getfst()][move.getlst()] = temp1;
        }

        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                if (chessPieces[i][j] == null) {
                    continue;
                }

                if (chessPieces[i][j].isWhite() != white) {
                    if (chessPieces[i][j].isValidMove(true, loc)) {
                        if (init != null && move != null) {
                            chessPieces[init.getfst()][init.getlst()] = temp1;
                            chessPieces[move.getfst()][move.getlst()] = temp2;
                        }

                        ChessBoard.repaint(white ? "White " : "Black "
                                + "is in check!");
                        if (loc == whiteKing && white) {
                            whiteInCheck = true;
                        } else if (loc == blackKing && !white) {
                            blackInCheck = true;
                        }

                        // System.out.println("White in check: " + whiteInCheck);
                        // System.out.println("Black in check: " + blackInCheck);
                        return true;
                    }
                }
            }
        }

        if (init != null && move != null) {
            chessPieces[init.getfst()][init.getlst()] = temp1;
            chessPieces[move.getfst()][move.getlst()] = temp2;
        }

        whiteInCheck = false;
        blackInCheck = false;
        return false;
    }

    public static int isMate() {
        // Return values:
        // -1: Black checkmated White
        // 0: Stalemate
        // 1: White checkmated Black
        // 2: No mate

        isInCheck(null, whiteKing, null, true);
        isInCheck(null, blackKing, null, false);
        Set<Coords> whiteMoves = new HashSet<Coords>();
        Set<Coords> blackMoves = new HashSet<Coords>();
        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                ChessPiece temp = chessPieces[i][j];
                if (temp == null) {
                    continue;
                }

                Set<Coords> tempMoves = temp.getPossibleMoves(false);
                if (temp.isWhite()) {
                    whiteMoves.addAll(tempMoves);
//                    if (whiteInCheck) {
//                        System.out.println(temp.toString() + ": " + tempMoves.size());
//                    }
                } else {
                    blackMoves.addAll(tempMoves);
//                    System.out.println(temp.toString() + ": " + tempMoves.size() + blackInCheck);
                }
            }
        }

//        System.out.println("White moves: " + whiteMoves.size());
//        System.out.println("Black moves: " + blackMoves.size());
        if (whiteMoves.isEmpty() && blackMoves.isEmpty()) {
            return 0;
        } else if (whiteMoves.isEmpty()) {
            return -1;
        } else if (blackMoves.isEmpty()) {
            return 1;
        } else {
            return 2;
        }
    }

    static ChessPiece[][] getBoard() {
        return chessPieces;
    }

    private static int getRelativeValue(Piece p) {
        switch (p) {
            case Pawn: return 1;
            case Knight: return 3;
            case Bishop: return 3;
            case Rook: return 5;
            case Queen: return 9;
            case King: return 0;
            default: return 0;
        }
    }
}
