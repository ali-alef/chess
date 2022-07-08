import java.util.ArrayList;
import java.util.List;

public class MiniMax {

    public static MiniMaxState miniMax(int[][] chess, boolean white, int depth) {
        return maximize(chess, white, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static MiniMaxState maximize(int[][] chess, boolean white, int depth, int alpha, int beta) {

        MiniMaxState miniMaxState = new MiniMaxState();
        miniMaxState.score = getUtility(chess, white);
        miniMaxState.chess = chess;
        List<int[][]> neighbours = getNeighbours(chess, white);

        // Terminal node or max depth reached.
        if (Math.abs(miniMaxState.score) >= 900 || depth == 0 || neighbours.size() == 0) {
            return miniMaxState;
        }

        MiniMaxState highestState = new MiniMaxState();
        highestState.score = Integer.MIN_VALUE;
        for (int[][] neighbour :
                neighbours) {
            MiniMaxState neighbourState = minimize(neighbour, white, depth - 1, alpha, beta);
            if (highestState.score < neighbourState.score) {
                highestState.score = neighbourState.score;
                highestState.chess = neighbour;
            }
            alpha = Math.max(alpha, highestState.score);
            if (alpha >= beta) {
                return highestState;
            }
        }

        return highestState;
    }

    public static MiniMaxState minimize(int[][] chess, boolean white, int depth, int alpha, int beta) {

        MiniMaxState miniMaxState = new MiniMaxState();
        miniMaxState.score = getUtility(chess, white);
        miniMaxState.chess = chess;
        List<int[][]> neighbours = getNeighbours(chess, white);

        // Terminal node or max depth reached.
        if (Math.abs(miniMaxState.score) <= -900 || depth == 0 || neighbours.size() == 0) {
            return miniMaxState;
        }

        MiniMaxState lowestState = new MiniMaxState();
        lowestState.score = Integer.MAX_VALUE;
        for (int[][] neighbour :
                neighbours) {
            MiniMaxState neighbourState = maximize(neighbour, white, depth - 1, alpha, beta);
            if (lowestState.score > neighbourState.score) {
                lowestState.score = neighbourState.score;
                lowestState.chess = neighbour;
            }
            beta = Math.min(beta, lowestState.score);
            if (beta <= alpha) {
                return lowestState;
            }
        }

        return lowestState;
    }

    public static int getUtility(int[][] chess, boolean white) {
        int w;
        if (white) w = 1;
        else w = -1;
        int score = 0;
        for (int[] row : chess) {
            for (int chessman : row) {
                switch (chessman) {
                    case Chessman.KING:
                        score += Chessman.KING_SCORE * w;
                        break;
                    case Chessman.QUEEN:
                        score += Chessman.QUEEN_SCORE * w;
                        break;
                    case Chessman.ROOK:
                        score += Chessman.ROOK_SCORE * w;
                        break;
                    case Chessman.BISHOP:
                        score += Chessman.BISHOP_SCORE * w;
                        break;
                    case Chessman.KNIGHT:
                        score += Chessman.KNIGHT_SCORE * w;
                        break;
                    case Chessman.PAWN:
                        score += Chessman.PAWN_SCORE * w;
                        break;
                    case -1 * Chessman.KING:
                        score -= Chessman.KING_SCORE * w;
                        break;
                    case -1 * Chessman.QUEEN:
                        score -= Chessman.QUEEN_SCORE * w;
                        break;
                    case -1 * Chessman.ROOK:
                        score -= Chessman.ROOK_SCORE * w;
                        break;
                    case -1 * Chessman.BISHOP:
                        score -= Chessman.BISHOP_SCORE * w;
                        break;
                    case -1 * Chessman.KNIGHT:
                        score -= Chessman.KNIGHT_SCORE * w;
                        break;
                    case -1 * Chessman.PAWN:
                        score -= Chessman.PAWN_SCORE * w;
                        break;
                }
            }
        }
        return score;
    }

    public static List<int[][]> getNeighbours(int[][] chess, boolean white) {
        List<int[][]> neighbours = new ArrayList<>();
        if (white) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[i].length; j++) {
                    int chessman = chess[i][j];
                    switch (chessman) {
                        case Chessman.KING: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]{{-1, 0}});
                            positionsList.add(new int[][]{{1, 0}});
                            positionsList.add(new int[][]{{0, 1}});
                            positionsList.add(new int[][]{{0, -1}});
                            positionsList.add(new int[][]{{-1, -1}});
                            positionsList.add(new int[][]{{1, 1}});
                            positionsList.add(new int[][]{{-1, 1}});
                            positionsList.add(new int[][]{{1, -1}});

                            iterativeSearchForNeighbours(chess, Chessman.KING, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case Chessman.QUEEN: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {0, -1},
                                            {0, -2},
                                            {0, -3},
                                            {0, -4},
                                            {0, -5},
                                            {0, -6},
                                            {0, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {0, 1},
                                            {0, 2},
                                            {0, 3},
                                            {0, 4},
                                            {0, 5},
                                            {0, 6},
                                            {0, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 0},
                                            {-2, 0},
                                            {-3, 0},
                                            {-4, 0},
                                            {-5, 0},
                                            {-6, 0},
                                            {-7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 0},
                                            {2, 0},
                                            {3, 0},
                                            {4, 0},
                                            {5, 0},
                                            {6, 0},
                                            {7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, -1},
                                            {-2, -2},
                                            {-3, -3},
                                            {-4, -4},
                                            {-5, -5},
                                            {-6, -6},
                                            {-7, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 1},
                                            {2, 2},
                                            {3, 3},
                                            {4, 4},
                                            {5, 5},
                                            {6, 6},
                                            {7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 1},
                                            {-2, 2},
                                            {-3, 3},
                                            {-4, 4},
                                            {-5, 5},
                                            {-6, 6},
                                            {-7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, -1},
                                            {2, -2},
                                            {3, -3},
                                            {4, -4},
                                            {5, -5},
                                            {6, -6},
                                            {7, -7}
                                    });
                            iterativeSearchForNeighbours(chess, Chessman.QUEEN, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case Chessman.ROOK: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {0, -1},
                                            {0, -2},
                                            {0, -3},
                                            {0, -4},
                                            {0, -5},
                                            {0, -6},
                                            {0, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {0, 1},
                                            {0, 2},
                                            {0, 3},
                                            {0, 4},
                                            {0, 5},
                                            {0, 6},
                                            {0, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 0},
                                            {-2, 0},
                                            {-3, 0},
                                            {-4, 0},
                                            {-5, 0},
                                            {-6, 0},
                                            {-7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 0},
                                            {2, 0},
                                            {3, 0},
                                            {4, 0},
                                            {5, 0},
                                            {6, 0},
                                            {7, 0}
                                    });
                            iterativeSearchForNeighbours(chess, Chessman.ROOK, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case Chessman.BISHOP: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {-1, -1},
                                            {-2, -2},
                                            {-3, -3},
                                            {-4, -4},
                                            {-5, -5},
                                            {-6, -6},
                                            {-7, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 1},
                                            {2, 2},
                                            {3, 3},
                                            {4, 4},
                                            {5, 5},
                                            {6, 6},
                                            {7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 1},
                                            {-2, 2},
                                            {-3, 3},
                                            {-4, 4},
                                            {-5, 5},
                                            {-6, 6},
                                            {-7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, -1},
                                            {2, -2},
                                            {3, -3},
                                            {4, -4},
                                            {5, -5},
                                            {6, -6},
                                            {7, -7}
                                    });
                            iterativeSearchForNeighbours(chess, Chessman.BISHOP, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case Chessman.KNIGHT: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]{{-1, -2}});
                            positionsList.add(new int[][]{{1, -2}});
                            positionsList.add(new int[][]{{-1, 2}});
                            positionsList.add(new int[][]{{1, 2}});
                            positionsList.add(new int[][]{{-2, -1}});
                            positionsList.add(new int[][]{{-2, +1}});
                            positionsList.add(new int[][]{{2, -1}});
                            positionsList.add(new int[][]{{2, +1}});

