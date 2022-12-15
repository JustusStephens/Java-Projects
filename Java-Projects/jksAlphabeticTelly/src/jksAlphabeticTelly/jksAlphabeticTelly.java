package jksAlphabeticTelly;
import javax.swing.JOptionPane;
/**
 Justus Stephens
 Alphabet coverter 9.1
 11/7/18
 cps 121
 */
public class jksAlphabeticTelly
{

	public static void main(String[] args)
	{
		String input;             //Actual tele number
		
		input = getNumber();     //Method for input
		
		noLetters(input); //get numbers for letters method
		


	}
	
	//Input method
	
	public static String getNumber()
	{
		String input;
		
		input = JOptionPane.showInputDialog("Please enter a 10 character telephone number with letters that you would like to be translated into numbers, please no dashes between numbers.");
		
		return input;
	}
	
	//Where the magic happens
	
	public static void noLetters(String input)
	{
		String change=input;
		char[] array = change.toCharArray();         //character array to store each individual character
		
		for(int count=0; count<10; count++)          //loop to check if num or letter
		{
			
			
			if(Character.isLetter(change.charAt(count)))
			{
				array[count] = changeLetter(change.charAt(count));      //if letter send to method, then assigned to array slot
			}
			
			else
			{
				array[count] = (change.charAt(count));                 //else it's assigned to array slot
			}
			
			
		}
		
		String output="";
		
		for(int print=0; print<10; print++)        //getting all numbers from array into one string, so I don't print 10 dialog boxes
			{
				output+=array[print];
			}
		
		JOptionPane.showMessageDialog(null, output);
	}
	
	//Change letter method
	public static char changeLetter(char c)
	{
		char change=c;
		
		change=Character.toLowerCase(change);
		System.out.println(change);
		
		if (change=='a' || change=='b' || change=='c')
		{
			change='2';
		}
		
		else if (change=='d' || change=='e' || change=='f')
		{
			change='3';
		}
		
		else if (change=='g' || change=='h' || change=='i')
		{
			change='4';
		}
		
		else if (change=='j' || change=='k' || change=='l')
		{
			change='5';
		}
		
		else if (change=='m' || change=='n' || change=='o')
		{
			change='6';
		}
		
		else if (change=='p' || change=='q' || change=='r'  || change=='s')
		{
			change='7';
		}
		
		else if (change=='t' || change=='u' || change=='v')
		{
			change='8';
		}
		
		else if (change=='w' || change=='x' || change=='y' || change=='z')
		{
			change='9';
		}
		
		return change;
	}
	


}
