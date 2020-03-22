abstract class Sudoku {
	private int[][] zones;
	public int pow(int num, int exp) {
		int result = 1;
		for (int i=0; i<exp; i++) {
			result *= num;
		}
		return result;
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
	protected void setzones(int[][] zones) {
		this.zones = zones;
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
		int[][] zones = {

			{ 0, 1, 2, 3}, // Rows
			{ 4, 5, 6, 7},
			{ 8, 9,10,11},
			{12,13,14,15},

			{ 0, 4, 8,12}, // Columns
			{ 1, 5, 9,13},
			{ 2, 6,10,14},
			{ 3, 7,11,15},

			{ 0, 1, 4, 5}, // Boxes
			{ 2, 3, 6, 7},
			{ 8, 9,12,13},
			{10,11,14,15},

		};
		this.setzones(zones);
	}
}

class Solver {
	public static void main(String[] args) {
		Sudoku2x2 sd = new Sudoku2x2();
		sd.print();
	}
}