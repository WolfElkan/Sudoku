class Number { // A number that can be in superposition
	private boolean[] possible;
	private int nEliminated = 0;
	private int value = 0;
	private int max;
	private int offset;
	public Number(int max) {
		this.max = max;
		if (max == 36) {
			offset = 0;
		} else {
			offset = 1;
		}
		possible = new boolean[max];
		for (int i=0; i<max; i++) {
			possible[i] = true;
		}
	}
	public void isnot(int num) {
		possible[num-offset] = false;
		nEliminated++;
	}
	// Add must
	public boolean canbe(int num) {
		return possible[num-offset];
	}
	public void mustbe(int num) {
		for (int i=0; i<max; i++) {
			possible[i] = false;
		}
		possible[num-offset] = true;
		nEliminated = max - 1;
		value = num;
	}
	public int value() {
		if (nEliminated == max - 1) {
			return value;
		} else {
			return 0;
		}
	}
	public boolean isknown() {
		return nEliminated + 1 == max;
	}
	public String toString() {
		if (!isknown()) {
			return " ";
		} else if (value < 10 - offset) {
			return Integer.toString(value);
		} else {
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			return alpha.substring(value-10,1);
		}
	}
}

abstract class Sudoku {
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
	public Sudoku(int scale) {
		this.scale = scale;
		this.size = pow(scale,4);
		content = new Number[size];
		for (int i=0; i<size; i++) {
			content[i] = new Number(pow(scale,2));
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
		Sudoku3x3 sd = new Sudoku3x3();
		sd.print();
	}
}