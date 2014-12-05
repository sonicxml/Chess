import java.util.Set;

public interface ChessPiece {

    public boolean move(Coords coord);
    
    public Set<Coords> getPossibleMoves();
    
    public boolean isValidMove(Coords coord);
    
    public String getIcon();
    
    public String toString();
    
    public boolean isWhite();
}
