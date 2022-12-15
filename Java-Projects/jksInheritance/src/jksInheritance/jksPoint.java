package jksInheritance;

import javax.swing.JOptionPane;

public class jksPoint 
{
	private double xCord;
	private double yCord;
	
	public jksPoint(double x, double y)
	{
		xCord = x;
		yCord = y;
		
	}
	
	public double getX()
	{
		return xCord;
	}
	
	public double getY()
	{
		return yCord;
	}
	
	public String showCords()
	{
		String cords;
		cords = ("(" + this.xCord + " , " + this.yCord + ")" );
		return cords;
	}
	

	public void setCords()
	{
		String input = JOptionPane.showInputDialog("Please enter a value for the X coordinate: \n");
		xCord = Double.parseDouble(input);
		
		String input2 = JOptionPane.showInputDialog("Please enter a value for the Y coordinate: \n");
		yCord = Double.parseDouble(input2);
	}
}
