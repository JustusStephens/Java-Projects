package jksReviewCylinder;

import javax.swing.JOptionPane;


public class RandH 
{
	double radius;
	double height;
	double surf;
	double vol;
	String dataOut, input;
	

	//Getting the values
	 RandH(double r, double h)
	{
		input = JOptionPane.showInputDialog("Please enter the radius of the cylinder");
		radius = Double.parseDouble(input);
			
		input = JOptionPane.showInputDialog("Please enter the height of the cylinder");
		height = Double.parseDouble(input);
			
	}
	
	double getRadius()
	{
		return radius;
	}
	
	double getHeight()
	{
		return height;
	}
	
	double getSurf()
	{
		surf = (2 * Math.PI * radius * height) + (2 * Math.PI *(radius * radius));
		surf = Math.round(surf);
		return surf;
	}
	
	double getVol()
	{
		vol = (Math.PI * (radius * radius) * height);
		vol = Math.round(vol);
		return vol;
	}
	
	String getOutput()
	{
		dataOut = ("Radius :"+radius+"\n");
		dataOut +=("Height :"+height+"\n");
		dataOut +=("Volume :"+vol+"\n");
		dataOut +=("Surface Area :"+surf+"\n");
		return dataOut;
	}
	
	
	
}

