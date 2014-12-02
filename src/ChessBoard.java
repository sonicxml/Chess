import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ChessBoard { // extends JPanel {
    JLabel mesg; // Current message to player
    private JButton[][] chessSquares = new JButton[10][10];
    private ChessPiece[][] chessPieces = new ChessPiece[8][8];
    private final Dimension SIZE = new Dimension(750, 750);
    JPanel frame = new JPanel(new GridBagLayout());
    Color DARK_TAN = new Color(190, 120, 50);
    Color LIGHT_TAN = new Color(247, 206, 132);

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
        
        for (int i = 1; i < chessSquares[0].length - 1; i++) {
            for (int j = 1; j < chessSquares[1].length - 1; j++) {
                if (i < 3 || i > 6) {
                    if (i == 2 || i == 7) {
                        // Pawn
                        String pawn = new String((i > 6) ? "♙" : "♟");
                        chessSquares[i][j].setText(pawn);
                        setLabelFont(chessSquares[i][j]);
                    } else if (j == 1 || j == 8) {
                        // Rook
                        String rook = new String((i > 6) ? "♖" : "♜");
                        chessSquares[i][j].setText(rook);
                        setLabelFont(chessSquares[i][j]);
                    } else if (j == 2 || j == 7) {
                        // Knight
                        String knight = new String((i > 6) ? "♘" : "♞");
                        chessSquares[i][j].setText(knight);
                        setLabelFont(chessSquares[i][j]);
                    } else if (j == 3 || j == 6) {
                        // Bishop
                        String bishop = new String((i > 6) ? "♗" : "♝");
                        chessSquares[i][j].setText(bishop);
                        setLabelFont(chessSquares[i][j]);
                    } else if (j == 4) {
                        // Queen
                        String queen = new String((i > 6) ? "♕" : "♛");
                        chessSquares[i][j].setText(queen);
                        setLabelFont(chessSquares[i][j]);
                    } else if (j == 5) {
                        // King
                        String king = new String((i > 6) ? "♔" : "♚");
                        chessSquares[i][j].setText(king);
                        setLabelFont(chessSquares[i][j]);
                    }

                    final int iFinal = i;
                    final int jFinal = j;

                    chessSquares[i][j].addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            toggleBackground(iFinal, jFinal);
                        }

                        public void mouseExited(MouseEvent e) {
                            toggleBackground(iFinal, jFinal);
                        }
                    });

                    chessSquares[i][j].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            toggleBackground(5, 5);
                        }
                    });

                } else {
                    // Blank
                    String blank = new String(" ");
                    chessSquares[i][j].setText(blank);
                    setLabelFont(chessSquares[i][j]);
                }
            }
        }
        this.mesg.setText("Game reset. White's move");
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

    private void setLabelFont(JButton button) {
        Font f = button.getFont();
        button.setFont(f.deriveFont(f.getStyle(), 25));
    }
}