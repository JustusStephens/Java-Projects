package jksInheritance;

import javax.swing.JOptionPane;

//set height, radius, center

public class jksCylinder extends jksCircle
{
	
	private double height;
	
	public jksCylinder(double xCord, double yCord, double radius, double h)
	{
		super(xCord,yCord,radius);
		height = h;
	}
	
	public void setHeight()
	{
		String input;
		input = JOptionPane.showInputDialog("Please enter the height of your Cylinder: \n");
		height = Double.parseDouble(input);
	}
	
	public String showCylinder()
	{
		String output;
		output = ("The height of your cylinder: " + height 
				+"\nThe radius of your base: " + radius
				+"\nThe center of your base: " + this.showCords());
		
		return output;
	}
	
	public double calcSurf()
	{
		double surf;
		surf = ((2 * Math.PI * this.getRadius() * height) + (2 * Math.PI * radius * radius));
		return surf;
	}
	
	public double calcVolume()
	{
		double volume;
		volume = Math.PI * radius * radius * height;
		return volume;
	}
	
	
}
