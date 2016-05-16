package pwr_java_lab04;

import java.awt.Color;

import javax.swing.JComponent;

public class World extends JComponent
{
	public boolean isGrassLeft(Snail snail)
	{
		if(snail.getGrass().isNoLeft())
			return false;
		return true;
	}
	public synchronized void moveSnail(Snail snail)
	{
		System.out.println("CLASS :World ||| METHOD :moveSnail");
		if(!isGrassLeft(snail))
		{
			System.out.println("CLASS :World ||| METHOD :isGrassLeft");
			reallocate(snail);
		}
	}
	public synchronized void reallocate(Snail snail)
	{
		System.out.println("CLASS :World ||| METHOD :reallocate");
		int id = snail.getGrass().getFieldID();
		if(!(meadow.getListOfGrassFields().get(id+1).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id+1));
		
		else if(!(meadow.getListOfGrassFields().get(id-1).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id-1));
		
		else if(!(meadow.getListOfGrassFields().get(id+9).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id+9));
		
		else if(!(meadow.getListOfGrassFields().get(id-10).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id-10));
	}
	
	public synchronized void changeGrassLevel()
	{
		
	}
	
		
	public int setAppetite(int appetiteValueFromSlider)
	{
		return this.snailsAppetite = appetiteValueFromSlider;
	}
	public int setGrassGrowthSpeed(int grassGrowthSpeedFromSlider)
	{
		return this.grassGrowthSpeed = grassGrowthSpeedFromSlider;
	}
	public Meadow getMeadow()
	{
		return meadow;
	}
	public int getSnailsAppetite()
	{
		return snailsAppetite;
	}
	public int getGrassGrowthSpeed()
	{
		return grassGrowthSpeed;
	}
	public Color[] getGrassColors()
	{
		return grassColors;
	}
	
	private Meadow meadow = new Meadow();
	private int snailsAppetite;
	private int grassGrowthSpeed;
	private Color[] grassColors = 
		{
				new Color(0,51,0),
				new Color(0,102,0),
				new Color(0,153,0),
				new Color(0,204,0),
				new Color(51,255,51),
				new Color(102,255,102),
				new Color(153,255,153),
				new Color(102,51,0),
		};
}
