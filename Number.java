class Number { // A number that can be in superposition
	private boolean[] possible;
	public int nEliminated = 0;
	private int value = 0;
	private int max;
	private int offset;
	public boolean known = false;
	public boolean debug = false;
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
	public int isnot(int num) {
		boolean debug = true;
		if (debug) {
			System.out.print(this);
			System.out.print(" - ");
			System.out.print(num);
		}
		if (possible[num-offset]) {
			possible[num-offset] = false;
			nEliminated++;
			if (nEliminated == max - 1) {
				known = true;
				for (int i=0; i<max; i++) {
					if (possible[i]) {
						value = i + 1;
					}
				}
			}
			if (debug) {
				System.out.print(" -> ");
				System.out.print(this);
			}
			return 1;
		}
		if (debug) {System.out.println();}
		return 0;
	}
	public boolean canbe(int num) {
		return possible[num-offset];
	}
	public int mustbe(int num) {
		int changes = 0;
		for (int i=0; i<max; i++) {
			changes += possible[i] ? 1 : 0;
			possible[i] = false;
		}
		possible[num-offset] = true;
		nEliminated = max - 1;
		value = num;
		known = true;
		return changes - 1; // subtract 1 for the correct answer
	}
	public int value() {
		if (known) {
			return value;
		} else {
			return 0;
		}
	}
	public boolean isknown() {
		// return nEliminated == max - 1;
		return known;
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
	public String blocks() {
		if (isknown()) {
			return Integer.toString(value);
		} else {
			return " ▁▂▃▄▅▆▇!#".substring(nEliminated,nEliminated+1);
		}
	}
	public String toString() {
		String result = "";
		for (int i=1; i<=max; i++) {
			if (canbe(i)) {
				result += Integer.toString(i);
			}
		}
		return result;
	}
	public String toBox() {
		if (isknown()) {
			return " " + Integer.toString(value) + " ";
		} else {
			return diagram();
		}
	}
	public String onechar() {
		if (false) {
			return DCLBOX();
		} else if (true) {
			return blocks();
		}
		if (!isknown()) {
			return " ";
		} else if (value < 11 - offset) {
			return Integer.toString(value);
		} else {
			return " ";
			// String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			// return alpha.substring(value-10,1);
		}
	}

	public String quadrants = " ▗▖▄▝▐▞▟▘▚▌▙▀▜▛█";
	public String diagram() {
		Quadrant q = new Quadrant();
		String result = "";
		result += q.c(false,    false,     canbe(1), canbe(5));
		result += q.c( canbe(2), canbe(3), canbe(6), canbe(7));
		result += q.c( canbe(4), canbe(8),false,     canbe(9));
		return result;
	}
	public void verbose() {
		for (int i=0; i<max; i++) {
			System.out.print(i+1);
			System.out.print(": ");
			System.out.println(possible[i]);
		}
	}
}