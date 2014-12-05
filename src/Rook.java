import java.util.Set;


public class Rook implements ChessPiece {
    
    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;

    public Rook(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
    }

    @Override
    public boolean move(Coords coord) {
        if (isValidMove(coords)) {
            this.coords.modify(coords.getfst(), coord.getlst());
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
        return (isWhite) ? "♖" : "♜";
    }

    @Override
    public String toString() {
        return "Rook";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
