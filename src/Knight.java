import java.util.HashSet;
import java.util.Set;


public class Knight extends ChessPiece {

    public Knight(Coords coords, boolean isWhite) {
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

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j = j + 2) {
                if (i == 0) {
                    int p = x + j;
                    int q = y - 2;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                addIfNotCheck(calledFromCheck, move);
                            }
                        } else {
                            addIfNotCheck(calledFromCheck, move);
                        }
                    }

                    p = x + j;
                    q = y + 2;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                addIfNotCheck(calledFromCheck, move);
                            }
                        } else {
                            addIfNotCheck(calledFromCheck, move);
                        }
                    }
                } else {
                    int p = x + i * 2;
                    int q = y + j;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                addIfNotCheck(calledFromCheck, move);
                            }
                        } else {
                            addIfNotCheck(calledFromCheck, move);
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♘" : "♞";
    }
}
