/***********************************************
 * jksTuringMachine
 * Justus Stephens
 *********************************************** 
 * This program reads a file called TM.txt, and processes its information.
 * The processed information is used to create a Turing Machine.
 * Then, the user enters a string to be evaluated by the Turing Machine.
 * This string is run through the TM, and its computation can be seen in the console.
 * The process will repeat until the user enters: "quit"
***********************************************/

package jksTuring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class jksTuringMain 
{

  public static void main(String[] args) 
  {
    System.out.println("Loading TM.txt");
    Scanner sc = null;
    try                                               //trying to read the file
    {
      sc = new Scanner(new File("TM.txt"));          //this works when file is in same folder in eclipse workspace
    }
    catch (FileNotFoundException e)                   //catching not found exception and exiting somewhat gracefully
    {
      System.out.println("TM.txt could not be found, please try again");
    }
      
    sc.useDelimiter(" ");                  //sets the delimiter pattern so we chop on spaces
    String Array[];                         //array for holding the data
    Array = new String[1000];               //assuming there is no more than 1000 lines of data
    int y = 0;
    while (sc.hasNext())                   //while there is something left to scan in
    { 
      Array[y] = (sc.nextLine());
      //System.out.println(Array[y]);
      y++;
    }   
    sc.close();                            //closes the scanner 
    
    int stateCount;
    int acceptingCount = 0;
    int transCount = 0;
    int[] acceptingStates = new int[1000];           //assuming no more than 1000 states
    String[][] transitions = new String[1000][1000];  //assuming the above here
    String[] alphabet = new String[100];              //assuming < 100 characters are in alphabet
//    alphabet[0] = "0";
//    alphabet[1] = "1";
//    alphabet[2] = "_";
//    int alphaNum = 3;
    
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
      else if(x>=2) //third line till end of file
      {
          for(int z=0; z<partsAfterSplit.length; z++)
            transitions[x-2][z] = partsAfterSplit[z];
          transCount++;
      }  
    }
      String input = getInput();
      while(input.compareToIgnoreCase("Quit") != 0)       //run until user types "quit"
      {
        calculate(acceptingStates, alphabet, transitions, input, acceptingCount, transCount);
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
   System.out.println(">>>>>Please enter an input string to evaluate, or type quit to exit");
   String inp = getInput.nextLine(); 
   return inp;
 }
 /********************************************************************************************************************************
  *calculate
  *********************************************************************************************************************************
  *First, the input string is sent to checkValidInput(), if the string is valid turing machine begins.
  *Current state is tracked, the current character of the string determines where to go from current state.
  *Current state is then updated to wherever the transition leads to (L/R), and the next character is evaluated.
  *The process repeats until halt, or length of repetitions > 1000.
   *******************************************************************************************************************************/
  public static void calculate(int[] acceptingStates, String[] alphabet, String[][]transitions, String input, int acceptingCount, int transCount)
  {
    boolean validInput;
    validInput = checkValidInput(input, alphabet);
    if(validInput == true)            //string is valid, can be processed
    {
      String[] inp = new String[input.length()]; 
      int currentChar = 0;
      int currentState = 0;
      boolean halt = false;
      for(int copy=0; copy<inp.length; copy++)
        inp[copy] = String.valueOf(input.charAt(copy));
      for(int z=0; z<1000; z++)       //loop until process is over, process charcters left to right
      {
        System.out.print(currentState + ": ");   //input needs unpdated every loop
        for(int p=0; p<inp.length; p++)
        {
          if(p==currentChar)
            System.out.print("[" + inp[currentChar] + "]");
          else
            System.out.print(inp[p]);
        }
        if(halt==true)
          System.out.print("        Halt");
        System.out.println();
        
        if(halt==false)
        for(int x=0; x<transCount; x++)
        {
          if(Integer.valueOf(transitions[x][0]) == currentState)  
          {
            if(transitions[x][1].equals(inp[currentChar]))              //current character matches
            {
              inp[currentChar] = transitions[x][2];                                    //write new input
              if(transitions[x][3].equals("R"))                                            //move right
                currentChar++;
              else if(transitions[x][3].equals("L"))                                       //move left
                currentChar--;
              else
                System.out.println("Error trying to scroll");
              currentState = Integer.valueOf(transitions[x][4]);
              x=transCount;
            }
          }
          else if (x==transCount-1)
          {
            halt = true;
            z=1001;
            System.out.print("        Halt");
            System.out.println();
          }
          if(halt==false)
            for(int a=0; a<acceptingCount; a++)
            {
              if(currentState == acceptingStates[a])
                halt = true;
            }
        }
        else
          z=1001;
      }
    }
  }
  
  /********************************************************************************************************************************
   *checkValidInput
   *********************************************************************************************************************************
   *compares each character of the alphabet with the current character of the input
   *if the input character does not match any alphabet characters, the string is invalid
   *the process repeats through all of the characters of the input string
   *input must begin an end with an underscore
    *******************************************************************************************************************************/
   public static boolean checkValidInput(String input, String[] alphabet)
   {
     boolean validString = true;
     
     if(input.charAt(0) != '_' || input.charAt(input.length()-1) != '_')
     {
       System.out.println("Invalid input, need underscore at begginging and end");
       validString = false;
     }

     return validString;
   }

}
