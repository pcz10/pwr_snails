package pwr_java_lab04;

import java.awt.Color;

public class Grass
{
	public Grass(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public boolean isTaken()
	{
		return taken;
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
	@Override
	public String toString() {
		return "\nGrass [taken=" + taken + ", x=" + x + ", y=" + y + ", startColor=" + startColor + "]";
	}
	private boolean taken = false;
	private int x;
	private int y;
	public static final int grassFieldWidth = 64;
	public static final int grassFieldHeight = 64;
	private Color startColor = new Color(0,51,0);
}
