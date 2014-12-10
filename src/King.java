import java.util.HashSet;
import java.util.Set;


public class King extends ChessPiece {

    Set<Coords> castlingMoves = new HashSet<Coords>();
    boolean hasMoved = false; // For castling
    private Coords initCoords;
    private Coords oldCoords;

    public King(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        initCoords = new Coords(coords.getfst(), coords.getlst());
        possibleMoves = new HashSet<Coords>();
        // castlingMoves = new HashSet<Coords>();
    }

    @Override
    public String move(Coords c) {
        if (isValidMove(false, c)) {
            int i1 = coords.getfst() + 1;
            int j1 = coords.getlst() + 1;
            int i2 = c.getfst() + 1;
            int j2 = c.getlst() + 1;

            oldCoords = new Coords(coords.getfst(), coords.getlst());
            if (this.getClass().getName().equals("King")
                    && this.castlingMoves.contains(c)) {
                boolean kingside = c.getlst() > coords.getlst();
                BoardState.castle(coords, c, kingside);
                this.coords.modify(c.getfst(), c.getlst());
                int rookI = coords.getfst();
                int rookJ = c.getlst() + ((kingside) ? -1 : 1);
                BoardState.chessPieces[rookI][rookJ].
                        coords.modify(rookI, rookJ);
            } else {
                BoardState.movePiece(coords, c, false);
                this.coords.modify(c.getfst(), c.getlst());
            }
            System.out.println("Moving out, diggity dawg!");

            String mesg = ((isWhite) ? "White " : "Black ") +
                    this.getClass().getName() + " from "
                    + String.valueOf((char) (j1 + 64))
                    + String.valueOf(i1) + " to " +
                    String.valueOf((char) (j2 + 64)) + String.valueOf(i2) + ".";

            Coords kingLoc = isWhite ?
                    BoardState.blackKing : BoardState.whiteKing;

            int mate = BoardState.isMate();
            if (mate == 0) {
                mesg += " Stalemate!";
                Game.endScreen(mate);
            } else if (mate == -1) {
                mesg += " Black checkmated White!";
                Game.endScreen(mate);
            } else if (mate == 1) {
                mesg += " White checkmated Black!";
                Game.endScreen(mate);
            } else if (BoardState.isInCheck(coords, kingLoc, null, !isWhite)) {
                mesg += (isWhite() ? " Black " : " White ") + "is in check!";
            }
            return mesg;
        } else {
            return null;
        }
    }

    @Override
    public Set<Coords> getPossibleMoves(boolean calledFromCheck) {
        if (!coords.equals(initCoords)) {
            hasMoved = true;
//            System.out.println("KING MOVED! ATTN KING MOVED! YO OYOYOO");
        }

        possibleMoves.removeAll(possibleMoves);

        int x = coords.getfst();
        int y = coords.getlst();
        ChessPiece[][] chessPieces = BoardState.getBoard();
        Coords move;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int p = x + i;
                int q = y + j;
                move = new Coords(p, q);
                if (inBounds(move)) {
                    if(chessPieces[p][q] != null &&
                            chessPieces[p][q].isWhite() != isWhite) {
                        if (!calledFromCheck &&
                            !BoardState.isInCheck(coords, move, move, isWhite)) {
                            possibleMoves.add(move);
                        } else if (calledFromCheck) {
                            possibleMoves.add(move);
                        }
                    } else if (chessPieces[p][q] == null) {
                        if (!calledFromCheck &&
                                !BoardState.isInCheck(coords, move,
                                        move, isWhite)) {
                            possibleMoves.add(move);
                        } else if (calledFromCheck) {
                            possibleMoves.add(move);
                        }
                    }
                }
            }
        }

        // Castling
        // Rules of Castling:
        // King & Rook can't have moved
        // King can't be in check and can't move in or through check
        boolean inCheck = (isWhite) ? BoardState.whiteInCheck :
                BoardState.blackInCheck;
        if (hasMoved == false && !inCheck) {
            // Kingside castling
            if (possibleMoves.contains(new Coords(x, y + 1))) {
                int q = y + 2;
                move = new Coords(x, q);
                if (chessPieces[x][q] == null &&
                    chessPieces[x][q + 1] != null &&
                    chessPieces[x][q + 1].getClass().getName().equals("Rook")
                    && !chessPieces[x][q + 1].hasMoved
                    && !calledFromCheck &&
                    !BoardState.isInCheck(coords, move, move, isWhite)) {
                    possibleMoves.add(move);
                    castlingMoves.add(move);
                }
            }

            // Queenside castling
            if (possibleMoves.contains(new Coords(x, y - 1))) {
                int q = y - 2;
                move = new Coords(x, q);
                if (chessPieces[x][q] == null &&
                    chessPieces[x][q - 1] == null &&
                    chessPieces[x][q - 2] != null &&
                    chessPieces[x][q - 2].getClass().getName().equals("Rook")
                    && !chessPieces[x][q - 2].hasMoved &&
                    !calledFromCheck &&
                    !BoardState.isInCheck(coords, move, move, isWhite)) {

                    possibleMoves.add(move);
                    castlingMoves.add(move);
                }
            }
        }
//        System.out.println(castlingMoves);
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♔" : "♚";
    }
}
