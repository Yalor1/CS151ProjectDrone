import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimeFrame extends JPanel implements ActionListener {
	JLabel timeLabel = new JLabel();
	JLabel seconds = new JLabel();
	int time = 60;
	Timer timer = new Timer(1000,this);
	
	public TimeFrame() {
		timer.start();
		add(timeLabel);
		add(seconds);
	}
	
	public void count() {
		
			time--;
		timeLabel.setText("Time:");
		seconds.setText(""+time+" seconds");
		if(time == 0) {
			timer.stop();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		count();
		repaint();
	}

}
