package pwr_java_lab04;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;

public class World extends JComponent
{
	public boolean isGrassLeft(Snail snail)
	{
		if(snail.getGrass().isNoLeft())
			return false;
		return true;
	}
	public void moveSnail(Snail snail)
	{
		reallocate(snail);	
	}
	public void reallocate(Snail snail)
	{
		int id = snail.getGrass().getFieldID();
		Grass grass = snail.getGrass();
		synchronized (grass){
			if(snail.getGrass().getFieldID()<99)
		{
			if(!(meadow.getListOfGrassFields().get(id+1).isTaken()))
				snail.setGrass(meadow.getListOfGrassFields().get(id+1));

			else if(!(meadow.getListOfGrassFields().get(id+10).isTaken()))
				snail.setGrass(meadow.getListOfGrassFields().get(id+10));
			
			else if(!(meadow.getListOfGrassFields().get(id-1).isTaken()))
				snail.setGrass(meadow.getListOfGrassFields().get(id-1));
			
			else if(!(meadow.getListOfGrassFields().get(id-10).isTaken()))
				snail.setGrass(meadow.getListOfGrassFields().get(id-10));
		}
		else if(!(meadow.getListOfGrassFields().get(id-10).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id-10));
		}
		grass.setTaken(false);
		snail.getGrass().setTaken(true);
	}
	public void eat(Snail snail)
	{
		snail.setSecondOccupiedGrassField(generateOccupiedGrassField(snail));
		changeGrassLevel(snail);
	}
	public void changeGrassLevel(Snail snail)
	{
		if(isGrassLeft(snail))
		{
			for(int i = 0; i<World.grassColors.length; ++i)
			{
				snail.getGrass().setColor(World.grassColors[i]);
				snail.getSecondOccupiedGrassField().setColor(World.grassColors[i]);
				snail.sleep(1);
			}
		}
	}
	private Grass generateOccupiedGrassField(Snail snail)
	{
		int id = snail.getGrass().getFieldID();
		Grass grass;
		if(meadow.getListOfGrassFields().get(id+10).isTaken())
		{
			if(meadow.getListOfGrassFields().get(id-10).isTaken())
			{
				if(meadow.getListOfGrassFields().get(id+1).isTaken())
				{
					System.out.println("dupa");
				}
				else 
					return grass = meadow.getListOfGrassFields().get(id+1);
			}
			else
				return grass = meadow.getListOfGrassFields().get(id-11);	
		}
		grass = meadow.getListOfGrassFields().get(id+10);
		return grass;
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
	public static Color[] grassColors = 
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
