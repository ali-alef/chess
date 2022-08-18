# Chess

this is a chess game that you can play with AI you or play it with two players that i write with Java.

# classes
  * Main 
    * Variables:
      * Frame: it is JFrame and its the first menu of game
      * color: color of the background
     * Methods:
      * constructor: makes the frame and set it up
      * setScreen: add button, logo, ... to the first page
      * addChessIcon: add logo
      * addSinglePlayBt: add singlePlayer button
      * addTwoPlayBt: add twoPlayer button
      * addSavedGameBt: add save game button
      * startGameSinglePlayer: starts the game for single player
      * startGameTwoPlayer: starts the game for two player
      * startSavedGame: if there is a saved game it will start the save game
   
* Board
  * Variables:
    * blackColor: color of the black squares
    * whiteColor: color of the white squares
    * moveColor: color of the possible squares to move
    * board: 2D array for the board state and the pieces position
    * width: width of the squares
    * height: height of the squares
    * playing: shows the game is for 2 player or one
    * aiPlayer: the AI player
    * whiteTurn: it is whites turn to play or no
    * aiPlaying: is AI playing
    * aiWhite: is AI white
    * possibleMoves: list of the possible squares to move for the chosen piece
    * movingPiece: which piece is selected to move
    * pieces: all the pieces
  * Methods:
    * ‫‪Constructor‬‬ 1: starting the game for two players
    * ‫‪Constructor‬‬ 2: continue the save game
    * ‫‪Constructor‬‬ 3: starting the game for one player
    * aiMove: makes the AI move
    * setSavedBoard: set the pieces possition in saved game and set the board
    * setPiece: give the pieces their first possition and add them to pieces
    * setBoard: creating the start state of the board and creating board variable 
    * drawPiece: drawing pieces in the board and showing them
    * setChanges: it change the pieces possition by the new 2D array of the board
    * save: save the board in the save.txt
    * paint: colors the possibleMoves and pain the board
    * mouseClicked: if the clicked ‫‪square is one of the possible moves it change the piece possition         or by the clicked piece it shows the possible squares to move
    * deadPiece: make the piece dead
* Piece:
  * Variables:
    * width: width of the piece picture
    * height: height of the piece picture
    * isDead: the piece is dead
    * x: possition of the piece in the board
    * y: possition of the piece in the board
    * image: image of the piece
  * Methods:  
    * paint: draw the piece
    * ‫‪possibleMoves‬‬: possible moves according to the board

### Executing program

* you can execute this game simply by running main function in Main.java it will show you the start page

## Authors

ali alef
