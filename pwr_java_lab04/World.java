package pwr_java_lab04;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class World extends JComponent
{	
	public boolean isGrassLeft(Snail snail)
	{
		synchronized(snail.getGrass())
		{
			if(snail.getGrass().isNoLeft())
				return false;
			return true;
		}
	}
	public boolean isSecondGrassFieldLeft(Snail snail)
	{
		if(snail.getSecondOccupiedGrassField().isNoLeft())
			return false;
		return true;
	}
	public void moveSnail(Snail snail)
	{    
		Grass grass = snail.getGrass();
		Grass grassOccupied = snail.getSecondOccupiedGrassField();
		int id = grass.getFieldID()-1;
			try 
			{
				synchronized (grass)
				{
					snail.setGrass(randomizeNewField(id, snail));
					snail.setSecondOccupiedGrassField(generateOccupiedGrassField(snail));
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				snail.setGrass(randomizeNewField(id,snail));
				snail.setSecondOccupiedGrassField(generateOccupiedGrassField(snail));
			}
		grass.setTaken(false);
		grassOccupied.setTaken(false);
		snail.getSecondOccupiedGrassField().setTaken(true);
		snail.getGrass().setTaken(true);
	}
	
	private Grass randomizeNewField(int id, Snail snail) 
	{
		ArrayList<Grass> randomFields = new ArrayList<>();
		fillRandomList(id,snail, randomFields);
		return selectNewRandomField(randomFields);
		
	}
	private void fillRandomList(int id,Snail snail, ArrayList<Grass> randomFields) 
	{
		try
		{
			if(snail.getX()>63 && snail.getX() <576)
			{
				randomFields.add(meadow.getListOfGrassFields().get(id+10));
				randomFields.add(meadow.getListOfGrassFields().get(id+1));
				randomFields.add(meadow.getListOfGrassFields().get(id-10));
				randomFields.add(meadow.getListOfGrassFields().get(id-1));
			}
			else if(snail.getX()<64)
				randomFields.add(meadow.getListOfGrassFields().get(id+10));
			else if(snail.getX()>575)
				randomFields.add(meadow.getListOfGrassFields().get(id-10));
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
	}
	
	public void eat(Snail snail)
	{
		int actualIndex = meadow.findActualGrassColor(snail.getGrass());
		int actualOccpiedIndex = meadow.findActualGrassColor(snail.getSecondOccupiedGrassField());
		synchronized (snail.getSecondOccupiedGrassField())
		{
			for(int i = actualOccpiedIndex; i < grassColors.length; ++i)
			{
				snail.getSecondOccupiedGrassField().setColor(grassColors[i]);
				if(actualIndex<8)
				{
					snail.getGrass().setColor(grassColors[actualIndex]);
					actualIndex+=1;
				}
				snail.sleep(1);
			}
		}
	}
	
	public Grass generateOccupiedGrassField(Snail snail)
	{
		ArrayList<Grass> occupiedFields = new ArrayList<>();
		int id = snail.getGrass().getFieldID()-1;
		occupiedFields = fillOccupiedFieldsList(id, snail, occupiedFields);
		return selectNewRandomField(occupiedFields);
	}
	
	private Grass selectNewRandomField(ArrayList<Grass> occupiedFields) 
	{
		Random randomFieldGenerator = new Random();
		int index = randomFieldGenerator.nextInt(occupiedFields.size());
		Grass grass = occupiedFields.get(index);
		return grass;
	}
	private ArrayList<Grass> fillOccupiedFieldsList(int id,Snail snail,ArrayList<Grass> occupiedFields) 
	{
		try 
		{
			if(snail.getX()>63 && snail.getX() <576)
			{	
				occupiedFields.add(meadow.getListOfGrassFields().get(id+10));
				occupiedFields.add(meadow.getListOfGrassFields().get(id-10));
				occupiedFields.add(meadow.getListOfGrassFields().get(id+1));
				occupiedFields.add(meadow.getListOfGrassFields().get(id-1));
			}
			else if(snail.getX()<64)
				occupiedFields.add(meadow.getListOfGrassFields().get(id+10));
			else if(snail.getX()>575)
				occupiedFields.add(meadow.getListOfGrassFields().get(id-10));
		}	
		catch(ArrayIndexOutOfBoundsException e)
		{}

		return occupiedFields;
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
