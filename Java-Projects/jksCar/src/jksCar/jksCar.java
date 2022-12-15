//Justus Stephens
//CPS 230
//May 10, 2020
//Buying a Car


package jksCar;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class jksCar
{
	public static void main(String[] args)
	{
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", Color.lightGray);		//color
		UI.put("Panel.background", Color.MAGENTA);			//color

		
		
		double prin;		//principal
		double air;  		//annual interest rate
		int years;			//years of loan
		int pmnt;			//payments per year
		
		prin = inputPr();	//getting inputs
		air = inputA();
		years = inputY();
		pmnt = inputP();
		
		double amortized;
		amortized = amor(prin, air, years, pmnt);
		//amortized = Math.round(amortized*100.0)/100.0;  //if I round the amortized then the calculations will be off by the last payment
		
		int paymentNum = 1;
		String header;
		
		header = header();
		table(prin, air, years, pmnt, paymentNum, amortized, header);
					
	}
	
	public static double inputPr()		//input principal
	{
		double pr;
		String input;
		input = JOptionPane.showInputDialog("Please enter the Principal of the car payment \n (For example: 30,000 = 30000)");
		pr = Double.parseDouble(input);
		return pr;
		
	}
	
	public static double inputA()		//input annual interest rate
	{
		double a;
		String input;
		input = JOptionPane.showInputDialog("Please enter the Annual Interest Rate of the car payment \n (For example: 8.5% = 0.085)");
		a = Double.parseDouble(input);
		return a;
		
	}
	
	public static int inputY()				//input years
	{
		int y;
		String input;
		input = JOptionPane.showInputDialog("Please enter the amount of Years on the loan");
		y = Integer.parseInt(input);
		return y;
	}
	
	public static int inputP()				//input payments per year
	{
		int p;
		String input;
		input = JOptionPane.showInputDialog("Please enter the amount of Payments per Year");
		p = Integer.parseInt(input);
		return p;
	}
	
	public static double amor(double prin, double air, int years, int pmnt)
	{
		double IN1 = 1+(air/pmnt);		//(1+i)
		double n = pmnt*years;			//total number of payments n
		double IN2 = IN1;				//to hold (1+i) for raising it to the nth power
		
		//System.out.println(IN1);
		//System.out.println(n);
		
		while(n > 1)			//calculates (1+i)^n
		{
			IN1 *= IN2;
			n = n-1;
			//System.out.println(IN1 + "  n:" + n);
		}
		
		return (prin * (air/pmnt) * IN1) / (IN1 - 1);
	}
	
	
	public static String header()
	{
		String header = ("                                                 Auto Loan Payment Table \n"
				+ "Payment Number               Interest Due              Toward Principal                 Remaining balance \n");
		return header;
				
	}
	
	public static void table(double prin, double air, int years, int pmnt, int paymentNum, double amortized, String header)
	{
		String [] output = new String[((years*pmnt)/24)];
		double interest = air*prin;							//interest
		double yearlyInterest = interest/pmnt;				//breaks interest up into amount of payments per year
		int yearCount=1;
		double totalInterest = yearlyInterest;

		for(int pgNum = 0; pgNum < ((years*pmnt)/24); pgNum++)	//number of pages
		{
			output[pgNum] = header;			//header on every page
			
			for(int page=0; page<24; page++)					//counts rows in a page
			{
				prin = prin-(amortized-yearlyInterest);
				
				if(paymentNum<10)
					output[pgNum] += ("            " + paymentNum + " 		                       	             " +  String.format("$%,.2f",yearlyInterest)  + "                           " +  String.format("$%,.2f",(amortized-yearlyInterest)) + "                              " +  String.format("$%,.2f", prin) +"\n" );
				else
					output[pgNum] += ("           " + paymentNum + " 		                    	               " +  String.format("$%,.2f",yearlyInterest)  + "                           " +  String.format("$%,.2f",(amortized-yearlyInterest)) + "                              " +  String.format("$%,.2f", prin) +"\n" );
				
					paymentNum++;
					yearCount++;
					yearlyInterest = (air*prin)/pmnt;
					totalInterest += yearlyInterest;
			}
			
			output[pgNum] += ("\n 	                                      			                  Total Interest: " + String.format("$%,.2f",totalInterest));
		}
		
		for(int print=0; print< ((years*pmnt)/24); print++)
		{
			JOptionPane.showMessageDialog(null, output[print]);
		}

	}

}
