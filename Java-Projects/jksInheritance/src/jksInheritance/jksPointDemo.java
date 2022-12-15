//Justus Stephens
//CPS 230
//Inheritance
//March 8, 2020

package jksInheritance;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.UIManager;

public class jksPointDemo 
{
	private static  DecimalFormat df2 = new DecimalFormat("#.##");  //for decimal places
	
	public static void main(String[] args) 
	{
		
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.GRAY);		//color
		UI.put("Panel.background", Color.MAGENTA);			//color
		
		
		double x=0;							//for x cord
		double y=0;							//for y cord
		double r=0;
		
		jksPoint obj = new jksPoint(x,y);		//point object
		
		obj.setCords();     //setting the actual x and y values
		x = obj.getX();		//returning x
		y = obj.getY();		//returning y
		
		String coords = obj.showCords();		//string to show the coordinates
		
		JOptionPane.showMessageDialog(null, "Coordinates for the point: \n" + coords);
		
		
		jksCircle obj2 = new jksCircle(x,y,r);		//circle time
		
		String output2;
		obj2.setRadius();		//sets radius, used for calculations
		
		output2 = (obj2.printRadius() + "\n" + obj2.showCircleCenter());  //shows where circle is
		JOptionPane.showMessageDialog(null, output2);
		
		
		String output3; 
		output3 = ("This circle has an area of: " + df2.format(obj2.calcArea()) 
		+ "\n and a circumference of: " + df2.format(obj2.calcCircum()) );			//shows calculations for cirlce
		JOptionPane.showMessageDialog(null, output3);
		
		double h = 0;
		r = obj2.getRadius();			//have to set r to radius here or it will be set as 0 in my cylinder class
			
		jksCylinder obj3 = new jksCylinder(x,y,r,h);
		
		obj3.setHeight();		//setting height
		
		String output4;		
		
		output4 = obj3.showCylinder();
		JOptionPane.showMessageDialog(null, output4);		//showing cylinder dimensions
		
		String output5;
		output5 = ("The Cylinder's surface area is: " + df2.format(obj3.calcSurf()) + 
				"\nThe Cylinder's volume is : " + df2.format(obj3.calcVolume()) );			//calcs
		JOptionPane.showMessageDialog(null, output5);

	}

}
