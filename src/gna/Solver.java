package gna;

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
		int width = this.getInitial().getSize();
		
		return false;
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


