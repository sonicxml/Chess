import java.util.HashSet;
import java.util.Set;


public class Knight implements ChessPiece {

    private Coords coords;
    public final boolean isWhite;
    private Set<Coords> possibleMoves;
    
    public Knight(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public boolean move(Coords c) {
        if (isValidMove(c)) {
            this.coords.modify(c.getfst(), c.getlst());
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
            for (int j = -1; j < 2; j = j + 2) {
                if (i == 0) {
                    int p = x + j;
                    int q = y - 2;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                possibleMoves.add(move);
                            }
                        } else {
                            possibleMoves.add(move);
                        }
                    }

                    p = x + j;
                    q = y + 2;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                possibleMoves.add(move);
                            }
                        } else {
                            possibleMoves.add(move);
                        }
                    }
                } else {
                    int p = x + i * 2;
                    int q = y + j;
                    move = new Coords(p, q);
                    if (inBounds(move)) {
                        if (chessPieces[p][q] != null) {
                            if (chessPieces[p][q].isWhite() != isWhite) {
                                possibleMoves.add(move);
                            }
                        } else {
                            possibleMoves.add(move);
                        }
                    }
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
        return (isWhite) ? "♘" : "♞";
    }

    @Override
    public String toString() {
        return "Knight";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    private boolean inBounds(Coords c) {
        int i = c.getfst();
        int j = c.getlst();

        return !(i > 7 || i < 0 || j > 7 || j < 0);
    }

}
