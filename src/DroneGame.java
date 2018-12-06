
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class DroneGame extends JFrame implements ConfigurationSpace {

	public DroneGame() {
		JFrame frame = new JFrame();
		frame.add(new Board());
		frame.add((new TimeFrame()), java.awt.BorderLayout.PAGE_START);
		frame.setTitle("Drone Game");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setResizable(false);
		
	}
	
	public static void main(String[] args) {
		new DroneGame();
	}
}


