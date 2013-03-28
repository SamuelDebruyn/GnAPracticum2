package gna;

import java.util.ArrayList;

public class Solver
{
	
	private final Board initial;
	
	// find a solution to the initial board
	public Solver( Board initial )
	{
		this.initial = initial;
	}
	
	// get the initial board
	public Board getInitial() {
		return initial;
	}

	// is the initial board solvable?
	public boolean isSolvable()
	{
		// first move the empty tile to its correct position
		int[] emptyCoords = this.getInitial().getEmpty();
		int x = emptyCoords[1];
		int y = emptyCoords[0];
		int size = this.getInitial().getSize();
		int[][] tiles = this.getInitial().getTiles();
		boolean isSolvable = false;
		
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
				if(i != size - 1 && j != size - 1){
					permutation.add(tiles[i][j]);
				}
			}
		}
		
		return isSolvable;
	}
	
	// return min number of moves to solve initial board;
	// -1 if no solution
	public int moves()
	{
		return 0;
	}
	
	// return an Iterable of board positions in solution
	public Iterable<Board> solution()
	{
		return null;
	}
}


