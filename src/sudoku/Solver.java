abstract class Sudoku {
	int content;
	public Sudoku(int size) {
		content = size;
	}
	public abstract void print();
}

class Sudoku3x3 extends Sudoku {
	public Sudoku3x3() {
		super(81);
	}
	public void print() {
		System.out.println(content);
		// for (int i=0; i<size; i++) {
		// 	System.out.print(content[i]);
		// }
		// System.out.println();
	}
}

class Solver {
	public static void main(String[] args) {
		Sudoku3x3 sd = new Sudoku3x3();
		sd.print();
	}
}