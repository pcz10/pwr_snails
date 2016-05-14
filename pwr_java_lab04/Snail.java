package pwr_java_lab04;

import java.awt.Color;
import java.util.Random;

import javax.swing.JComponent;

public class Snail extends JComponent
{
	public Snail(Meadow meadow)
	{
		this.controlVar = meadow;
		generateGrassField();
		setCoordinates();
		System.out.println(this.toString());
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
	
	public Color getColor()
	{
		return color;
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
		return "Snail [grass=" + grass + ", x=" + x + ", y=" + y + "]";
	}

	private Grass grass;
	private Meadow controlVar;
	private Color color = new Color(200,2,2);
	private int x;
	private int y;
	private int appetite;
}
