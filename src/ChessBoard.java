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
    JLabel mesg; // Current message to player

    private JButton[][] chessSquares = new JButton[10][10];

    private static ChessPiece[][] chessPieces = new ChessPiece[8][8];
    private ChessPiece[][] oldPieces;
    int oldX;
    int oldY;

    private final Dimension SIZE = new Dimension(750, 750);
    JPanel frame = new JPanel(new GridBagLayout());

    Color DARK_TAN = new Color(190, 120, 50);
    Color LIGHT_TAN = new Color(247, 206, 132);

    int whiteScore = 0;
    int blackScore = 0;
    boolean isWhitesMove = true;
    boolean isClicked = false;

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
                            repaint();
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

    public void reset() {
        setDefaultBGColors();
        whiteScore = 0;
        blackScore = 0;
        isClicked = false;
        
        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                if (i < 2 || i > 5) {
                    Coords location = new Coords(i, j);
                    boolean isWhite = (i > 5);
                    if (i == 1 || i == 6) {
                        // Pawn
                        chessPieces[i][j] = (new Pawn(location, isWhite));
                    } else if (j == 0 || j == 7) {
                        // Rook
                        chessPieces[i][j] = (new Rook(location, isWhite));
                    } else if (j == 1 || j == 6) {
                        // Knight
                        chessPieces[i][j] = (new Knight(location, isWhite));
                    } else if (j == 2 || j == 5) {
                        // Bishop
                        chessPieces[i][j] = (new Bishop(location, isWhite));
                    } else if (j == 3) {
                        // Queen
                        chessPieces[i][j] = (new Queen(location, isWhite));
                    } else if (j == 4) {
                        // King
                        chessPieces[i][j] = (new King(location, isWhite));
                    }
                } else {
                    // Blank
                    chessPieces[i][j] = null;
                }
            }
        }

        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                int i2 = i + 1;
                int j2 = j + 1;
                if (chessPieces[i][j] == null) {
                    chessSquares[i2][j2].setText("");
                    setLabelFont(chessSquares[i2][j2]);
                } else {
                    chessSquares[i2][j2].setText(chessPieces[i][j].getIcon());
                    setLabelFont(chessSquares[i2][j2]);
                }
            }
        }
        this.mesg.setText("New Game! It's White's move. White: "
                + Integer.toString(whiteScore) + ", Black: "
                + Integer.toString(blackScore));
    }

    public void undo() {
        chessPieces = oldPieces.clone();
        isWhitesMove = !isWhitesMove;
        Game.toggleUndo(false);
        // TODO: Undo score changes
        repaint();
    }

    public void repaint() {
        for (int i = 0; i < chessPieces[0].length; i++) {
            for (int j = 0; j < chessPieces[1].length; j++) {
                int i2 = i + 1;
                int j2 = j + 1;
                if (chessPieces[i][j] == null) {
                    chessSquares[i2][j2].setText("");
                    setLabelFont(chessSquares[i2][j2]);
                } else {
                    chessSquares[i2][j2].setText(chessPieces[i][j].getIcon());
                    setLabelFont(chessSquares[i2][j2]);
                }
            }
        }
        String move = new String((isWhitesMove) ? "White's move"
                : "Black's move");
        this.mesg.setText("It's " + move + " . White: "
                + Integer.toString(whiteScore) + ", Black: "
                + Integer.toString(blackScore));

    }

    public static ChessPiece[][] getBoard() {
        return chessPieces;
    }
    
    private void setDefaultBGColors() {
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

    private void toggleBackground(int i, int j) {
        if (chessSquares[i][j].getBackground() == Color.YELLOW) {
            chessSquares[i][j].setBackground((i % 2 != j % 2) ? DARK_TAN
                    : LIGHT_TAN);
        } else {
            chessSquares[i][j].setBackground(Color.YELLOW);
        }
    }

    private void togglePossibleMoves(int i, int j, Set<Coords> coords) {
        for (Coords c : coords) {
            toggleBackground(c.getfst() + 1, c.getlst() + 1);
        }
    }
    
    private void setLabelFont(JButton button) {
        Font f = button.getFont();
        button.setFont(f.deriveFont(f.getStyle(), 25));
    }

    private void actionLogic(int iF, int jF) {
        if (chessPieces[iF][jF] != null && !isClicked && 
            chessPieces[iF][jF].isWhite() == isWhitesMove) {
            
            Set<Coords> possMoves = 
                    chessPieces[iF][jF].getPossibleMoves();
            togglePossibleMoves(iF, jF, possMoves);
            isClicked = true;
            oldX = iF;
            oldY = jF;
            oldPieces = copyChessPieces(chessPieces);
        } else if (isClicked) {
            Set<Coords> possMoves = 
                 chessPieces[oldX][oldY].getPossibleMoves();
            togglePossibleMoves(oldX, oldY, possMoves);
            isClicked = false;
            if(chessPieces[oldX][oldY].move(new Coords(iF, jF))) {
                ChessPiece temp = chessPieces[oldX][oldY];
                chessPieces[oldX][oldY] = null;
                chessPieces[iF][jF] = temp;
                Game.toggleUndo(true);
                if (chessPieces[iF][jF].isWhite()) {
                    whiteScore++;
                    isWhitesMove = false;
                } else {
                    blackScore++;
                    isWhitesMove = true;
                }
            } 
        }
    }
        
    private ChessPiece[][] copyChessPieces(ChessPiece[][] input) {
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