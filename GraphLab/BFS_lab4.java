import java.util.*;
public class BFS_lab4 {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    public BFS_lab4(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }




    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }


    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }


    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }

    public static void main(String[] args) {
    int Counter = 0; //Counter initated later for location
    String from = args[0]; //Stating what from and to is
    String to = args[1];
    Scanner input = new Scanner(System.in);//Scanner created
    Stack<String> namestack = new Stack(); //Stack created
    RedBlackTrees<String, Integer> b = new RedBlackTrees<>(); //new tree
    while(input.hasNext()){ //While there is a next line
      String state = input.next(); //next input is the next state
      namestack.push(state);//The state is pushed onto the stack
    }
    for(String i: namestack){ //For the whole stack
      if(!b.contains(i)){ //If the state is not in the tree
        b.put(i,Counter);//Place it at counter
        Counter ++;
      }
      else{
        continue;
      }
    }
    Graph graph = new Graph(b.size()); //Graph created of the tree size as those are the unique states
    for(int i=namestack.size(); i>0;i-=2){ //Going from the back forwards
      graph.addEdge(b.get(namestack.pop()),b.get(namestack.pop())); //Add both the states that are on one line
    }

    BFS_lab4 a = new BFS_lab4(graph,b.get(from));

    System.out.print(from + " to " + to + ": ");
    for(int i: a.pathTo(b.get(to))){
      System.out.print(b.getKey(i) + " "); //
    }
System.out.println();
    }

}
