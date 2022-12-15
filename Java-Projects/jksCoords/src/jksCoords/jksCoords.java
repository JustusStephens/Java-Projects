/**
 * Justus Stephens
 * cps 161
 * Jan 31
 * Assignment 2 Coordinates
 */
package jksCoords;

import javax.swing.JOptionPane;

public class jksCoords
{

	public static void main(String[] args)
	{
		double x1, x2, y1, y2, distance, midx, midy;
		String input;
		String output="";
		
		//Getting input //////////////////////////////////////////////////////////////
		
		input = JOptionPane.showInputDialog("Please enter your first x coordinate");
		x1 = Double.parseDouble(input);
		input = JOptionPane.showInputDialog("Please enter your first y coordinate");
		y1 = Double.parseDouble(input);
		input = JOptionPane.showInputDialog("Please enter your second x coordinate");
		x2 = Double.parseDouble(input);
		input = JOptionPane.showInputDialog("Please enter your second y coordinate");
		y2 = Double.parseDouble(input);
		
		//Doing Calculations //////////////////////////////////////////////////////////
		
		distance = Math.sqrt(((x2-x1) * (x2-x1)) + ((y2-y1) * (y2-y1)));
		
		midx = (x1+x2) / 2.0;
		
		midy = (y1+y2) / 2.0;
		
		//Decimal precision///////////////////////////////////////////////////////////////////////
		
		x1 = Math.round(x1 * 10) / 10.0;
		x2 = Math.round(x2 * 10) / 10.0;
		y1 = Math.round(y1 * 10) / 10.0;
		y2 = Math.round(y2 * 10) / 10.0;
		midx = Math.round(midx * 10) / 10.0;
		midy = Math.round(midy * 10) / 10.0;
		
		distance = Math.round(distance * 100) / 100.0;  //two decimal places
		
		//output/////////////////////////////////////////////////////////////////////////////
		
		output += "Your first point is (" + x1+","+y1+") \n";
		output += "Your second point is (" + x2+","+y2+") \n";
		output += "Your midpoint location is (" + midx+","+midy+") \n";
		output += "The distance between the points is "+distance+ "\n";
		
		JOptionPane.showMessageDialog(null,output);
	}

}
