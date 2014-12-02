import java.util.Set;


public class Queen implements ChessPiece {
    
    public Coords<Integer, Integer> coords;
    public boolean isWhite;
    public Set<Coords<Integer, Integer>> possibleMoves;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isValidMove(Coords<Integer, Integer> coord) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♕" : "♛";
    }

}
