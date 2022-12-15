/**
 * Justus Stephens
 * cps 121
 * Standard Scores
 * 12/9/18
 */

package jksStandardScores;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;


public class jksStandardScores 
{

	public static void main(String[] args)
	{
		String filename="";         //Get filename
		int counter=0;              //Count number of scores
		
		filename = JOptionPane.showInputDialog("Enter the name of your file.");
		
		try
		{
			//These two lines open the file
			
			File file = new File(filename);
			Scanner inputFile = new Scanner(file);
			
			//Count number of scores in file
			
			while(inputFile.hasNext())
			{
				int check = inputFile.nextInt();                 //Reads every int
				counter = counter + 1;                           //counter plus one
			}
			
			int[] scores = new int[counter];                     //Size of array is counter
			
			//Close file
			inputFile.close();
			
	//**************************Reading all scores into our array********************
			
			File file2 = new File(filename);
			Scanner inputFile2 = new Scanner(file2);
			
			int counter2=0;                                      //To assign each array slot a variable
			
			while(inputFile2.hasNext())
			{
				int place = inputFile2.nextInt();                 //Reads every int
				scores[counter2]=place;
				counter2++;
			}
			
			//Close file
			inputFile2.close();
			
	//*************************Method time (avg, high, low, frequency)******************
			
			getAvg(scores,counter2);            //avg method
			getHigh(scores,counter2);           //high method
			getLow(scores,counter2);            //low method
			getFrequency(scores,counter2);      //Frequency method
		}
		catch(FileNotFoundException e)
		{
			//If the file can't be found
			JOptionPane.showMessageDialog(null, "The file: "+filename+", can't be found in this version of reality.");
		}
		
	}
	
	//******************************Method Avg************************************************
	
	private static void getAvg(int[] scores, int counter2)
	{
		double average=0;                  //get an average
		int num=0;						//Get number of numbers to div avg
		for(int x=0; x<counter2; x++)
			{
				average += scores[x];
				num++;
			}
		average/=num;
		JOptionPane.showMessageDialog(null, "The test average is: "+String.format("%.2f",average));
	}
	
	//******************************Method High********************************************
	
	private static void getHigh(int[] scores, int counter2)
	{
		int high=0;                  //get the high

		for(int x=0; x<counter2; x++)
			{
				if(scores[x]>high)   //if higher assign to high
				{
					high = scores[x];
				}
			}
		
		JOptionPane.showMessageDialog(null, "The highest score is: "+high);
	}
	
	//**********************************Method Low******************************************
	
	private static void getLow(int[] scores, int counter2)
	{
		int low=101;                  //get the lowest

		for(int x=0; x<counter2; x++)
			{
				if(scores[x]<low)     //if lower assign to low
				{
					low = scores[x];
				}
			}
		
		JOptionPane.showMessageDialog(null, "The lowest score is: "+low);
	}
	
	//*********************************Method Frequency***********************************

	private static void getFrequency(int[] scores, int counter2)
	{
		int[] freq = new int[51];       //Holds the frequency of each score from 0 to 50
		
		for(int c=0; c<=50; c++)         //Goes through each possible score 0-50
		{
			for(int dup=0; dup<counter2; dup++)      //Goes through entire array to check duplicate
			{
				if(c==scores[dup])               //If true then the number gets 1 added to frequency
				{
					freq[c]++;
				}
			}
		}
		
		String output="";                     //So we don't have unnecessary message boxes 0-25
		
		for(int print=0; print<=25; print++)
		{
			output = output + "Frequency of: "+print+" is:"+freq[print] +"\n";
		}
		
		JOptionPane.showMessageDialog(null, output);
		
		String output2="";                     // 26-50
		
		for(int print=26; print<=50; print++)
		{
			output2 = output2 + "Frequency of: "+print+" is:"+freq[print] +"\n";
		}
		
		JOptionPane.showMessageDialog(null, output2);	
		
	}
}
