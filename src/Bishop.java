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
    public boolean move(Coords coord) {
        if (isValidMove(coords)) {
            this.coords.modify(coords.getfst(), coords.getlst());
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
    public boolean isValidMove(Coords coord) {
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
