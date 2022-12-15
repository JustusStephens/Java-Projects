//Justus Stephens
//CSC 310
//April 2021

package jksStrassen;

import java.io.*;
import java.util.Scanner;

public class mainStrassen 
{
  public static void main(String[] args)throws Exception
  {
    Scanner scan = new Scanner(new File("C:\\Users\\jstep\\eclipse-workspace\\jksStrassen\\src\\jksStrassen\\Matrix20.txt"));
    scan.useDelimiter(",");
    
    int Array1[][];             //one of two arrays to hold a matrix
    int Array2[][];             //second array to hold matrix
    int count = 0;              //used to split the text file into matrix
    int size = 1;               //2^n
    int temp1 = 0;               //used to compute size
    
    Scanner getDimensions = new Scanner (System.in);
    System.out.println("What is the size of n for your matrix, where 2^n X 2^n is the matrix size?");
    String dimensions = getDimensions.nextLine();
    temp1=Integer.valueOf(dimensions);
    
    for(int x=0; x<temp1; x++)                   //computing 2 to the power of n
      size = size * 2;
    
    int totalEntries = size*size;
    
    System.out.println("You entered a matrix of size: " + size+"X"+size);
    System.out.println("If this is incorrect, the program will not work!\n");
    
    Array1 = new int[size][size];       //A 2d array with size 2^n rows and cols
    Array2 = new int[size][size];
    String s;
    
    int row = 0;                      //row counter for array1
    int col = 0;                      //col counter for array1
    int rowB = 0;                     //row counter for array2
    int colB = 0;                     //col counter for array2
    
    while(scan.hasNext())             //scanning the txt file for the actual values of the matricies
    {
      s = scan.next();
      s = s.replaceAll("\\s", "");
      if(count < totalEntries)
      {        
        Array1[row][col] = Integer.valueOf(s);
        
        if(col+1==size)                                 //if the limit on the number of columns is reached, set to 0 and increment row
        {
          col = 0;
          row++;
        }
        
        else
          col++;
        
      }
      else
      {
        Array2[rowB][colB] = Integer.valueOf(s);
        
        if(colB+1==size)
        {
          colB = 0;
          rowB++;
        }
        
        else
          colB++;
      }
      
      count++;                                         
    }

    int [][] solutionArray;
   solutionArray = new int[size][size];
   
   int temp2 = 0;
   Scanner getMethod = new Scanner (System.in);
   System.out.println("Type 1 for Strassen, 2 for Brute Force, or 3 for Combined");
   String method = getMethod.nextLine();
   temp2=Integer.valueOf(method);
   
   if(temp2==1)                                                                //strassen
   {
     long startTime = System.nanoTime();
     solutionArray = strassen(Array1, Array2, size);
     long endTime = System.nanoTime();
     long totalTime = endTime - startTime;
     System.out.println("Time Strassen ran in nanoseconds : " + totalTime);
   }
   else if(temp2==2)                                                           //brute force
   {
     long startTime = System.nanoTime();
     solutionArray = bruteForce(Array1, Array2, solutionArray, size);
     long endTime = System.nanoTime();
     long totalTime = endTime - startTime;
     System.out.println("Time Brute ran in nanoseconds : " + totalTime);
   }
   else                                                                         //combined
   {
     Scanner getBreak = new Scanner (System.in);
     System.out.println("Type the value of n, for whem the matrix reaches 2^n Strassens splits to Brute Force: ");
     String brk = getBreak.nextLine();
     int b=Integer.valueOf(brk);
     long startTime = System.nanoTime();                              
     solutionArray = combined(Array1, Array2, size, b, temp1);
     long endTime = System.nanoTime();
     long totalTime = endTime - startTime;
     System.out.println("Time Combined ran in nanoseconds : " + totalTime);
   }
    
////////////////////////////////////////////////////IF YOU WANT TO SEE OUTPUT DELETE THE "//" BELOW////////////////////////////////
//    for(int print = 0; print<size; print++)
//     for(int x=0; x<size; x++)
//          System.out.println("solutionArray main after strassen [" + print + "][" + x + "] : " + solutionArray[print][x]); 
    
    
  }
  
  ////////////////////Strassen's Algorithm////////////////////////////////
  
