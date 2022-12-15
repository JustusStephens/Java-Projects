//Justus Stephens
//CSC 462
//September 24

package jksAStar;

import java.io.*; 
import java.util.*; 

public class jksAStarMain 
{
  private static int range;                         //to track valid start and end node input
  
  public static void main(String[] args) 
  {
    double GraphWithEdges[][];
    double HeuristicVals[][];
    GraphWithEdges = getEdgeWeightFile();
    range = getRange();
    System.out.println("Range: " + range);
    int startNodeID = getStartNode(range);
    int endNodeID = getEndNode(range);  
    HeuristicVals = getMinCostFile();
    alphaStar(GraphWithEdges, HeuristicVals, startNodeID, endNodeID);
  }
  
  ///////////////////////////////////////////get edge weight csv/////////////////////////////////////////////////////////////
  
  static double[][] getEdgeWeightFile()
  {
    Scanner getFilepath = new Scanner (System.in);
    System.out.println("Please paste the edge weight file path into the terminal, \nand make sure you delete the quotations surrounding the filepath");
    String filepath = getFilepath.nextLine(); 
    
      Scanner sc = null;
      try                                               //trying to read the file
      {
        sc = new Scanner(new File(filepath));
      }
      catch (FileNotFoundException e)                   //catching not found exception and exiting somewhat gracefully
      {
        System.out.println("You enterned an invalid file, please try again");
      }
      
      sc.useDelimiter(",");                  //sets the delimiter pattern so we chop on commas 
      String Array[];                         //array for holding the data
      Array = new String[1000];               //assuming there is no more than 1000 lines of data
      int y = 0;
      while (sc.hasNext())                   //while there is something left to scan in
      { 
        Array[y] = (sc.nextLine());
        y++;
      }   
      sc.close();                            //closes the scanner  
      String ArrayLine;
      String Array2D[][];
      int maxRange = 0;
      Array2D = new String[y][1000];    //array to hold all nodes and their respective connections. 200 nodes, and none have more than 10 connections
        for(int w=0; w<y; w++)
        {
          ArrayLine = Array[w];
          String[] partsAfterSplit = ArrayLine.split( "," );
          
          for(int store=0; store < partsAfterSplit.length; store++)
          {
            Array2D[w][store] = partsAfterSplit[store];
            if(store == 0 & w!=0)
              if(Integer.valueOf(Array2D[w][0]) > maxRange)
                maxRange = Integer.valueOf(Array2D[w][0]);
          }
          
        }
        
        double[][] GraphArray;                                         //adjacency matrix rep of graph
        GraphArray = new double[maxRange+1][maxRange+1];               //return this array 200 nodes 1-200, 10 nodes 1-10
        
        for(int row=1; row<y; row++)
            GraphArray[Integer.valueOf(Array2D[row][0])][Integer.valueOf(Array2D[row][1])] = Double.valueOf(Array2D[row][2]);        //1st node
            
        for(int fillRow=1; fillRow<maxRange+1; fillRow++)
          for(int fillCol=1; fillCol<maxRange+1; fillCol++)
            if(GraphArray[fillRow][fillCol] > 0.00000000001)
              GraphArray[fillRow][fillCol] = GraphArray[fillRow][fillCol];
            else
              GraphArray[fillRow][fillCol] = 0;
    setRange(maxRange);
    System.out.println("Max range: " + maxRange);
    return GraphArray;
  }
  
  ////////////////////////////////////////////////get heuristic csv//////////////////////////////////////////////////////
  
  static double[][] getMinCostFile()
  {
    Scanner getFilepath2 = new Scanner (System.in);
    System.out.println("Please paste the heuristic file path into the terminal, \nand make sure you delete the quotations surrounding the filepath");
    String filepath2 = getFilepath2.nextLine(); 
    
      Scanner sc2 = null;
      try                                               //trying to read the file
      {
        sc2 = new Scanner(new File(filepath2));
      }
      catch (FileNotFoundException e)                   //catching not found exception and exiting somewhat gracefully
      {
        System.out.println("You enterned an invalid file, please try again");
      }
      
      sc2.useDelimiter(",");                  //sets the delimiter pattern so we chop on commas 
      String Array2[];                         //array for holding the data
      Array2 = new String[1000];               //assuming there is no more than 1000 lines of data
      int y2 = 0;
      while (sc2.hasNext())                   //while there is something left to scan in
      { 
        Array2[y2] = (sc2.nextLine());
        y2++;
      }   
      sc2.close();                            //closes the scanner  
      
      String ArrayLine2;
      String Array2D2[][];
      Array2D2 = new String[y2][1000];    //array to hold all nodes and their respective connections. 200 nodes, and none have more than 10 connections
        for(int w=0; w<y2; w++)
        {
          ArrayLine2 = Array2[w];
          String[] partsAfterSplit2 = ArrayLine2.split( "," );
          
          for(int store=0; store < partsAfterSplit2.length; store++)
          {
            Array2D2[w][store] = partsAfterSplit2[store];
          }
        }
        
        double heuristic[][];
        heuristic = new double[getRange()+1][getRange()+1]; 
        
        for(int r=7; r<range+7; r++)                                                       //data starts at 7 through range+7
          for(int c=1; c<range+1; c++)                                                     //range+1 will give correct amount of cols
          {
            if(c >= (r-6))                                                                //filling in heuristic vals
            {
              heuristic[r-6][c] = Double.valueOf(Array2D2[r][c]);
              heuristic[c][r-6] = Double.valueOf(Array2D2[r][c]);
            }
          }
          
        return heuristic;
  }
  
