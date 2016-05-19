package pwr_java_lab04;

import java.util.ArrayList;

public class Meadow implements Runnable
{
	@Override
	public void run() 
	{
		while(true)
		{
			if(ControlPanel.world.getGrassGrowthSpeed() > 0)
			{
				increaseGrassLevel();
			}
		    sleep(ControlPanel.world.getGrassGrowthSpeed());
		}
	}

    void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
	public Meadow()
	{
		fillListOfGrassFields();
	}
	
	public void increaseGrassLevel()
	{
		for(Grass grass : this.listOfGrassFields)
		{
			if(!(grass.isTaken()) && !(grass.getColor() == World.grassColors[0]))
				grass.setColor(World.grassColors[findActualGrassColor(grass)-1]);
		}
	}
	public int findActualGrassColor(Grass grass)
	{
		int helperIndex = 7;
		if((grass.getColor() == World.grassColors[helperIndex]))
			return helperIndex;
		else
		{
			while(!(grass.getColor() == World.grassColors[helperIndex]))	
				--helperIndex;
			return helperIndex;
		}
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
	}
	
	private ArrayList<Grass> listOfGrassFields = new ArrayList<>();
	private ArrayList<Snail> listOfSnails = new ArrayList<>();
	public static final int MEADOW_WIDTH = 640;
	
}
