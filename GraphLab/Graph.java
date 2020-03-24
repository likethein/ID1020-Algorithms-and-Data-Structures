public class Graph{

	private final int V;
	private int E;
	private Bag<Integer>[] adj;

	public Graph(int V){

		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];

		for(int i = 0; i<V; i++){
			adj[i] = new Bag<Integer>();
		}
	}

	public int V() {
        return V;
    }

    public int E() {
        return E;
    }

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public int degree(Graph g, int v){



		return adj[v].size();
	}

	public int maxDegree(Graph g){
		int max = 0;
		for(int i = 0; i < g.V(); i++){
			if(degree(g,i) > max){
				max = degree(g, i);
			}
		}
		return max;
	}

	public double averageDegree(Graph g){
		return 2.0 * g.E() / g.V();
	}

	public int numberOfSelfLoops(Graph g){
		int count = 0;
		for(int v = 0; v<g.V(); v++){
			for(int w : g.adj(v)){
				if(v == w) count++;
			}
		}
		return count/2;
	}

	public Iterable<Integer> adj(int v){
		return adj[v];
	}

	public static void main(String[] args){

		Graph a = new Graph(5);

		a.addEdge(1,1);

		System.out.println(a.degree(a,1));
	}
}
