package com.practice.code.general;

import java.util.*;

public class SnakeGame {

    int width, height;
    int foodIndex;
    int[][] food;
    int score;
    Deque<int[]> snake;
    Set<String> snakeBody;
    Map<String, int[]> directions;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.score = 0;
        this.foodIndex = 0;

        this.snake = new LinkedList<>();
        this.snake.add(new int[]{0, 0});

        this.snakeBody = new HashSet<>();
        this.snakeBody.add("0,0");

        this.directions = new HashMap<>();
        directions.put("U", new int[]{-1, 0});
        directions.put("D", new int[]{1, 0});
        directions.put("L", new int[]{0, -1});
        directions.put("R", new int[]{0, 1});
    }

    public int move(String direction) {
        // Get the current head position of the snake
        int[] curr = snake.peekFirst();
        int newRow = curr[0] + directions.get(direction)[0];
        int newCol = curr[1] + directions.get(direction)[1];

        // Check for out-of-bounds
        if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
            return -1; // Game over due to out-of-bounds
        }

        // Prepare the new position as a string and remove tail temporarily
        String newPosition = newRow + "," + newCol;
        int[] tail = snake.peekLast();
        snakeBody.remove(tail[0] + "," + tail[1]);

        // Check for collision with itself (excluding the current tail)
        if (snakeBody.contains(newPosition)) {
            return -1; // Game over due to self-collision
        }

        // Add the new head to the snake
        int[] newHead = new int[]{newRow, newCol};
        snake.addFirst(newHead);
        snakeBody.add(newPosition);

        // Check if the snake has found food
        if (foodIndex < food.length && newRow == food[foodIndex][0] && newCol == food[foodIndex][1]) {
            // Eat the food, increase score, and move to next food
            foodIndex++;
            score++;
            // Do not remove the tail since the snake grows
        } else {
            // Move the snake by removing the tail
            snake.pollLast();
            //snakeBody.add(tail.getKey() + "," + tail.getValue());
        }

        return score;
    }

    public static void main(String[] args) {
        int[][] food = {{1, 2}, {0, 1}};
        SnakeGame game = new SnakeGame(3, 2, food);

        System.out.println(game.move("R")); // Expected Output: 0
        System.out.println(game.move("D")); // Expected Output: 0
        System.out.println(game.move("R")); // Expected Output: 1 (eats food)
        System.out.println(game.move("U")); // Expected Output: 1
        System.out.println(game.move("L")); // Expected Output: 2 (eats food)
        System.out.println(game.move("U")); // Expected Output: -1 (hits the wall)
    }
}
