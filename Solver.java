class Sudoku3x3 extends Sudoku {
	public Sudoku3x3() {
		super(3);
		String zone_names[] = {
			"Row 1",
			"Row 2",
			"Row 3",
			"Row 4",
			"Row 5",
			"Row 6",
			"Row 7",
			"Row 8",
			"Row 9",
			"Col A",
			"Col B",
			"Col C",
			"Col D",
			"Col E",
			"Col F",
			"Col G",
			"Col H",
			"Col I",
			"Box UL",
			"Box UC",
			"Box UR",
			"Box ML",
			"Box MC",
			"Box MR",
			"Box DL",
			"Box DC",
			"Box DR",
		};
		String cell_names[] = {
			"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1",
			"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2",
			"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "I3",
			"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "I4",
			"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "I5",
			"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", "I6",
			"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7", "I7",
			"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8", "I8",
			"A9", "B9", "C9", "D9", "E9", "F9", "G9", "H9", "I9",
		};
		this.zone_names = zone_names;
		this.cell_names = cell_names;	}
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
		int n = sd.given(puzzle);
		sd.debug = true;
		// sd.content[0].isnot(4);
		sd.boxprint();
		n += sd.eliminate();
		sd.boxprint();
		n += sd.eliminate();
		sd.boxprint();
		n += sd.eliminate();
		sd.boxprint();
		n += sd.eliminate();
		sd.boxprint();
		// n += sd.eliminate();
		// sd.boxprint();
		// n += sd.eliminate();
		// sd.boxprint();
		// n += sd.eliminate();
		// sd.boxprint();
		// n += sd.eliminate();
		// sd.boxprint();
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