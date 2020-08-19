//discussed project with mitchell and emily
package GraphSearch;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DFS
{  
    public static Set<Integer> visited; // vertices already visited
    
    private static Iterator<Integer> [ ] iter;
    private static List<Integer> [ ] adjList;   
    private static int [ ] numberPaths; // numberPaths[x] is the number of 
                                        // of paths from x to the destination
    
   
    private static void visit(int v){ visited.add(v);}
    
    static public void init(List<Integer> [] adjList)
    {      
        visited = new HashSet<>();
        iter = new Iterator[adjList.length];
        numberPaths = new int[adjList.length];        
        DFS.adjList = adjList;      
    }
    
    /**
     * Start a completely new search from a vertex v to destination d
     * @param v
     * @param d
     */
    public static void reset(int v, int d)
    {      
        visited.clear();
        for (int k = 0; k < iter.length; k++)
        {
            iter[k] = adjList[k].iterator();      
            numberPaths[k] = 0;
        }
        numberPaths[d] = 1;
    }  
    /**
     * Perform a DFS search from a start vertex v to find the number of 
     * paths to a destination vertex d
     * @param v 
     * @param d
     */
    public static void search(int v, int d)
    {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(v);
        visit(v);
        while(stack.size()>0){
            int current = stack.pop();
            if(iter[current].hasNext()){
                visit(current);
                int next = iter[current].next();
                stack.push(current);
                if(!visited.contains(next)){
                    stack.push(next);
                    visit(next); 
                }
                else{
                    numberPaths[current]+=numberPaths[next];
                }
            } 
            else{
                if(current!=v){
                    numberPaths[stack.peek()]+=numberPaths[current];
                }
            } 
        }
    }
    
    public static void printNumberOfPaths(Graph g)
    {
        for (int v = 0; v < adjList.length; v++)
        {
            System.out.printf("%s : %d\n", g.getVertexName(v), numberPaths[v]);
        }
    }
}
