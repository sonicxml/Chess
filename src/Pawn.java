import java.util.HashSet;
import java.util.Set;

public class Pawn implements ChessPiece {

    public final boolean isWhite;
    private Coords coords;
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
        int i = x;
        int j = y;
        Coords move;
        ChessPiece[][] chessPieces = BoardState.getBoard();

        // White moves up the board, black moves down
        int incr = isWhite ? -1 : 1;

        if (x == 1 || x == 6) {
            i = x + 2 * incr;
            j = y;
            move = new Coords(i, j);
            if (chessPieces[i][j] == null) {
                possibleMoves.add(move);
            }
        }

        // Forward move
        i = x + incr;
        j = y;
        move = new Coords(i, j);
        if (chessPieces[i][j] == null)
            possibleMoves.add(move);
        // Attacking move 1
        i = x + incr;
        j = y + incr;
        move = new Coords(i, j);
        if (chessPieces[i][j] != null && chessPieces[i][j].isWhite() != isWhite)
            possibleMoves.add(move);
        // Attacking move 2
        i = x + incr;
        j = y - incr;
        move = new Coords(i,j);
        if (chessPieces[j][j] != null && chessPieces[j][j].isWhite() != isWhite)
            possibleMoves.add(move);

        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords c) {
        return possibleMoves.contains(c);
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
