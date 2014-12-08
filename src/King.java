import java.util.HashSet;
import java.util.Set;


public class King extends ChessPiece {

    public King(Coords coords, boolean isWhite) {
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
            for (int j = -1; j < 2; j++) {
                int p = x + i;
                int q = y + j;

                move = new Coords(p, q);
                if (inBounds(move)) {
                    if(chessPieces[p][q] != null &&
                            chessPieces[p][q].isWhite() != isWhite) {
                        if (!calledFromCheck &&
                                !BoardState.isInCheck(coords, move,
                                        move, isWhite)) {
                            possibleMoves.add(move);
                        } else if (calledFromCheck) {
                            possibleMoves.add(move);
                        }
                    } else if (chessPieces[p][q] == null) {
                        if (!calledFromCheck &&
                                !BoardState.isInCheck(coords, move,
                                        move, isWhite)) {
                            possibleMoves.add(move);
                        } else if (calledFromCheck) {
                            possibleMoves.add(move);
                        }
                    }
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♔" : "♚";
    }
}
