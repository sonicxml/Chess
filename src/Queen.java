import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;


public class Queen implements ChessPiece {
    
    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;

    public Queen(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public boolean move(Coords coord) {
        if (isValidMove(coord)) {
            this.coords.modify(coords.getfst(), coords.getlst());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<Coords> getPossibleMoves() {
        possibleMoves.removeAll(possibleMoves);

        int x = coords.getfst();
        int y = coords.getlst();
        ChessPiece[][] chessPieces = ChessBoard.getBoard();
        Coords move;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int scale = 1;
                try {
                    while (chessPieces[x + i*scale][y + j*scale] == null) {
                        move = new Coords(x + i*scale, y + j*scale);
                        possibleMoves.add(move);
                        scale++;
                    }
                    if (chessPieces[x + i*scale][y + j*scale] != null &&
                        chessPieces[x + i*scale][y + j*scale].isWhite() != isWhite) {
                        move = new Coords(x + i*scale, y + j*scale);
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
    public boolean isValidMove(Coords c) {
        return possibleMoves.contains(c);
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♕" : "♛";
    }

    @Override
    public String toString() {
        return "Queen";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

}
