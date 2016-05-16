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
		if(startColor == grassColors[7])
			return true;
		return false;
	}
	public  void setTaken(boolean state)
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
	@Override
	public String toString() {
		return "\nGrass [taken=" + taken + ", x=" + x + ", y=" + y + ", startColor = " + startColor + " field id = " + fieldID + "]";
	}
	private boolean taken = false;
	private int fieldID;
	private int x;
	private int y;
	public static final int grassFieldWidth = 64;
	public static final int grassFieldHeight = 64;
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
	private Color startColor = grassColors[7];
	private static int idHelper = 0;
}
