import java.util.Set;

abstract class ChessPiece {

    boolean isWhite;
    Coords coords;
    private Coords oldCoords;
    Set<Coords> possibleMoves;

    public String move(Coords c) {
        if (isValidMove(false, c)) {
            int i1 = coords.getfst() + 1;
            int j1 = coords.getlst() + 1;
            int i2 = c.getfst() + 1;
            int j2 = c.getlst() + 1;

            oldCoords = new Coords(coords.getfst(), coords.getlst());
            BoardState.movePiece(coords, c);
            this.coords.modify(c.getfst(), c.getlst());
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
            } else if (mate == -1) {
                mesg += " Black checkmated White!";
            } else if (mate == 1) {
                mesg += " White checkmated Black!";
            } else if (BoardState.isInCheck(coords, kingLoc, null, !isWhite)) {
                mesg += (isWhite() ? " Black " : " White ") + "is in check!";
            }
            return mesg;
        } else {
            return null;
        }
    }
    
    public abstract Set<Coords> getPossibleMoves(boolean calledFromCheck);
    
    boolean isValidMove(boolean calledFromCheck, Coords c) {
        // If calledFromCheck is true, then we won't check for check
        // when searching for possible moves to avoid a stack overflow
        possibleMoves = getPossibleMoves(calledFromCheck);
        return possibleMoves.contains(c);
    }
    
    public abstract String getIcon();

    public String toString() {
        return (isWhite() ? "White " : "Black ") + this.getClass().getName() +
                " at " + String.valueOf((char) (coords.getlst() + 1 + 64))
                + String.valueOf(coords.getfst() + 1);
    }
    
    public boolean isWhite() {
        return isWhite;
    }

    public void undoLastMove() {
        BoardState.movePiece(this.coords, oldCoords);
        this.coords = oldCoords;
    }

    boolean inBounds(Coords c) {
        int i = c.getfst();
        int j = c.getlst();

        return !(i > 7 || i < 0 || j > 7 || j < 0);
    }

    void addIfNotCheck(boolean calledFromCheck, Coords move) {
        if (calledFromCheck) {
            possibleMoves.add(move);
        } else {
            if ((isWhite()) ? BoardState.whiteInCheck : BoardState.blackInCheck) {
                Coords kingLoc = isWhite ?
                        BoardState.whiteKing : BoardState.blackKing;
                if (!BoardState.isInCheck(coords, kingLoc, move, isWhite())) {
                    possibleMoves.add(move);
                }
            } else {
                possibleMoves.add(move);
            }
        }
    }
}
