package ai;

import java.util.List;

import board.Board;
import board.Coordinate;

public class AI {
	
	public Coordinate AIs_MOVE;
	public int counter = 0;
	
	public AI() {
		super();
	}

	public int miniMaxAlphaBeta(Board board, int depth, int player, int alpha, int beta) {
		
		//System.out.println(depth);
		List<Coordinate> possibleMoves = board.getPossibleMoves();
		if (board.hasPlayerWon(Board.AI_PLAYER)) {
			return 1;
		} else if (board.hasPlayerWon(Board.HUMAN_PLAYER)) {
			return -1;
		} else if (board.isADraw()) {
			return 0;
		}

		
		//System.out.println("Blah");
		for (int i = 0; i < possibleMoves.size(); i++) {

			Coordinate point = possibleMoves.get(i);

			if (player == Board.AI_PLAYER) {
				board = board.makeMove(point, player);
				//System.out.println("Blah");
				int score = miniMaxAlphaBeta(board, depth + 1, Board.HUMAN_PLAYER, alpha, beta);
				//counter+=1;
				board = board.unmakeMove(point);

				if (score > alpha) {
					System.out.println(score);
					alpha = score;
					AIs_MOVE = point;
				}

				/*if (alpha >= 0) {
					if (depth == 0) {
						AIs_MOVE = point;
					}
				}*/
				
				if(alpha >= beta)
				{
					//AIs_MOVE = point;
					break;
				}
			} else if (player == Board.HUMAN_PLAYER) {
				board = board.makeMove(point, player);
				int score = miniMaxAlphaBeta(board, depth + 1, Board.AI_PLAYER, alpha, beta);
				//counter+=1;
				if (score < beta) {
					beta = score;
				}
				
				if(alpha >= beta)
				{
					board.unmakeMove(point);
					break;
				}
			}
			board.unmakeMove(point);
		}

		return player == Board.AI_PLAYER ? alpha : beta;
	}
	
	public int miniMax(Board board, int depth, int player) {
		System.out.println(depth);
		List<Coordinate> possibleMoves = board.getPossibleMoves();

		if (board.hasPlayerWon(Board.AI_PLAYER)) {
			return 1;
		} else if (board.hasPlayerWon(Board.HUMAN_PLAYER)) {
			return -1;
		} else if (board.isADraw()) {
			return 0;
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		//System.out.println("Blah");
		for (int i = 0; i < possibleMoves.size(); i++) {

			Coordinate point = possibleMoves.get(i);

			if (player == Board.AI_PLAYER) {
				board = board.makeMove(point, player);
				//System.out.println("Blah");
				int score = miniMax(board, depth + 1, Board.HUMAN_PLAYER);

				//counter+=1;
				board = board.unmakeMove(point);

				if (score > max) {
					max = score;
				}

				if (score >= 0) {
					if (depth == 0) {
						
						AIs_MOVE = point;
					}
				}
			} else if (player == Board.HUMAN_PLAYER) {
				board = board.makeMove(point, player);
				int score = miniMax(board, depth + 1, Board.AI_PLAYER);
				//counter+=1;
				if (score < min) {
					min = score;
				}

				if (min == -1) {
					board = board.unmakeMove(point);
					break;
				}
			}
			board.unmakeMove(point);
		}

		return player == Board.AI_PLAYER ? max : min;
	}

}
