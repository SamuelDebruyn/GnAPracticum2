package gna;

public class Measure {
	
	
	public static void main( String[] args )
	{
		int N = StdIn.readInt();
		int[][] tiles = new int[N][N];
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tiles[i][j] = StdIn.readInt();
		
		Board initial = new Board(tiles);
		long start, end, duration;
		
		start = System.nanoTime();
		Solver solverManhattan = new Solver(initial, false);
		end = System.nanoTime();
		duration = (end - start) / 1000;
		System.out.println("Manhattan:\n\nmoves: " + solverManhattan.moves() + "\nduration: " + duration + "µs\n\n");
		start = System.nanoTime();
		Solver solverHamming = new Solver(initial, true);
		end = System.nanoTime();
		duration = (end - start) / 1000;
		System.out.println("Hamming:\n\nmoves: " + solverHamming.moves() + "\nduration: " + duration + "µs\n\n");
		
		
	}

}
