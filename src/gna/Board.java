package gna;

import java.util.ArrayList;

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
	
	// returns the array with the tiles
	public int[][] getTiles() {
		return tiles;
	}
	
	// returns the height
	public int getHeight() {
		return height;
	}

	// returns the width
	public int getWidth() {
		return width;
	}
	
	// return the tile with the given indexes (possible ArrayIndexOutOfBoundsException)
	public int getTile(int i, int j){
		int[][] tiles = this.getTiles();
		return tiles[i][j];
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
		// we can't use the methods defined in this class if it's not a board
		Board toCompare = (Board) y;
		
		// first check if it has the same dimensions
		if(this.getHeight() != toCompare.getHeight() || this.getWidth() != toCompare.getWidth())
			return false;
		
		// now compare every tile
		for(int i = 0; i < this.getHeight(); i++){
			for(int j = 0; j < this.getWidth(); j++){
				if(this.getTile(i, j) != toCompare.getTile(i, j))
					return false;
			}
		}
		
		return true;
	}
	
	// return an Iterable of all neighboring board positions
	public Iterable<Board> neighbors()
	{
		ArrayList<Board> neighborList = new ArrayList<Board>();
		int[] emptyIndexes = this.getEmpty();
		int[][] modify = this.getTiles();
		
		// keep trying and catching until we tried the 4 sides
		for(int i = 0; i < 4; i++){
			try{
				int firstIndex = 0;
				int secondIndex = 0;
				switch(i){
				case 0:
					// move left to empty
					firstIndex = emptyIndexes[0];
					secondIndex = emptyIndexes[1] - 1;
					break;
				case 1:
					// move up to empty
					firstIndex = emptyIndexes[0] - 1;
					secondIndex = emptyIndexes[1];
					break;
				case 2:
					// move right to empty
					firstIndex = emptyIndexes[0];
					secondIndex = emptyIndexes[1] + 1;
					break;
				case 3:
					// move down to empty
					firstIndex = emptyIndexes[0] + 1;
					secondIndex = emptyIndexes[1];
					break;
				}
				int nextPosition = this.getTile(firstIndex, secondIndex);
				modify[emptyIndexes[0]][emptyIndexes[1]] = nextPosition;
				modify[firstIndex][secondIndex] = 0;
				neighborList.add(new Board(modify));
			}catch(ArrayIndexOutOfBoundsException e){
				// the empty position is in a corner or on one of the 4 sides
			}
		}
		
		return neighborList;
		
	}
	
	// return a string representation of the board
	public String toString()
	{
		return "<empty>";
	}
	
	// return the indexes with the empty position
	public int[] getEmpty(){
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		for(int i=0; i<this.getHeight(); i++){
			for(int j=0; j<this.getWidth(); i++){
				if(this.getTile(i, j) == 0){
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return result;
	}
}