                            iterativeSearchForNeighbours(chess, Chessman.KNIGHT, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case Chessman.PAWN: {
                            List<int[][]> positionsList = new ArrayList<>();
                            if (i == 6) {
                                if (chess[i - 1][j] == 0 && chess[i - 2][j] == 0)
                                    positionsList.add(new int[][]{{-1, 0}, {-2, 0}});
                                else if (chess[i - 1][j] == 0)
                                    positionsList.add(new int[][]{{-1, 0}});
                            } else if ( i > 0 && chess[i - 1][j] == 0) positionsList.add(new int[][]{{-1, 0}});


                            if (i > 0 && j > 0 && chess[i - 1][j - 1] < 0) positionsList.add(new int[][]{{-1, -1}});
                            if (i > 0 && j < 7 && chess[i - 1][j + 1] < 0) positionsList.add(new int[][]{{-1, 1}});

                            iterativeSearchForNeighbours(chess, Chessman.PAWN, neighbours, i, j, positionsList, white);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[i].length; j++) {
                    int chessman = chess[i][j];
                    switch (chessman) {
                        case -1 * Chessman.KING: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]{{-1, 0}});
                            positionsList.add(new int[][]{{1, 0}});
                            positionsList.add(new int[][]{{0, 1}});
                            positionsList.add(new int[][]{{0, -1}});
                            positionsList.add(new int[][]{{-1, -1}});
                            positionsList.add(new int[][]{{1, 1}});
                            positionsList.add(new int[][]{{-1, 1}});
                            positionsList.add(new int[][]{{1, -1}});

                            iterativeSearchForNeighbours(chess, -1 * Chessman.KING, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case -1 * Chessman.QUEEN: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {0, -1},
                                            {0, -2},
                                            {0, -3},
                                            {0, -4},
                                            {0, -5},
                                            {0, -6},
                                            {0, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {0, 1},
                                            {0, 2},
                                            {0, 3},
                                            {0, 4},
                                            {0, 5},
                                            {0, 6},
                                            {0, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 0},
                                            {-2, 0},
                                            {-3, 0},
                                            {-4, 0},
                                            {-5, 0},
                                            {-6, 0},
                                            {-7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 0},
                                            {2, 0},
                                            {3, 0},
                                            {4, 0},
                                            {5, 0},
                                            {6, 0},
                                            {7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, -1},
                                            {-2, -2},
                                            {-3, -3},
                                            {-4, -4},
                                            {-5, -5},
                                            {-6, -6},
                                            {-7, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 1},
                                            {2, 2},
                                            {3, 3},
                                            {4, 4},
                                            {5, 5},
                                            {6, 6},
                                            {7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 1},
                                            {-2, 2},
                                            {-3, 3},
                                            {-4, 4},
                                            {-5, 5},
                                            {-6, 6},
                                            {-7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, -1},
                                            {2, -2},
                                            {3, -3},
                                            {4, -4},
                                            {5, -5},
                                            {6, -6},
                                            {7, -7}
                                    });
                            iterativeSearchForNeighbours(chess, -1 * Chessman.QUEEN, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case -1 * Chessman.ROOK: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {0, -1},
                                            {0, -2},
                                            {0, -3},
                                            {0, -4},
                                            {0, -5},
                                            {0, -6},
                                            {0, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {0, 1},
                                            {0, 2},
                                            {0, 3},
                                            {0, 4},
                                            {0, 5},
                                            {0, 6},
                                            {0, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 0},
                                            {-2, 0},
                                            {-3, 0},
                                            {-4, 0},
                                            {-5, 0},
                                            {-6, 0},
                                            {-7, 0}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 0},
                                            {2, 0},
                                            {3, 0},
                                            {4, 0},
                                            {5, 0},
                                            {6, 0},
                                            {7, 0}
                                    });
                            iterativeSearchForNeighbours(chess, -1 * Chessman.ROOK, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case -1 * Chessman.BISHOP: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]
                                    {
                                            {-1, -1},
                                            {-2, -2},
                                            {-3, -3},
                                            {-4, -4},
                                            {-5, -5},
                                            {-6, -6},
                                            {-7, -7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, 1},
                                            {2, 2},
                                            {3, 3},
                                            {4, 4},
                                            {5, 5},
                                            {6, 6},
                                            {7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {-1, 1},
                                            {-2, 2},
                                            {-3, 3},
                                            {-4, 4},
                                            {-5, 5},
                                            {-6, 6},
                                            {-7, 7}
                                    });
                            positionsList.add(new int[][]
                                    {
                                            {1, -1},
                                            {2, -2},
                                            {3, -3},
                                            {4, -4},
                                            {5, -5},
                                            {6, -6},
                                            {7, -7}
                                    });
                            iterativeSearchForNeighbours(chess, -1 * Chessman.BISHOP, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case -1 * Chessman.KNIGHT: {
                            List<int[][]> positionsList = new ArrayList<>();
                            positionsList.add(new int[][]{{-1, -2}});
                            positionsList.add(new int[][]{{1, -2}});
                            positionsList.add(new int[][]{{-1, 2}});
                            positionsList.add(new int[][]{{1, 2}});
                            positionsList.add(new int[][]{{-2, -1}});
                            positionsList.add(new int[][]{{-2, +1}});
                            positionsList.add(new int[][]{{2, -1}});
                            positionsList.add(new int[][]{{2, +1}});

                            iterativeSearchForNeighbours(chess, -1 * Chessman.KNIGHT, neighbours, i, j, positionsList, white);
                        }
                        break;
                        case -1 * Chessman.PAWN: {
                            List<int[][]> positionsList = new ArrayList<>();
                            if (i == 1) {
                                if (chess[i + 1][j] == 0 && chess[i + 2][j] == 0)
                                    positionsList.add(new int[][]{{1, 0}, {2, 0}});
                                else if (chess[i + 1][j] == 0)
                                    positionsList.add(new int[][]{{1, 0}});
                            } else if (i < 7 && chess[i + 1][j] == 0) positionsList.add(new int[][]{{1, 0}});


                            if (i < 7 && j > 0 && chess[i + 1][j - 1] > 0) positionsList.add(new int[][]{{1, -1}});
                            if (i < 7 && j < 7 && chess[i + 1][j + 1] > 0) positionsList.add(new int[][]{{1, 1}});

                            iterativeSearchForNeighbours(chess, -1 * Chessman.PAWN, neighbours, i, j, positionsList, white);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    private static void iterativeSearchForNeighbours(int[][] chess, int chessman, List<int[][]> neighbours, int i, int j, List<int[][]> positionsList, boolean white) {
        for (int[][] positions :
                positionsList) {
            for (int[] position : positions) {
                int[][] neighbour = copyChess(chess);

                if (i + position[0] >= 0 &&
                        i + position[0] < 8 &&
                        j + position[1] >= 0 &&
                        j + position[1] < 8) {

                    if ((white && neighbour[i + position[0]][j + position[1]] > 0) ||
                            (!white && neighbour[i + position[0]][j + position[1]] < 0))
                        break;

                    if ((white && neighbour[i + position[0]][j + position[1]] < 0) ||
                            (!white && neighbour[i + position[0]][j + position[1]] > 0)) {
                        neighbour[i + position[0]][j + position[1]] = chessman;
                        neighbour[i][j] = 0;
                        neighbours.add(neighbour);
                        break;
                    }

                    neighbour[i + position[0]][j + position[1]] = chessman;
                    neighbour[i][j] = 0;
                    neighbours.add(neighbour);
                }
            }
        }
    }

    public static int[][] copyChess(int[][] chess) {
        int[][] newChess = new int[chess.length][chess.length];
        for (int i = 0; i < chess.length; i++) {
            System.arraycopy(chess[i], 0, newChess[i], 0, chess[i].length);
        }
        return newChess;
    }

    public static class MiniMaxState {
        int[][] chess;
        int score;

        public int getScore() {
            return score;
        }

        public int[][] getChess() {
            return chess;
        }
    }
}
