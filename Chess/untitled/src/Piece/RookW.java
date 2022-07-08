package Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class RookW extends Piece{

    public RookW(int x, int y) throws IOException {
        super(x, y);
        image = ImageIO.read(getClass().getResource("icons/w_rook.png"));
        id = 3;
    }

    public ArrayList<int[]> possibleMoves(int[][] board){
        ArrayList<int[]> moves = new ArrayList<>();

        int newX = x + 1, newY = y;

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

        if(newY < 8 && board[newY][newX] < 0) {
            moves.add(new int[]{newX, newY});
        }

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
