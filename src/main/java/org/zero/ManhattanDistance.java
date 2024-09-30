package org.zero;

import java.util.function.Function;

public class ManhattanDistance implements Function<Node, Integer> {
    @Override
    public Integer apply(Node node) {
        var distance = 0;
        for (int i = 0; i < node.getBoard().length; i++) {
            for (int j = 0; j < node.getBoard()[0].length; j++) {
                var currentValue = node.getBoard()[i][j];
                if (currentValue != 0) {
                    var targetRow = (currentValue - 1) / node.getBoard().length;
                    var targetCol = (currentValue - 1) % node.getBoard().length;
                    distance += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return distance;
    }
}
