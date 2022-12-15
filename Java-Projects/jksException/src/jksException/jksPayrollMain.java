//Justus Stephens
//CPS 230
//Exceptions
//April 12, 2020


package jksException;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.UIManager;

public class jksPayrollMain 
{
	private static  DecimalFormat df2 = new DecimalFormat("#.##");  //for decimal places
	
	public static void main(String[] args)
	{
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.BLACK);		//color
		UI.put("Panel.background", Color.ORANGE);			//color
		
		String name = null;
		int idNumber = 0;
		double payRate = 0;
		double hoursWorked = 0;
		
		Payroll obj = new Payroll(name, idNumber);
		
		obj.setName(name);
		obj.setIdNumber(idNumber);
		obj.setPayRate(payRate);
		obj.setHoursWorked(hoursWorked);
		
		name = obj.getName();
		idNumber = obj.getIdNumber();
		payRate = obj.getPayRate();
		hoursWorked = obj.getHoursWorked();
		
		double gross = obj.getGrossPay();
		
		JOptionPane.showMessageDialog(null, "Name : " + name + "\n" +
				"Id Number : " + idNumber + "\n" +
				"Pay Rate : $" + payRate + "\n" +
				"Hours Worked : " + hoursWorked + "\n" +
				"Gross Pay : $" + df2.format(gross) + "\n");
	}

}
