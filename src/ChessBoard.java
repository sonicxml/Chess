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
//                    JLabel jlabel = new JLabel(label);
//                    jlabel.setHorizontalAlignment(JLabel.CENTER);
//                    jlabel.setVerticalAlignment(JLabel.CENTER);
//                    square.add(jlabel);
                } else if (j == 0 || j == 9) {
                    // Numerical label
                    String label = (i > 0 && i < 9) ? String.valueOf(i) : "";
                    square.setText(label);
//                    JLabel jlabel = new JLabel(label);
//                    jlabel.setHorizontalAlignment(JLabel.CENTER);
//                    jlabel.setVerticalAlignment(JLabel.CENTER);
//                    square.add(jlabel);
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
        for (int i = 1; i < chessSquares[0].length - 1; i++) {
            for (int j = 1; j < chessSquares[1].length - 1; j++) {
                if (i < 3 || i > 6) {
                    if (i == 2 || i == 7) {
                        // Pawn
//                        JLabel pawn = new JLabel((i > 6) ? "♙" : "♟");
//                        setLabelFont(pawn);
                       
                        String pawn = new String((i > 6) ? "♙" : "♟");
                        chessSquares[i][j].setText(pawn);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(pawn);
                    } else if (j == 1 || j == 8) {
                        // Rook
//                        JLabel rook = new JLabel((i > 6) ? "♖" : "♜");
//                        setLabelFont(rook);
                        String rook = new String((i > 6) ? "♖" : "♜");
                        chessSquares[i][j].setText(rook);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(rook);
                    } else if (j == 2 || j == 7) {
                        // Knight
//                        JLabel knight = new JLabel((i > 6) ? "♘" : "♞");
//                        setLabelFont(knight);
                        String knight = new String((i > 6) ? "♘" : "♞");
                        chessSquares[i][j].setText(knight);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(knight);
                    } else if (j == 3 || j == 6) {
                        // Bishop
//                        JLabel bishop = new JLabel((i > 6) ? "♗" : "♝");
//                        setLabelFont(bishop);
                        String bishop = new String((i > 6) ? "♗" : "♝");
                        chessSquares[i][j].setText(bishop);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(bishop);
                    } else if (j == 4) {
                        // Queen
//                        JLabel queen = new JLabel((i > 6) ? "♕" : "♛");
//                        setLabelFont(queen);
                        String queen = new String((i > 6) ? "♕" : "♛");
                        chessSquares[i][j].setText(queen);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(queen);
                    } else if (j == 5) {
                        // King
//                        JLabel king = new JLabel((i > 6) ? "♔" : "♚");
//                        setLabelFont(king);
                        String king = new String((i > 6) ? "♔" : "♚");
                        chessSquares[i][j].setText(king);
                        setLabelFont(chessSquares[i][j]);
//                        chessSquares[i][j].add(king);
                    }
                    
                    final int iFinal = i;
                    final int jFinal = j;

                    chessSquares[i][j].addMouseListener(new MouseAdapter() {
                        final Color initial = chessSquares[iFinal][jFinal].getBackground();
                        public void mouseEntered(MouseEvent e) {
                            Border thickBorder = new LineBorder(Color.YELLOW, 12);
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
//                    JLabel blank = new JLabel(" ");
//                    setLabelFont(blank);
                    String blank = new String(" ");
                    chessSquares[i][j].setText(blank);
                    setLabelFont(chessSquares[i][j]);
//                    chessSquares[i][j].add(blank);
                }
            }
        }
        this.mesg.setText("Game reset. White's move");
    }
    
    
    public void toggleBackground(int i, int j) {
        // Border thickBorder = new LineBorder(Color.YELLOW, 12);
        // chessSquares[i][j].setBorder(thickBorder);
        if (chessSquares[i][j].getBackground() == Color.YELLOW) {
            chessSquares[i][j].setBackground((i % 2 != j % 2) ? DARK_TAN
                                                              : LIGHT_TAN);
        } else {
            chessSquares[i][j].setBackground(Color.YELLOW);
        }
    }
    
    private class mouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e, int i, int j) {
            // Border thickBorder = new LineBorder(Color.YELLOW, 12);
            chessSquares[i][j].setBackground(Color.YELLOW);
        }
    }
    private void setLabelFont(JButton button) {
        Font f = button.getFont();
        button.setFont(f.deriveFont(f.getStyle(), 25));

    }

}