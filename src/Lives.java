
import javax.swing.ImageIcon;


public class Lives extends Sprite {
	
	private final int INITIAL_X = 1050; // initial position of Airplane
	
	public Lives(int x, int y) {
		super(x,y);
		
		initLives();
	}

	// class to load airplane image
	private void initLives() {
		loadImage("src/images/heart.png");
		getImageDimensions();
	}
	
	
}
