
import javax.swing.ImageIcon;


public class Airplane extends Sprite {
	
	private final int INITIAL_X = 1050; // initial position of Airplane
	
	public Airplane(int x, int y) {
		super(x,y);
		
		initAirplane();
	}

	// class to load airplane image
	private void initAirplane() {
		loadImage("images/plane.png");
		getImageDimensions();
	}
	
	public void move() {
		if(x < 0) {
			x = INITIAL_X; // once airplane touches the left border it will return to its initial position
		}
		
		x -= 3; // airplane direction to the left
	}
}
