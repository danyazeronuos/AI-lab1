package org.zero;

import java.util.*;

public class Main {

    private static final int[][] TARGET = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
    };



    public static void main(String[] args) {
        // Стартовая матрица
        int[][] start = {
                {0, 4, 3},
                {5, 2, 8},
                {7, 1, 6}
        };


        Node node = new Node(start, 0, 0, 0, null);
        var resultGreedy = findWithGreedySearch(node);

        resultGreedy.ifPresent(element -> {
            System.out.println(element.getSteps());
            System.out.println(Arrays.deepToString(element.getBoard()));
        });

        printHistory(resultGreedy);
//        var resultDeep = findWithDeepSearch(node);
/*        resultDeep.ifPresent(element -> {
            System.out.println(element.getSteps());
            System.out.println(Arrays.deepToString(element.getBoard()));
        });*/
    }

    private static void printHistory(Optional<Node> resultGreedy) {
        List<Node> history = new ArrayList<>();
        var resultNode = resultGreedy.orElse(null);
        while (resultNode.getParent() != null) {
            history.add(resultNode);
            resultNode = resultNode.getParent();
        }
        history = history.reversed();

        for (Node n : history) {
            System.out.println(n);
        }
    }

    private static Optional<Node> findWithGreedySearch(Node root) {

        var move = new Move(root.getBoard().length);
        var manhattanDistance = new ManhattanDistance();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(manhattanDistance::apply));
        Set<Node> visited = new HashSet<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var current = queue.poll();

            if (current.isGoal(TARGET)) return Optional.of(current);

            visited.add(current);

            for(Node branch : move.move(current)){
                if (!visited.contains(branch)) queue.add(branch);
            }
        }
        return Optional.empty();
    }


    private static  Optional<Node> findWithDeepSearch(Node root) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();
        var move = new Move(root.getBoard().length);
        stack.push(root);

        while(!stack.isEmpty()){
            var current = stack.pop();
            if (current.isGoal(TARGET)) return Optional.of(current);

            if (visited.contains(current)) continue;
            visited.add(current);

            for(Node branch : move.move(current)){
                stack.push(branch);
            }
        }

        return Optional.empty();
    }
}
