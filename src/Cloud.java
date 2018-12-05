
import javax.swing.ImageIcon;


public class Cloud extends Sprite {

	private static final int BOARD_WIDTH = 0;

	public Cloud(int x, int y) {
		super(x,y);
		
		initCloud();
	}
	private void initCloud() {
		loadImage("C:/Users/aldrich_mangune/Personal/CS151ProjectDrone/src/images/cloud.png");
		getImageDimensions();
	}
	
	public void move() {
		if(x < -300) {
			x = 2000; // once airplane touches the left border it will return to its initial position
		}
		
		x -= 1;
	}
}





