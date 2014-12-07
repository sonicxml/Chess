import java.util.HashSet;
import java.util.Set;


public class King implements ChessPiece {
    
    public final boolean isWhite;
    private Coords coords;
    private Coords oldCoords;
    private Set<Coords> possibleMoves;

    public King(Coords coords, boolean isWhite) {
        this.coords = coords;
        this.isWhite = isWhite;
        possibleMoves = new HashSet<Coords>();
    }

    @Override
    public boolean move(Coords c) {
        if (isValidMove(c)) {
            oldCoords = new Coords(coords.getfst(), coords.getlst());
            BoardState.movePiece(coords, c);
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
                        possibleMoves.add(move);
                    } else if (chessPieces[p][q] == null) {
                        possibleMoves.add(move);
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
        return (isWhite) ? "♔" : "♚";
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void undoLastMove() {
        BoardState.movePiece(this.coords, oldCoords);
        this.coords = oldCoords;
    }

    private boolean inBounds(Coords c) {
        int i = c.getfst();
        int j = c.getlst();

        return !(i > 7 || i < 0 || j > 7 || j < 0);
    }

}
