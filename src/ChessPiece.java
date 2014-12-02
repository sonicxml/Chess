import java.util.Set;

public interface ChessPiece {
    public boolean move(Coords<Integer, Integer> coord);
    
    public Set<Coords<Integer, Integer>> getPossibleMoves();
    
    public boolean isValidMove(Coords<Integer, Integer> coord);
    
    public String getIcon();
}
