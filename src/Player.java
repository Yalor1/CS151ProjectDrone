import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite implements ConfigurationSpace {
	
	public Player(int x, int y) {
		super(x,y);
		
		initPlayer();
	}
	
	private void initPlayer() {
		loadImage("src/images/drone.png");
		getImageDimensions();
	}
	
	// class that allows player to move around freely
	public void move() {
		 x += dx;
	        y += dy;

	        if (x < 1) {
	            x = 1;
	        }

	        if (y < 1) {
	            y = 1;
	        }
	}
	
	// player moves when key pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}
	}
	
}
