package jksMiniMax;
//Justus Stephens
//CSC 462
//October 15, 2021

import java.util.Arrays;
import java.util.Scanner;

public class jksMiniMaxMain
{
  private static boolean win;

  public static void main(String[] args) 
  {
   int[] board = new int[9];             //the tic tac toe board, 0==O, 1==X
   Arrays.fill(board, 3);                //fill board with irrelevant value
   int turn = 1;
   int input = getInput();
   
   if(input == 1)                        //both experts, should always tie
   {
     for(int x=1; x<=9 & win==false; x++)
     {
       if(x%2 == 0)
         System.out.print("Minimax taken by Player 2 (X), ");
       else
         System.out.print("Minimax taken by Player 1 (O), ");
       turn = minimax(board,turn);
     }
   }
   
   else if(input==2)                         //player 1 is expert, X never wins
   {
     for(int x=1; x<=9 & win==false; x++)
     {
       if(x%2 == 0)                           //player 2's turns
       {
         double random = Math.random() * 100;
         if(random<50)
         {
           System.out.print("Minimax taken by Player 2 (X), ");
           turn = minimax(board,turn);
         }
         else
         {
           System.out.print("Random taken by Player 2 (X), ");
           turn = randomMove(board,turn);
         }
       }
       else //player 1's turns
       {
         System.out.print("Minimax taken by Player 1 (O), ");
         turn = minimax(board,turn);
       }
     }
   }
   
   else if(input==3)                        //player 2 is expert, O never wins
   {
     for(int x=1; x<=9 & win==false; x++)
     {
       if(x%2 == 1)                           //player 1's turns
       {
         double random = Math.random() * 100;
         if(random<50)
         {
           System.out.print("Minimax taken by Player 1 (O), ");
           turn = minimax(board,turn);
         }
         else
         {
           System.out.print("Random taken by PLayer 1 (O), ");
           turn = randomMove(board,turn);
         }
       }
       else                                   //player 2's turns
       {
         System.out.print("Minimax taken by Player 2 (X), ");
         turn = minimax(board,turn);
       }
     }
   }
   
   else                                     //both have 50% chance minimax
   {
     for(int x=1; x<=9 & win==false; x++)
     {
       double random = Math.random() * 100;
       if(random<50)
        {
         if(x%2 == 0)
           System.out.print("Minimax taken by Player 2 (X), ");
         else
           System.out.print("Minimax taken by Player 1 (O), ");
         turn = minimax(board,turn);
        }
        else
        {
          if(x%2 == 0)
            System.out.print("Random taken by Player 2 (X), ");
          else
            System.out.print("Random taken by Player 1 (O), ");
          turn = randomMove(board,turn);
        }
     }
  }
}           //end main
  
  static int getInput()                           //method for getting input
  {
    Scanner getInput = new Scanner (System.in);
    System.out.println("Enter 1 for both players as experts\n"
        + "Enter 2 for player 1 (O) as an expert and player 2 (X) at 50% Minimax\n"
        + "Enter 3 for player 2 (X) at an expert and player 1 (O) at 50% Minimax\n"
        + "Enter 4 for both players at 50% Minimax");
    String inp = getInput.nextLine();
    int x = Integer.valueOf(inp); 
    return x;
  }
  
