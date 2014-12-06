import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard {
    static JLabel mesg; // Current message to player

    private static JButton[][] chessSquares = new JButton[10][10];

    private static final Dimension SIZE = new Dimension(750, 750);
    static JPanel frame = new JPanel(new GridBagLayout());

    static final Color DARK_TAN = new Color(190, 120, 50);
    static final Color LIGHT_TAN = new Color(247, 206, 132);

    static int whiteScore = 0;
    static int blackScore = 0;
    static boolean isWhitesMove = true;
    static boolean isClicked = false;
    static boolean isGreen = false;

    static int oldX;
    static int oldY;

    public ChessBoard(JLabel mesg) {
        GridBagConstraints c = new GridBagConstraints();
        frame.setPreferredSize(SIZE);
        frame.setMaximumSize(SIZE);
        frame.setMinimumSize(SIZE);
        frame.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.setFocusable(true);

        for (int i = 0; i < chessSquares[0].length; i++) {
            for (int j = 0; j < chessSquares[1].length; j++) {
                JButton square = new JButton();
                square.setBorder(BorderFactory.createLineBorder(Color.green,3));
                square.setBorderPainted(false);
                square.setPreferredSize(new Dimension(70, 70));
                square.setOpaque(true);

                if (i == 0 || i == 9) {
                    // Alphabetical label
                    String label = (j > 0 && j < 9) ? String
                            .valueOf((char) (j + 64)) : "";
                    square.setText(label);
                } else if (j == 0 || j == 9) {
                    // Numerical label
                    String label = (i > 0 && i < 9) ? String.valueOf(i) : "";
                    square.setText(label);
                } else {
                    square.setBackground((i % 2 != j % 2) ? DARK_TAN
                            : LIGHT_TAN);

                    final int iF = i;
                    final int jF = j;

                    square.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            toggleBackground(iF, jF);
                        }

                        public void mouseExited(MouseEvent e) {
                            toggleBackground(iF, jF);
                        }
                    });

                    square.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // chessPieces is offset by 1 in each direction
                            actionLogic(iF - 1, jF - 1);
                            repaint("");
                        }
                    });
                }

                chessSquares[i][j] = square;
                c.gridx = j;
                c.gridy = i;
                frame.add(chessSquares[i][j], c);
            }
        }
        this.mesg = mesg;
    }

    public static void reset(ChessBoard chessBoard) {
        setDefaultBGColors();
        BoardState.resetPieces();
        whiteScore = 0;
        blackScore = 0;
        isClicked = false;
        isWhitesMove = true;

        for (int i = 0; i < BoardState.chessPieces[0].length; i++) {
            for (int j = 0; j < BoardState.chessPieces[1].length; j++) {
                int i2 = i + 1;
                int j2 = j + 1;
                if (BoardState.chessPieces[i][j] == null) {
                    chessSquares[i2][j2].setText("");
                    setLabelFont(chessSquares[i2][j2]);
                } else {
                    chessSquares[i2][j2].setText(
                            BoardState.chessPieces[i][j].getIcon());
                    setLabelFont(chessSquares[i2][j2]);
                }
            }
        }
        mesg.setText("New Game! It's White's move. White: "
                + Integer.toString(whiteScore) + ", Black: "
                + Integer.toString(blackScore));
    }

    public static void undo() {
        BoardState.chessPieces = BoardState.oldPieces.clone();
        isWhitesMove = !isWhitesMove;
        Game.toggleUndo(false);
        // TODO: Undo score changes
        repaint("");
    }

    public static void repaint(String message) {
        message = (message != null) ? message : "";
        for (int i = 0; i < BoardState.chessPieces[0].length; i++) {
            for (int j = 0; j < BoardState.chessPieces[1].length; j++) {
                int i2 = i + 1;
                int j2 = j + 1;
                if (BoardState.chessPieces[i][j] == null) {
                    chessSquares[i2][j2].setText("");
                    setLabelFont(chessSquares[i2][j2]);
                } else {
                    chessSquares[i2][j2].setText(
                            BoardState.chessPieces[i][j].getIcon());
                    setLabelFont(chessSquares[i2][j2]);
                }
            }
        }
        String move = new String((isWhitesMove) ? "White's move"
                : "Black's move");
        mesg.setText(message + " It's " + move + " . White: "
                + Integer.toString(whiteScore) + ", Black: "
                + Integer.toString(blackScore));

    }

    private static void setDefaultBGColors() {
        for (int i = 0; i < chessSquares[0].length; i++) {
            for (int j = 0; j < chessSquares[1].length; j++) {
                if (i > 0 && i < 9 && j > 0 && j < 9) {
                    chessSquares[i][j]
                            .setBackground((i % 2 != j % 2) ? DARK_TAN
                                    : LIGHT_TAN);
                }
            }
        }
    }

    private static void toggleBackground(int i, int j) {
        if (chessSquares[i][j].getBackground() == Color.YELLOW) {
            chessSquares[i][j].setBackground((i % 2 != j % 2) ? DARK_TAN
                    : LIGHT_TAN);
        } else {
            chessSquares[i][j].setBackground(Color.YELLOW);
        }
        frame.repaint();
    }

    private static void togglePossibleMoves(int i, int j, Set<Coords> coords) {
        for (Coords c : coords) {
            int x = c.getfst() + 1;
            int y = c.getlst() + 1;
            chessSquares[x][y].setBorderPainted(!isGreen);
        }
        isGreen = !isGreen;
        frame.repaint();
    }

    private static void setLabelFont(JButton button) {
        Font f = button.getFont();
        button.setFont(f.deriveFont(f.getStyle(), 25));
    }

    private static void actionLogic(int iF, int jF) {
        if (BoardState.chessPieces[iF][jF] != null && !isClicked &&
                BoardState.chessPieces[iF][jF].isWhite() == isWhitesMove) {

            Set<Coords> possMoves =
                    BoardState.chessPieces[iF][jF].getPossibleMoves();
            togglePossibleMoves(iF, jF, possMoves);
            isClicked = true;
            oldX = iF;
            oldY = jF;
            BoardState.oldPieces = copyChessPieces(BoardState.chessPieces);
        } else if (isClicked) {
            Set<Coords> possMoves =
                    BoardState.chessPieces[oldX][oldY].getPossibleMoves();
            togglePossibleMoves(oldX, oldY, possMoves);
            isClicked = false;
            if (BoardState.chessPieces[oldX][oldY].move(new Coords(iF, jF))) {
                ChessPiece temp = BoardState.chessPieces[oldX][oldY];
                BoardState.chessPieces[oldX][oldY] = null;
                BoardState.chessPieces[iF][jF] = temp;
                Game.toggleUndo(true);
                if (BoardState.chessPieces[iF][jF].isWhite()) {
                    whiteScore++;
                    isWhitesMove = false;
                } else {
                    blackScore++;
                    isWhitesMove = true;
                }
            }
        }
    }

    private static ChessPiece[][] copyChessPieces(ChessPiece[][] input) {
        if (input == null) {
            throw new IllegalArgumentException("Null ChessPieces");
        }

        ChessPiece[][] target = new ChessPiece[input[0].length][input[1].length];
        for (int i = 0; i < input[0].length; i++) {
            for (int j = 0; j < input[1].length; j++) {
                if (input[i][j] == null) {
                    target[i][j] = null;
                } else {
                    target[i][j] = input[i][j];
                }
            }
        }
        return target;
    }
}