abstract class Sudoku {
	public int[][] zones; // eventually make this private
	public int pow(int num, int exp) {
		int result = 1;
		for (int i=0; i<exp; i++) {
			result *= num;
		}
		return result;
	}
	protected int base_scale(int i, int j, int k, int l) {
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
	protected int base_scale(int j, int k, int l) {
		return base_scale(0,j,k,l);
	}
	protected int base_scale(int k, int l) {
		return base_scale(0,k,l);
	}
	protected int base_scale(int l) {
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
		int nZones = pow(scale,2) * 3;
		int lZones = pow(scale,2);
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
						// System.out.print(i);
						// System.out.print(j);
						// System.out.print(k);
						// System.out.print(l);
						int row = base_scale(i,j);
						int col = base_scale(k,l);
						int val = base_scale(i,k,j,l);
						// padprint(row,4);
						// padprint(col,4);
						// padprint(val,4);
						// padprint(zones[row][col],5);
						// System.out.println();
						// int val = k + l * max;
						zones[row+offset][col] = val;
					}
				}
			}
		}
		System.out.println();
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
		sd.print();
		sd.assume_zones();
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