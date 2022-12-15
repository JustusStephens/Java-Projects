package jksException;

import javax.swing.JOptionPane;

//All of the exceptions are in the set methods

public class Payroll
{
   private String name;          // Employee name
   private int idNumber;         // ID number
   private double payRate;       // Hourly pay rate
   private double hoursWorked;   // Number of hours worked

   /**
   	The constructor initializes an object with the
		employee's name and ID number.
		@param n The employee's name.
		@param i The employee's ID number.
   */

   public Payroll(String n, int i)
   {
      name = n;
      idNumber = i;
   }

   /**
   	The setName sets the employee's name.
		@param n The employee's name.
   */

   public void setName(String n)
   {
	  n = JOptionPane.showInputDialog("Please enter the employee's name: \n");
	  
	  if(n == null || n.isEmpty())			//checking to see if n is null, if so exception is thrown
	  {
		  try
		  {
			  throw new Exception("The String for variable name is empty");
		  }
		  
		  catch (Exception e)
		  {
			  e.printStackTrace();		
			  System.exit(2);				
		  }
	  }
	  
	  else					//if n is not null, we march forward
		  name = n;
   }

   /**
   	The setIdNumber sets the employee's ID number.
		@param i The employee's ID number.
   */
	
   public void setIdNumber(int i)
   {
	  String s = JOptionPane.showInputDialog("Please enter the employee's id number: \n");
	  i = Integer.parseInt(s);
	  
	  if(i<=0)			//checking to see if i is an invalid integer, if so exception is thrown
	  {
		  try
		  {
			  throw new Exception("Negative number or Zero, invalid for idNumber");
		  }
		  
		  catch (Exception e)
		  {
			  e.printStackTrace();		
			  System.exit(2);				
		  }
	  }
	  
	  else
		  idNumber = i;
   }

   /**
   	The setPayRate sets the employee's pay rate.
		@param p The employee's pay rate.
   */
	
   public void setPayRate(double p)
   {
	  String s = JOptionPane.showInputDialog("Please enter the pay rate: \n");
	  p = Double.parseDouble(s);
	  
	  if(p<=0 || p>25)			//checking to see if p is an invalid number, if so exception is thrown
	  {
		  try
		  {
			  throw new Exception("Pay Rate cannot be negative or greater than 25");
		  }
		  
		  catch (Exception e)
		  {
			  e.printStackTrace();		
			  System.exit(2);				
		  }
	  }
	  
	  else
		  payRate = p;
   }

   /**
   	The setHoursWorked sets the number of hours worked.
		@param h The number of hours worked.
   */

   public void setHoursWorked(double h)
   {
	   String s = JOptionPane.showInputDialog("Please enter the hours worked: \n");
	   h = Double.parseDouble(s);
	   
	   if(h<=0 || h>84)			//checking to see if h is an invalid number, if so exception is thrown
		  {
			  try
			  {
				  throw new Exception("Hours worked cannot be negative or greater than 84");
			  }
			  
			  catch (Exception e)
			  {
				  e.printStackTrace();		
				  System.exit(2);				
			  }
		  }
		  
		  else
			 hoursWorked = h;
   }

   /**
   	The getName returns the employee's name.
		@return The employee's name.
   */

   public String getName()
   {
      return name;
   }

   /**
   	The getIdNumber returns the employee's ID number.
		@return The employee's ID number.
   */
	
   public int getIdNumber()
   {
      return idNumber;
   }

   /**
   	The getPayRate returns the employee's pay rate.
		@return The employee's pay rate.
   */

   public double getPayRate()
   {
      return payRate;
   }

   /**
   	The getHoursWorked returns the hours worked by the
		employee.
		@return The hours worked.
   */


   public double getHoursWorked()
   {
      return hoursWorked;
   }

   /**
   	The getGrossPay returns the employee's gross pay.
		@return The employee's gross pay.
   */

   public double getGrossPay()
   {
      return hoursWorked * payRate;
   }
}