import java.util.HashSet;
import java.util.Set;


public class Rook implements ChessPiece {
    
    public final boolean isWhite;
    private Coords coords;
    private Coords oldCoords;
    private Set<Coords> possibleMoves;

    public Rook(Coords coords, boolean isWhite) {
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

        for (int i = -1; i < 2; i= i + 2) {
            try {
                int scale = 1;
                int p = x + i * scale;
                int q = y;
                while (chessPieces[p][q] == null) {
                    move = new Coords(p, q);
                    possibleMoves.add(move);
                    scale++;
                    p = x + i * scale;
                }

                if (chessPieces[p][q] != null &&
                        chessPieces[p][q].isWhite() != isWhite) {
                    move = new Coords(p, q);
                    possibleMoves.add(move);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Do Nothing
            }

            try {
                int scale = 1;
                int p = x;
                int q = y + i * scale;
                while (chessPieces[p][q] == null) {
                    move = new Coords(p, q);
                    possibleMoves.add(move);
                    scale++;
                    q = y + i * scale;
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
        return possibleMoves;
    }

    @Override
    public boolean isValidMove(Coords c) {
        return possibleMoves.contains(c);
    }

    @Override
    public String getIcon() {
        return (isWhite) ? "♖" : "♜";
    }

    @Override
    public String toString() {
        return "Rook";
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

}
