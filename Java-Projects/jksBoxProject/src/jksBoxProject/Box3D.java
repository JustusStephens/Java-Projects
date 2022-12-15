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

public class Box3D 
{
	double length;
	double width;
	double height;
	double multiplyer;
	String dataOut;
	
	//Constructor for 3d box
	Box3D(double wid, double len, double hei)
	{
		length = len;
		width = wid;
		height = hei;
		JOptionPane.showMessageDialog(null, "The dimensions of your 3D box are length: "+length+", width: "+width+", height: "+height);
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
