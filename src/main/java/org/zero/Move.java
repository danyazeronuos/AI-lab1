package org.zero;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {
    private int matrixSize;
    private static final Position[] DIRECTIONS = {
            new Position(-1, 0),
            new Position(1, 0),
            new Position(0, -1),
            new Position(0, 1)};

    public Move(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public List<Node> move(Node node) {

        var updateNode = new UpdateNode();
        return Arrays.stream(DIRECTIONS)
                .map(element -> new Position(node.getX() + element.x(), node.getY() + element.y()))
                .filter(element -> {
                    return (!Objects.equals(element.x(), node.getX()) || !Objects.equals(element.y(), node.getY()))
                            && element.x() >= 0 && element.y() >= 0
                            && element.x() < matrixSize && element.y() < matrixSize;
                    //x >= 0 && x < 3 && y >= 0 && y < 3;
                }).map(element -> updateNode.apply(element, node)).toList();

    }
}