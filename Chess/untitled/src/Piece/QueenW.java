package Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class QueenW extends Piece{

    public QueenW(int x, int y) throws IOException {
        super(x, y);
        image = ImageIO.read(getClass().getResource("icons/w_queen.png"));
        id = 2;
    }

    public ArrayList<int[]> possibleMoves(int[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();

        int newX = x + 1, newY = y + 1;

        while(newX < 8 && newY < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX++;
            newY++;
        }

        if(newX < 8 && newY < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y - 1;


        while(newX > -1 && newY > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX--;
            newY--;
        }

        if(newX > -1 && newY > -1 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y - 1;


        while(newX < 8 && newY > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX++;
            newY--;
        }

        if(newX < 8 && newY > -1 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y + 1;


        while(newX > -1 && newY < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX--;
            newY++;
        }

        if(newX > -1 && newY < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y;

        while(newX < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX++;
        }

        if(newX < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;

        while(newX > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX--;
        }

        if(newX > -1 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newY = y + 1;
        newX = x;

        while(newY < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newY++;
        }

        if(newY < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newY = y - 1;

        while(newY > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newY--;
        }

        if(newY > -1 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        return moves;
    }
}
