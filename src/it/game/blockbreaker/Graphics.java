package it.game.blockbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics extends JPanel implements ActionListener{
	private Bar paddle;
	private Map map;
	private Ball ball;
	private Game game;
	private String state;
	private Timer t = new Timer(delay, this);
	
	public static final int delay = 15;

	public Graphics(Game g) {
		t.start();
		state = "START";
		paddle = g.getPaddle();
		map = g.getMap();
		ball = g.getBall();
		game = g;
		
		//add a keyListner 
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		if(game.isPlay()) {
			if(new Rectangle(ball.getX(), ball.getY(), 14, 14).intersects(new Rectangle(paddle.getX(), paddle.getY(), 70, 8))) {
				ball.setYdir(-ball.getYdir());
			}
			
			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.getBrickWidth() + 50;
						int brickY = i * map.getBrickHeight() + 50;
						int brickWidth = map.getBrickWidth();
						int brickHeight = map.getBrickHeight();
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), 14, 14);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							map.setTotalBrick(map.getTotalBrick() - 1);
							game.setScore(game.getScore() + 5);
							//ball.setY(-ball.getY());
							//ball.setY(-ball.getX());
						}
					}
				}
			}
			
			ball.setX(ball.getX() + ball.getXdir());
			ball.setY(ball.getY() + ball.getYdir());
			
			if(ball.getX() < 0) {
				ball.setXdir(-ball.getXdir());
			}
			else if(ball.getY() < 0) {
				ball.setYdir(-ball.getYdir());
			}
			else if(ball.getX() > game.width - 13) {
				ball.setXdir(-1 * ball.getXdir());
			}
			else if(ball.getY() >= game.height - 30) {
				state = "END";
			}
		}
		repaint();
	}
	
	public void paint(java.awt.Graphics g) {
		//background
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Game.width, Game.height);
		
		//border
		g2d.setColor(Color.yellow);
		g2d.fillRect(0, 0, 3, game.width);
		g2d.fillRect(0, 0, game.height, 3);
		g2d.fillRect(game.height - 3, 0, 3, game.width);
		
		if(map.getTotalBrick() == 0) {
			g2d.setColor(Color.magenta);
			g2d.setFont(new Font("serif", Font.BOLD, 30));
			g2d.drawString("Congratulations you win the game! Your score: " + game.getScore(), game.width / 2 - 300, game.height / 2 - 40);
		}
		
		if(state.equals("START")) {
			//title
			g2d.setColor(Color.white);
			g2d.setFont(new Font("serif", Font.BOLD, 50));
			g2d.drawString("BLOCK BREAKER", game.width / 2 - 250, game.height / 2 - 220);
			
			//blocks
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 10; j++) {
					g2d.setColor(Color.orange);
					g2d.fillRect(j * 40 + 110, i * 15 + 120, 40, 15);
					g2d.setStroke(new BasicStroke(3));
					g2d.setColor(Color.black);
					g2d.drawRect(j * 40 + 110, i * 15 + 120, 40, 15);
				}
			}
			
			//text 
			g2d.setColor(Color.white);
			g2d.setFont(new Font("serif", Font.BOLD, 22));
			g2d.drawString("Press any key to start...", 130, 450);
			
			//paddle
			g2d.setColor(Color.green);
			g2d.fillRect(200, 300, 100, 18);
			
			//ball
			g2d.setColor(Color.red);
			g2d.fillOval(180, 230, 20, 20);
		}
		else if("RUNNING".equals(state)) {
			//paddle
			g2d.setColor(Color.green);
			g2d.fillRect(paddle.getX(), paddle.getY(), 70, 8);
			
			//ball
			g2d.setColor(Color.red);
			g2d.fillOval(ball.getX(), ball.getY(), 14, 14);
			
			//score
			g2d.setColor(Color.white);
			g2d.setFont(new Font("serif", Font.BOLD, 20));
			g2d.drawString(String.valueOf(game.getScore()), game.width - 50, 30);
			
			//drawing map
			map.draw((Graphics2D)g);
			
			g.dispose();
		}
		else if("END".equals(state) && map.getTotalBrick() > 0) {
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.ITALIC, 25));
			g.drawString("Your score: " + game.getScore() + "pt", game.width / 2 - 110, game.height / 2 - 40);
		}
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
