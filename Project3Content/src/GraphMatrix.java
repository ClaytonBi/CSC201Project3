import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph{
    private int[][] adjMatrix;//the adjacency matrix
    private int num_v;//number of vertices
    private int num_e;//number of edges
    private boolean[] Visited;//array of boolean containing whether the vertices have been visited

    //default constructor
    public GraphMatrix(){
        num_v = 1;
        num_e = 0;
        adjMatrix = new int[num_v][num_v];
        Visited = new boolean[num_v];
    }

    //parameterized constructor
    public GraphMatrix(int n){//n is number of vertices
        init(n);//call init to initialize all local variables
    }

    // Initialize the graph with some number of vertices
    public void init(int n){
        num_v = n;//assign number n to number of vertices
        num_e = 0;//initial number of edges is 0
        adjMatrix = new int[num_v][num_v];//construct adjMatrix as an n*n matrix
        Visited = new boolean[num_v];//construct Visited as an n element array
    }

    // Return the number of vertices
    public int nodeCount(){
        return num_v;//return number of vertices
    }

    // Return the current number of edges. This operation must be a constant time operation.
    public int edgeCount(){
        return num_e;//return number of edges
    }

    // Return an int[] containing the edges of vertex, v. The array should contain vertex ids or labels of the adjacent vertices.
    public int[] getEdges(int v){
        int[] arr = new int[2];
        return arr;
    }

    // Adds a new edge from node v to node w with weight wgt
    public void addEdge(int v, int w, int wgt){
        adjMatrix[v][w] = wgt;//set row v column w to wgt
        num_e++;//increment num_e
    }

    // Get the weight value for an edge
    public int getWeight(int v, int w){
        return adjMatrix[v][w];//return the value at row v column w
    }

    // Set the weight of v and w.
    public void setWeight(int v, int w){
    }

    // Removes the edge from the graph.
    public void removeEdge(int v, int w){
        adjMatrix[v][w] = 0;//set row v column w to 0
        num_e--;//decrement num_e
    }

    // Returns true if and only if the graph has an edge between v and w.
    public boolean hasEdge(int v, int w){
        if (adjMatrix[v][w] == 0){//return true if the wgt of edg v-w is 0
            return false;
        }
        else{//return false if the wgt of edg v-w is not 0
            return true;
        }
    }

    // Returns an array containing vertex id's of the neighbors of v.
    public int[] neighbors(int v){
        int neighbor_count = 0;//initialize neighbor_count to count the number of neighbors
        for (int i = 0; i < adjMatrix.length; ++i){//the for loop counts the number of v's neighbors
            if (adjMatrix[v][i] > 0){
                neighbor_count++;
            }
        }
        int[] neighbor_v = new int[neighbor_count];//construct array of integers based on number of neighbors
        neighbor_count = 0;//switch neighbor_count back to 0 for later inserting elements into array
        for (int i = 0; i < adjMatrix[v].length; ++i){//the for loop counts the number of v's neighbors
            if (adjMatrix[v][i] > 0){
                neighbor_v[neighbor_count] = i;//add the neighbor to neighbor_v
                neighbor_count++;//increment neighbor_count
            }
        }
        return neighbor_v;
    }

    // Resets the Visited array (required for BFS) to all false.
    public void resetVisited(){
        for (int i = 0; i < Visited.length; ++i){
            Visited[i] = false;
        }
    }

    // Performs a Breadth-First-Search starting at vertex, v. On PreVisit, the current vertex's label/id should be appended to the end of an ArrayList. Do not perform a PostVisit operation.
    // Index 0 of the array should be the starting vertex, v. Index 1 should be one of v's neighbors and so on.
    // Once BFS is completed, the ArrayList is returned.
    public ArrayList<Integer> BFS(int v){
        ArrayList<Integer> arr = new ArrayList<>();
        Queue<Integer> tmp = new LinkedList<Integer>();//initialize queue
        tmp.add(v);//enqueue starting vertex
        //这里不知道要不要把v标为已读
        Visited[v] = true;//mark the stating vertex as visited
        while (!tmp.isEmpty()){
            int target = tmp.remove();//dequeue the queue and assign the dequeued vertex to target
            arr.add(target);//add the dequeued vertex to arrayList
            int[] neighbors = neighbors(target);//find the neighbors of target
            for (int i = 0; i < neighbors.length; ++i){
                if (!Visited[neighbors[i]]){//if the element is not visited, enqueue it and mark as visited
                    tmp.add(neighbors[i]);//enqueue
                    Visited[neighbors[i]] = true;//mark the vertex as visited
                }
            }
        }
        return arr;//return the finalized arrayList
    }


    // Returns true if there is a path between v and w. Otherwise returns false. You may use the BFS method (above) for this method.
    public boolean hasPath(int v, int w){
        //basic logic is to use BFS to find every vertex that v can reach and every vertex that w can reach
        //either if w is within BFS(v) or if v is within BFS(w), there is a path between v and w
        this.resetVisited();//reset Visited
        ArrayList<Integer> accessibleV = this.BFS(v);//find every vertex that v can reach
        ArrayList<Integer> accessibleW = this.BFS(w);//find every vertex that w can reach
        for (int i = 0; i < accessibleV.size(); ++i){//if w is within BFS(v), return true
            if (accessibleV.get(i) == w){
                return true;
            }
        }
        for (int i = 0; i < accessibleW.size(); ++i){//if v is within BFS(w), return true
            if (accessibleW.get(i) == v){
                return true;
            }
        }
        return false;//if none of the process above triggers the return statement, then v and w are not reachable to each other, return false
    }

    // Performs a topologicalSort if the graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order. You must use BFS
    public ArrayList<Integer> topologicalSort(){
        ArrayList<Integer> arr = new ArrayList<>();
        return arr;
    }
}
