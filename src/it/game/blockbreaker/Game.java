package it.game.blockbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Game implements KeyListener{
	private Graphics graphics;
	private Bar paddle;
	private Map map;
	private Ball ball;
	private int score = 0;
	private boolean play = false;
	public static final int width = 600;
	public static final int height = 600;
	public JFrame window;
	
	public Game() {
		paddle = new Bar();
		map = new Map(10, 10);
		ball = new Ball();
		window = new JFrame();
		graphics = new Graphics(this);
		window.add(graphics);
		window.setTitle("BLOCK_BREAKER");
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);	
	}
	
	public void start() {
		graphics.setState("RUNNING");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(graphics.getState().equals("RUNNING")) {
			if(keyCode == KeyEvent.VK_LEFT) {
				if(paddle.getX() <= 10) {
					paddle.setX(10);
				}
				else {
					paddle.moveLeft();
					play = true;
				}
			}
			
			if(keyCode == KeyEvent.VK_RIGHT) {
				if(paddle.getX() >= 523) {
					paddle.setX(523);
				}
				else {
					paddle.moveRight();
					play = true;
				}
			}
		}
		else {
			this.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public Bar getPaddle() {
		return paddle;
	}
	
	public void setPaddle(Bar b) {
		this.paddle = b;
	}

	public Ball getBall() {
		return ball;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map bl) {
		this.map = bl;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
