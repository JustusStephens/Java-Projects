import java.util.Random;                //Random class
import javax.swing.JOptionPane;         //GUI class
/**
 Justus Stephens
 Random Number Guessing Game
 10/21/2018
 */
public class RandomNumGuess
{

	public static void main(String[] args)
	{
		Random randomNumbers = new Random();          //making me a random
		int count = 0;                                //guess counter
		int answer;                                   //the actual number
		int userGuess = 0;                            //user guess
		String input;                                 //to get input
		
		JOptionPane.showMessageDialog(null, "Welcome to the Random Number Guesser 9000. \n Try to guess the number between 1 and 1024.");
		
		answer= randomNumbers.nextInt(1024+1);        //Set random values from 1 to 1024
		
		
		while(answer!=userGuess)                      //Loop till answer is guessed
		{
			input=JOptionPane.showInputDialog("Guess the number");
			userGuess=Integer.valueOf(input);
			
			count++;
			
			if(userGuess<answer)                                             //too low
			{
				JOptionPane.showMessageDialog(null, "Too low try again");
			}
			
			if(userGuess>answer)                                             //too high
			{
				JOptionPane.showMessageDialog(null, "Too high try again");
			}
			
		}
		
		JOptionPane.showMessageDialog(null, "Congradulations, you guessed the number in "+count+" guesses.");

	}

}
