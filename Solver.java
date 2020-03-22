abstract class Sudoku {
	public int nZones;
	public int lZones;
	public int[][] zones; // eventually make this private
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
		for (int i=0; i<size; i++) {
			content[i] = new Number(pow(scale,2));
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
			System.out.print(z);
			System.out.println("---");
			for (int y=0; y<lZones; y++) {
				int x = zones[z][y];
				System.out.println(x);
				Number num = content[x];
				System.out.println(num);
				if (num.isknown()) {
					for (int y1=0; y1<lZones; y1++) {
						if (y1 != y) {
							int x1 = zones[z][y1];
							Number num1 = content[x1];
							// System.out.println(num.value());
							num1.isnot(num.value());
						}
					}
				}
			}
		}
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
	}
}

class Solver {
	public static void main(String[] args) {
		Sudoku2x2 sd = new Sudoku2x2();
		sd.assume_zones();
		int[] puzzle = {
			0, 1, 0, 0,
			4, 2, 0, 0,
			0, 0, 2, 0,
			0, 3, 0, 0,
		};
		sd.given(puzzle);
		sd.eliminate();
		sd.print();
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