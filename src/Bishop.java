import java.util.Set;


public class Bishop implements ChessPiece {
    
    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;

    public Bishop(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
    }

    @Override
    public boolean move(Coords c) {
        if (isValidMove(c)) {
            this.coords.modify(c.getfst(), c.getlst());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Coords> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♗" : "♝";
    }

    @Override
    public String toString() {
        return "Bishop";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