  static int minimax(int[] board,int turn)      //minimax method
  {
    if(turn==1)                           //best move is to take middle
    {
      board[4] = 0;
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==2)                           //if middle not taken, take it. Otherwise take corner
    {
      if(board[4] == 3)
        board[4] = 1;
      else                                //taking top left corner if middle is already taken
        board[0] = 1;
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==3)                      //do not go in opposing corner, need to take next to the taken corner to force blocks
    {
      if(board[4] == 3)                                               //middle not taken, then take
        board[4] = 0;
      else if(board[0]==1 || board[2]==1 || board[6]==1 || board[8]==1)    //opponent took corner, take next to it
      {
        if(board[0]==1)
          board[3] = 0;
        else if(board[2]==1)
          board[5] = 0;
        else if(board[6]==1)
          board[3] = 0;
        else if(board[8]==1)
          board[7] = 0;
      }
      else if(board[1]==1 || board[3]==1 || board[5]==1 || board[7]==1)  //player 1 is in middle and player 2 took a side, player 1 can take a corner and win
      {
        if(board[1]==1)
          board[2] = 0;
        else if(board[3]==1)
          board[0] = 0;
        else if(board[5]==1)
          board[1] = 0;
        else if(board[7]==1)
          board[3] = 0;
      }
      
      else if(board[0]==0 || board[2]==0 || board[6]==0 || board[8]==0) //player 1 is in corner, player 2 is in middle, force block
      {
        if(board[0]==0)
          board[6] = 0;
        else if(board[2]==0)
          board[8] = 0;
        else if(board[6]==0)
          board[0] = 0;
        else if(board[8]==0)
          board[2] = 0;
      }
      else                          //not sure if this is needed, but just incase
      {
        if(board[1]==0)
          board[2] = 0;
        else if(board[3]==0)
          board[0] = 0;
        else if(board[5]==0)
          board[1] = 0;
        else if(board[7]==0)
          board[3] = 0;
      }
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==4)  //check for wins, then blocks, then preventing inevitable losses, if none of those make logical move
    {                             
      
      if(board[4]==1 & board[0]==1 & board[8]==3)          //all possible wins
      {
        board[8]=1;
        win = true;
      }
      else if(board[4]==1 & board[1]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[4]==1 & board[2]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[4]==1 & board[3]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[4]==1 & board[5]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[4]==1 & board[6]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[4]==1 & board[7]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[4]==1 & board[8]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[0]==1 & board[1]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[0]==1 & board[2]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[0]==1 & board[3]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[0]==1 & board[6]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[2]==1 & board[1]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[2]==1 & board[0]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[2]==1 & board[5]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[2]==1 & board[8]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[6]==1 & board[3]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[6]==1 & board[7]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[6]==1 & board[8]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[7]==1 & board[8]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      
      else if(board[4]==0 & board[0]==0 & board[8]==3)          //all possible blocks
        board[8]=1;
      else if(board[4]==0 & board[1]==0 & board[7]==3)
        board[7]=1;
      else if(board[4]==0 & board[2]==0 & board[6]==3)
        board[6]=1;
      else if(board[4]==0 & board[3]==0 & board[5]==3)
        board[5]=1;
      else if(board[4]==0 & board[5]==0 & board[3]==3)
        board[3]=1;
      else if(board[4]==0 & board[6]==0 & board[2]==3)
        board[2]=1;
      else if(board[4]==0 & board[7]==0 & board[1]==3)
        board[1]=1;
      else if(board[4]==0 & board[8]==0 & board[0]==3)
        board[0]=1;
      else if(board[0]==0 & board[1]==0 & board[2]==3)
        board[2]=1;
      else if(board[0]==0 & board[2]==0 & board[1]==3)
        board[1]=1;
      else if(board[0]==0 & board[3]==0 & board[6]==3)
        board[6]=1;
      else if(board[0]==0 & board[6]==0 & board[3]==3)
        board[3]=1;
      else if(board[2]==0 & board[1]==0 & board[0]==3)
        board[0]=1;
      else if(board[2]==0 & board[0]==0 & board[1]==3)
        board[1]=1;
      else if(board[2]==0 & board[5]==0 & board[8]==3)
        board[8]=1;
      else if(board[2]==0 & board[8]==0 & board[5]==3)
        board[5]=1;
      else if(board[6]==0 & board[3]==0 & board[0]==3)
        board[0]=1;
      else if(board[6]==0 & board[7]==0 & board[8]==3)
        board[8]=1;
      else if(board[6]==0 & board[8]==0 & board[7]==3)
        board[7]=1;
      else if(board[7]==0 & board[8]==0 & board[6]==3)
        board[6]=1;
      
      else if(board[1]==0 & board[5]==0 & board[2]==3)             //block possible future wins
        board[2]=1;
      else if(board[7]==0 & board[5]==0 & board[8]==3)             
        board[8]=1;
      else if(board[7]==0 & board[3]==0 & board[6]==3)             
        board[6]=1;
      else if(board[1]==0 & board[3]==0 & board[0]==3)             
        board[0]=1;
      else if(board[1]==0 & board[6]==0 & board[5]==3)             
        board[5]=1;
      else if(board[1]==0 & board[8]==0 & board[3]==3)             
        board[3]=1;
      else if(board[7]==0 & board[0]==0 & board[5]==3)             
        board[5]=1;
      else if(board[7]==0 & board[2]==0 & board[3]==3)             
        board[3]=1;
      else if(board[2]==0 & board[3]==0 & board[0]==3)             
        board[0]=1;
      else if(board[8]==0 & board[3]==0 & board[6]==3)             
        board[6]=1;
      else if(board[5]==0 & board[6]==0 & board[0]==3)             
        board[0]=1;
      else if(board[0]==0 & board[5]==0 & board[2]==3)        
        board[2]=1;
      
      else    //no win or no block found
      {
        if(board[4]==3)     //take middle
          board[4] = 1;
        else                //else take a side
        {
            if(board[1] == 3)
              board[1] = 1;
            else if(board[3] == 3)
              board[3] = 1;
            else if(board[5] == 3)
              board[5] = 1;
            else if(board[7] == 3)
              board[7] = 1;
        }
      }
      
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==5)
    {      
      if(board[4]==0 & board[0]==0 & board[8]==3)          //all possible wins
      {
        board[8]=0;
        win = true;
      }
      else if(board[4]==0 & board[1]==0 & board[7]==3)
      {
        board[7]=0;
        win = true;
      }
      else if(board[4]==0 & board[2]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      else if(board[4]==0 & board[3]==0 & board[5]==3)
      {
        board[5]=0;
        win = true;
      }
      else if(board[4]==0 & board[5]==0 & board[3]==3)
      {
        board[3]=0;
        win = true;
      }
      else if(board[4]==0 & board[6]==0 & board[2]==3)
      {
        board[2]=0;
        win = true;
      }
      else if(board[4]==0 & board[7]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[4]==0 & board[8]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[0]==0 & board[1]==0 & board[2]==3)
      {
        board[2]=0;
        win = true;
      }
      else if(board[0]==0 & board[2]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[0]==0 & board[3]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      else if(board[0]==0 & board[6]==0 & board[3]==3)
      {
        board[3]=0;
        win = true;
      }
      else if(board[2]==0 & board[1]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[2]==0 & board[0]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[2]==0 & board[5]==0 & board[8]==3)
      {
        board[8]=0;
        win = true;
      }
      else if(board[2]==0 & board[8]==0 & board[5]==3)
      {
        board[5]=0;
        win = true;
      }
      else if(board[6]==0 & board[3]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[6]==0 & board[7]==0 & board[8]==3)
      {
        board[8]=0;
        win = true;
      }
      else if(board[6]==0 & board[8]==0 & board[7]==3)
      {
        board[7]=0;
        win = true;
      }
      else if(board[7]==0 & board[8]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      
      else if(board[4]==1 & board[0]==1 & board[8]==3)          //all possible blocks
        board[8]=0;
      else if(board[4]==1 & board[1]==1 & board[7]==3)
        board[7]=0;
      else if(board[4]==1 & board[2]==1 & board[6]==3)
        board[6]=0;
      else if(board[4]==1 & board[3]==1 & board[5]==3)
        board[5]=0;
      else if(board[4]==1 & board[5]==1 & board[3]==3)
        board[3]=0;
      else if(board[4]==1 & board[6]==1 & board[2]==3)
        board[2]=0;
      else if(board[4]==1 & board[7]==1 & board[1]==3)
        board[1]=0;
      else if(board[4]==1 & board[8]==1 & board[0]==3)
        board[0]=0;
      else if(board[0]==1 & board[1]==1 & board[2]==3)
        board[2]=0;
      else if(board[0]==1 & board[2]==1 & board[1]==3)
        board[1]=0;
      else if(board[0]==1 & board[3]==1 & board[6]==3)
        board[6]=0;
      else if(board[0]==1 & board[6]==1 & board[3]==3)
        board[3]=0;
      else if(board[2]==1 & board[1]==1 & board[0]==3)
        board[0]=0;
      else if(board[2]==1 & board[0]==1 & board[1]==3)
        board[1]=0;
      else if(board[2]==1 & board[5]==1 & board[8]==3)
        board[8]=0;
      else if(board[2]==1 & board[8]==1 & board[5]==3)
        board[5]=0;
      else if(board[6]==1 & board[3]==1 & board[0]==3)
        board[0]=0;
      else if(board[6]==1 & board[7]==1 & board[8]==3)
        board[8]=0;
      else if(board[6]==1 & board[8]==1 & board[7]==3)
        board[7]=0;
      else if(board[7]==1 & board[8]==1 & board[6]==3)
        board[6]=0;
      
      
      else if(board[0]==1 & board[5]==1 & board[4]==0 & board[3]==0)                    //block possibility of losing
        board[2]=0;
      else if(board[6]==1 & board[5]==1 & board[4]==0 & board[3]==0)                   
        board[8]=0;
      else if(board[8]==1 & board[3]==1 & board[4]==0 & board[5]==0)                    
        board[6]=0;
      else if(board[2]==1 & board[3]==1 & board[4]==0 & board[5]==0)                    
        board[2]=0;
      
      
      else    //no win or no block found
      {
        if(board[4]==3)     //take middle
          board[4] = 0;
        else                //else take a corner
        {
          double random = Math.random() * 4;      //so games can differ when both expert
          if(random<1)
          {
            if(board[0] == 3)
              board[0] = 0;
            else if(board[2] == 3)
              board[2] = 0;
            else if(board[6] == 3)
              board[6] = 0;
            else if(board[8] == 3)
              board[8] = 0;
          }
          
          else if(random>=1 & random <2)
          {
            if(board[2] == 3)
              board[2] = 0;
            else if(board[0] == 3)
              board[0] = 0;
            else if(board[6] == 3)
              board[6] = 0;
            else if(board[8] == 3)
              board[8] = 0;
          }
          
          else if(random<=2 & random<3)
          {
            if(board[6] == 3)
              board[6] = 0;
            else if(board[2] == 3)
              board[2] = 0;
            else if(board[0] == 3)
              board[0] = 0;
            else if(board[8] == 3)
              board[8] = 0;
          }
          
          else
          {
            if(board[8] == 3)
              board[8] = 0;
            else if(board[2] == 3)
              board[2] = 0;
            else if(board[6] == 3)
              board[6] = 0;
            else if(board[0] == 3)
              board[0] = 0;
          }
        }
      }
      
      turn++;
      print(board, turn);
      return turn;
    }
    
    if(turn==6)
    {
     // boolean win = false;                                  //dont keep playing if win is found
      
      if(board[4]==1 & board[0]==1 & board[8]==3)          //all possible wins
      {
        board[8]=1;
        win = true;
      }
      else if(board[4]==1 & board[1]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[4]==1 & board[2]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[4]==1 & board[3]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[4]==1 & board[5]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[4]==1 & board[6]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[4]==1 & board[7]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[4]==1 & board[8]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[0]==1 & board[1]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[0]==1 & board[2]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[0]==1 & board[3]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[0]==1 & board[6]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[2]==1 & board[1]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[2]==1 & board[0]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[2]==1 & board[5]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[2]==1 & board[8]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[6]==1 & board[3]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[6]==1 & board[7]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[6]==1 & board[8]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[7]==1 & board[8]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      
      else if(board[4]==0 & board[0]==0 & board[8]==3)          //all possible blocks
        board[8]=1;
      else if(board[4]==0 & board[1]==0 & board[7]==3)
        board[7]=1;
      else if(board[4]==0 & board[2]==0 & board[6]==3)
        board[6]=1;
      else if(board[4]==0 & board[3]==0 & board[5]==3)
        board[5]=1;
      else if(board[4]==0 & board[5]==0 & board[3]==3)
        board[3]=1;
      else if(board[4]==0 & board[6]==0 & board[2]==3)
        board[2]=1;
      else if(board[4]==0 & board[7]==0 & board[1]==3)
        board[1]=1;
      else if(board[4]==0 & board[8]==0 & board[0]==3)
        board[0]=1;
      else if(board[0]==0 & board[1]==0 & board[2]==3)
        board[2]=1;
      else if(board[0]==0 & board[2]==0 & board[1]==3)
        board[1]=1;
      else if(board[0]==0 & board[3]==0 & board[6]==3)
        board[6]=1;
      else if(board[0]==0 & board[6]==0 & board[3]==3)
        board[3]=1;
      else if(board[2]==0 & board[1]==0 & board[0]==3)
        board[0]=1;
      else if(board[2]==0 & board[0]==0 & board[1]==3)
        board[1]=1;
      else if(board[2]==0 & board[5]==0 & board[8]==3)
        board[8]=1;
      else if(board[2]==0 & board[8]==0 & board[5]==3)
        board[5]=1;
      else if(board[6]==0 & board[3]==0 & board[0]==3)
        board[0]=1;
      else if(board[6]==0 & board[7]==0 & board[8]==3)
        board[8]=1;
      else if(board[6]==0 & board[8]==0 & board[7]==3)
        board[7]=1;
      else if(board[7]==0 & board[8]==0 & board[6]==3)
        board[6]=1;
      
      else    //no win or no block found
      {
        if(board[4]==3)     //take middle
          board[4] = 1;
        else                //else take a corner or a side
        {
          if(board[0] == 3)
            board[0] = 1;
          else if(board[2] == 3)
            board[2] = 1;
          else if(board[6] == 3)
            board[6] = 1;
          else if(board[8] == 3)
            board[8] = 1;
          else if(board[1] == 3)
            board[1] = 1;
          else if(board[3] == 3)
            board[3] = 1;
          else if(board[5] == 3)
            board[5] = 1;
          else if(board[7] == 3)
            board[7] = 1;
        }
      }
      
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==7)
    {
     // boolean win = false;                                  //dont keep playing if win is found
      
      if(board[4]==0 & board[0]==0 & board[8]==3)          //all possible wins
      {
        board[8]=0;
        win = true;
      }
      else if(board[4]==0 & board[1]==0 & board[7]==3)
      {
        board[7]=0;
        win = true;
      }
      else if(board[4]==0 & board[2]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      else if(board[4]==0 & board[3]==0 & board[5]==3)
      {
        board[5]=0;
        win = true;
      }
      else if(board[4]==0 & board[5]==0 & board[3]==3)
      {
        board[3]=0;
        win = true;
      }
      else if(board[4]==0 & board[6]==0 & board[2]==3)
      {
        board[2]=0;
        win = true;
      }
      else if(board[4]==0 & board[7]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[4]==0 & board[8]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[0]==0 & board[1]==0 & board[2]==3)
      {
        board[2]=0;
        win = true;
      }
      else if(board[0]==0 & board[2]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[0]==0 & board[3]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      else if(board[0]==0 & board[6]==0 & board[3]==3)
      {
        board[3]=0;
        win = true;
      }
      else if(board[2]==0 & board[1]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[2]==0 & board[0]==0 & board[1]==3)
      {
        board[1]=0;
        win = true;
      }
      else if(board[2]==0 & board[5]==0 & board[8]==3)
      {
        board[8]=0;
        win = true;
      }
      else if(board[2]==0 & board[8]==0 & board[5]==3)
      {
        board[5]=0;
        win = true;
      }
      else if(board[6]==0 & board[3]==0 & board[0]==3)
      {
        board[0]=0;
        win = true;
      }
      else if(board[6]==0 & board[7]==0 & board[8]==3)
      {
        board[8]=0;
        win = true;
      }
      else if(board[6]==0 & board[8]==0 & board[7]==3)
      {
        board[7]=0;
        win = true;
      }
      else if(board[7]==0 & board[8]==0 & board[6]==3)
      {
        board[6]=0;
        win = true;
      }
      
      else if(board[4]==1 & board[0]==1 & board[8]==3)          //all possible blocks
        board[8]=0;
      else if(board[4]==1 & board[1]==1 & board[7]==3)
        board[7]=0;
      else if(board[4]==1 & board[2]==1 & board[6]==3)
        board[6]=0;
      else if(board[4]==1 & board[3]==1 & board[5]==3)
        board[5]=0;
      else if(board[4]==1 & board[5]==1 & board[3]==3)
        board[3]=0;
      else if(board[4]==1 & board[6]==1 & board[2]==3)
        board[2]=0;
      else if(board[4]==1 & board[7]==1 & board[1]==3)
        board[1]=0;
      else if(board[4]==1 & board[8]==1 & board[0]==3)
        board[0]=0;
      else if(board[0]==1 & board[1]==1 & board[2]==3)
        board[2]=0;
      else if(board[0]==1 & board[2]==1 & board[1]==3)
        board[1]=0;
      else if(board[0]==1 & board[3]==1 & board[6]==3)
        board[6]=0;
      else if(board[0]==1 & board[6]==1 & board[3]==3)
        board[3]=0;
      else if(board[2]==1 & board[1]==1 & board[0]==3)
        board[0]=0;
      else if(board[2]==1 & board[0]==1 & board[1]==3)
        board[1]=0;
      else if(board[2]==1 & board[5]==1 & board[8]==3)
        board[8]=0;
      else if(board[2]==1 & board[8]==1 & board[5]==3)
        board[5]=0;
      else if(board[6]==1 & board[3]==1 & board[0]==3)
        board[0]=0;
      else if(board[6]==1 & board[7]==1 & board[8]==3)
        board[8]=0;
      else if(board[6]==1 & board[8]==1 & board[7]==3)
        board[7]=0;
      else if(board[7]==1 & board[8]==1 & board[6]==3)
        board[6]=0;
      
      else    //no win or no block found
      {
        if(board[4]==3)     //take middle
          board[4] = 0;
        else                //else take a corner then a side
        {
          if(board[0] == 3)
            board[0] = 0;
          else if(board[2] == 3)
            board[2] = 0;
          else if(board[6] == 3)
            board[6] = 0;
          else if(board[8] == 3)
            board[8] = 0;
          else if(board[1] == 3)
            board[1] = 0;
          else if(board[3] == 3)
            board[3] = 0;
          else if(board[5] == 3)
            board[5] = 0;
          else if(board[7] == 3)
            board[7] = 0;
        }
      }
      

      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==8)
    {
     // boolean win = false;                                  //dont keep playing if win is found
      
      if(board[4]==1 & board[0]==1 & board[8]==3)          //all possible wins
      {
        board[8]=1;
        win = true;
      }
      else if(board[4]==1 & board[1]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[4]==1 & board[2]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[4]==1 & board[3]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[4]==1 & board[5]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[4]==1 & board[6]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[4]==1 & board[7]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[4]==1 & board[8]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[0]==1 & board[1]==1 & board[2]==3)
      {
        board[2]=1;
        win = true;
      }
      else if(board[0]==1 & board[2]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[0]==1 & board[3]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      else if(board[0]==1 & board[6]==1 & board[3]==3)
      {
        board[3]=1;
        win = true;
      }
      else if(board[2]==1 & board[1]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[2]==1 & board[0]==1 & board[1]==3)
      {
        board[1]=1;
        win = true;
      }
      else if(board[2]==1 & board[5]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[2]==1 & board[8]==1 & board[5]==3)
      {
        board[5]=1;
        win = true;
      }
      else if(board[6]==1 & board[3]==1 & board[0]==3)
      {
        board[0]=1;
        win = true;
      }
      else if(board[6]==1 & board[7]==1 & board[8]==3)
      {
        board[8]=1;
        win = true;
      }
      else if(board[6]==1 & board[8]==1 & board[7]==3)
      {
        board[7]=1;
        win = true;
      }
      else if(board[7]==1 & board[8]==1 & board[6]==3)
      {
        board[6]=1;
        win = true;
      }
      
      else if(board[4]==0 & board[0]==0 & board[8]==3)          //all possible blocks
        board[8]=1;
      else if(board[4]==0 & board[1]==0 & board[7]==3)
        board[7]=1;
      else if(board[4]==0 & board[2]==0 & board[6]==3)
        board[6]=1;
      else if(board[4]==0 & board[3]==0 & board[5]==3)
        board[5]=1;
      else if(board[4]==0 & board[5]==0 & board[3]==3)
        board[3]=1;
      else if(board[4]==0 & board[6]==0 & board[2]==3)
        board[2]=1;
      else if(board[4]==0 & board[7]==0 & board[1]==3)
        board[1]=1;
      else if(board[4]==0 & board[8]==0 & board[0]==3)
        board[0]=1;
      else if(board[0]==0 & board[1]==0 & board[2]==3)
        board[2]=1;
      else if(board[0]==0 & board[2]==0 & board[1]==3)
        board[1]=1;
      else if(board[0]==0 & board[3]==0 & board[6]==3)
        board[6]=1;
      else if(board[0]==0 & board[6]==0 & board[3]==3)
        board[3]=1;
      else if(board[2]==0 & board[1]==0 & board[0]==3)
        board[0]=1;
      else if(board[2]==0 & board[0]==0 & board[1]==3)
        board[1]=1;
      else if(board[2]==0 & board[5]==0 & board[8]==3)
        board[8]=1;
      else if(board[2]==0 & board[8]==0 & board[5]==3)
        board[5]=1;
      else if(board[6]==0 & board[3]==0 & board[0]==3)
        board[0]=1;
      else if(board[6]==0 & board[7]==0 & board[8]==3)
        board[8]=1;
      else if(board[6]==0 & board[8]==0 & board[7]==3)
        board[7]=1;
      else if(board[7]==0 & board[8]==0 & board[6]==3)
        board[6]=1;
      
      else    //no win or no block found
      {
        if(board[4]==3)     //take middle
          board[4] = 1;
        else                //else take a corner or a side
        {
          if(board[0] == 3)
            board[0] = 1;
          else if(board[2] == 3)
            board[2] = 1;
          else if(board[6] == 3)
            board[6] = 1;
          else if(board[8] == 3)
            board[8] = 1;
          else if(board[1] == 3)
            board[1] = 1;
          else if(board[3] == 3)
            board[3] = 1;
          else if(board[5] == 3)
            board[5] = 1;
          else if(board[7] == 3)
            board[7] = 1;
        }
      }
      
      turn++;
      print(board, turn);
      return turn;
    }
    if(turn==9)              //just fill last square
    {
      if(board[0] == 3)
        board[0] = 0;
      else if(board[2] == 3)
        board[2] = 0;
      else if(board[6] == 3)
        board[6] = 0;
      else if(board[8] == 3)
        board[8] = 0;
      else if(board[1] == 3)
        board[1] = 0;
      else if(board[3] == 3)
        board[3] = 0;
      else if(board[5] == 3)
        board[5] = 0;
      else if(board[7] == 3)
        board[7] = 0;
      turn++;
      print(board, turn);
    }
    return turn;
  }
  
  static int randomMove(int[] board, int turn)        //method for making a random move
  {
    if(turn%2 == 1)                                   //player 1 has called the method
    {
      boolean move = false;
      while(move!=true)
      {
        double random = Math.random() * 9;
        if(random<=1)
        {
          if(board[0] == 3)
          {
            move=true;
            board[0] = 0;
          }
        }
        else if(random<=2)
        {
          if(board[1] == 3)
          {
            move=true;
            board[1] = 0;
          }
        }
        else if(random<=3)
        {
          if(board[2] == 3)
          {
            move=true;
            board[2] = 0;
          }
        }
        else if(random<=4)
        {
          if(board[3] == 3)
          {
            move=true;
            board[3] = 0;
          }
        }
        else if(random<=5)
        {
          if(board[4] == 3)
          {
            move=true;
            board[4] = 0;
          }
        }
        else if(random<=6)
        {
          if(board[5] == 3)
          {
            move=true;
            board[5] = 0;
          }
        }
        else if(random<=7)
        {
          if(board[6] == 3)
          {
            move=true;
            board[6] = 0;
          }
        }
        else if(random<=8)
        {
          if(board[7] == 3)
          {
            move=true;
            board[7] = 0;
          }
        }
        else if(random<=9)
        {
          if(board[8] == 3)
          {
            move=true;
            board[8] = 0;
          }
        }
      }
      turn++;
      print(board, turn);                                  //check if random move was a win
      if(board[4]==0 & board[0]==0 & board[8]==0)          //all possible wins
      {
        board[8]=0;
        win = true;
      }
      else if(board[4]==0 & board[1]==0 & board[7]==0)
      {
        board[7]=0;
        win = true;
      }
      else if(board[4]==0 & board[2]==0 & board[6]==0)
      {
        board[6]=0;
        win = true;
      }
      else if(board[4]==0 & board[3]==0 & board[5]==0)
      {
        board[5]=0;
        win = true;
      }
      else if(board[4]==0 & board[5]==0 & board[3]==0)
      {
        board[3]=0;
        win = true;
      }
      else if(board[4]==0 & board[6]==0 & board[2]==0)
      {
        board[2]=0;
        win = true;
      }
      else if(board[4]==0 & board[7]==0 & board[1]==0)
      {
        board[1]=0;
        win = true;
      }
      else if(board[4]==0 & board[8]==0 & board[0]==0)
      {
        board[0]=0;
        win = true;
      }
      else if(board[0]==0 & board[1]==0 & board[2]==0)
      {
        board[2]=0;
        win = true;
      }
      else if(board[0]==0 & board[2]==0 & board[1]==0)
      {
        board[1]=0;
        win = true;
      }
      else if(board[0]==0 & board[3]==0 & board[6]==0)
      {
        board[6]=0;
        win = true;
      }
      else if(board[0]==0 & board[6]==0 & board[3]==0)
      {
        board[3]=0;
        win = true;
      }
      else if(board[2]==0 & board[1]==0 & board[0]==0)
      {
        board[0]=0;
        win = true;
      }
      else if(board[2]==0 & board[0]==0 & board[1]==0)
      {
        board[1]=0;
        win = true;
      }
      else if(board[2]==0 & board[5]==0 & board[8]==0)
      {
        board[8]=0;
        win = true;
      }
      else if(board[2]==0 & board[8]==0 & board[5]==0)
      {
        board[5]=0;
        win = true;
      }
      else if(board[6]==0 & board[3]==0 & board[0]==0)
      {
        board[0]=0;
        win = true;
      }
      else if(board[6]==0 & board[7]==0 & board[8]==0)
      {
        board[8]=0;
        win = true;
      }
      else if(board[6]==0 & board[8]==0 & board[7]==0)
      {
        board[7]=0;
        win = true;
      }
      else if(board[7]==0 & board[8]==0 & board[6]==0)
      {
        board[6]=0;
        win = true;
      }
      return turn;
    }
    
    else                                //player 2 has called the method
    {
      boolean move = false;
      while(move!=true)
      {
        double random = Math.random() * 9;
        if(random<=1)
        {
          if(board[0] == 3)
          {
            move=true;
            board[0] = 1;
          }
        }
        else if(random<=2)
        {
          if(board[1] == 3)
          {
            move=true;
            board[1] = 1;
          }
        }
        else if(random<=3)
        {
          if(board[2] == 3)
          {
            move=true;
            board[2] = 1;
          }
        }
        else if(random<=4)
        {
          if(board[3] == 3)
          {
            move=true;
            board[3] = 1;
          }
        }
        else if(random<=5)
        {
          if(board[4] == 3)
          {
            move=true;
            board[4] = 1;
          }
        }
        else if(random<=6)
        {
          if(board[5] == 3)
          {
            move=true;
            board[5] = 1;
          }
        }
        else if(random<=7)
        {
          if(board[6] == 3)
          {
            move=true;
            board[6] = 1;
          }
        }
        else if(random<=8)
        {
          if(board[7] == 3)
          {
            move=true;
            board[7] = 1;
          }
        }
        else if(random<=9)
        {
          if(board[8] == 3)
          {
            move=true;
            board[8] = 1;
          }
        }
      }
      turn++;
      print(board, turn);
      if(board[4]==1 & board[0]==1 & board[8]==1)          //all possible wins
      {
        board[8]=1;
        win = true;
      }
      else if(board[4]==1 & board[1]==1 & board[7]==1)
      {
        board[7]=1;
        win = true;
      }
      else if(board[4]==1 & board[2]==1 & board[6]==1)
      {
        board[6]=1;
        win = true;
      }
      else if(board[4]==1 & board[3]==1 & board[5]==1)
      {
        board[5]=1;
        win = true;
      }
      else if(board[4]==1 & board[5]==1 & board[3]==1)
      {
        board[3]=1;
        win = true;
      }
      else if(board[4]==1 & board[6]==1 & board[2]==1)
      {
        board[2]=1;
        win = true;
      }
      else if(board[4]==1 & board[7]==1 & board[1]==1)
      {
        board[1]=1;
        win = true;
      }
      else if(board[4]==1 & board[8]==1 & board[0]==1)
      {
        board[0]=1;
        win = true;
      }
      else if(board[0]==1 & board[1]==1 & board[2]==1)
      {
        board[2]=1;
        win = true;
      }
      else if(board[0]==1 & board[2]==1 & board[1]==1)
      {
        board[1]=1;
        win = true;
      }
      else if(board[0]==1 & board[3]==1 & board[6]==1)
      {
        board[6]=1;
        win = true;
      }
      else if(board[0]==1 & board[6]==1 & board[3]==1)
      {
        board[3]=1;
        win = true;
      }
      else if(board[2]==1 & board[1]==1 & board[0]==1)
      {
        board[0]=1;
        win = true;
      }
      else if(board[2]==1 & board[0]==1 & board[1]==1)
      {
        board[1]=1;
        win = true;
      }
      else if(board[2]==1 & board[5]==1 & board[8]==1)
      {
        board[8]=1;
        win = true;
      }
      else if(board[2]==1 & board[8]==1 & board[5]==1)
      {
        board[5]=1;
        win = true;
      }
      else if(board[6]==1 & board[3]==1 & board[0]==1)
      {
        board[0]=1;
        win = true;
      }
      else if(board[6]==1 & board[7]==1 & board[8]==1)
      {
        board[8]=1;
        win = true;
      }
      else if(board[6]==1 & board[8]==1 & board[7]==1)
      {
        board[7]=1;
        win = true;
      }
      else if(board[7]==1 & board[8]==1 & board[6]==1)     
        win = true;
      
      return turn;
    }
    
  }
  
  static void print(int[] board, int turn)        //method for outputting the board
  {
    String [] niceBoard = new String[9];          //convert integer board into pretty String board
    for(int x=0; x<9; x++)
    {
      if(board[x] == 0)
        niceBoard[x] = "O";
      else if(board[x] == 1)
        niceBoard[x] = "X";
      else
        niceBoard[x] = " ";
    }
    System.out.println("Board at turn #" + (turn-1));
    System.out.print(niceBoard[0] + "|" + niceBoard[1] + "|" + niceBoard[2] + "\n");
    System.out.println("-----");
    System.out.print(niceBoard[3] + "|" + niceBoard[4] + "|" + niceBoard[5] + "\n");
    System.out.println("-----");
    System.out.print(niceBoard[6] + "|" + niceBoard[7] + "|" + niceBoard[8] + "\n");
    System.out.println("*********************************************");
  }

}
