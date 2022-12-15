//Justus Stephens
//CSC 462
//September 14, 2021
//RESUBMITTED

package jksDepthBreadth;

import java.io.*; 
import java.util.*; 
   
  class mainDepthBreadth
  {
    public static void main(String args[]) 
    { 
      
      Scanner getFilepath = new Scanner (System.in);
      System.out.println("Please paste the file path into the terminal :) \nand make sure you delete the quotations surrounding the filepath");
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
        Array2D = new String[y][1000];    //array to hold all nodes and their respective connections. 200 nodes, and none have more than 10 connections
          for(int w=0; w<y; w++)
          {
            ArrayLine = Array[w];
            String[] partsAfterSplit = ArrayLine.split( "," );
            
            for(int store=0; store < partsAfterSplit.length; store++)
            {
              Array2D[w][store] = partsAfterSplit[store];
              System.out.println("Array2D[" + w + "][" + store + "]: " + Array2D[w][store]);
            }
          }
          
          int countEdges = 0;
          
          for(int r=0; r<y; r++)                             //counting all of the edges
            for(int c=0; c<1000; c++)
              if(Array2D[r][c] != null)
                countEdges++;
          
          Graph g = new Graph(countEdges);                  //Make a graph with correct number of edges
          
          for(int r=1; r<y; r++)                            //add the Array2D data into the graph class, so dfs and bfs can utilize
            for(int c=1; c<1000; c++)
              if(Array2D[r][c] != null)
              {
                g.addChild(r,Integer.valueOf(Array2D[r][c]));
              }
                
        int startNodeID = getStartNode(y);
        int endNodeID = getEndNode(y);  
        String bfsPath = "";
        g.depthFirstSearch(startNodeID, endNodeID);
        g.breadthFirstSearch(startNodeID, endNodeID, bfsPath);
        
    } 
    
    ////////////////////////////////////////////////Method for getting the starting node/////////////////////////////////////////////////
    
    public static int getStartNode(int range) 
    {
      int startNode = -1;
      range = range-1;
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
    
    //////////////////////////////////////////////Method for getting the end node//////////////////////////////////////////////////////
    
    public static int getEndNode(int range)
    {
      int endNode = -1;
      range = range-1;
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
}
  
///////////////////////////////Class to hold the graph structure, utilized by dfs and bfs///////////////////////////////////////////////////////
class Graph 
{ 
    private int Nodes;                    // Number of nodes in the graph
   
    private LinkedList<Integer> list[];   //adjacency list
   
    Graph(int n)                          //Graph constructor, making adjacency lists for amount of nodes
    { 
        Nodes = n; 
        list = new LinkedList[n]; 
        for (int i=0; i<n; ++i) 
            list[i] = new LinkedList(); 
    } 
     
    void addChild(int node, int child) 
    { 
        list[node].add(child);  // Add child to node's list of children. 
    } 
    
    
   void depthFirstSearch(int root, int target)                    //main dfs method
   { 
      boolean check[] = new boolean[Nodes];         //to check if a node has been visited already
      boolean found = false;                        //to keep track of when the targetNode is found within traversal
       
      String path = "";                             //path of solution always starts with root node
  
      path = depthFirstTraverse(check, root, target, found, path);  //go to the root and begin recursive traversal
      
      System.out.println("\n");
      System.out.println("DFS Path found: ");        //display the traversal path, until node is found
      
      for(int x=0; path.charAt(x) != 'X'; x++)
        System.out.print(path.charAt(x));
      System.out.println("\n");
   }
   
    String depthFirstTraverse(boolean checked[], int node, int targetNode, boolean found, String path)   //traversal function for dfs (does the search)
    { 
        checked[node] = true;                                     //marks node as visited
        path = path + (String.valueOf(node) + "-");               //adding node to traversal path
        if(targetNode == node)                                    //the target node has been found through traversing dfs
        {
          path = path + " XXX ";                                  //sets flag in path, so I know where to stop the traversal path for output
          found = true;                                           //other wise entire traversal path is output
          return path;
        }
        if (found == false)
        {
          Iterator<Integer> edgeScan = list[node].listIterator();                                 //scanning all edges of current node
          while (edgeScan.hasNext() & found == false) 
          { 
              int nextNode = edgeScan.next(); 
              if (!checked[nextNode])                                                             //if node is not visited, and is not target node
              {
                path = depthFirstTraverse(checked, nextNode, targetNode, found, path);            //recursively call dfs traverse
              }
          }
        }
        
        return path;
    }  
         
    void breadthFirstSearch(int root, int target, String bfsPath)           //bfs traverse method
    {  
        boolean checked[] = new boolean[Nodes];                     //boolean array to check vertices
        int startNode = root;
   
        LinkedList<Integer> queue = new LinkedList<Integer>();      //queue for bfs
   
        checked[root]=true;                                         //current node checked
        queue.add(root);                                            //add current node to queue
        bfsPath += (String.valueOf(root) + "-");
   
        while (queue.size() != 0)                                  //while there is something in the queue
        { 
            root = queue.poll();                                   //the root is the first element in the queue
            Iterator<Integer> node = list[root].listIterator();    //get all children of current node
            
            while (node.hasNext())
            { 
                int child = node.next(); 
                if (!checked[child])                                //if the node has not been checked
                { 
                    checked[child] = true;                          //it is now checked
                    queue.add(child);                               //add it to the back of the queue
                    bfsPath += (String.valueOf(child) + "-");       //add it to the bfs traversal path
                    if(target==child)                               //if it is the end node, mark accordingly
                      bfsPath += ("XXX");                 
                } 
            }

        }
         
        System.out.println("BFS Path found: ");                     //display the traversal path, until target node is found
        
        for(int x=0; bfsPath.charAt(x) != 'X'; x++)
          System.out.print(bfsPath.charAt(x));
    } 
} 
