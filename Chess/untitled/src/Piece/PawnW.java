package Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class PawnW extends Piece{

    public PawnW(int x, int y) throws IOException {
        super(x, y);
        image = ImageIO.read(getClass().getResource("icons/w_pawn.png"));
        id = 6;
    }

    public ArrayList<int[]> possibleMoves(int[][] board){
        ArrayList<int[]> moves = new ArrayList<>();

        int newX = x, newY = y - 1;

        if(newY < 8 && board[newY][newX] == 0)
            moves.add(new int[]{newX, newY});

        if(y == 6){
            if(board[y - 1][x] == 0 && board[y - 2][x] == 0)
                moves.add(new int[]{x, y - 2});
        }

        newX = x - 1;
        newY = y - 1;

        if(newX > -1 && newY < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        newX = x + 1;
        newY = y - 1;

        if(newX < 8 && newY < 8 && board[newY][newX] < 0)
            moves.add(new int[]{newX, newY});

        return moves;
    }
}
