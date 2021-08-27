package testRequirementsGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Main {

	public static void main(String[] args) {
	
		Graph<Vertex, DefaultEdge> g  = new DefaultDirectedGraph<Vertex,DefaultEdge>(DefaultEdge.class);
		
//		g  = buildGraph("book");
//		g  = buildGraph("exercise 5");
		g  = buildGraph("exercise 6");
		
		// Show graph
		Set<DefaultEdge> set = g.edgeSet();
		for(DefaultEdge e  : set) {
			System.out.println(e.toString());
		}
		
		// compute prime paths
		Set<Path> newPaths = primePaths(g);
		
		System.out.println("Result "+newPaths.size()+" Paths ");
		for(Path p : newPaths) {
			System.out.println(p.toString());
		}
		
		// Show test paths by length
		List<Path> sortedPaths = sortByLength(newPaths);
		
		System.out.println("Result "+sortedPaths.size()+" Paths ");
		for(Path p : sortedPaths) {
			System.out.println(p.toString());
		}
		

	}
	

	public static Set<Path> primePaths(Graph<Vertex, DefaultEdge> g){
		
		Set<Path> paths = new HashSet<Path>();
		Set<Vertex> vertexs = g.vertexSet();
		// Initialize procedure with zero length paths 
		for(Vertex v : vertexs) {
			if(g.outDegreeOf(v)==0){
				v.setFinal(true);
			}else {
				v.setFinal(false);
			}
			paths.add(new Path(v));
		}
		//extend paths
		boolean changes = true;
		int time = 0;
		while(changes) {
			time++;
			Set<Path> newPaths = new HashSet<Path>();
			for(Path p : paths) {
				boolean hasExtended = false;
				if( !p.isFinal() && !p.isCyclic()) {
					// try to extend
					Vertex lastVertex = p.getLast();
					Set<Vertex> nextVertexs = getTargets(lastVertex, g);
					for(Vertex v : nextVertexs) {
						if (!p.hasVertex(v)) {
							Path newPath = p.clone();
							newPath.addVertex(v);
							newPaths.add(newPath);
							hasExtended = true;
						}else {
							if( (p.getFirst().equals(v))) {
								Path newPath = p.clone();
								newPath.addVertex(v);
								newPath.setCyclic(true);
								newPaths.add(newPath);
								hasExtended = true;
							}
						}
					}
					if(!hasExtended) {
						p.setFinal(true);
						newPaths.add(p);
					}
				}// end try to extend
			}
			// filter old paths that are contained in newPaths
			for(Path p : paths){
				boolean removeP = false;
				for(Path ep : newPaths) {
					//if(ep.isSubpath(p)) {
					if(ep.covers(p)) {	
						removeP = true; 
						break;
					}
				}
				if(!removeP) {
					newPaths.add(p);
				}
			}
			
			changes = false;
			for(Path newPath : newPaths) {
					if(!paths.contains(newPath)) {
						changes = true;
						break;
					}	
			}
			changes = changes || (paths.size() != newPaths.size());
			
			paths = newPaths;
			
			System.out.println("Result "+newPaths.size()+" Paths for "+time+" time");
			for(Path p : newPaths) {
				System.out.println(p.toString());
			}
		}
		return paths;
	}
	
	
	private static Set<Vertex> getTargets(Vertex v, Graph<Vertex,DefaultEdge> g) {
		Set<DefaultEdge> edges = g.outgoingEdgesOf(v);
		Set<Vertex> vertexs = new HashSet<Vertex>();
		for(DefaultEdge e : edges)
			vertexs.add( g.getEdgeTarget(e));
		return vertexs;	
	}
	
	private static Set<Vertex> getSources(Vertex v, Graph<Vertex,DefaultEdge> g) {
		Set<DefaultEdge> edges = g.incomingEdgesOf(v);
		Set<Vertex> vertexs = new HashSet<Vertex>();
		for(DefaultEdge e : edges)
			vertexs.add( g.getEdgeSource(e));
		return vertexs;	
	}
	
	private static List<Path> sortByLength(Set<Path> paths) {
		LinkedList<Path> list = new LinkedList<Path>();
		for(Path p : paths)
			list.add(p);
		Collections.sort(list);
		return list;
	}
	
	
	private static Graph<Vertex, DefaultEdge> buildGraph(String string) {
		Graph<Vertex, DefaultEdge> g  = new DefaultDirectedGraph<Vertex,DefaultEdge>(DefaultEdge.class);
		
		switch(string){
			case "book":
				// Build a graph example from book
				Vertex v0 = new Vertex("n0",0);Vertex v1 = new Vertex("n1",1);Vertex v2 = new Vertex("n2",2);
				Vertex v3 = new Vertex("n3",3);Vertex v4 = new Vertex("n4",4);Vertex v5 = new Vertex("n5",5);
				Vertex v6 = new Vertex("n6",6);
				g.addVertex(v0);g.addVertex(v1);g.addVertex(v2);
				g.addVertex(v3);g.addVertex(v4);g.addVertex(v5);
				g.addVertex(v6);
				g.addEdge(v0, v1);
				g.addEdge(v0, v4);
				g.addEdge(v1, v2);
				g.addEdge(v1, v5);
				g.addEdge(v2, v3);
				g.addEdge(v3, v1);
				g.addEdge(v4, v4);
				g.addEdge(v4, v6);
				g.addEdge(v5, v6);
				break;
				
			case "exercise 5":
				// Graph of exercise 5
				v1 = new Vertex("n1",1); v2 = new Vertex("n2",2);
				v3 = new Vertex("n3",3); v4 = new Vertex("n4",4); v5 = new Vertex("n5",5);
				v6 = new Vertex("n6",6); Vertex v7 = new Vertex("n7",7); Vertex v8 = new Vertex("n8",8);
				Vertex v9 = new Vertex("n9",9); Vertex v10 = new Vertex("n10",10); Vertex v11 = new Vertex("n11",11);
				Vertex v12 = new Vertex("n12",12); Vertex v13 = new Vertex("n13",13); Vertex v14 = new Vertex("n14",14);
				Vertex v15 = new Vertex("n15",15); Vertex v16 = new Vertex("n16",16); Vertex v17 = new Vertex("n17",17);
				Vertex v18 = new Vertex("n18",18); Vertex v19 = new Vertex("n19",19);  
				
				g.addVertex(v1);g.addVertex(v2);g.addVertex(v3);g.addVertex(v4);g.addVertex(v5);g.addVertex(v6);g.addVertex(v7);g.addVertex(v8);g.addVertex(v9);
				g.addVertex(v10);g.addVertex(v11);g.addVertex(v12);g.addVertex(v13);g.addVertex(v14);g.addVertex(v15);g.addVertex(v16);g.addVertex(v17);
				g.addVertex(v18);g.addVertex(v19);
		
				g.addEdge(v1, v2);
				g.addEdge(v2, v3);g.addEdge(v2, v19);
				g.addEdge(v3, v4);g.addEdge(v3, v5);
				g.addEdge(v5, v6);g.addEdge(v5, v7);
									g.addEdge(v7, v8);g.addEdge(v7, v9);//if
				g.addEdge(v4, v10);g.addEdge(v6, v10);g.addEdge(v8, v10);g.addEdge(v9, v10);//
				//switch
				g.addEdge(v10, v11);g.addEdge(v10, v12);g.addEdge(v10, v13);g.addEdge(v10, v16);;g.addEdge(v10, v17);
																					g.addEdge(v16, v17);
				g.addEdge(v13, v14);g.addEdge(v13, v15); 
				g.addEdge(v14, v18);g.addEdge(v15, v18);
				g.addEdge(v11, v18);g.addEdge(v12, v18);g.addEdge(v17, v18);
				g.addEdge(v18, v2);
				break;
				
			case "exercise 6":
				v1 = new Vertex("n1",1); v2 = new Vertex("n2",2);
				v3 = new Vertex("n3",3); v4 = new Vertex("n4",4); v5 = new Vertex("n5",5);
				v6 = new Vertex("n6",6); v7 = new Vertex("n7",7); v8 = new Vertex("n8",8);
				v9 = new Vertex("n9",9);  v10 = new Vertex("n10",10);  v11 = new Vertex("n11",11);
				v12 = new Vertex("n12",12);  v13 = new Vertex("n13",13);  v14 = new Vertex("n14",14);
				v15 = new Vertex("n15",15);
								
				g.addVertex(v1);g.addVertex(v2);g.addVertex(v3);g.addVertex(v4);g.addVertex(v5);g.addVertex(v6);g.addVertex(v7);g.addVertex(v8);g.addVertex(v9);
				g.addVertex(v10);g.addVertex(v11);g.addVertex(v12);g.addVertex(v13);g.addVertex(v14);g.addVertex(v15);

				g.addEdge(v1, v2);
				g.addEdge(v2, v3);g.addEdge(v2, v11);
				g.addEdge(v3, v4);
				g.addEdge(v4, v5);g.addEdge(v4, v8);
				g.addEdge(v5, v6);g.addEdge(v5, v7);
				g.addEdge(v7, v4);
				g.addEdge(v6, v8);
				g.addEdge(v8, v9);g.addEdge(v8, v10);
				g.addEdge(v9, v10);
				g.addEdge(v10, v2);
				g.addEdge(v11, v12);
				g.addEdge(v12, v13);g.addEdge(v12, v15);
				g.addEdge(v13, v14);
				g.addEdge(v14, v12);
				break;
				
		}
		
		return g;
	}
	

}
