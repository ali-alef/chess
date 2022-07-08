package Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class  BishopB extends Piece {

    public BishopB(int x, int y) throws IOException {
        super(x, y);
        image = ImageIO.read(getClass().getResource("icons/b_bishop.png"));
        id = -4;
    }

    public ArrayList<int[]> possibleMoves(int[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();

        int newX = x + 1, newY = y + 1;

        while(newX < 8 && newY < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX++;
            newY++;
        }

        if(newX < 8 && newY < 8 && board[newY][newX] > 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y - 1;


        while(newX > -1 && newY > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX--;
            newY--;
        }

        if(newX > -1 && newY > -1 && board[newY][newX] > 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y - 1;


        while(newX < 8 && newY > -1 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX++;
            newY--;
        }

        if(newX < 8 && newY > -1 && board[newY][newX] > 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y + 1;


        while(newX > -1 && newY < 8 && board[newY][newX] == 0){
            moves.add(new int[]{newX, newY});
            newX--;
            newY++;
        }

        if(newX > -1 && newY < 8 && board[newY][newX] > 0)
            moves.add(new int[]{newX, newY});

        return moves;
    }
}
