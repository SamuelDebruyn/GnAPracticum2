package gna;

import java.util.ArrayList;

public class Board implements Cloneable
{

	private final int[][] tiles;
	private final int size;
	private int moves;
	private Board previous;

	// construct a board from an N-by-N array of tiles
	public Board( int[][] tiles )
	{
		this.tiles = tiles;
		this.size = tiles.length;
		this.setMoves(0);
	}

	// copy constructor
	public Board(Board old){
		this.size = old.getSize();
		this.setMoves(old.getMoves());
		int[][] oldTiles = old.getTiles();
		this.tiles = new int[this.getSize()][this.getSize()];
		for(int i = 0; i < old.getSize(); i++){
			for(int j = 0; j < old.getSize(); j++){
				this.tiles[i][j] = oldTiles[i][j];
			}
		}
	}

	// returns the array with the tiles
	public int[][] getTiles() {
		return tiles;
	}

	// returns the size
	public int getSize() {
		return size;
	}

	// get the previous board configuration
	public Board getPrevious() {
		return previous;
	}

	// set the previous board configuration
	public void setPrevious(Board previous) {
		this.previous = previous;
	}

	// returns the number of moves to get to this configuration
	public int getMoves() {
		return moves;
	}

	// sets the number of moves to get to this configuration
	public void setMoves(int moves) {
		this.moves = moves;
	}

	// return the tile with the given indexes (possible ArrayIndexOutOfBoundsException)
	public int getTile(int i, int j){
		int[][] tiles = this.getTiles();
		return tiles[i][j];
	}

	// return the representation of a tile as a string (possible ArrayIndexOutOfBoundsException)
	private String getTileString(int i, int j){
		String result = "";
		int max = this.getSize() * this.getSize() - 1;
		int length = String.valueOf(max).length();
		int tile = this.getTile(i, j);
		if(tile == 0){
			result+= " ";
		}else{
			result+= Integer.toString(tile);
		}
		while(result.length() < length)
			result = " " + result;
		return result;
	}

	// return number of blocks out of place
	public int hamming()
	{
		int outOfPlace = 0;
		int shouldBe = 1;
		for(int i = 0; i < this.getSize(); i++){
			for(int j = 0; j < this.getSize(); j++){
				// last tile should be empty tile
				int tile = this.getTile(i, j);
				if(tile != shouldBe && tile != 0)
					outOfPlace++;
				shouldBe++;
			}
		}
		return outOfPlace;
	}

	// return sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		int manhattanSum = 0;

		for(int i = 0; i < this.getSize(); i++){
			for(int j = 0; j < this.getSize(); j++){
				if(this.getTile(i, j) != 0){
					int[] destination = this.getDestination(this.getTile(i, j));
					int distance = Math.abs(destination[0] - i) + Math.abs(destination[1] - j);
					manhattanSum += distance;
				}
			}
		}

		return manhattanSum;
	}

	// return indexes of the position where this number should be in the board (number should be between 0 and N^2)
	private int[] getDestination(int number){
		
		int[] result = new int[2];
		
		result[0] = number / this.getSize();
		result[1] = number % this.getSize();
		
		if(result[1] == 0){
			result[0]--;
			result[1] = this.getSize() - 1;
		}else{
			result[1]--;
		}

		return result;
	}

	// does this board position equal y
	public boolean equals(Object y)
	{
		if(y == null)
			return false;

		// we can't use the methods defined in this class if it's not a board
		Board toCompare = (Board) y;

		// first check if it has the same dimensions
		if(this.getSize() != toCompare.getSize())
			return false;

		// now compare every tile
		for(int i = 0; i < this.getSize(); i++){
			for(int j = 0; j < this.getSize(); j++){
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
		Board copy;
		int[] emptyIndexes;
		int[][] modify;

		// keep trying and catching until we tried the 4 sides
		for(int i = 0; i < 4; i++){
			try{
				copy = new Board(this);
				emptyIndexes = copy.getEmpty();
				modify = copy.getTiles();
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
				int nextPosition = copy.getTile(firstIndex, secondIndex);
				modify[emptyIndexes[0]][emptyIndexes[1]] = nextPosition;
				modify[firstIndex][secondIndex] = 0;
				// modify is a shallow copy
				neighborList.add(copy);
			}catch(ArrayIndexOutOfBoundsException e){
				// the empty position is in a corner or on one of the 4 sides
			}
		}

		return neighborList;

	}

	// return a string representation of the board
	@Override
	public String toString()
	{
		String result = "";
		for(int i = 0; i < this.getSize() - 1; i++){
			for(int j = 0; j < this.getSize() - 1; j++){
				result += this.getTileString(i, j) + " ";
			}
			result += this.getTileString(i, this.getSize() - 1) + "\n";
		}
		for(int j = 0; j < this.getSize() - 1; j++){
			result += this.getTileString(this.getSize() - 1, j) + " ";
		}
		result += this.getTileString(this.getSize() - 1, this.getSize() - 1);
		return result;
	}

	// return the indexes with the empty position
	public int[] getEmpty(){
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		for(int i = 0; i < this.getSize(); i++){
			for(int j = 0; j < this.getSize(); j++){
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