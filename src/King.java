import java.util.Set;


public class King implements ChessPiece {
    
    private Coords<Integer, Integer> coords;
    public final boolean isWhite;
    private Set<Coords<Integer, Integer>> possibleMoves;

    public King(Coords<Integer, Integer> coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
    }

    @Override
    public boolean move(Coords<Integer, Integer> coord) {
        if (isValidMove(coords)) {
            this.coords.modify(coords.getfst(), coords.getlst());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Coords<Integer, Integer>> getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords<Integer, Integer> coord) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♔" : "♚";
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
