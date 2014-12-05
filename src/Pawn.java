import java.util.HashSet;
import java.util.Set;

public class Pawn implements ChessPiece {
    
    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;
    
    public Pawn(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }
    
    @Override
    public boolean move(Coords coords) {
        if (isValidMove(coords)) {
            this.coords.modify(coords.getfst(), coords.getlst());
            System.out.println("Moving out, diggity dawg!");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Coords> getPossibleMoves() {
        possibleMoves.removeAll(possibleMoves);
        System.out.println(coords.toString());
        
        if (coords.getfst() == 0 || coords.getfst() == 7) {
            return possibleMoves; // Empty set - promotion time
        }
        
        // White moves up the board, black moves down
        int incr = isWhite ? -1 : 1; 
        
        if (coords.getfst() == 1 || coords.getfst() == 6) {
            possibleMoves.add(
                    new Coords(coords.getfst() + 2 * incr, coords.getlst()));
        }
        // Forward move
        possibleMoves.add(new Coords(coords.getfst() + incr, coords.getlst()));
        // Attacking move 1
        possibleMoves.add(new Coords(
                coords.getfst() + incr, coords.getlst() + incr));
        // Attacking move 2
        possibleMoves.add(new Coords(
                coords.getfst() + incr, coords.getlst() - incr));
        
        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords coord) {
        return possibleMoves.contains(coord);
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♙" : "♟";
    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
