package board;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int NULL_PLAYER = 0;
	public static final int AI_PLAYER = 1;
	public static final int HUMAN_PLAYER = 2;
	public static final int dim = 3;
	public int currentPlayer;
	public int[][] boardSpace = new int[3][3];

	public Board() {
		super();
		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				boardSpace[x][y] = NULL_PLAYER;
			}
		}
	}

	public Board(int[][] boardSpace) {
		this.boardSpace = boardSpace;
	}

	public Board(int[][] boardSpace, int currentPlayer) {
		this.boardSpace = boardSpace;
		this.currentPlayer = currentPlayer;
	}

	public void displayBoard() {
		System.out.println();

		for (int x = 0; x < dim; x++) {
			System.out.print("|");
			for (int y = 0; y < dim; y++) {
				if (boardSpace[x][y] == AI_PLAYER) {
					System.out.print("X");
				} else if (boardSpace[x][y] == HUMAN_PLAYER) {
					System.out.print("O");
				} else {
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.println();
		}
	}

	public List<Coordinate> getPossibleMoves() {
		List<Coordinate> possibleMoves = new ArrayList<>();

		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {
				if (boardSpace[x][y] == NULL_PLAYER) {
					Coordinate emptyPoint = new Coordinate(x, y);
					possibleMoves.add(emptyPoint);
				}
			}
		}
		return possibleMoves;
	}

	public Board makeMove(Coordinate point, int player) {
		int[][] newBoardSpace = boardSpace.clone();
		newBoardSpace[point.x][point.y] = player;
		return new Board(newBoardSpace, player == 1 ? 2 : 1);
	}

	public Board unmakeMove(Coordinate point) {
		int[][] newBoardSpace = boardSpace.clone();
		newBoardSpace[point.x][point.y] = NULL_PLAYER;
		return new Board(newBoardSpace);
	}

	public boolean hasPlayerWon(int player) {
		if (boardSpace[0][0] == player && boardSpace[1][1] == player && boardSpace[2][2] == player
				|| boardSpace[0][2] == player && boardSpace[1][1] == player && boardSpace[2][0] == player) {
			return true;
		}

		for (int i = 0; i < dim; i++) {
			if (boardSpace[i][0] == player && boardSpace[i][1] == player && boardSpace[i][2] == player
					|| boardSpace[0][i] == player && boardSpace[1][i] == player && boardSpace[2][i] == player) {
				return true;
			}
		}
		return false;
	}

	public boolean isADraw() {
		List<Coordinate> possibleMoves = getPossibleMoves();
		return possibleMoves.isEmpty();
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}
}
