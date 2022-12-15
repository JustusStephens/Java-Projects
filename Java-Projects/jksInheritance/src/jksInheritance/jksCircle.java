package jksInheritance;

import javax.swing.JOptionPane;
//radius
//center aka the point
//circum, area

public class jksCircle extends jksPoint
{
	protected double radius;
	
	public jksCircle(double xCord, double yCord, double rad)
	{
		super(xCord,yCord);
		radius = rad;
	}
	
	public void setRadius()
	{
		String input = JOptionPane.showInputDialog("Please enter the radius of the circle: \n");
		radius = Double.parseDouble(input);
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public String printRadius()
	{
		String output;
		output = ("The Radius of the circle is: " + radius);
		return output;
	}
	
	public double calcArea()
	{
		double area;
		area = Math.PI * (radius * radius);
		return area;
	}
	
	public double calcCircum()
	{
		double circum;
		circum = 2 * Math.PI  * radius;
		return circum;
	}
	
	public String showCircleCenter()
	{
		String output = ("The center of your circle is at the coordinates: \n"
				+ "(" + super.getX() + " , " + super.getY() + ")" );
		
		return output;
	}
}
