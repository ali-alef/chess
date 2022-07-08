public class AIPlayer implements Player {

    @Override
    public int[][] nextMove(int[][] chess, boolean white) {
        MiniMax.MiniMaxState miniMaxState = MiniMax.miniMax(chess, white, 4);
        return miniMaxState.getChess();
    }
}
