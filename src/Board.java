import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener, ConfigurationSpace {

	private static final long serialVersionUID = 1L;
	private Dimension d;
	private ArrayList<Airplane> airplanes;
	private ArrayList<Cloud> clouds;
	private ArrayList<Lives> lives;
	private Player drone;
	private Airplane airplane;
	private Cloud cloud;
	private Lives live;
	private Timer timer;
	private String message = "Game Over";
	private int crashes = 0;
	
	private boolean ingame = true;
	private boolean pause = false;
    
	private final int[][] planePos = {
	        {1238, 390}, {1500, 890}, {1380, 89},
	        {1550, 109}, {1080, 639}, {1080, 839},
	        {1190, 259}, {1660, 500}, {1590, 550},
	        {1080, 709}, {1060, 400}, {1010, 70},
	        {1030, 159}, {1890, 80}, {1030, 760},
	        {1840, 795}, {1790, 30}, {1420, 400},
	        {1400, 259}, {1060, 850}, {1240, 90},
	     
	    };
	
	private final int[][] cloudPos = {
			{10, 10}, {300,500}, {700, 100}, {900, 600},
			{1150, 10}, {1350, 500}, {1550, 100}, {1750, 600},
			
			};
	
	private final int[][] livesPos = {
			 {100,90},{50,1},{0,0}
	};
		
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		ingame = true;
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		drone = new Player(DRONE_WIDTH, DRONE_HEIGHT);

		initClouds();
		initAirplanes();
		initLives();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void initAirplanes() {
		airplanes = new ArrayList<>();
		
		for(int[] p : planePos) {
			airplanes.add(new Airplane(p[0], p[1]));
		}
	}
	
	public void initClouds() {
		clouds = new ArrayList<>();
		
		for(int[] c : cloudPos) {
			clouds.add((new Cloud(c[0], c[1])));
		}
	}
	
	public void initLives() {
		lives = new ArrayList<>();
		
		for(int[] l : livesPos) {
			lives.add((new Lives(l[0], l[1])));
		}
	}
	
	@Override	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if (ingame) {
			drawClouds(g);
			drawPlayer(g);
			drawAirplanes(g);
			drawLives(g);
			
		}
		else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	// class to draw drone
	private void drawPlayer(Graphics g) {
		if(drone.isVisible()) {
			g.drawImage(drone.getImage(), drone.getX(), drone.getY(), this);
		}
	}
	
	//class to draw airplanes
	private void drawAirplanes(Graphics g) {
		
		for(Airplane airplane : airplanes) {
			if(airplane.isVisible()) {
				g.drawImage(airplane.getImage(), airplane.getX(), airplane.getY(), this);
			}
		}
	}
	
	//class to draw clouds in background
	private void drawClouds(Graphics g) {
		
		for(Cloud cloud : clouds) {
			if(cloud.isVisible()) {
				g.drawImage(cloud.getImage(), cloud.getX(), cloud.getY(), this);
			}
		}
	}
	
	private void drawLives(Graphics g) {
		
		for(Lives live: lives) {
			if(live.isVisible()) {
				g.drawImage(live.getImage(), live.getX(), live.getX(), this);
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent E) {
		
		inGame();
		updatePlayer();
		updateAirplanes();
		updateClouds();
		updateLives();
		if(!pause) {
			checkCollisions();
		}
		System.out.println(drone.getLives());
		//checkCollisions();
		repaint();
	}
	
	private void inGame() {
		
		if(!ingame) {
			
			timer.stop();

		}
	}
	
	private void updatePlayer() {
		
		if(drone.isVisible()) {
			drone.move();
		}
	}
	
	private void updateClouds() {

		if(clouds.isEmpty()) {
			ingame = false; 
			return;
		}

		for(int i = 0; i < clouds.size(); i++) {

			Cloud p = clouds.get(i);

			if(p.isVisible()) {
				p.move();
			} else {
				clouds.remove(i);
			}
		}
	}
	
	private void updateLives() {
		if(lives.isEmpty()) {
			ingame = false; 
			return;
		}

		if(drone.isCrashing()) {
			int i = 0;
			lives.remove(i);
			i++;
			drone.setCrashing(false);
			
			
			
		}
	}
	
	private void updateAirplanes() {
		
		if(airplanes.isEmpty()) {
			ingame = false; 
			return;
		}
		
		for(int i = 0; i < airplanes.size(); i++) {
			
			Airplane p = airplanes.get(i);
			
			if(p.isVisible()) {
				p.move();
			} else {
				airplanes.remove(i);
			}
		}
	}
	
	// creates rectangle around images to check for collisions
	public void checkCollisions() {
		
		Rectangle r3 = drone.getBounds();
		
		for(Airplane airplane : airplanes) {
			Rectangle r2 = airplane.getBounds();
			
			if(r3.intersects(r2)) {
				
				System.out.println(pause);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!pause) {
					drone.loseLife();
					drone.setCrashing(true);
					crashes++;
					pause = true;
					new java.util.Timer().schedule(
							new java.util.TimerTask() {
								@Override
								public void run() {
									pause = false;
								}
							},
							3000
							);
				}
				if(drone.getLives() == 0) {
					ingame = false;
					drone.setVisible(false);
					airplane.setVisible(false);
				}
				
			}
		}
		
	}
	
	 private void drawGameOver(Graphics g) {

	        String msg = "Game Over";
	        Font large = new Font("Helvetica", Font.BOLD, 30);
	        FontMetrics fm = getFontMetrics(large);

	        g.setColor(Color.black);
	        g.setFont(large);
	        g.drawString(msg, (BOARD_WIDTH - fm.stringWidth(msg)) / 2,
	                BOARD_HEIGHT / 2);
	    }

	
	private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e) {
			drone.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e) {
			drone.keyPressed(e);
			
					}
				}		

}
