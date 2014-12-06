import java.util.HashSet;
import java.util.Set;


public class Knight implements ChessPiece {

    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;
    
    public Knight(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
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
        return (isWhite) ? "♘" : "♞";
    }

    @Override
    public String toString() {
        return "Knight";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
