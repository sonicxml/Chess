import java.util.Set;

public abstract class ChessPiece {

    protected boolean isWhite;
    protected Coords coords;
    protected Coords oldCoords;
    protected Set<Coords> possibleMoves;

    public boolean move(Coords c) {
        if (isValidMove(c)) {
            oldCoords = new Coords(coords.getfst(), coords.getlst());
            BoardState.movePiece(coords, c);
            this.coords.modify(c.getfst(), c.getlst());
            System.out.println("Moving out, diggity dawg!");
            return true;
        } else {
            return false;
        }
    }
    
    public abstract Set<Coords> getPossibleMoves();
    
    public boolean isValidMove(Coords c) {
        return possibleMoves.contains(c);
    }
    
    public abstract String getIcon();

    public abstract String toString();
    
    public boolean isWhite() {
        return isWhite;
    }

    public void undoLastMove() {
        BoardState.movePiece(this.coords, oldCoords);
        this.coords = oldCoords;
    }

    protected boolean inBounds(Coords c) {
        int i = c.getfst();
        int j = c.getlst();

        return !(i > 7 || i < 0 || j > 7 || j < 0);
    }

}
