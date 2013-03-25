package gna;

import java.util.Iterator;

public class Board
{
	
	private final int[][] tiles;
	private final int height;
	private final int width;
	
	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}
	
	// return number of blocks out of place
	public int hamming()
	{
		return 0;
	}
	
	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		return 0;
	}
	
	// does this board position equal y
	public boolean equals(Object y)
	{
		return false;
	}
	
	// return an Iterable of all neighboring board positions
	public Iterable<Board> neighbors()
	{
		return new Iterable<Board>(){

			@Override
			public Iterator<Board> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
	
	// return a string representation of the board
	public String toString()
	{
		return "<empty>";
	}
}