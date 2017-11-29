import java.util.Stack;

public class GraphAdjMatrix implements Graph{
	private int[][]edges;
	
	public GraphAdjMatrix(int vertices){
		edges = new int[vertices][vertices];
	}
	//unweighted directed
	public void addEdge(int src, int tar){
		edges[src][tar] = 1;
	}
	//check the adjmatrix row for selected number. Any value that == 1 means it is an outdegree
	public int outdegree(int v1){
		int degree = 0;
		for(int i = 0; i < edges.length; i++){
			if(edges[v1][i] == 1)
				degree++;
		}
		
		return degree;
	}
	//same as outdegree but switch to check column, where indegrees are 
	public int indegree(int v1){
		int degree = 0;
		for(int i = 0; i < edges.length; i++){
			if(edges[i][v1] == 1)
				degree++;
		}
		
		return degree;
	}
	//makes an array of outdegrees(neighbors)
	public int[] neighbors(int vertex){
		int increment = 0;
		int[] neighbors = new int[outdegree(vertex)];
		for(int i = 0; i < edges.length; i++){
			if(edges[vertex][i] != 0){
				neighbors[increment] = i;
				increment++;
			}
		}
		return neighbors;
	}
	//depth first search
	public void topologicalSort() {
		boolean[] visited = new boolean[edges.length];
		for(int i = 0; i < edges.length; i++){
			if(!visited[i]){
				topologicalSort(i, visited);
			}
		}
		
	}
	//checks the starting point, makes a stack and refreshes the stack for the neighbors
	//repeats process as long as the vertex hasn't been visited yet to prevent cycling
	private void topologicalSort(int vertex, boolean[] visited) {
		Stack<Integer> s = new Stack<Integer>();
		visited[vertex] = true;
		s.push(new Integer(vertex));
		System.out.print(vertex);
		while(!s.empty()){
			int v = s.pop();
			int[] it = neighbors(v);
			for(int i = it.length; i > 0; i--){
				if(!visited[i]){
					System.out.print(i);
					visited[i] = true;
					s.push(new Integer(i));
					}
				}
			}
		}
}