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
        if (n <= 0){//error condition: number of vertices is non-positive. If so, print error and initialize the graph with 1 vertex
            System.out.println("Error: number of vertices can't be negative.");
            init(1);
        }
        else{
            init(n);//call init to initialize all local variables
        }
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

    // Adds a new edge from node v to node w with weight wgt
    public void addEdge(int v, int w, int wgt){
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
        }
        else if (wgt <= 0){//error condition: entering a non-positive wgt
            System.out.println("Error: invalid wgt.");
        }
        else{
            adjMatrix[v][w] = wgt;//set row v column w to wgt
            num_e++;//increment num_e
        }
    }

    // Get the weight value for an edge
    public int getWeight(int v, int w){
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
            return 0;
        }
        else{
            return adjMatrix[v][w];//return the value at row v column w
        }
    }

    // Set the weight of v and w.
    public void setWeight(int v, int w, int wgt){
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
        }
        else if (wgt < 0){//error condition: entering a negative wgt
            System.out.println("Error: invalid wgt.");
        }
        else{
            adjMatrix[v][w] = wgt;//set row v column w to wgt
            num_e++;//increment num_e
        }
    }

    // Removes the edge from the graph.
    public void removeEdge(int v, int w){
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
        }
        else{
            adjMatrix[v][w] = 0;//set row v column w to 0
            num_e--;//decrement num_e
        }
    }

    // Returns true if and only if the graph has an edge between v and w.
    public boolean hasEdge(int v, int w){
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
            return false;
        }
        if (adjMatrix[v][w] == 0){//return true if the wgt of edg v-w is 0
            return false;
        }
        else{//return false if the wgt of edg v-w is not 0
            return true;
        }
    }

    // Returns an array containing vertex id's of the neighbors of v.
    public int[] neighbors(int v){
        if ((v<0) || (v>=adjMatrix.length)){//error condition: index out of bounds, print error statement and return null
            System.out.println("Error: index out of bounds.");
            return null;
        }
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
        if ((v<0) || (v>=adjMatrix.length)){//error condition: index out of bounds, print error statement and return null
            System.out.println("Error: index out of bounds.");
            return null;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        Queue<Integer> tmp = new LinkedList<Integer>();//initialize queue
        tmp.add(v);//enqueue starting vertex
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
        if ((v<0) || (v>=adjMatrix.length) || (w<0) || (w>= adjMatrix[0].length)){//error condition: index out of bounds, print error statement
            System.out.println("Error: index out of bounds.");
            return false;
        }
        //basic logic is to use BFS to find every vertex that v can reach and every vertex that w can reach
        //either if w is within BFS(v) or if v is within BFS(w), there is a path between v and w
        this.resetVisited();//reset Visited
        ArrayList<Integer> accessibleV = this.BFS(v);//find every vertex that v can reach
        for (int i = 0; i < accessibleV.size(); ++i){//if w is within BFS(v), return true
            if (accessibleV.get(i) == w){
                return true;
            }
        }
        return false;//if none of the process above triggers the return statement, then v and w are not reachable to each other, return false
    }

    // Performs a topologicalSort if the graph and returns an ArrayList that contains the vertex labels/id in topologically sorted order. You must use BFS
    public ArrayList<Integer> topologicalSort(){
        this.resetVisited();//reset visited
        ArrayList<Integer> arr = new ArrayList<>();//construct arr to contain the sorted vertices
        int[] inDegrees = new int[adjMatrix.length];//construct inDegrees to contain the in-degrees of the vertex


        //initialize the content of inDegrees
        int count;
        for (int i = 0; i < adjMatrix[0].length; ++i){//this for loop loops through each column of adjMatrix
            count = 0;//set count to 0
            for (int j = 0; j < adjMatrix.length; ++j){//for each position in the column, if the value > 0, increment count
                if (adjMatrix[j][i] > 0){
                    count++;
                }
            }
            inDegrees[i] = count;//assign the count as the corresponding in-degree to position i in inDegrees
        }


        //sorting
        boolean stopLoop = Visited[0];//initialize stopLoop for the while loop
        for (int i = 0; i < Visited.length; ++i){//loop through all elements in Visited, stopLoop will only be true when all elements in Visited are true
            stopLoop = stopLoop && Visited[i];
        }
        while (!stopLoop){
            int target = -1;
            for (int i = 0; i < Visited.length; ++i){//loop through each vertex
                if ((Visited[i] == false) && (inDegrees[i] == 0)){//if the vertex is not visited and its in-degree is 0, assign its id to target and break inner loop
                    target = i;
                    break;
                }
            }
            arr.add(target);//add target to arr
            Visited[target] = true;//mark target as visited
            int[] neighbor = neighbors(target);//get target's neighbor
            for (int i = 0; i < neighbor.length; ++i){//decrement the in-degree of target's neighbors
                inDegrees[neighbor[i]]--;
            }


            //update stopLoop
            stopLoop = Visited[0];//initialize stopLoop for the while loop
            for (int i = 0; i < Visited.length; ++i){//loop through all elements iun Visited, stopLoop will only be true when all elements in Visited are true
                stopLoop = stopLoop && Visited[i];
            }
        }


        return arr;
    }
}
