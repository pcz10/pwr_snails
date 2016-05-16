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
		setCoordinates();
		System.out.println(this.toString());
		this.snail = this;
	}
	
	@Override
	public void run() 
	{
		threadID = Thread.currentThread().getId();
		
		while(true)
		{

			ControlPanel.world.eat(this.snail);
			if(!(ControlPanel.world.isGrassLeft(this.snail)));
				ControlPanel.world.moveSnail(this.snail);
			System.out.println("debug print " + this.grass + this.threadID);
		}
	}
    void sleep(int seconds) {
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
		int index = randomGenerator.nextInt(controlVar.getListOfGrassFields().size());
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
	
	
	@Override
	public String toString() {
		return "\nSnail [grass=" + grass + ", x=" + x + ", y=" + y +"]";
	}
	private Snail snail;
	private Grass grass;
	private Meadow controlVar;
	private Color color = new Color(200,2,2);
	private int x;
	private int y;
}
