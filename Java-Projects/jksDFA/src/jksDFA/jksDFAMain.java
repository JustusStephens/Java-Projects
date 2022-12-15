/***********************************************
 * jksDFA
 * Justus Stephens
 *********************************************** 
 * This program reads a file called DFA.txt, and processes its information.
 * The processed information is used to create a Deterministic Finite Automaton.
 * Then The user enters a string to be evaluated by the DFA.
 * This string is run through the DFA, and its computation can be seen in the console.
 * The process will repeat until the user enters: "quit"
***********************************************/

package jksDFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class jksDFAMain
{
  /********************************************************************************************************************************
  * jksDFAMain
  *********************************************************************************************************************************
  *Processes the file and calls input() and then calls calcuate(), passing all of the processed information: the accepting states, 
  *the alphabet, the transitions, the input, and the length of the alphabet.
  *Repeat until the user enters "quit"
  ********************************************************************************************************************************/
  public static void main(String[] args) 
  {
    System.out.println("Loading DFA.txt");
    Scanner sc = null;
    try                                               //trying to read the file
    {
      sc = new Scanner(new File("DFA.txt"));          //this works when file is in same folder in eclipse workspace
    }
    catch (FileNotFoundException e)                   //catching not found exception and exiting somewhat gracefully
    {
      System.out.println("DFA.txt could not be found, please try again");
    }
      
    sc.useDelimiter(" ");                  //sets the delimiter pattern so we chop on spaces
    String Array[];                         //array for holding the data
    Array = new String[1000];               //assuming there is no more than 1000 lines of data
    int y = 0;
    while (sc.hasNext())                   //while there is something left to scan in
    { 
      Array[y] = (sc.nextLine());
      y++;
    }   
    sc.close();                            //closes the scanner 
    
    int stateCount;
    int alphaNum = 0;
    int acceptingCount = 0;
    int[] acceptingStates = new int[100];           //assuming no more than 100 states
    String[] alphabet = new String[100];            //assuming no more than 100 characters in alphabet
    String[][] transitions = new String[100][100];  //assuming the above here
    
    String ArrayLine;                      //parse file
      for(int x=0; x<y; x++)               //while x is less than number of lines
      {
        ArrayLine = Array[x];
        String[] partsAfterSplit = ArrayLine.split( " " );
        if(x==0)      //first line
        {
          String stateNum = partsAfterSplit[0];
          stateCount = Integer.valueOf(stateNum);
        }
        else if(x==1) //second line
        {
          for(int z=0; z<partsAfterSplit.length; z++)
          {
            acceptingStates[z] = Integer.valueOf(partsAfterSplit[z]);
            acceptingCount++;
          }
        }
        else if(x==2) //third line
        {
          for(int z=0; z<partsAfterSplit.length; z++)
          {
            alphabet[z] = partsAfterSplit[z];
            alphaNum++;
          }
        }
        else if(x>=3) //fourth line through 3+stateCount
        {
          for(int z=0; z<partsAfterSplit.length; z++)
            transitions[x-3][z] = partsAfterSplit[z];
        }
      }
            
      String input = getInput();
      while(input.compareToIgnoreCase("Quit") != 0)       //run until user types "quit"
      {
        calculate(acceptingStates, alphabet, transitions, input, alphaNum, acceptingCount);
        input = getInput();
      }
      System.out.println("Goodbye");
    
  } //main
  
  /********************************************************************************************************************************
  *getInput
  *********************************************************************************************************************************
  *Prompts the user for the input String to evaluate, sends this string back to main
  ********************************************************************************************************************************/
  public static String getInput()
  {
    Scanner getInput = new Scanner (System.in);
    System.out.println(">>>>>Please enter an input string to evaluate");
    String inp = getInput.nextLine(); 
    return inp;
  }
  
  /********************************************************************************************************************************
  *calculate
  *********************************************************************************************************************************
  *First, the input string is sent to checkValidInput(), if the string is valid dfa traversal begins.
  *Current state is tracked, the current character of the string determines where to go from current state.
  *Current state is then updated to wherever the transition led to, and the next character is evaluated.
  *The process repeats until there are no characters left.
   *******************************************************************************************************************************/
  public static void calculate(int[] acceptingStates, String[] alphabet, String[][]transitions, String input, int alphaNum, int acceptingCount)
  {
    boolean validInput;
    validInput = checkValidInput(input, alphabet, alphaNum);
    if(validInput == true)            //string is valid, can be processed
    {
      String prettyInput = input;
      int countInput=0;
      int currentState = 0;
      for(int z=0; z < input.length(); z++)       //loop until process is over, process charcters left to right
      {
        System.out.print(currentState + ",");   //input needs unpdated every loop
        for(int x=countInput; x<input.length(); x++)
          System.out.print(input.charAt(x));
        for(int x=0; x<3; x++)
        {
          if(input.charAt(z) == alphabet[x].charAt(0))    //input character is equal to alphabet character
            currentState = Integer.valueOf(transitions[currentState][x]);
        }
        countInput++;
        System.out.print(" ------> " + currentState + ",");
        for(int x=countInput; x<input.length(); x++)
          System.out.print(input.charAt(x));
        if(countInput == input.length())
          System.out.print("{e}");
        System.out.println();
      }
      boolean accept = false;
      for(int a=0; a<acceptingCount; a++)
      {
        if(currentState == acceptingStates[a])
        {
          accept = true;
          System.out.println("ACCEPTED\n");
        }
      }
      if(accept == false)
        System.out.println("REJECTED\n");
    }
  }
  
  /********************************************************************************************************************************
  *checkValidInput
  *********************************************************************************************************************************
  *compares each character of the alphabet with the current character of the input
  *if the input character does not match any alphabet characters, the string is invalid
  *the process repeats through all of the characters of the input string
   *******************************************************************************************************************************/
  public static boolean checkValidInput(String input, String[] alphabet, int alphaNum)
  {
    boolean validChar = false;
    boolean validString = true;
    for(int c=0; c<input.length(); c++)
    {
      for(int x=0; x<alphaNum; x++)
      {
        if(alphabet[x].charAt(0) == input.charAt(c))
          validChar = true;
      }
      if(validChar == false)            //if one character is invalid, whole string is invalid
      {
        if(validString == true)
        {
          validString = false;
          System.out.println("Rejected \nInvalid character: "+ input.charAt(c) + ", in " + input);
        }
      }
      validChar = false;
    }
    return validString;
  }

} //class
