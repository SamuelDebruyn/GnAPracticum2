package gna;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solver
{

	private final Board initial;

	// find a solution to the initial board
	public Solver( Board initial )
	{
		this.initial = initial;
		
		// create a priority queue with the default capacity
		PriorityQueue<Board> queue = new PriorityQueue<Board>(11, new BoardComparator());
	}

	// get the initial board
	public Board getInitial() {
		return initial;
	}

	// is the initial board solvable?
	public boolean isSolvable()
	{
		
		int[] emptyCoords = this.getInitial().getEmpty();
		int x = emptyCoords[1];
		int y = emptyCoords[0];
		int size = this.getInitial().getSize();
		int[][] tiles = this.getInitial().getTiles();

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
		if(!this.isSolvable())
			return -1;
		// TODO
		return -1;
	}

	// return an Iterable of board positions in solution
	public Iterable<Board> solution()
	{
		// TODO
		return null;
	}
	
	class BoardComparator implements Comparator<Board>{

		@Override
		public int compare(Board first, Board second) {
			
			int firstManhattan = first.manhattan();
			int secondManhattan = second.manhattan();
			
			return Integer.compare(firstManhattan, secondManhattan);
			
		}
		
	}
}


