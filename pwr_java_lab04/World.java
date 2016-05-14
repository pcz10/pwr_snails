package pwr_java_lab04;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class World extends JComponent
{

	public Meadow getMeadow()
	{
		return meadow;
	}
	private Meadow meadow = new Meadow();
}
