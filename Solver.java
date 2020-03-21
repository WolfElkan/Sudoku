abstract class Sudoku {
	int content[];
	int size;
	public Sudoku(int size) {
		this.size = size;
		content = new int[size];
	}
	public abstract void print();
}

class Sudoku3x3 extends Sudoku {
	public Sudoku3x3() {
		super(81);
	}
	public void print() {
		String bar = "+-------+-------+-------+";
		System.out.println(bar);
		System.out.print("| ");
		for (int i=0; i<size; i++) {
			System.out.print(content[i]);
			System.out.print(' ');
			if (i % 27 == 26) {
				System.out.print("| ");
				System.out.println();
				System.out.println(bar);
				if (i < size - 1) {
					System.out.print("| ");
				}
			} else if (i % 9 == 8) {
				System.out.print("|\n| ");
			} else if (i % 3 == 2) {
				System.out.print("| ");
			}
		}
	}
}

class Solver {
	public static void main(String[] args) {
		Sudoku3x3 sd = new Sudoku3x3();
		sd.print();
	}
}