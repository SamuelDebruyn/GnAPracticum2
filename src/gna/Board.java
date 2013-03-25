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
	
	// return the tile with the given indexes
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
		return false;
	}
	
	// return an Iterable of all neighboring board positions
	public Iterable<Board> neighbors()
	{
		ArrayList<Board> neighborList = new ArrayList<Board>();
		int[] emptyIndexes = this.getEmpty();
		int[][] current = this.getTiles();
		
		try{
			
			
			
		}
		
		/*//left upper corner
		if(emptyIndexes[0] == 0 && emptyIndexes[1] == 0){
			
			
		//right upper corner
		}else if(emptyIndexes[0] == 0 && emptyIndexes[1] == this.getWidth() - 1){
			
			
		//left bottom corner
		}else if(emptyIndexes[1] == 0 && emptyIndexes[0] == this.getHeight() - 1){
			
			
		//right bottom corner
		}else if(emptyIndexes[1] == this.getWidth() - 1 && emptyIndexes[0] == this.getHeight() - 1){
			
			
		//not a corner
		}else{
			
		}*/
		
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