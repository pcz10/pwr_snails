package pwr_java_lab04;

import java.awt.Color;
import java.util.Random;

public class Snail implements Runnable
{
	public long threadID;
	public Snail(Meadow meadow)
	{
		this.controlVar = meadow;
		generateGrassField();
		this.secondOccupiedGrassField = ControlPanel.world.generateOccupiedGrassField(this);
		setCoordinates();
		System.out.println(this.toString());
	}
	
	@Override
	public void run() 
	{
		threadID = Thread.currentThread().getId();
		ControlPanel.world.moveSnail(this);
		while(true)
		{
			if(ControlPanel.world.getSnailsAppetite()>0)
				ControlPanel.world.eat(this);
			
			if(!(ControlPanel.world.isGrassLeft(this)));
				ControlPanel.world.moveSnail(this);
			sleep(1);
		}
	}
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    
	private Grass generateGrassField()
	{	
		Grass grass = generateRandomGrassFieldData();
		while(grass.isTaken())
			grass = generateRandomGrassFieldData();
		grass.setTaken(true);
		return this.grass = grass;
	}
	private Grass generateRandomGrassFieldData()
	{	
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(controlVar.getListOfGrassFields().size()-11);
		Grass grass = controlVar.getListOfGrassFields().get(index);
		return grass;
	}
	private void setCoordinates()
	{
		this.x = this.grass.getX();
		this.y = this.grass.getY();
	}
	public void setGrass(Grass grass)
	{
		this.grass = grass;
		this.setCoordinates();
	}
	public Color getColor()
	{
		return color;
	}
	public Grass getGrass()
	{
		return grass;
	}
	public int getX()
	{
		return this.x;
	}
	public int getY()
	{
		return this.y;
	}
	public Grass getSecondOccupiedGrassField()
	{
		return this.secondOccupiedGrassField;
	}
	public void setSecondOccupiedGrassField(Grass grass)
	{
		this.secondOccupiedGrassField = grass;
	}
	@Override
	public String toString() {
		return "\nSnail [grass=" + grass + ", x=" + x + ", y=" + y +"]";
	}
	private Grass grass;
	private Grass secondOccupiedGrassField;
	private Meadow controlVar;
	private Color color = new Color(200,2,2);
	private int x;
	private int y;
}
