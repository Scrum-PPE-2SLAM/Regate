import javax.swing.*;
import java.awt.event.*;

public class DTimer implements ActionListener
{
	protected int timeCount;	
	protected Timer timer;		
	private LancementRegate window;

	public DTimer (LancementRegate window)
	{	
		this.timeCount = 0;
		this.timer = new Timer (1000, this);
		this.window = window;
	}
	
	public void startDTimer ()
	{	
		this.timer.start ();
	}
	
	public void stopDTimer ()
	{	
		this.timer.stop ();
	}
	
	public int getTime ()
	{	
		return ( this.timeCount * 1000); 
	}
	
	public void reinitDTimer() 
	{
		stopDTimer();
		timeCount = 0;
	}
	
	public boolean isRunning ()
	{	
		return ( this.timer.isRunning () );
	}

	public void actionPerformed (ActionEvent e) 
	{
		this.timeCount++;
		this.window.setChrono(timeCount*1000);
	}
}
