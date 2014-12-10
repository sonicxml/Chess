import java.util.HashSet;
import java.util.Set;


public class Rook extends ChessPiece {

    public boolean hasMoved = false; // For castling
    private Coords initCoords;

    public Rook(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        initCoords = new Coords(coords.getfst(), coords.getlst());
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public Set<Coords> getPossibleMoves(boolean calledFromCheck) {
        if (!coords.equals(initCoords)) {
            hasMoved = true;
            System.out.println("KING MOVED! ATTN KING MOVED! YO OYOYOO");
        }

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
                    addIfNotCheck(calledFromCheck, move);

                    scale++;
                    p = x + i * scale;
                }

                if (chessPieces[p][y] != null &&
                        chessPieces[p][y].isWhite() != isWhite) {
                    move = new Coords(p, y);
                    addIfNotCheck(calledFromCheck, move);

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Do Nothing
            }

            try {
                int scale = 1;
                int q = y + i * scale;
                while (chessPieces[x][q] == null) {
                    move = new Coords(x, q);
                    addIfNotCheck(calledFromCheck, move);

                    scale++;
                    q = y + i * scale;
                }

                if (chessPieces[x][q] != null &&
                        chessPieces[x][q].isWhite() != isWhite) {
                    move = new Coords(x, q);
                    addIfNotCheck(calledFromCheck, move);

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
}