  public static int[][] strassen(int[][] Array1, int[][]Array2, int size)
  {
    if(size==1)                                             //if a 1x1 matrix, its just multiplication
    {
      int [][] solutionArray;
      solutionArray = new int[size][size];
      solutionArray[0][0] = Array1[0][0] * Array2[0][0];
      return solutionArray;
    }
    
    else
    {
    
    int [][] solutionArray;
    solutionArray = new int[size][size];
    int split = size/2;                              //dividing the matrices
    size = size/2; 
    int[][] A00 = new int[split][split];
    int[][] A01 = new int[split][split];
    int[][] A10 = new int[split][split];
    int[][] A11 = new int[split][split];
    int[][] B00 = new int[split][split];
    int[][] B01 = new int[split][split];
    int[][] B10 = new int[split][split];
    int[][] B11 = new int[split][split];
    
    for(int x=0; x<split; x++)
      for(int y=0; y<split; y++)
      {                                         //Getting values for the divided matrices
        A00[x][y] = Array1[x][y];
        A01[x][y] = Array1[x][y+split];
        A10[x][y] = Array1[split+x][y];
        A11[x][y] = Array1[split+x][split+y];
        B00[x][y] = Array2[x][y];
        B01[x][y] = Array2[x][split+y];
        B10[x][y] = Array2[split+x][y];
        B11[x][y] = Array2[split+x][split+y];
      }
    
    //Calculating m1-m7 with recursion and the resulting answer matrix
    int[][] m1 = strassen(A00, sub(B01, B11, split), split);
    int[][] m2 = strassen(add(A00, A01, split), B11, split);
    int[][] m3 = strassen(add(A10, A11, split), B00, split);
    int[][] m4 = strassen(A11, sub(B10, B00, split), split); 
    int[][] m5 = strassen(add(A00, A11, split), add(B00, B11, split), split);
    int[][] m6 = strassen(sub(A01, A11, split), add(B10, B11, split), split);
    int[][] m7 = strassen(sub(A00, A10, split), add(B00, B01, split), split);
    
    int[][] C00 = sub(add(add(m5,m4,split), m6, split), m2, split);
    int[][] C01 = add(m1, m2, split);
    int[][] C10 = add(m3, m4, split);
    int[][] C11 = sub(sub(add(m5, m1, split), m3, split), m7, split);
    
    //copy and return solutionArray//
    for(int x=0; x<split; x++)
      for(int y=0; y< split; y++)
      {
        solutionArray[x][y] = C00[x][y];
        solutionArray[x][y+split] = C01[x][y];
        solutionArray[x+split][y] = C10[x][y];
        solutionArray[x+split][y+split] = C11[x][y];
      }
    return solutionArray;
    }
  }
  
  public static int[][] bruteForce(int[][] Array1, int[][]Array2, int[][]solutionArray, int size)
  {
    for(int x=0; x<size; x++)
      for(int y=0; y<size; y++)
        for(int z=0; z<size; z++)
          solutionArray[x][y] = solutionArray[x][y] + (Array1[x][z] * Array2[z][y]);
    return solutionArray;
  }
  
  public static int[][] combined(int[][] Array1, int[][]Array2, int size, int brk, int count)
  {
    int [][] solutionArray;
    solutionArray = new int[size][size];
    int split = size/2;
    count --;
    if(count==brk)                                             //break size reached
    {
      solutionArray = bruteForce(Array1, Array2, solutionArray, size);
      return solutionArray;
    }
    
    else  if(count!=brk) {
    
                                                      //dividing the matrices
    int[][] A00 = new int[split][split];
    int[][] A01 = new int[split][split];
    int[][] A10 = new int[split][split];
    int[][] A11 = new int[split][split];
    int[][] B00 = new int[split][split];
    int[][] B01 = new int[split][split];
    int[][] B10 = new int[split][split];
    int[][] B11 = new int[split][split];
    
    for(int x=0; x<split; x++)
      for(int y=0; y<split; y++)
      {
        A00[x][y] = Array1[x][y];
        A01[x][y] = Array1[x][y+split];
        A10[x][y] = Array1[split+x][y];
        A11[x][y] = Array1[split+x][split+y];
        B00[x][y] = Array2[x][y];
        B01[x][y] = Array2[x][split+y];
        B10[x][y] = Array2[split+x][y];
        B11[x][y] = Array2[split+x][split+y];
      }
    
    //Calculating m1-m7 with recursion and the resulting answer matrix
    int[][] m1 = combined(A00, sub(B01, B11, split), split, brk, count);
    int[][] m2 = combined(add(A00, A01, split), B11, split, brk, count);
    int[][] m3 = combined(add(A10, A11, split), B00, split, brk, count);
    int[][] m4 = combined(A11, sub(B10, B00, split), split, brk, count); 
    int[][] m5 = combined(add(A00, A11, split), add(B00, B11, split), split,  brk, count);
    int[][] m6 = combined(sub(A01, A11, split), add(B10, B11, split), split,  brk, count);
    int[][] m7 = combined(sub(A00, A10, split), add(B00, B01, split), split,  brk, count);
    
    int[][] C00 = sub(add(add(m5,m4,split), m6, split), m2, split);
    int[][] C01 = add(m1, m2, split);
    int[][] C10 = add(m3, m4, split);
    int[][] C11 = sub(sub(add(m5, m1, split), m3, split), m7, split);
    
    //copy and return solutionArray//
    for(int x=0; x<split; x++)
      for(int y=0; y< split; y++)
      {
        solutionArray[x][y] = C00[x][y];
        solutionArray[x][y+split] = C01[x][y];
        solutionArray[x+split][y] = C10[x][y];
        solutionArray[x+split][y+split] = C11[x][y];
      }
    }
    return solutionArray;
  }
  
  static int[][] add(int[][]Array1, int[][]Array2, int size)              //matrix addition
  {
    int[][] sum;
    sum = new int[size][size];
    for(int x=0; x<size; x++)
      for(int y=0; y<size; y++)
      {
        sum[x][y] = Array1[x][y] + Array2[x][y];
      }
  
    return sum;
  }
  
  static int[][] sub(int[][]Array1, int[][]Array2, int size)              //matrix subtraction
  {
    int[][] dif;
    dif = new int[size][size];
    for(int x=0; x<size; x++)
      for(int y=0; y<size; y++)
      {
        dif[x][y] = Array1[x][y] - Array2[x][y];
      }
  
    return dif;
  }
    
}
