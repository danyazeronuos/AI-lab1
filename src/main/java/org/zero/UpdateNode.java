package org.zero;

import java.util.function.BiFunction;
import java.util.function.Function;

public class UpdateNode implements BiFunction<Position, Node, Node> {
    @Override
    public Node apply(Position position, Node node) {
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            newBoard[i] = node.getBoard()[i].clone();
        }
        newBoard[node.getX()][node.getY()] = newBoard[position.x()][position.y()];
        newBoard[position.x()][position.y()] = 0;
        return new Node(newBoard, position.x(), position.y(), node.getSteps()+1);
    }
}
