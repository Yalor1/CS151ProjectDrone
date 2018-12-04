
import java.util.Random;
import java.awt.event.KeyEvent;

public class Player extends Sprite implements ConfigurationSpace {
	private int stopper = 0;
	private int randomNumY = 1;
	private int randomNumX = 1;
	private int xTemp;
	private int yTemp;
	private boolean check = true;
	private boolean pX = true;
	private boolean pY = true;
	private boolean nX = true;
	private boolean nY = true;

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
		Random rand = new Random();
		if (checking()){ 
			if (stopper > 50)	//move on its own randomly
			{
				randomNumY = rand.nextInt(3) - 1;
				randomNumX = rand.nextInt(3) - 1;
				if (randomNumX == 0){
					int b = (int) Math.round(Math.random());
					if (b == 1){
						randomNumX = 1;
					}
					else randomNumX = -1;
				}
				if (randomNumY == 0){
					int a = (int) Math.round(Math.random());
					if (a == 1){
						randomNumY = 1;
					}
					else randomNumY = -1;
				}
				stopper = 0;
			}
			x += randomNumX;
			y += randomNumY;
			stopper++;
		}

		x += dx;		
		y += dy;

		if (x < 1) {
			x = 1;
		}
		if (x > 1370) {
			x = 1370;
		}
		if (y < 1) {
			y = 1;
		}
		if (y > 600) {
			y = 600;
		}
	}

	// player moves when key pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			xTemp = randomNumX;
			yTemp = randomNumY;
			randomNumX = 0;
			randomNumY = 0;
			stopper = 0;
			dx = -3;
			nX = false;
			dx++;
		}
		if (key == KeyEvent.VK_RIGHT) {
			xTemp = randomNumX;
			yTemp = randomNumY;
			randomNumX = 0;
			randomNumY = 0;
			stopper = 0;
			pY = false;
			dx = 2;
		}
		if (key == KeyEvent.VK_DOWN) {
			xTemp = randomNumX;
			yTemp = randomNumY;
			randomNumX = 0;
			randomNumY = 0;
			stopper = 0;
			pY = false;
			dy = 2;
		}
		if (key == KeyEvent.VK_UP) {
			xTemp = randomNumX;
			yTemp = randomNumY;
			randomNumX = 0;
			randomNumY = 0;
			stopper = 0;
			nY = false;
			dy = -2;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			if (checking() == false){
				randomNumX = xTemp;
				randomNumY = yTemp;
				alwaysMove(randomNumX);
				alwaysMove(randomNumY);
			}
			nX = true;
			dx = 0;

		}
		if (key == KeyEvent.VK_RIGHT) {
			if (checking() == false){
				randomNumX = xTemp;
				randomNumY = yTemp;
				alwaysMove(randomNumX);
				alwaysMove(randomNumY);
			}
			pX = true;
			dx = 0;
		}
		if (key == KeyEvent.VK_DOWN) {
			if (checking() == false){
				randomNumX = xTemp;
				randomNumY = yTemp;
				alwaysMove(randomNumX);
				alwaysMove(randomNumY);
			}
			pY = true;
			dy = 0;
		}
		if (key == KeyEvent.VK_UP) {
			if (checking() == false){
				randomNumX = xTemp;
				randomNumY = yTemp;
				alwaysMove(randomNumX);
				alwaysMove(randomNumY);
			}
			nY = true;
			dy = 0;
		}
	}

	public void alwaysMove(int a){
		if (a == 0){
			int b = (int) Math.round(Math.random());
			if (b == 1){
				a = 1;
			}
			else a = -1;
		}
	}
	public boolean checking(){ //return false if we press and holding on arrow move button
		if (pX == false || pY == false || nX == false || nY == false){
			check = false;
			return check;
		}
		else {
			check = true;
			return check;
		}

	}
}
