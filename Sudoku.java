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
	public int size;
	public int scale;
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
	public int given(int[] givens) {
		int changes = 0;
		for (int i=0; i<size; i++) {
			if (givens[i] != 0) {
				changes += content[i].mustbe(givens[i]);
			}
		}
		return changes;
	}
	public int eliminate() {
		int changes = 0;
		for (int z=0; z<nZones; z++) {
			if (debug) {
				System.out.print(zone_names[z]);
				System.out.println("-----------");
			}
			boolean[] needed = new boolean[size];
			for (int i=0; i<size; i++) {
				needed[i] = true;
			}
			for (int y=0; y<lZones; y++) {
				int x = zones[z][y];
				Number num = content[x];
				if (num.isknown()) {
					int val = num.value();
					needed[val-1] = false;
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
			// for (int i=0; i<size; i++) {
			// 	if (needed[i]) {
			// 		int count = 0;
			// 		for (int y=0; y<lZones; y++) {
			// 			int x = zones[z][y];
			// 			Number num = content[x];
			// 			if (num.canbe(i+1)) {
			// 				count++;
			// 			}
			// 		}
			// 		if (count == size-1) {
			// 			for (int y=0; y<lZones; y++) {
			// 				int x = zones[z][y];
			// 				Number num = content[x];
			// 				if (num.canbe(i+1)) {
			// 					num.mustbe(i+1);
			// 					break;
			// 				}
			// 			}
			// 		}
			// 	}
			// }
		}
		return changes;
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
	public void boxprint() {
		String topbar = "┏"; // Construct bars
		String midbar = "┣"; 
		String botbar = "┗"; 
		String minbar = "┠";
		for (int i=0; i<scale; i++) {
			for (int j=0; j < scale; j++) {
				for (int k=0; k < 3; k++) {
					topbar += "━";
					midbar += "━";
					botbar += "━";
					minbar += "─";
				}
				if (j < scale-1) {
					topbar += "┯";
					midbar += "┿";
					botbar += "┷";
					minbar += "┼";
				}
			}
			if (i < scale-1) {
				topbar += "┳";
				midbar += "╋";
				botbar += "┻";
				minbar += "╂";
			} else {
				topbar += "┓";
				midbar += "┫";
				botbar += "┛";
				minbar += "┨";
			}
		}
		System.out.println(topbar); // Print top bar
		System.out.print("┃"); // Print left bar
		for (int i=0; i<size; i++) {
			System.out.print(content[i].toBox()); // Print number
			if (i % pow(scale,3) == pow(scale,3)-1) { //Print mid bar
				System.out.print("┃"); // First right bar
				System.out.println();
				if (i < size - 1) {
					System.out.println(midbar);
					System.out.print("┃"); // First left bar
				} else {
					System.out.println(botbar);
				}
			} else if (i % pow(scale,2) == pow(scale,2)-1) {
				System.out.println("┃"); // Lower right bar
				System.out.println(minbar);
				System.out.print("┃"); // Lower left bar
			} else if (i % scale == scale - 1) {
				System.out.print("┃"); // Middle bars
			} else {
				System.out.print("│"); // print space
			}
		}
	}
}