class Sudoku3x3 extends Sudoku {
	public Sudoku3x3() {
		super(3);
	}
}

class Sudoku2x2 extends Sudoku {
	public Sudoku2x2() {
		super(2);
		String zone_names[] = {
			"Row 1",
			"Row 2",
			"Row 3",
			"Row 4",
			"Col A",
			"Col B",
			"Col C",
			"Col D",
			"Box UL",
			"Box UR",
			"Box DL",
			"Box DR",
		};
		String cell_names[] = {
			"A1", "B1", "C1", "D1",
			"A2", "B2", "C2", "D2",
			"A3", "B3", "C3", "D3",
			"A4", "B4", "C4", "D4",
		};
		this.zone_names = zone_names;
		this.cell_names = cell_names;
	}
}

class Solver {
	public static void main(String[] args) {
		Sudoku3x3 sd = new Sudoku3x3();
		sd.assume_zones();
		int[] puzzle = {
			 0, 0, 0, 0, 5, 0, 0, 0, 8,
			 0, 0, 0, 0, 0, 0, 6, 0, 0,
			 0, 0, 7, 3, 0, 6, 0, 0, 0,
			 0, 0, 1, 8, 0, 0, 0, 7, 0,
			 0, 2, 0, 0, 3, 0, 0, 0, 1,
			 0, 0, 3, 0, 2, 0, 0, 6, 0,
			 0, 7, 0, 0, 8, 5, 0, 0, 0,
			 6, 3, 4, 0, 0, 7, 0, 0, 0,
			 5, 0, 0, 0, 1, 0, 0, 0, 0,
		};
		sd.given(puzzle);
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		sd.eliminate();
		sd.boxprint();
		// sd.content[3].verbose();
		// System.out.println(sd.content[3].nEliminated);
		// System.out.println(sd.content[3].known);
		// print(sd.zones, 12, 4);
	}
	public static void print(int[][] arr, int len, int wid) {
		Sudoku2x2 sd = new Sudoku2x2();
		for (int i=0; i<len; i++) {
			for (int j=0; j<wid; j++) {
				sd.padprint(arr[i][j],2);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}