/**
 * This part of my program includes a
 * constructor and methods
 * for the first box
 * 
 * Justus Stephens
 * 11/25/18
 * cps 121
 */

package jksBoxProject;

import javax.swing.JOptionPane;

public class Cube
{
	double dimension;
	double multiplyer;
	String dataOut;
	
	Cube(double dim)
	{
		dimension = dim;
		JOptionPane.showMessageDialog(null, "Your cube has a length, width, and height of "+dimension);
	}
	
	//Volume calc
	double volume()
	{
		return dimension * dimension * dimension;
	}
	
	//surface area calc
	double surfaceArea()
	{
		return (6* (dimension*dimension));
	}
	
		 String makeLargerMult(double mult)
	{
		 multiplyer = mult;
		 dimension *= multiplyer;
		 dataOut = ("new dimension for your cube is: "+dimension);
		 return dataOut;
		 
	}
		 
		 String makeLargerAdd(double d)
			{
				 
				 dimension=d;
				 dataOut = ("new dimension for the cube is: "+dimension);
				 return dataOut;
					 
			}
		 
		 String output()
		 {
			 dataOut = ("The final dimension for your cube is: "+dimension);
			 JOptionPane.showMessageDialog(null, dataOut);
			 return dataOut;
		 }

}
