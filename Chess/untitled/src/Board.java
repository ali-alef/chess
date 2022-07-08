import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import Piece.*;

public class Board extends JFrame implements MouseListener {

    Color blackColor = new Color(100, 5, 27);
    Color whiteColor = new Color(91, 59, 59);
    Color moveColor = new Color(66, 2, 18);
    int[][] board = new int[8][8];
    int width = 800;
    int height = 800;

    int heightCell = 100;
    int widthCell = 100;
    int playing;
    AIPlayer aiPlayer;
    boolean whiteTurn = true;
    boolean aiPlaying = false;
    boolean aiWhite = false;
    ArrayList<int[]> possibleMoves = new ArrayList<>();
    Piece moivngPiece;
    ArrayList<Piece> pieces = new ArrayList<>();
    PawnB[] pawnB = new PawnB[8];
    BishopB[] bishopB = new BishopB[2];
    RookB[] rookB = new RookB[2];
    KnightB[] knightB = new KnightB[2];
    KingB kingB;
    QueenB queenB;
    PawnW[] pawnW = new PawnW[8];
    BishopW[] bishopW = new BishopW[2];
    RookW[] rookW = new RookW[2];
    KnightW[] knightW = new KnightW[2];
    KingW kingW;
    QueenW queenW;

    Board() throws IOException {
        setBoard();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800, 0, width, height + 30);
        this.setVisible(true);
        this.setResizable(false);
        this.addMouseListener(this);
    }

    Board(int[][] board, boolean whiteTurn, int playing) throws IOException {
        setSavedBoard(board);
        this.whiteTurn = whiteTurn;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800, 0, width, height + 30);
        this.setVisible(true);
        this.addMouseListener(this);
        this.board = board;
        if (playing == 0) {
            aiPlaying = true;
            aiWhite = false;
            aiPlayer = new AIPlayer();
        }
        if (playing == 1) {
            aiPlaying = true;
            aiWhite = true;
            aiPlayer = new AIPlayer();
        }

        if (aiPlaying && aiWhite == whiteTurn) {
            aiMove();
        }
    }

    Board(int white) throws IOException {
        setBoard();
        aiPlaying = true;
        aiPlayer = new AIPlayer();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(800, 0, width, height + 30);
        this.setVisible(true);
        this.addMouseListener(this);
        if (white == 0) {
            aiWhite = true;
            aiMove();
            whiteTurn = false;
        } else
            aiWhite = false;
    }

    void aiMove() {
        int[][] newBoard = aiPlayer.nextMove(board, aiWhite);
        setChanges(newBoard);
    }

    private void setSavedBoard(int[][] board) throws IOException {
        setPiece();

        for (Piece piece : pieces) {
            piece.x = -1;
            piece.y = -1;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (Piece piece : pieces) {
                    if (piece.id == board[i][j] && piece.x == -1) {
                        piece.x = j;
                        piece.y = i;
                        break;
                    }
                }
            }
        }

        for (Piece piece : pieces)
            if (piece.x == -1 && piece.y == -1)
                piece.isDead = true;

        paint(this.getGraphics());
    }

    private void setPiece() throws IOException {
        for (int i = 0; i < 8; i++)
            pawnB[i] = new PawnB(1, i);

        for (int i = 0; i < 8; i++)
            pawnW[i] = new PawnW(6, i);

        bishopW[0] = new BishopW(7, 2);
        bishopW[1] = new BishopW(7, 5);

        bishopB[0] = new BishopB(0, 2);
        bishopB[1] = new BishopB(0, 5);

        rookW[0] = new RookW(7, 0);
        rookW[1] = new RookW(7, 7);

        rookB[0] = new RookB(0, 0);
        rookB[1] = new RookB(0, 7);

        knightB[0] = new KnightB(0, 1);
        knightB[1] = new KnightB(0, 6);

        knightW[0] = new KnightW(7, 1);
        knightW[1] = new KnightW(7, 6);

        queenW = new QueenW(7, 3);
        queenB = new QueenB(0, 3);

        kingB = new KingB(0, 4);
        kingW = new KingW(7, 4);

        for (int i = 0; i < 8; i++)
            pieces.add(pawnB[i]);

        for (int i = 0; i < 8; i++)
            pieces.add(pawnW[i]);

        pieces.add(bishopB[0]);
        pieces.add(bishopB[1]);

        pieces.add(bishopW[0]);
        pieces.add(bishopW[1]);

        pieces.add(rookB[0]);
        pieces.add(rookB[1]);

        pieces.add(rookW[0]);
        pieces.add(rookW[1]);

        pieces.add(knightB[0]);
        pieces.add(knightB[1]);

        pieces.add(knightW[0]);
        pieces.add(knightW[1]);

        pieces.add(queenB);
        pieces.add(queenW);

        pieces.add(kingB);
        pieces.add(kingW);
    }

    private void setBoard() throws IOException {
        setPiece();

        for (Piece piece : pieces)
            board[piece.y][piece.x] = piece.id;
    }

    private void drawPiece(Graphics g) {
        for (Piece piece : pieces) {
            if (!piece.isDead) {
                piece.paint(g);
            }
        }
    }

    private void setChanges(int[][] cpBoard) {
        int x = 0, y = 0;
        int newX = 0, newY = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != cpBoard[i][j]) {
                    if (cpBoard[i][j] == 0) {
                        x = j;
                        y = i;
                    } else {
                        newX = j;
                        newY = i;
                    }
                }
            }
        }

        deadPiece(newX, newY);

        for (Piece piece : pieces) {
            if (piece.x == x && piece.y == y) {
                piece.x = newX;
                piece.y = newY;
                break;
            }
        }

        board = cpBoard;
        save();
        paint(this.getGraphics());
    }

    private void save() {
        if (aiPlaying && aiWhite)
            playing = 1;
        else if (aiPlaying && !aiWhite)
            playing = 0;
        else
            playing = 3;

        if(kingW.isDead || kingB.isDead)
            playing = 10;

        try {
            PrintStream print = new PrintStream("save.txt");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++)
                    print.print(board[i][j] + " ");

                print.println();
            }

            print.println(playing);
            print.println(whiteTurn);
            print.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void paint(Graphics g) {
        if (g == null)
            return;

        boolean isBlack = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!isBlack)
                    g.setColor(whiteColor);
                else
                    g.setColor(blackColor);

                g.fillRect(widthCell * j, heightCell * i + 30, widthCell, heightCell);

                isBlack = !isBlack;
            }

            isBlack = !isBlack;
        }

        for (int[] move : possibleMoves) {
            g.setColor(moveColor);
            g.fillRect(widthCell * move[0], heightCell * move[1] + 30, widthCell, heightCell);

            if (move[0] % 2 == move[1] % 2)
                g.setColor(whiteColor);
            else
                g.setColor(blackColor);

            g.fillRect(widthCell * move[0] + 5, heightCell * move[1] + 35, widthCell - 10, heightCell - 10);
        }

        drawPiece(g);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX() / 100;
        int y = (mouseEvent.getY() - 30) / 100;

        boolean moveMade = false;

        for (int[] move : possibleMoves) {
            if (move[0] == x && move[1] == y) {
                deadPiece(x, y);
                board[moivngPiece.y][moivngPiece.x] = 0;
                board[y][x] = moivngPiece.id;
                moivngPiece.x = x;
                moivngPiece.y = y;
                moveMade = true;

                if (aiPlaying)
                    aiMove();
                else
                    whiteTurn = !whiteTurn;

                possibleMoves = new ArrayList<>();
                save();
            }
        }

        if (!moveMade) {
            for (Piece piece : pieces) {
                if (piece.x == x && piece.y == y && ((whiteTurn && piece.id > 0) || (!whiteTurn && piece.id < 0))) {
                    possibleMoves = piece.possibleMoves(board);
                    moivngPiece = piece;
                }
            }
        }

        paint(this.getGraphics());
    }

    private void deadPiece(int x, int y) {
        for (Piece piece : pieces)
            if (piece.x == x && piece.y == y) {
                piece.isDead = true;
                pieces.remove(piece);
                break;
            }

        if (kingB.isDead)
            gameEnd(true);

        if (kingW.isDead)
            gameEnd(false);
    }

    private void gameEnd(boolean whiteWin) {
        if (whiteWin)
            JOptionPane.showMessageDialog(null, "white win the game");
        else
            JOptionPane.showMessageDialog(null, "black win the game");

        this.dispose();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
