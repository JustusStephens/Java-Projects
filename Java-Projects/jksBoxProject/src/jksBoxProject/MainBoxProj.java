/**
 * Main program where everything is called
 * and where user is prompted
 * 
 * Justus Stephens
 * cps 121
 */

package jksBoxProject;

import javax.swing.JOptionPane;

public class MainBoxProj 
{

	public static void main(String[] args) 
	{
		//Assigning variables
		double volume;
		double surfArea;
		String input;
		int num;
		int larger;
		double change, c1, c2, c3;
		
		//Assigning user a box
		input = JOptionPane.showInputDialog("Pick a number: 1, 2, or 3 to be given a box");
		num=Integer.valueOf(input);
		
		//First box
		if(num == 1)
		{
			//First box
			Box firstBox = new Box(3, 2, 1);
			volume = firstBox.volume();
			surfArea = firstBox.surfaceArea();
			JOptionPane.showMessageDialog(null, "your box volume is "+volume+" and Surface area is "+surfArea);
			input = JOptionPane.showInputDialog("Make your box larger by multiplying? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			//Multiply if 1
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("How many times larger?");
				change = Double.valueOf(input);
				firstBox.makeLargerMult(change);
			}

			input = JOptionPane.showInputDialog("Make your box larger by adding to each side? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			//Add individual if 1
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("Add what to width?");
				c1 = Double.valueOf(input);
				
				input=JOptionPane.showInputDialog("Add what to length?");
				c2 = Double.valueOf(input);
				
				input=JOptionPane.showInputDialog("Add what to height?");
				c3 = Double.valueOf(input);
				
				firstBox.makeLargerAdd(c1, c2, c3);
			}
			
			firstBox.output();
		}
		
		//Cube
		if(num == 2)
		{	
			//Second box
			Cube secondBox = new Cube(1);
			volume = secondBox.volume();
			surfArea = secondBox.surfaceArea();
			
			JOptionPane.showMessageDialog(null, "your box volume is "+volume+" and Surface area is "+surfArea);
			
			input = JOptionPane.showInputDialog("Make your box larger by multiplying? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("How many times larger?");
				change = Double.valueOf(input);
				secondBox.makeLargerMult(change);
			}

			input = JOptionPane.showInputDialog("Make your box larger by adding to each side? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			//Add individual if 1
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("Add what to dimension?");
				c1 = Double.valueOf(input);
				
				secondBox.makeLargerAdd(c1);
			}
			
			secondBox.output();
		}
		
		//3d rectangle solid
		if(num==3)
		{
			//Third box
			Box3D thirdBox = new Box3D(5,3,1);
			volume = thirdBox.volume();
			surfArea = thirdBox.surfaceArea();
			
			JOptionPane.showMessageDialog(null, "your box volume is "+volume+" and Surface area is "+surfArea);
			
			input = JOptionPane.showInputDialog("Make your box larger by multiplying? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			//Multiply everything if 1
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("How many times larger?");
				change = Double.valueOf(input);
				thirdBox.makeLargerMult(change);
			}
			
			input = JOptionPane.showInputDialog("Make your box larger by adding to each side? Type 1 for yes or 2 for no");
			larger = Integer.valueOf(input);
			
			//Add individual if 1
			if(larger==1)
			{
				input=JOptionPane.showInputDialog("Add what to width?");
				c1 = Double.valueOf(input);
				
				input=JOptionPane.showInputDialog("Add what to length?");
				c2 = Double.valueOf(input);
				
				input=JOptionPane.showInputDialog("Add what to height?");
				c3 = Double.valueOf(input);
				
				thirdBox.makeLargerAdd(c1, c2, c3);
			}
			
			thirdBox.output();
			
		}
		
		
	}

}
