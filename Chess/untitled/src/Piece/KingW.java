package Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class KingW extends Piece{

    public KingW(int x, int y) throws IOException {
        super(x, y);
        image = ImageIO.read(getClass().getResource("icons/w_king.png"));
        id = 2;
    }

    public ArrayList<int[]> possibleMoves(int[][] board) {

        ArrayList<int[]> moves = new ArrayList<>();

        int newX = x + 1, newY = y + 1;

        if(newX < 8 && newY < 8 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y - 1;

        if(newX > -1 && newY > -1 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y - 1;

        if(newX < 8 && newY > -1 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y + 1;

        if(newX > -1 && newY < 8 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x;
        newY = y + 1;

        if(newY < 8 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y;

        if(newX < 8 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x - 1;
        newY = y;

        if(newX > -1 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        newX = x;
        newY = y - 1;

        if(newY > -1 && board[newY][newX] <= 0)
            moves.add(new int[]{newX, newY});

        return moves;
    }
}
