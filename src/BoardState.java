public class BoardState {
    ChessPiece[][] chessPieces;

    BoardState() {
        chessPieces = new ChessPiece[8][8];
        resetPieces();
    }
    public void resetPieces() {
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

    public ChessPiece[][] getBoard() {
        return chessPieces;
    }

    public void modifyboard(ChessPiece[][] old) {
        if (chessPieces == old) {
            System.out.println("HOUSTON");
        } else {
            System.out.println("NO HOUSTON MAYBE");
        }
        chessPieces = old;
    }
}
