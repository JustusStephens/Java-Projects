/**
 * Justus Stephens
 * CPS 161
 * Temperature Assignment 1
 * Jan 21, 2019
 */
package jksTemperatuure;

import javax.swing.JOptionPane;
public class jskTemperature
{

	public static void main(String[] args) 
	{
		double temp = 0.0;         //For the actual number in F
		double temp2 = 0.0;        //To hold C
		String in;               //For getting input
		
		in = JOptionPane.showInputDialog("Please enter the temperature in Fahrenheit.");
		
		temp = Double.parseDouble(in);
		
		temp2 = (temp - 32.0) * (5.0/9.0);          // equation
		
		temp = Math.round(temp * 100) / 100.0;       //Rounding to one decimal place
		temp2 = Math.round(temp2 * 10) / 10.0;
		
		JOptionPane.showMessageDialog(null, temp+" degrees Fahrenheit is "+temp2+" degrees Celsius");
	}

}
