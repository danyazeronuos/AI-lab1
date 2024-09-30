package org.zero;

import java.util.Arrays;
import java.util.Objects;

public class Node {
    private final Integer steps;
    private final int[][] board;
    private final Integer x;
    private final Integer y;

    boolean isGoal(int[][] node) {
        return Arrays.deepEquals(this.getBoard(), node);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                builder.append(board[i][j]).append(",");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public Integer getSteps() {
        return steps;
    }

    public Node(int[][] board, Integer x, Integer y, Integer steps) {
        this.board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i].clone();
        }
        this.x = x;
        this.y = y;
        this.steps = steps;
    }

    public Integer getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node node)) return false;
        return Objects.deepEquals(board, node.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(board));
    }

    public int[][] getBoard() {
        return board;
    }

}
