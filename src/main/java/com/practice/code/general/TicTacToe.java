package com.practice.code.general;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TicTacToe {
    int n;
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;


    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    public int move(int row, int col, int player) {
        int add = (player == 1) ? 1 : -1;

        rows[row] += add;
        cols[col] += add;

        if(row == col) {
            diagonal += add;
        }

        if(row + col == n-1) {
            antiDiagonal += add;
        }

        if(Math.abs(rows[row]) == n || Math.abs(cols[col]) == n ||
                Math.abs(antiDiagonal) == n || Math.abs(diagonal) == n ) {
            return player;
        }
        return 0;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        System.out.println(game.move(0, 0, 1)); // Returns 0, no winner
        System.out.println(game.move(0, 2, 2)); // Returns 0, no winner
        System.out.println(game.move(2, 2, 1)); // Returns 0, no winner
        System.out.println(game.move(1, 1, 2)); // Returns 0, no winner
        System.out.println(game.move(2, 0, 1)); // Returns 0, no winner
        System.out.println(game.move(1, 0, 2)); // Returns 0, no winner
        System.out.println(game.move(2, 1, 1)); // Returns 1, player 1 wins
    }
}
