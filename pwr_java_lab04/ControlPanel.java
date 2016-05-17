package pwr_java_lab04;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel
{
	public static World world = new World();
	
	public ControlPanel()
	{
		Thread newWorldThread = new Thread(world.getMeadow());
		newWorldThread.start();
		//this.world = world;
		SnailHandler snailHandler = new SnailHandler();
		JButton addSnailButton = new JButton("New snail");
		this.addSnailButton = addSnailButton;
		addSnailButton.setLocation(700,50);
		addSnailButton.setSize(100, 50);
		addSnailButton.setVisible(true);
		addSnailButton.addActionListener(snailHandler);
		
		JLabel appetiteSliderTitle = new JLabel("Snails appetite slider");
		this.appetiteSliderTitle = appetiteSliderTitle;
		
		JSlider changeAppetiteSlider = new JSlider(0,1,0);
		this.changeAppetiteSlider = changeAppetiteSlider;
		AppetiteHandler appetiteHandler = new AppetiteHandler();
		changeAppetiteSlider.addChangeListener(appetiteHandler);
		
		JLabel grassGrowthSliderTitle = new JLabel("Grass growth speed slider");
		this.grassGrowthSliderTitle = grassGrowthSliderTitle;
		
		JSlider changeGrassGrowthSlider = new JSlider(0,4,0);
		this.changeGrassGrowthSlider = changeGrassGrowthSlider;
		GrassGrowthHandler grassGrowthHandler = new GrassGrowthHandler();
		changeGrassGrowthSlider.addChangeListener(grassGrowthHandler);
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		setSnailButtonProperties(); 
		setAppetiteSliderProperties(); 
		setGrassGrowthSliderProperties();
		
		for(Grass grass : world.getMeadow().getListOfGrassFields())
		{
			g2.setColor(grass.getColor());
			g2.fillRect(grass.getX(), grass.getY(), 64, 64);
		}
		
		for(Snail snail : world.getMeadow().getListOfSnail())
		{
			g2.setColor(snail.getColor());
			g2.fillOval(snail.getX(), snail.getY(), 64, 64);
		}
		
		repaint();
	}
	
	private void setSnailButtonProperties() 
	{
		addSnailButton.setLocation(700,50);
		addSnailButton.setSize(100, 50);
		addSnailButton.setVisible(true);
		add(this.addSnailButton);
	}
	private void setAppetiteSliderProperties() 
	{
		changeAppetiteSlider.setLocation(700,220);
		createAppetiteSliderLabels();
		changeAppetiteSlider.setVisible(true);
		add(this.changeAppetiteSlider);
	}
	private void createAppetiteSliderLabels() 
	{
		changeAppetiteSlider.setPaintLabels(true);
		Hashtable<Integer,JLabel> position = new Hashtable<>();
		position.put(0, new JLabel("NOT HUNGRY"));
		position.put(1, new JLabel("HUNGRY"));
		changeAppetiteSlider.setLabelTable(position);
		appetiteSliderTitle.setLocation(700, 190);
		add(this.appetiteSliderTitle);
	}
	private void setGrassGrowthSliderProperties()
	{
		changeGrassGrowthSlider.setLocation(700,320);
		//changeGrassGrowthSlider.setSize(180, 25);
		createGrassGrowthSliderLabels();
		changeGrassGrowthSlider.setVisible(true);
		add(this.changeGrassGrowthSlider);
	}
	private void createGrassGrowthSliderLabels() 
	{
		changeGrassGrowthSlider.setPaintLabels(true);
		Hashtable<Integer,JLabel> position = new Hashtable<>();
		position.put(0, new JLabel("0"));
		position.put(1, new JLabel("FASTEST"));
		position.put(4, new JLabel("SLOWEST"));
		changeGrassGrowthSlider.setLabelTable(position);
		grassGrowthSliderTitle.setLocation(700, 290);
		add(this.grassGrowthSliderTitle);
	}
	private class SnailHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Snail snail = new Snail(world.getMeadow());
			world.getMeadow().getListOfSnail().add(snail);
			Thread newSnailThread = new Thread(snail);
			newSnailThread.start();
		}
	}
	private class AppetiteHandler implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			world.setAppetite(changeAppetiteSlider.getValue());
			changeAppetiteSlider.setValue(changeAppetiteSlider.getValue());
			System.out.println(world.getSnailsAppetite());
		}
	}
	private class GrassGrowthHandler implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			world.setGrassGrowthSpeed(changeGrassGrowthSlider.getValue());
			changeGrassGrowthSlider.setValue(changeGrassGrowthSlider.getValue());
			System.out.println(world.getGrassGrowthSpeed());
		}
	}

	private JButton addSnailButton;
	private JSlider changeAppetiteSlider;
	private JSlider changeGrassGrowthSlider;
	private JLabel appetiteSliderTitle;
	private JLabel grassGrowthSliderTitle;
}
