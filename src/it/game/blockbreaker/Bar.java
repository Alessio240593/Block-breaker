package it.game.blockbreaker;

public class Bar {
	private int x;
	private int y;
	
	public Bar() {
		x = 300;
		y = 500;
	}
	
	public void moveRight() {
		x += 20;
	}
	
	public void moveLeft() {
		x -= 20;
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
}
