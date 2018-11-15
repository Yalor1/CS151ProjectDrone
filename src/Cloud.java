
import javax.swing.ImageIcon;


public class Cloud extends Sprite {

	public Cloud(int x, int y) {
		super(x,y);
		
		initCloud();
	}
	private void initCloud() {
		loadImage("src/images/cloud.png");
		getImageDimensions();
	}
}



