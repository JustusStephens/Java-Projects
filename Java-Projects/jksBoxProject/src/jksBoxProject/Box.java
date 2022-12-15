
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

public class Box
{
	double width;
	double length;
	double height;
	double multiplyer;
	String dataOut;
	
	//This constructor is for the first box only (2 dimensional)
	Box(double w, double l, double h)
	{
		width = w;
		length = l;
		height = h;
		JOptionPane.showMessageDialog(null, "Your box has a width of "+width+" and a length of "+length+" and a height of "+height);
	}
	
	//Volume calc
	double volume()
	{
		return length * width * height;
	}
	
	//surface area calc
	double surfaceArea()
	{
		return (2 * (width*length + height*length + height*width));
	}
	
		 String makeLargerMult(double mult)
	{
		 multiplyer = mult;
		 width *= multiplyer;
		 length *= multiplyer;
		 height *= multiplyer;
		 dataOut = ("new dimensions, width: "+width+" length: "+length+" height: "+height);
		 return dataOut;
		 
	}
		 
		 String makeLargerAdd(double w, double l, double h)
			{
				 
				 width += w;
				 length += l;
				 height += h;
				 dataOut = ("new dimensions, width: "+width+" length: "+length+" height: "+height);
				 return dataOut;
					 
			}
		 
		 String output()
		 {
			 dataOut = ("The final dimensions are width: "+width+" length: "+length+" height: "+height);
			 JOptionPane.showMessageDialog(null, dataOut);
			 return dataOut;
		 }
		
}

