package pwr_java_lab04;

import java.awt.Color;

public class Grass
{
	public Grass(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.fieldID = idHelper++;
	}

	public  boolean isTaken()
	{
		return taken;
	}
	public boolean isNoLeft()
	{
		if(startColor == World.grassColors[0])
			return true;
		return false;
	}
	public void setTaken(boolean state)
	{
		this.taken = state;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Color getColor()
	{
		return startColor;
	}
	public int getFieldID()
	{
		return fieldID;
	}
	public void setColor(Color color)
	{
		startColor = color;
	}
	@Override
	public String toString() {
		return "\nGrass [taken=" + taken + ", x=" + x + ", y=" + y + ", startColor = " + startColor + " field id = " + fieldID + "]";
	}
	
	private boolean taken = false;
	private int fieldID=0;
	private int x;
	private int y;
	public static final int grassFieldWidth = 64;
	public static final int grassFieldHeight = 64;

	private Color startColor = World.grassColors[7];
	private static int idHelper = 1;
}
