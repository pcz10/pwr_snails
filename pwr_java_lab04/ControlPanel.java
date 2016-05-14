package pwr_java_lab04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel
{
	public ControlPanel()
	{
		JPanel controlPanel = new JPanel();
		World world = new World();
		this.world = world;
		controlPanel.add(world);
		SnailHandler snailHandler = new SnailHandler();
		JButton addSnailButton = new JButton("New snail");
		this.addSnailButton = addSnailButton;
		addSnailButton.addActionListener(snailHandler);
	}
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		for(Grass grass : this.world.getMeadow().getListOfGrassFields())
		{
			g2.setColor(grass.getColor());
			g2.fillRect(grass.getX(), grass.getY(), 64, 64);
		}
		setSnailButtonProperties();
		
		for(Snail snail : this.world.getMeadow().getListOfSnail())
		{
			g2.setColor(snail.getColor());
			g2.fillOval(snail.getX(), snail.getY(), 64, 64);
		}
	}
	
	private void setSnailButtonProperties() 
	{
		add(this.addSnailButton);
		addSnailButton.setLocation(700,50);
		addSnailButton.setSize(100, 50);
		addSnailButton.setVisible(true);
	}
	
	private class SnailHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			world.getMeadow().getListOfSnail().add(new Snail(world.getMeadow()));
			repaint();
		}
	}
	private World world ;
	private JButton addSnailButton;
}
