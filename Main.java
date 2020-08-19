
package GraphSearch;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Main
{   
    public static void main(String[] args) throws FileNotFoundException
    {       
        Scanner sc = new Scanner(System.in);
        while (true)
        {
           System.out.print("Is there another graph to process? (Y/N)");
           
           String response = sc.nextLine().toLowerCase();
           if (response.charAt(0) != 'y')
           {
               System.out.println("Goodbye");
               System.exit(0);
           }
           // Pop up a file chooser and select a file containing graph data        
           JFileChooser chooser = new JFileChooser();
           int result = chooser.showOpenDialog(null);
           if (result != JFileChooser.APPROVE_OPTION)
           {
               System.out.println("No file selected.");
               continue;
           }
           // Read the file  and put a scanner on it
           Scanner graphScanner = new Scanner(chooser.getSelectedFile());
           
           Graph graph = new Graph(graphScanner);
           
           System.out.println();
           
           // Output the map of sring vertices to integers.
           System.out.println("Vertex name to integer map:");
           System.out.println(graph.vertexNameToNumberMap);
           // Output the map of integer vertices to strings.
           System.out.println("Vertex integer to string names map:");
           System.out.println(Arrays.toString(graph.vertexNames));          
           
           
           System.out.println();
           
           // Output the adjacency list in string form
           System.out.println("Adjacency list for the graph in string names form:");
           graph.outputString(System.out);
           String sourceVertex, destVertex;
           while (true)
           {             
              System.out.print("Enter a source vertex, blank line to quit: ");
              sourceVertex = sc.nextLine();            
              sourceVertex = sourceVertex.trim();
              if (sourceVertex.length() == 0) break;           
              int sourceVert = graph.getVertexNumber(sourceVertex);
              
              System.out.print("Enter a destination vertex, blank line to quit: ");
              destVertex = sc.nextLine();             
              destVertex = destVertex.trim();
              if (destVertex.length() == 0) break;           
              int destVert = graph.getVertexNumber(destVertex);             
              
              System.out.printf("Number of Paths  from %s to %s found by "
                                + "non-recursive method.\n", sourceVertex, destVertex);
              DFS.init(graph.getAdjacencyList());
              DFS.reset(sourceVert, destVert);
              DFS.search(sourceVert, destVert);
              DFS.printNumberOfPaths(graph);
              System.out.println();              
           
                System.out.printf("Number of Paths  from %s to %s found by "
                                + "recursive method.\n", sourceVertex, destVertex);
              RecursiveDFS.init(graph.getAdjacencyList());
              RecursiveDFS.reset(sourceVert, destVert);                      
              RecursiveDFS.search(sourceVert, destVert);
              RecursiveDFS.printNumberOfPaths(graph);
              System.out.println();                        
           }           
        } 
    }  
    
    static List<Integer> getPathFromPred(Map<Integer, Integer> pred, int dest)
    {       
       LinkedList<Integer>  path = new LinkedList<>();    
       if (!pred.containsKey(dest)) {return path;}
       
       while (true)
       {     
           path.addFirst(dest);
           Integer prev = pred.get(dest);
           if (prev == null) break;          
           dest = prev;
       }       
       return path;
    }
}
