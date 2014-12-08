import java.util.HashSet;
import java.util.Set;


public class Bishop extends ChessPiece {

    public Bishop(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public Set<Coords> getPossibleMoves(boolean calledFromCheck) {
        possibleMoves.removeAll(possibleMoves);

        int x = coords.getfst();
        int y = coords.getlst();
        ChessPiece[][] chessPieces = BoardState.getBoard();
        Coords move;

        for (int i = -1; i < 2; i = i + 2) {
            for (int j = -1; j < 2; j = j + 2) {
                int scale = 1;
                try {
                    int p = x + i * scale;
                    int q = y + j * scale;
                    while (chessPieces[p][q] == null) {
                        move = new Coords(p, q);
                        possibleMoves.add(move);
                        scale++;
                        p = x + i * scale;
                        q = y + j * scale;
                    }
                    if (chessPieces[p][q] != null &&
                            chessPieces[p][q].isWhite() != isWhite) {
                        move = new Coords(p, q);
                        possibleMoves.add(move);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do Nothing
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♗" : "♝";
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
