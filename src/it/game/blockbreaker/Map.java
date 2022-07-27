package it.game.blockbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	private int brickHeight;
	private int brickWidth;
	private int totalBrick;
	int map[][];
	
	public Map(int row, int col) {
		map = new int[row][col];
		totalBrick = row * col;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		brickWidth = 500 / row;
		brickHeight =  150 / col;
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.orange);
					g.fillRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col){
		map[row][col] = value;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public void setBrickHeight(int brickHeight) {
		this.brickHeight = brickHeight;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public void setBrickWidth(int brickWidth) {
		this.brickWidth = brickWidth;
	}

	public int getTotalBrick() {
		return totalBrick;
	}

	public void setTotalBrick(int totalBrick) {
		this.totalBrick = totalBrick;
	}
}
