import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Toviah
 * A representation of a graph using an adjancency matrix. 
 * The graph has algorithms to perform a depth-first and breadth-first search.
 */
public class GraphAdjMatrix {
	int vertexCount;
	private int[][] adjMatrix;

	public int[][] getAdjMatrix() {
		return adjMatrix;
	}

	public GraphAdjMatrix(String fileName) throws IOException {
		Scanner scanner = new Scanner(new File(fileName));
		vertexCount = scanner.nextInt();
		adjMatrix = new int[vertexCount][vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				adjMatrix[i][j] = scanner.nextInt();
			}
		}
	}

	// if true, there is an edge from i to j
	private boolean hasEdge(int i, int j) {
		if (adjMatrix[i][j] == 1)
			return true;
		else
			return false;
	}

	private Vector<Integer> getEdges(int vertex) {
		Vector<Integer> edges = new Vector<Integer>();

		for (int i = 0; i < vertexCount; i++) {
			if (hasEdge(vertex, i)) {
				edges.add(i);
			}
		}
		return edges;
	}

	public int[] bfs(int source) {
		char[] colors = new char[vertexCount];
		int[] distances = new int[vertexCount];
		int[] predecessors = new int[vertexCount];
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		//initialize values
		for(int i = 0; i < vertexCount; i++){
			if(i != source){
				colors[i] = 'w';
				distances[i] = Integer.MAX_VALUE;
				predecessors[i] = -1;
			}
		}
		
		queue.add(source);
		while(!queue.isEmpty()){
			int u = queue.pop();
			Vector<Integer> adjacencies = getEdges(u);
			for (Integer v: adjacencies){
				if(colors[v] == 'w'){
					colors[v] = 'g';
					distances[v] = distances[u] + 1; 
					predecessors[v] = u;
					queue.add(v);
				}
				colors[u] = 'b';
			}
		}
		
		return distances;

	}

	public int[] dfs() {
		
		char[] colors = new char[vertexCount];
		int[] grayTime = new int[vertexCount];
		int[] blackTime = new int[vertexCount]; 
		int[] predecessors = new int[vertexCount];
		
		//intialize values
		for (int i = 0; i < vertexCount; i++){
			colors[i] = 'w';
			predecessors[i] = -1;
			grayTime[i] = 0;
			blackTime[i] = 0;
		}
		AtomicInteger time = new AtomicInteger(0);
		for (int i = 0; i < vertexCount; i++){
			if (colors[i] == 'w'){
				dfsVisit(i, colors, grayTime, blackTime, predecessors, time);
			}
		}
		return blackTime;
	}

	private void dfsVisit(int vertex, char[] colors, int[] grayTime, int[] blackTime, int[] predecessors, AtomicInteger time) {		
		grayTime[vertex] = time.incrementAndGet();
		colors[vertex] = 'g';
		Vector<Integer> adj = getEdges(vertex);
		for (Integer v:adj){
			if(colors[v] == 'w'){
				predecessors[v] = vertex;
				dfsVisit(v, colors, grayTime, blackTime, predecessors, time);
			}
		colors[vertex] = 'b';
		
		}
		blackTime[vertex] = time.incrementAndGet();		
	}

	public static void main(String[] args) throws IOException {
		
		GraphAdjMatrix graph = new GraphAdjMatrix("graph1");
		/*
		int[][] matrix = graph.getAdjMatrix();
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		*/
		int[] distances = graph.bfs(1);
		System.out.println(Arrays.toString(distances));
		
		GraphAdjMatrix graph2 = new GraphAdjMatrix("graph2");
		int[] times = graph2.dfs();
		System.out.println(Arrays.toString(times));
		
	}
	
}
