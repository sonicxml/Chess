import java.util.Set;

public class Pawn implements ChessPiece {
    
    public Coords<Integer, Integer> coords;
    public boolean isWhite;
    public Set<Coords<Integer, Integer>> possibleMoves;
    
    public Pawn(Coords<Integer, Integer> coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
    }
    
    @Override
    public boolean move(Coords<Integer, Integer> coords) {
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
        return (isWhite) ? "♙" : "♟";
    }

    @Override
    public String getName() {
        return "Pawn";
    }

}
