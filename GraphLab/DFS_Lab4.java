import java.util.*;
public class DFS_Lab4 {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex


    public DFS_Lab4(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }


    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }


    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    public static void main(String[] args) {
    int Counter = 0;
    String from = args[0];
    String to = args[1];
    Scanner input = new Scanner(System.in);
    Stack<String> namestack = new Stack();
    RedBlackTrees<String, Integer> b = new RedBlackTrees<>();
    while(input.hasNext()){
      String state = input.next();
      namestack.push(state);
    }
    for(String i: namestack){
      if(!b.contains(i)){
        b.put(i,Counter);
        Counter ++;
      }
      else{
        continue;
      }
    }
    Graph graph = new Graph(b.size());
    for(int i=namestack.size(); i>0;i-=2){
      graph.addEdge(b.get(namestack.pop()),b.get(namestack.pop()));
    }

    DFS_Lab4 a = new DFS_Lab4(graph,b.get(from));

    System.out.print(from + " to " + to + ": ");
    for(int i: a.pathTo(b.get(to))){
      System.out.print(b.getKey(i) + " ");
    }

    }

}
