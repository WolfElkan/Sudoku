abstract class Sudoku {
	public boolean debug = false;
	public int nZones;
	public int lZones;
	public int[][] zones; // eventually make this private
	public String[] zone_names;
	public String[] cell_names;
	public int pow(int num, int exp) {
		int result = 1;
		for (int i=0; i<exp; i++) {
			result *= num;
		}
		return result;
	}
	protected int b(int i, int j, int k, int l) {
		int total = 0;
		total += i;
		total *= scale;
		total += j;
		total *= scale;
		total += k;
		total *= scale;
		total += l;
		return total;
	}
	protected int b(int j, int k, int l) {
		return b(0,j,k,l);
	}
	protected int b(int k, int l) {
		return b(0,k,l);
	}
	protected int b(int l) {
		return l;
	}
	public void padprint(int num, int pad) {
		String str = Integer.toString(num);
		while (str.length() < pad) {
			str = ' '+str;
		}
		System.out.print(str);
	}
	Number content[];
	int size;
	int scale;
	// Constructor
	public Sudoku(int scale) {
		this.scale = scale;
		this.size = pow(scale,4);
		content = new Number[size];
		int max = pow(scale,2);
		for (int i=0; i<size; i++) {
			content[i] = new Number(max);
			content[i].debug = debug;
		}
	}
	protected void set_zones(int[][] zones) {
		this.zones = zones;
	}
	public void assume_zones() {
		nZones = pow(scale,2) * 3;
		lZones = pow(scale,2);
		int[][] zones = new int[nZones][lZones];
		get_zones(zones);
		set_zones(zones);
	}
	private void get_zones(int[][] zones) {
		int nZonesPerType = pow(scale,2);
		get_row_zones(zones,0);
		get_col_zones(zones,nZonesPerType);
		get_box_zones(zones,nZonesPerType * 2);
	}
	private void get_row_zones(int[][] zones, int offset) {
		int max = pow(scale,2);
		for (int i=0; i<max; i++) {
			for (int j=0; j<max; j++) {
				int v = i * max + j;
				zones[i+offset][j] = v;
			}
		}
	}
	private void get_col_zones(int[][] zones, int offset) {
		int max = pow(scale,2);
		for (int i=0; i<max; i++) {
			for (int j=0; j<max; j++) {
				int v = i + j * max;
				zones[i+offset][j] = v;
			}
		}
	}
	private void get_box_zones(int[][] zones, int offset) {
		int max = pow(scale,2);
		// System.out.println("ijkl row col val  now");
		for (int i=0; i<scale; i++) {
			for (int j=0; j<scale; j++) {
				for (int k=0; k<scale; k++) {
					for (int l=0; l<scale; l++) {
						// int row = b(i,j);
						// int col = b(k,l);
						// int val = b(i,k,j,l);
						// padprint(row,4);
						// padprint(col,4);
						// padprint(val,4);
						// padprint(zones[row][col],5);
						// System.out.println();
						// int val = k + l * max;
						zones[b(i,j)+offset][b(k,l)] = b(i,k,j,l);
					}
				}
			}
		}
		System.out.println();
	}
	public void given(int[] givens) {
		for (int i=0; i<size; i++) {
			if (givens[i] != 0) {
				content[i].mustbe(givens[i]);
			}
		}
	}
	public void eliminate() {
		for (int z=0; z<nZones; z++) {
			if (debug) {
				System.out.print(zone_names[z]);
				System.out.println("-----------");
			}
			for (int y=0; y<lZones; y++) {
				int x = zones[z][y];
				Number num = content[x];
				if (num.isknown()) {
					int val = num.value();
					if (debug) {
						System.out.print(cell_names[x]);
						System.out.println("----");
					}
					for (int y1=0; y1<lZones; y1++) {
						if (y1 != y) {
							int x1 = zones[z][y1];
							Number num1 = content[x1];
							if (debug) {
								System.out.print(cell_names[x1]);
								System.out.print(": ");
							}
							num1.isnot(val);
						}
					}
				}
			}
			// for (int y=0; y<lZones; y++) {
			// 	int x = zones[z][y];
			// 	Number num = content[x];
			// 	// System.out.print("  y: ");
			// 	// System.out.print(y);
			// 	System.out.print("x: ");
			// 	System.out.print(x);
			// 	System.out.print(",  num: ");
			// 	System.out.println(num);
			// }
		}
	}
	public int iterate() {
		/// Record current state (copy contents)
		Number[] current = new Number[size];
		for (int i=0; i<size; i++) {
			current[i] = this.content[i];
		}

		/// Run eliminate
		eliminate();

		/// check state:
			/// if all Numbers are known:
				/// return 2
			/// if state is the same as it was before:
				/// return 0
			/// if contents contains Numbers where all are false:
				/// return -1
			/// else:
				/// return 1

		return 2;
		///  2 = Solved
		///  0 = Stalled
		/// -1 = Error
		///  1 = Progress
	}
	public int solve() {
		int result;
		do {
			result = iterate();
		} while (result == 1);
		if (result == 2 || result == -1) {
			return result;
		}
		/// while result == 0:
			/// save puzzle's current state as variable
			///   (associated with this call of the method)
			/// choose a Number 
			/// choose a value that is still possible for it
			/// assert that it must be that value
			/// result = solve() [we're getting recursive now]
			/// if result == 2:
				/// return result
			/// else if result == -1:
				/// restore puzzle to saved state
				/// assert that originally chosen Number is NOT
				///   the originally chosen value, and repeat.
		return 2;
	}
	public void print() {
		String bar = "+"; // Construct bar
		for (int i=0; i<scale; i++) {
			for (int j=0; j < scale * 2 + 1; j++) {
				bar += "-";
			}
			bar += "+";
		}
		System.out.println(bar); // Print top bar
		System.out.print("| "); // Print left bar
		for (int i=0; i<size; i++) {
			System.out.print(content[i]); // Print number
			System.out.print(' '); // print space
			if (i % pow(scale,3) == pow(scale,3)-1) { //Print mid bar
				System.out.print("| ");
				System.out.println();
				System.out.println(bar);
				if (i < size - 1) {
					System.out.print("| ");
				}
			} else if (i % pow(scale,2) == pow(scale,2)-1) {
				System.out.print("|\n| ");
			} else if (i % scale == scale - 1) {
				System.out.print("| ");
			}
		}
	}
}

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
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
		sd.eliminate();
		sd.print();
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