  ////////////////////////////////////////////////get start node////////////////////////////////////////////////
  
  public static int getStartNode(int range) 
  {
    int startNode = -1;
    boolean valid = false;                                   //checks for valid input     
    Scanner getStartingNode = new Scanner (System.in);      //scanning in user input for starter node
    while(valid == false)                                   //loop so user can enter valid input
    {
      System.out.println("Please enter the starting node ID (1-" + range + ")");
      String startNodeID = getStartingNode.nextLine();
      
      if(Integer.valueOf(startNodeID) >= 1 & Integer.valueOf(startNodeID) <=range)    //if true loop will not repeat
        valid = true;
      
      else
        System.out.println("Invalid start node ID, please try again");
      
      startNode = Integer.valueOf(startNodeID);
    }
    return startNode;
  }
  
  ////////////////////////////////////////////////////get end node/////////////////////////////////////////////////////////////
  
  public static int getEndNode(int range)
  {
    int endNode = -1;
    boolean valid = false;
    Scanner getEndNode = new Scanner (System.in);           
    while(valid == false) 
    {
      System.out.println("Please enter the ending node ID (1-" + range + ")");
      String endNodeID = getEndNode.nextLine();
      if(Integer.valueOf(endNodeID) >= 1 & Integer.valueOf(endNodeID) <=range)
        valid = true;
      else
        System.out.println("Invalid end node ID, please try again");
      endNode = Integer.valueOf(endNodeID);
    }
    return endNode;
  }

  //////////////////////////////////////////////////////////////set range, graph with 200 nodes, range will be 200////////////////////////
  
  static void setRange(int y)
  {
     range = y;
  }
  
  ///////////////////////////////////////////////////////////////get range value////////////////////////////////////////////////
  
  static int getRange()
  {
    return range;
  }
  
  ///////////////////////////////////////////////////////////////A * algorithm//////////////////////////////////////////////////
  
  public static void alphaStar(double[][] graphOfNodes, double[][] heuristicVals, int startNode, int endNode)
  {
    double distance[] = new double[graphOfNodes.length];           //distance of all nodes to the start nodes
    double calcVisit[] = new double[graphOfNodes.length];           //calcs which node to visit next 
    boolean checked[] = new boolean[graphOfNodes.length];           //was a node checked already
    int [] prev = new int[graphOfNodes.length];                     //tracks which node came from which (for path)
    Arrays.fill(distance, Double.MAX_VALUE);                        //fill distance with max values
    Arrays.fill(calcVisit, Double.MAX_VALUE); 
    Arrays.fill(prev, 0);                                           //since there is no 0 node
    prev[startNode] = -1;                                           //give start node a unique value
    distance[startNode] = 0;                                        //start node to itself always 0
    calcVisit[startNode] = heuristicVals[startNode][endNode];       //initialize calcVisit with it's respective heuristic
    
    while(true)                                                     //until return statement is reached
    {
      double nextLowest = Integer.MAX_VALUE;                        //hold's the next lowest node's path value
      int nextLowestVal = -1;                                       //tracks the node's index
      for(int x=0; x< calcVisit.length; x++)
      {
        if(calcVisit[x] < nextLowest && !checked[x])
        {
          nextLowest = calcVisit[x];
          nextLowestVal = x;
        }
      }
      
      if(nextLowestVal == -1) //not found
      {
        System.out.println("No path was found between the start and end nodes");
        return;
      }
      
      else if(nextLowestVal == endNode) //found
      {
        System.out.println("Goal Node found");
        int x = endNode;
        int [] output = new int[1000];
        int count = 0;
        output[0] = endNode;
        while(prev[x] != -1)                                        //assign the path to array, needs reversed
        {
          x = prev[x];
          count++;
          output[count] = x;
        }
        System.out.println("A* minimum cost path:");
        System.out.print("[" + distance[nextLowestVal] + "] ");     //print the cost
        for(int op=count; op>=0; op--)                              //print array in reverse
        {
          if(op==0)
            System.out.print(output[op]);
          else
            System.out.print(output[op] + "-");
        }
        return;                                                     //get out of alphaStar
      }
            
      for(int x=0; x<graphOfNodes[nextLowestVal].length; x++)       //get edges of current lowest node
      {
        if(graphOfNodes[nextLowestVal][x] !=0 && !checked[x])       //if the edge has yet to be checked
        {
          if(distance[nextLowestVal] + graphOfNodes[nextLowestVal][x] < distance[x])        //if the edge's path is shorter
          {
            distance[x] = distance[nextLowestVal] + graphOfNodes[nextLowestVal][x];         //calculate new node's distance
            calcVisit[x] = distance[x] + heuristicVals[x][endNode];                         //new node's distance plus the heuristic
            prev[x] = nextLowestVal;                                                        //track parent node
          }
        }
      }
      
      checked[nextLowestVal] = true;
    }
  }
}
