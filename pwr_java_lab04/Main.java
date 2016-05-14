package pwr_java_lab04;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		ControlPanel controlPanel = new ControlPanel();
		
		JFrame mainFrame = new JFrame("SIMULATOR");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainFrame.getContentPane().add(controlPanel);
		mainFrame.setVisible(true);
	}

	public static final int FRAME_WIDTH = 840;
	public static final int FRAME_HEIGHT = 640;
}
