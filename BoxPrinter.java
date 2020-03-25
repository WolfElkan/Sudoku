class BoxChar {
	public String dark = " ╴╸╶─╾╺╼━╵┘┙└┴┵┕┶┷╹┚┛┖┸┹┗┺┻╷┐┑┌┬┭┍┮┯│┤┥├┼┽┝┾┿╿┦┩┞╀╃┡╄╇╻┒┓┎┰┱┏┲┳╽┧┪┟╁╅┢╆╈┃┨┫┠╂╉┣╊╋";

	public char c(int left, int right, int up, int down, int style) {
		String charset = dark;
		if (style == 0) {
			if (left  >= 1) {left  = 1;}
			if (right >= 1) {right = 1;}
			if (up    >= 1) {up    = 1;}
			if (down  >= 1) {down  = 1;}
		}
		int index = 0;
		index +=  left *  1;
		index += right *  3;
		index +=    up *  9;
		index +=  down * 27;
		return charset.charAt(index);
	}
	public char c(int left, int right, int up, int down) {
		return c(left,right,up,down,1);
	}
	public char intersection(Line horz, Line vert) {
		return ' ';
	}
}

class Line {
	public Line(int style) {

	}
}

class Quadrant {
	public char c(int ul, int dl, int ur, int dr) {
		int index = 0;
		index += ur * 4;
		index += dr * 1;
		index += ul * 8;
		index += dl * 2;
		return " ▗▖▄▝▐▞▟▘▚▌▙▀▜▛█".charAt(index);
	}
	public char c(boolean ul, boolean dl, boolean ur, boolean dr) {
		int uli = ul ? 1 : 0;
		int dli = dl ? 1 : 0;
		int uri = ur ? 1 : 0;
		int dri = dr ? 1 : 0;
		return c(uli,dli,uri,dri);
	}
}

class BoxPrinter {
	public static char boxprint(String[] args) {
		BoxChar bp = new BoxChar();
		int up    = Integer.parseInt(args[1]);
		int right = Integer.parseInt(args[2]);
		int down  = Integer.parseInt(args[3]);
		int left  = Integer.parseInt(args[4]);
		return bp.c(left,right,up,down);
	}
	public static char quadprint(String[] args) {
		Quadrant quad = new Quadrant();
		int ur = Integer.parseInt(args[1]);
		int dr = Integer.parseInt(args[2]);
		int ul = Integer.parseInt(args[3]);
		int dl = Integer.parseInt(args[4]);
		return quad.c(ur, dr, ul, dl);
	}
	public static void main(String[] args) {
		if (args[0] == "quad") {
			System.out.println(boxprint(args));
		} else {
			System.out.println(quadprint(args));
		}
	}
}