
import javax.swing.*;

public class GameTimer  extends JLabel{

	private int	seconds, minutes;
	private Timer timer;

	public GameTimer()
	{
		seconds = 0;
		minutes = 0;

		timer = new Timer(1000, event ->{
			this.increment();
			this.setText(this.toString());
		});
	}

	public void start()
	{
		timer.start();
	}

	public void stop()
	{
		timer.stop();
	}

	public void increment()
	{
		seconds++;
		if (seconds >= 60 )
		{
			minutes++;
			seconds = 0;
		}
	}

	public String toString()
	{
		return String.format("%02d:%02d", minutes, seconds);
	}
}
