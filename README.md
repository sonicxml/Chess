Chess
=====
(C) Trevin Gandhi 2014
=====

Chess game for UPenn's CIS 120 final project. This was created in the fall semester of my freshman year. 

Classes:
 - Game.java: This class includes the basic GUI elements, such as the JFrame. It calls ChessBoard.java to make the chess board.
 - ChessBoard.java: Makes the chessboard layout, which is an array of JButtons. Keeps track of coloring them properly and has MouseListeners and ActionListeners that highlight buttons when the mouse hovers over them and displays possible moves when buttons are clicked.
 - BoardState.java: Contains an array of the ChessPieces. ARray elements are null if there is no ChessPiece there. Also, keeps track of things like score, check, and checkmate.
 - ChessPiece.java: Abstract Class for chess pieces. All chess pieces inherit from it. Uses Sets to store possible moves.