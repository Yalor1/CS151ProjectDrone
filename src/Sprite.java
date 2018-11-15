import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.*;

import javax.swing.ImageIcon;

public class Sprite {
	private boolean visible;
	private Image image;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean crashing;
	protected int dx;
	protected int dy;
	
	public Sprite(int x, int y) {
		
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	protected void getImageDimensions() {
		
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void loadImage(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	protected void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
