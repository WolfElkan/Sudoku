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
		if (num != 0 || offset == 0) {
			possible[num-offset] = false;
			nEliminated++;
		}
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
	public String DCLBOX() {
		String powerSet4 = " DCLBOX1ATE2H34#";
		int index = 0;
		int power = 1;
		for (int i=3; i>=0; i--) {
			if (!possible[i]) {
				index += power;
			}
			power *= 2;
		}
		return powerSet4.substring(index,index+1);
	}
	public String toString() {
		if (true) {
			return DCLBOX();
		}
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