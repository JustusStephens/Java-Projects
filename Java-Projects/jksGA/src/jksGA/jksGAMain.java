package jksGA;
//Justus Stephens
//CSC 462
//October 1, 2021

import java.util.Scanner;

public class jksGAMain 
{
  private static double[][] snake = new double [20][25]; 
  public static void main(String[] args) 
  {
    int trial = 0;
    int hitOverTrial = 0;
    int mutationRate;
    
    Scanner getInput = new Scanner (System.in);                           //get mutation rate for experiment
    System.out.println("Please enter the mutation rate (1-100)%");
    String input = getInput.nextLine(); 
    mutationRate = Integer.valueOf(input);
    
    while(trial<30)                                                      //a trial is one cycle of the algorithm (either hit goal, or go 1000 generations)
    {
      int gen=1;
      boolean hit = false;
      while(gen<1001 & hit==false)
      {
        double [][] goalDistance = new double [25][2];                   // holds the end value of each segment (how close was it)
        double distanceMade[] = new double[20];
        
        if(gen==1)
        {      
          for(int i=0; i<20; i++)                                       //initializing first population with 25 segments of random angles from -pi/2 to pi/2
          {
            for(int seg=0; seg<25; seg++)
            {
              snake[i][seg] = ((Math.random() * (Math.PI)/2));          //give a value between 0 and pi/2
            }
          }
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////calculating distances////////////////////////////////////////////////////////
            
          for(int i=0; i<20; i++)                 //calculating the path
          {
            double xCoord=10;                      //start x at 10 (middle-ish)
            double yCoord=1;                      //start y at 1 (almost bottom)
            boolean inPlay = true;
            
            for(int seg=0; seg<25 && inPlay==true; seg++)
            {        
              if(xCoord>32 || xCoord<0)           //snake is out of the x range
              {
                goalDistance[i][0] = xCoord;
                goalDistance[i][1] = yCoord;
                inPlay = false;
                distanceMade[i] = Math.sqrt(((29-xCoord)*(29-xCoord))+((15-yCoord)*(15-yCoord)));
              }
              
              else if(yCoord>18 || yCoord<0)      //snake is out of y range
              {
                goalDistance[i][0] = xCoord;
                goalDistance[i][1] = yCoord;
                inPlay = false;
                distanceMade[i] = Math.sqrt(((29-xCoord)*(29-xCoord))+((15-yCoord)*(15-yCoord)));
              }
              
              else if(Math.sqrt(((29-xCoord)*(29-xCoord))+((15-yCoord)*(15-yCoord))) <= 0.5)  //snake hit goal, stop segment
              {
                goalDistance[i][0] = xCoord;
                goalDistance[i][1] = yCoord;
                inPlay = false;
                System.out.println("snake " + i + " hit the goal!!");
                distanceMade[i] = Math.sqrt(((29-xCoord)*(29-xCoord))+((15-yCoord)*(15-yCoord)));
              }
              
              else                                //snake is still in playing field
              {
                goalDistance[i][0] = xCoord;                                  //save current coordinates
                goalDistance[i][1] = yCoord;
                xCoord = xCoord +  Math.cos(snake[i][seg]);        //calculate next coordinates
                yCoord = yCoord +  Math.sin(snake[i][seg]);                    //this is right but, will never reach goal unless +Math.pi/2
                distanceMade[i] = (Math.sqrt(((29-xCoord)*(29-xCoord))+((15-yCoord)*(15-yCoord))));     //save current distance from goal
              }
            }
          }
          
          //sort distanceMade from lowest to highest, for selection (the lower the number, the closer they are to the goal)
          
          int [] sortDist = new int[20];                          //keeping track of which snakes to select
          for(int fill=0; fill<20; fill++)
            sortDist[fill] = fill;
      
                  
          for(int low=0; low<=distanceMade.length - 2; low++)           //selection sort
          {
            for(int up=low+1; up<distanceMade.length; up++)
            {
              if(distanceMade[low]>distanceMade[up])
              {
                double temp = distanceMade[low];
                distanceMade[low] = distanceMade[up];
                distanceMade[up] = temp;
                int temp2 = sortDist[low];
                sortDist[low] = sortDist[up];
                sortDist[up] = temp2;
              }
            }
          }
          
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////
          /////////////////////////////////////////////selection///////////////////////////////////////////////////////
          
          int [] newPop = new int[5];
    
          if(distanceMade[0] <= 0.5)          //check to see if the closest distance is a hit on the goal
          {
            hit = true;
            hitOverTrial++;
          }
          
          //select 5
          int p = 0;
          while(p<5)
          {
            double select = Math.random() * 100;
            if(select<=30)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[0])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[0];
                p++;
              }
            }
            else if(select>30 & select<=45)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[1])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[1];
                p++;
              }
            }
            else if(select>45 & select<=55)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[2])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[2];
                p++;
              }
            }
            else if(select>55 & select<=65)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[3])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[3];
                p++;
              }
            }
            else if(select>65 & select<=70)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[4])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[4];
                p++;
              }
            }
            else if(select>70 & select<=73)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[5])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[5];
                p++;
              }
            }
            else if(select>73 & select<=76)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[6])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[6];
                p++;
              }
            }
            else if(select>76 & select<=78)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[7])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[7];
                p++;
              }
            }
            else if(select>78 & select<=80)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[8])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[8];
                p++;
              }
            }
            else if(select>80 & select<=82)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[9])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[9];
                p++;
              }
            }
            else if(select>82 & select<=84)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[10])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[10];
                p++;
              }
            }
            else if(select>84 & select<=86)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[11])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[11];
                p++;
              }
            }
            else if(select>86 & select<=88)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[12])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[12];
                p++;
              }
            }
            else if(select>88 & select<=90)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[13])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[13];
                p++;
              }
            }
            else if(select>90 & select<=92)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[14])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[14];
                p++;
              }
            }
            else if(select>92 & select<=94)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[15])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[15];
                p++;
              }
            }
            else if(select>94 & select<=96)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[16])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[16];
                p++;
              }
            }
            else if(select>96 & select<=98)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[17])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[17];
                p++;
              }
            }
            else if(select>98 & select<=99)
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[18])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[18];
                p++;
              }
            }
            else
            {
              boolean notUsed = true;
              int c=0;
              while(c<5)
              {
                if(newPop[c] == sortDist[19])
                  notUsed = false;
                c++;
              }
              if(notUsed == true)
              {
                newPop[p] = sortDist[19];
                p++;
              }
            }
            
          }
          
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////
          /////////////////////////////////////////////crossover///////////////////////////////////////
          double [][] babySnakes = new double [20][25];
          
          for(int s=0; s<5; s++)                                //the selected 5 are immediatley in new generation
            for(int seg=0; seg<25; seg++)
            {
              babySnakes[s][seg] = snake[newPop[s]][seg];
            }
          
          int count = 2;                                        //start adding crossover babies at 5
          
          for(int a=0; a<2; a++)
            for(int b=a+1; b<5; b++)
            {          
              for(int seg=0; seg<25; seg++)
              {
                  if(seg<5)
                  {
                    babySnakes[count*2+1][seg] = snake[newPop[a]][seg];
                    babySnakes[count*2+2][seg] = snake[newPop[b]][seg];
                  }
                  else if(seg<10 & seg>=5)
                  {
                    babySnakes[count*2+1][seg] = snake[newPop[b]][seg];
                    babySnakes[count*2+2][seg] = snake[newPop[a]][seg];
                  }
                  else if(seg<15 & seg>=10)
                  {
                    babySnakes[count*2+1][seg] = snake[newPop[a]][seg];
                    babySnakes[count*2+2][seg] = snake[newPop[b]][seg];
                  }
                  else
                  {
                    babySnakes[count*2+1][seg] = snake[newPop[b]][seg];
                    babySnakes[count*2+2][seg] = snake[newPop[a]][seg];
                  }
                }
              count++;
            }
         ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          //////////////////////////////////mutation/////////////////////////////////////////////////////////////////////
          for(int x=0; x<20; x++)
          {
            double random = Math.random() * 100;
            if(random<=mutationRate)                                     //mutation rate (10=10%, 20=20%, ...)
            {
              int rand = (int) Math.random() * 25;
              babySnakes[x][rand] =  (Math.PI/2 - (Math.random() * Math.PI));
            }   
          }
          /////////////////////////////////////////////////////////////////////////////////////////////////////////////
          /////////////////////////////////////////////new population being set///////////////////////////////////////
        for(int t=0; t<20; t++)
          for(int r=0; r<25; r++)
            snake[t][r] = babySnakes[t][r];
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        gen++;
      } //gen
      
      trial++;
      if(hit==true)
        System.out.println("Trial #" + trial + " hit the goal on gen #" + (gen-1));                   //track hit, gives some guidance
      gen = 1;
    } //trial loop
    
    System.out.println("Mutation rate: " + mutationRate + "%");                                       //output
    System.out.println("\nOver 30 trials, with a max of 1000 generations per trial");
    System.out.println("The goal was reached: " + hitOverTrial + " time(s)");
  }  //main
} 
