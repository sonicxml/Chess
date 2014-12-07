import java.util.Set;

public interface ChessPiece {

    public boolean move(Coords c);
    
    public Set<Coords> getPossibleMoves();
    
    public boolean isValidMove(Coords c);
    
    public String getIcon();

    public String toString();
    
    public boolean isWhite();

    public void undoLastMove();
}
