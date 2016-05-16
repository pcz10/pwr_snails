package pwr_java_lab04;

import java.util.ArrayList;

public class Meadow 
{
	
	public Meadow()
	{
		fillListOfGrassFields();
	}
	public Grass returnFreeField(Grass grass)
	{
		int index = grass.getFieldID();
		if(this.listOfGrassFields.get(index+1).isTaken())
			return this.listOfGrassFields.get(index-1);
		else return this.listOfGrassFields.get(index+1); 
	}
	public ArrayList<Snail> getListOfSnail()
	{
		return listOfSnails;
	}
	public ArrayList<Grass> getListOfGrassFields()
	{
		return listOfGrassFields;
	}
	private void fillListOfGrassFields()
	{
		for(int i = 0; i < MEADOW_WIDTH; i+= Grass.grassFieldWidth)
			for(int j = 0; j < Main.FRAME_HEIGHT; j+= Grass.grassFieldHeight)
			{
				this.listOfGrassFields.add(new Grass(i,j));
			} 	
		System.out.println(this.listOfGrassFields);
	}
	private ArrayList<Grass> listOfGrassFields = new ArrayList<>();
	private ArrayList<Snail> listOfSnails = new ArrayList<>();
	public static final int MEADOW_WIDTH = 640;
}
