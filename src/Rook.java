import java.util.HashSet;
import java.util.Set;


public class Rook extends ChessPiece {
    
    public Rook(Coords coords, boolean isWhite) {
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

        for (int i = -1; i < 2; i= i + 2) {
            try {
                int scale = 1;
                int p = x + i * scale;
                while (chessPieces[p][y] == null) {
                    move = new Coords(p, y);
                    possibleMoves.add(move);
                    scale++;
                    p = x + i * scale;
                }

                if (chessPieces[p][y] != null &&
                        chessPieces[p][y].isWhite() != isWhite) {
                    move = new Coords(p, y);
                    possibleMoves.add(move);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Do Nothing
            }

            try {
                int scale = 1;
                int q = y + i * scale;
                while (chessPieces[x][q] == null) {
                    move = new Coords(x, q);
                    possibleMoves.add(move);
                    scale++;
                    q = y + i * scale;
                }

                if (chessPieces[x][q] != null &&
                        chessPieces[x][q].isWhite() != isWhite) {
                    move = new Coords(x, q);
                    possibleMoves.add(move);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                // Do Nothing
            }
        }
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♖" : "♜";
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
