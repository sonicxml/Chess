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
        int i = coords.getfst();
        int j = coords.getlst();
        int x = c.getfst();
        int y = c.getlst();
        
        ChessPiece[][] chessPieces = BoardState.getBoard();

        int incr = chessPieces[i][j].isWhite() ? -1 : 1;

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        
        if(chessPieces[x][y] != null) {
            if (i + incr == x && j + incr == y) 
                return true;
            else if (i + incr == x && j - incr == y) 
                return true;
            else 
                return false;
        } else {
            if (i + incr == x && j == y) 
                return true;
            else if (i + 2 * incr == x && j == y) 
                return true;
            else 
                return false;
        }
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
