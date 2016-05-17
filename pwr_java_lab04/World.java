package pwr_java_lab04;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class World extends JComponent
{	
	public boolean isGrassLeft(Snail snail)
	{
		if(snail.getGrass().isNoLeft())
			return false;
		return true;
	}
	public boolean isSecondGrassFieldLeft(Snail snail)
	{
		if(snail.getSecondOccupiedGrassField().isNoLeft())
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
			if(snail.getGrass().getFieldID()<99)
		{
			try 
			{
			snail.setGrass(randomizeNewField(snail, id));
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Demanded Postion is outside frame. Im generating new one");
				snail.setGrass(randomizeNewField(snail,id));
			}
		}
		else if(!(meadow.getListOfGrassFields().get(id-10).isTaken()))
			snail.setGrass(meadow.getListOfGrassFields().get(id-10));
		
		grass.setTaken(false);
		snail.getSecondOccupiedGrassField().setTaken(false);
		snail.getGrass().setTaken(true);
	}
	
	private Grass randomizeNewField(Snail snail, int id) 
	{
		ArrayList<Grass> randomFields = new ArrayList<>();
		fillRandomList(id,snail , randomFields);
		Random randomFieldGenerator = new Random();
		int index = randomFieldGenerator.nextInt(randomFields.size());
		Grass grass = randomFields.get(index);
		return grass;
	}
	private void fillRandomList(int id,Snail snail, ArrayList<Grass> randomFields) 
	{
		try
		{
			if(snail.getX()<575)
			{
				randomFields.add(meadow.getListOfGrassFields().get(id+1));
				randomFields.add(meadow.getListOfGrassFields().get(id+10));
				randomFields.add(meadow.getListOfGrassFields().get(id-1));
				randomFields.add(meadow.getListOfGrassFields().get(id-10));
			}
			else
			{
				randomFields.add(meadow.getListOfGrassFields().get(id-10));
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
	}
	
	public void eat(Snail snail)
	{
		snail.setSecondOccupiedGrassField(generateOccupiedGrassField(snail));
		
			for(int i = meadow.findActualGrassColor(snail.getGrass()); i >0; ++i)
			{
				snail.getGrass().setColor(grassColors[i]);
				snail.getSecondOccupiedGrassField().setColor(grassColors[i]);
			}
	}
	
	private Grass generateOccupiedGrassField(Snail snail)
	{
		int id = snail.getGrass().getFieldID();
		Grass grass;
		fillOccupiedFieldsList(id, snail);
		if(!(meadow.getListOfGrassFields().get(id+10).isTaken()))
			return grass = occupiedFields.get(0);
		else if(!(meadow.getListOfGrassFields().get(id-10).isTaken()))
			return grass = occupiedFields.get(1);
		else if(!(meadow.getListOfGrassFields().get(id+1).isTaken()))
			return grass = occupiedFields.get(2);
		else if(!(meadow.getListOfGrassFields().get(id-1).isTaken()))	
			return grass = occupiedFields.get(3);
		else 
			return null;
	}

	private ArrayList<Grass> fillOccupiedFieldsList(int id,Snail snail) {
		ArrayList<Grass> occupiedFields = new ArrayList<>();
		try 
		{
				occupiedFields.add(meadow.getListOfGrassFields().get(id+10));
				occupiedFields.add(meadow.getListOfGrassFields().get(id-10));
				occupiedFields.add(meadow.getListOfGrassFields().get(id+1));
				occupiedFields.add(meadow.getListOfGrassFields().get(id-1));
		}
		catch(ArrayIndexOutOfBoundsException e)
		{}
		this.occupiedFields = occupiedFields;
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
	private ArrayList<Grass> occupiedFields = new ArrayList<>();

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
