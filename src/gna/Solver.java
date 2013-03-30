package gna;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solver
{

	private final Board initial;
	private final ArrayList<Board> solution = new ArrayList<Board>();

	// find a solution to the initial board
	public Solver( Board initial )
	{
		this.initial = initial;
		// use manhattan by default
		this.solve(new BoardManhattanComparator());
	}

	// define if you want to use manhattan or hamming
	public Solver(Board initial, boolean useHammingInstead){
		this.initial = initial;
		if(useHammingInstead)
			this.solve(new BoardHammingComparator());
		else
			this.solve(new BoardManhattanComparator());
	}

	// solve the board with the given comparator
	private void solve(Comparator<Board> comp){

		if(this.isSolvable()){

			// create a priority queue with the default capacity
			PriorityQueue<Board> aStarQueue = new PriorityQueue<Board>(11, comp);

			// add the initial board
			aStarQueue.add(initial);

			// A*
			while(aStarQueue.peek().hamming() != 0){

				Board minimum = aStarQueue.poll();

				// TODO: debug
				int hamming = minimum.hamming();
				int manhattan = minimum.manhattan();
				int moves = minimum.getMoves();

				for(Board neighbor : minimum.neighbors()){
					boolean isDuplicate = false;
					for(Board inQueue: aStarQueue){
						if(inQueue.equals(neighbor))
							isDuplicate = true;
					}
					
					if(!isDuplicate){
						neighbor.setMoves(minimum.getMoves() + 1);
						neighbor.setPrevious(minimum);
						aStarQueue.add(neighbor);
					}
				}

			}

			// now the first element in the queue is the solution
			Board solved = aStarQueue.poll();
			solution.add(solved);

			Board previous = solved.getPrevious();

			while(previous != null){
				solution.add(previous);
				previous = previous.getPrevious();
			}

			Collections.reverse(solution);

		}
	}

	// get the initial board
	public Board getInitial() {
		return initial;
	}

	// get the solution as an ArrayList
	public ArrayList<Board> getSolution() {
		return solution;
	}

	// is the initial board solvable?
	public boolean isSolvable()
	{

		// we don't want to modify the initial board, using a copy instead
		Board copy = new Board(this.getInitial());
		int[] emptyCoords = copy.getEmpty();
		int x = emptyCoords[1];
		int y = emptyCoords[0];
		int size = copy.getSize();
		// shallow copy
		int[][] tiles = copy.getTiles();

		// first move the empty tile to its correct position
		while(x != size -1){
			int old = tiles[y][x + 1];
			tiles[y][x + 1] = 0;
			tiles[y][x] = old;
			x++;
		}
		while(y != size -1){
			int old = tiles[y + 1][x];
			tiles[y + 1][x] = 0;
			tiles[y][x] = old;
			y++;
		}

		ArrayList<Integer> permutation = new ArrayList<Integer>();

		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(i != size - 1 || j != size - 1){
					permutation.add(tiles[i][j]);
				}
			}
		}

		// neutral element
		BigInteger product1 = BigInteger.valueOf(1);
		BigInteger product2 = BigInteger.valueOf(1);

		for(int i = 0; i < permutation.size() - 1; i++){
			for(int j = permutation.size() - 1; j > i; j--){
				product1 = product1.multiply(BigInteger.valueOf(permutation.get(i) - permutation.get(j)));
				product2 = product2.multiply(BigInteger.valueOf(j - i));
			}
		}

		BigInteger result = product1.divide(product2);
		int sign = result.signum();

		return sign > 0 ? true : false;
	}

	// return min number of moves to solve initial board;
	// -1 if no solution
	public int moves()
	{
		return this.getSolution().size() - 1;
	}

	// return an Iterable of board positions in solution
	public Iterable<Board> solution()
	{
		return this.getSolution();
	}

	private class BoardManhattanComparator implements Comparator<Board>{

		@Override
		public int compare(Board first, Board second) {
			return Integer.compare(first.manhattan() + first.getMoves(), second.manhattan() + second.getMoves());
		}

	}

	private class BoardHammingComparator implements Comparator<Board>{

		@Override
		public int compare(Board first, Board second) {
			return Integer.compare(first.hamming() + first.getMoves(), second.hamming() + second.getMoves());
		}

	}
}


