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
		
		start = System.currentTimeMillis();
		Solver solverManhattan = new Solver(initial, false);
		end = System.currentTimeMillis();
		duration = (end - start) / 1000;
		System.out.println("Manhattan:\n\nmoves: " + solverManhattan.moves() + "\nduration: " + duration + "s\n\n");
		start = System.currentTimeMillis();
		Solver solverHamming = new Solver(initial, true);
		end = System.currentTimeMillis();
		duration = (end - start) / 1000;
		System.out.println("Hamming:\n\nmoves: " + solverHamming.moves() + "\nduration: " + duration + "s");
		
		
	}

}
