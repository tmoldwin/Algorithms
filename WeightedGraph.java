import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

/*
 * A weighted graph representation for Dijkstra's algorithm
 */
public class WeightedGraph {
	int vertexCount;
	int[][] adjMatrix;
	PriorityQueue<Vertex> pq;
	ArrayList<Vertex> vertices;

	
	private class Vertex implements Comparable<Vertex>{
		private int ID;
		public int distance;


		public Vertex(int i){
			ID = i;
		}

		@Override
		public int compareTo(Vertex o) {
			// TODO Auto-generated method stub
			return Integer.valueOf(distance).compareTo((Integer.valueOf(o.distance)));
		}
		

	}

	public WeightedGraph(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		vertexCount = scanner.nextInt();
		adjMatrix = new int[vertexCount][vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				adjMatrix[i][j] = scanner.nextInt();
			}
		}
		pq = new PriorityQueue<Vertex>();
		vertices = new ArrayList<Vertex>();
	}

	// if true, there is an edge from i to j
	private int getEdge(int i, int j) {
		return adjMatrix[i][j];
	}

	private Vector<Vertex> getEdges(Vertex vertex) {
		Vector<Vertex> edges = new Vector<Vertex>();

		for (int i = 0; i < vertexCount; i++) {
			if (getEdge(vertex.ID, i) > 0) {
				edges.add(vertices.get(i));
			}
		}
		return edges;
	}

	private void initializeSingleSource(int s) {
		for (int i = 0; i < vertexCount; i++) {
			Vertex v = new Vertex(i);
			v.distance = 500;
			vertices.add(v);
			pq.add(v);
		}
		vertices.get(s).distance = 0;
		
		
	}
	
	private void relax(Vertex u, Vertex v){
		if(v.distance > u.distance + adjMatrix[u.ID][v.ID]){
			v.distance = u.distance + adjMatrix[u.ID][v.ID];
		}
		
	}
	
	public Vector<Vertex> dijkstra(){
		initializeSingleSource(0);
		Vector<Vertex> determined = new Vector<Vertex>();
		while(!pq.isEmpty()){
			Vertex u = pq.remove();
			determined.add(u);
			Vector<Vertex> adj = getEdges(u);
			for(Vertex v:adj){
				relax(u,v);
			}
		}
		return determined;
	}

	public static void main(String[] args) throws FileNotFoundException{
		try {
			WeightedGraph wg = new WeightedGraph("graph3");
			Vector<Vertex> vertices = wg.dijkstra();
			for(Vertex v: vertices){
				System.out.println(v.ID + " " + v.distance);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
