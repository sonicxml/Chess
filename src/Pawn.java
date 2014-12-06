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
    public boolean move(Coords c) {
        if (isValidMove(c)) {
            this.coords.modify(c.getfst(), c.getlst());
            System.out.println("Moving out, diggity dawg!");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Coords> getPossibleMoves() {
        possibleMoves.removeAll(possibleMoves);

        if (coords.getfst() == 0 || coords.getfst() == 7) {
            return possibleMoves; // Empty set - promotion time
        }

        int x = coords.getfst();
        int y = coords.getlst();
        Coords move;

        // White moves up the board, black moves down
        int incr = isWhite ? -1 : 1;

        if (x == 1 || x == 6) {
            move = new Coords(x + 2 * incr, y);
            if (isValidMove(move)) {
                possibleMoves.add(move);
            }
        }

        // Forward move
        move = new Coords(x + incr, y);
        if (isValidMove(move))
            possibleMoves.add(move);
        // Attacking move 1
        move = new Coords(x + incr, y + incr);
        if (isValidMove(move))
            possibleMoves.add(move);
        // Attacking move 2
        move = new Coords(x + incr, y - incr);
        if (isValidMove(move))
            possibleMoves.add(move);

        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords c) {
        int i = coords.getfst();
        int j = coords.getlst();
        int x = c.getfst();
        int y = c.getlst();

        ChessPiece[][] chessPieces = BoardState.getBoard();

        int incr = isWhite ? -1 : 1;

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        if (chessPieces[x][y] != null && chessPieces[x][y].isWhite() != isWhite) {
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
