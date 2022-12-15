/**
 * Justus Stephens
 * CPS 161
 * Review Arrays
 * Jan 19. 2019
 */

package jksReviewArray;

import javax.swing.JOptionPane;

public class jksReviewArray
{

	public static void main(String[] args)
	{
		double [] sales = new double[10];
		int index;
		double largestSale, smallestSale, sum, avg;
		String input="";               //to hold input
		
		 //initialization loop//////////////////////////////////////////////////////////
		
		for(int i=0; i<10; i++)          
		{
			sales[i]=0;
		}
		
		//input data into array loop////////////////////////////////////////////////////
		
		for(int in=0; in<10; in++)          
		{
			input = JOptionPane.showInputDialog("Please enter sale # "+(in+1)+":");
			sales[in] = Double.parseDouble(input);
			
		//	JOptionPane.showMessageDialog(null, sales[in]);
		}
		
		//Printing in reverse////////////////////////////////////////////////////////////
		
		String output="";    //So we don't have 10 message boxes
		
		for(int p=9; p>=0; p--)          
		{
			output += ("Sale number "+(p+1)+": "+String.format("$%,.2f",sales[p])+"\n");
		}
		
		JOptionPane.showMessageDialog(null, output);  //Outputs all 10 lines in 1 box
		
		//Finding sum and average/////////////////////////////////////////////////////////
		
		sum = 0;
		avg = 0;
		
		for(int c=0; c<10; c++)
		{
			sum += sales[c];
		}
		avg = sum/10;
		
		JOptionPane.showMessageDialog(null, "Total sales are: "+String.format("$%,.2f",sum));
		JOptionPane.showMessageDialog(null, "The average sale is: "+String.format("$%,.2f",avg));
		
		//Finding smallest and largest sale////////////////////////////////////////////////
		
		largestSale=0;
		smallestSale=sales[0];
		
		for(int f=0; f<10; f++)
		{
			if(sales[f] > largestSale)
			{
				largestSale = sales[f];
			}
			
			if(sales[f] < smallestSale)
			{
				smallestSale = sales[f];
			}
		}
		
		JOptionPane.showMessageDialog(null, "Largest sale: "+String.format("$%,.2f",largestSale));
		JOptionPane.showMessageDialog(null, "Smallest sale: "+String.format("$%,.2f",smallestSale));
	}

}
