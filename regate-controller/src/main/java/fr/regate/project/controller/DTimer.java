package fr.regate.project.controller;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import fr.regate.project.view.*;

public class DTimer implements ActionListener
{
	protected int timeCount;	
	protected Timer timer;		
	private LancementRegate window;
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

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
		return ( this.timeCount);
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
		this.window.setLblChrono(df.format(timeCount*1000 - 3.6 * Math.pow(10,6)));
	}
}
