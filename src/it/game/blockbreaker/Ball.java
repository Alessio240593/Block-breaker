package it.game.blockbreaker;

public class Ball {
	private int x;
	private int y;
	private int xdir;
	private int ydir;
	private static Bar paddle;
	
	public Ball() {
		paddle = new Bar();
		x = paddle.getX();
		y= paddle.getY() - 16;
		xdir = -1;
		ydir = -2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getXdir() {
		return xdir;
	}

	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	public int getYdir() {
		return ydir;
	}

	public void setYdir(int ydir) {
		this.ydir = ydir;
	}
}
