package main;
import java.util.Random;
import java.util.Scanner;

import ai.AI;
import board.Board;
import board.Coordinate;

public class TicTacToe {

	public static final Random rand = new Random();

	public static void main(String[] args) {
		Board board = new Board();

		board.displayBoard();
		System.out.println();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Select who should go first 1(AI) or 2(Human)");
		int selection = input.nextInt();

		if (selection == 1) {
			board.currentPlayer = 1;
			Coordinate point = new Coordinate(rand.nextInt(Board.dim), rand.nextInt(Board.dim));
			board.makeMove(point, Board.AI_PLAYER);
			board.displayBoard();
		}

		do {
			System.out.println("Enter your coordinates (x,y) for example 1 1 (One space One): ");

			int x = input.nextInt();
			int y = input.nextInt();

			Coordinate point = new Coordinate(x, y);
			board = board.makeMove(point, Board.HUMAN_PLAYER);

			if (board.hasPlayerWon(Board.AI_PLAYER)) {
				System.out.println("You Lose!");
				System.exit(0);
			} else if (board.hasPlayerWon(Board.HUMAN_PLAYER)) {
				System.out.println("You win!");
				System.exit(0);
			} else if (board.isADraw()) {
				System.out.println("Its a draw!");
				System.exit(0);
			}

			AI minimaxAI = new AI();
			long startTime = System.nanoTime();
			minimaxAI.miniMaxAlphaBeta(board, 0, Board.AI_PLAYER,Integer.MIN_VALUE, Integer.MAX_VALUE);
			//minimaxAI.miniMax(board, 0, Board.AI_PLAYER);
			System.out.println(minimaxAI.counter);
			long endTime = System.nanoTime();
			System.out.println((endTime - startTime) / 1000000000.0);
			board.makeMove(minimaxAI.AIs_MOVE, Board.AI_PLAYER);
			board.displayBoard();

			if (board.hasPlayerWon(Board.AI_PLAYER)) {
				System.out.println("You Lose!");
				System.exit(0);
			} else if (board.hasPlayerWon(Board.HUMAN_PLAYER)) {
				System.out.println("You win!");
				System.exit(0);
			}

		} while (!board.getPossibleMoves().isEmpty());

		if (board.isADraw()) {
			System.out.println("Its a Draw!");
		}
	}
}
