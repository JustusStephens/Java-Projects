/**
 * Main program where calling happens
 * Justus Stephens
 * Cylinder Review
 * Jan 19. 2019
 */
package jksReviewCylinder;

import javax.swing.JOptionPane;

public class jksDefault
{

	public static void main(String[] args) 
	{
		//Assigning Variables
		double radius, height, surfArea, vol;
		String output = "";
		
		//Get me some values
		
		RandH Cylinder = new RandH (0,0);
		
		radius = Cylinder.getRadius();
		height = Cylinder.getHeight();
		surfArea = Cylinder.getSurf();
		vol = Cylinder.getVol();
		output = Cylinder.getOutput();
		
		JOptionPane.showMessageDialog(null, output);

	}

}
