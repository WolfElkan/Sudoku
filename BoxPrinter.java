class BoxPrinter {
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
	public static void main(String[] args) {
		BoxPrinter bp = new BoxPrinter();
		System.out.println(bp.c(1,0,2,2));
	}
}