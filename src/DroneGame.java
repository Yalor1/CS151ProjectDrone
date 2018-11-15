
import javax.swing.JFrame;

public class DroneGame extends JFrame implements ConfigurationSpace {

	public DroneGame() {
		add(new Board());
		setTitle("Drone Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new DroneGame();
	}
}


