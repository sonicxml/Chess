import java.util.HashSet;
import java.util.Set;

public class Pawn extends ChessPiece {

    public Pawn(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public Set<Coords> getPossibleMoves(boolean calledFromCheck) {
        possibleMoves.removeAll(possibleMoves);

        if (coords.getfst() == 0 || coords.getfst() == 7) {
            return possibleMoves; // Empty set - promotion time
        }

        int x = coords.getfst();
        int y = coords.getlst();
        int i;
        int j;
        Coords move;
        ChessPiece[][] chessPieces = BoardState.getBoard();

        // White moves up the board, black moves down
        int incr = isWhite ? -1 : 1;

        if (x == 1 || x == 6) {
            i = x + 2 * incr;
            j = y;
            move = new Coords(i, j);
            if (chessPieces[i][j] == null) {
                addIfNotCheck(calledFromCheck, move);
            }
        }

        // Forward move
        i = x + incr;
        j = y;
        move = new Coords(i, j);
        if (chessPieces[i][j] == null)
            addIfNotCheck(calledFromCheck, move);

        // Attacking move 1
        i = x + incr;
        j = y + incr;
        move = new Coords(i, j);
        if (inBounds(move)) {
            if (chessPieces[i][j] != null
                    && chessPieces[i][j].isWhite() != isWhite)
                addIfNotCheck(calledFromCheck, move);
        }

        // Attacking move 2
        i = x + incr;
        j = y - incr;
        move = new Coords(i, j);
        if (inBounds(move)) {
            if (chessPieces[i][j] != null
                    && chessPieces[i][j].isWhite() != isWhite)
                addIfNotCheck(calledFromCheck, move);
        }

        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♙" : "♟";
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
