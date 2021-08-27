package testRequirementsGenerator;

import java.util.LinkedList;
import java.util.List;

public class Path implements Comparable{
	
	private LinkedList<Vertex> path = null;
	private boolean hasCycle;
	private boolean isFinal;
	private boolean coveredByTest;

	public Path() {
		path = new LinkedList<Vertex>();
		hasCycle = false;
		isFinal = false;
	}
	
	public Path(LinkedList<Vertex> path) {
		super();
		this.path = path;
		hasCycle = hasCycle();
		isFinal = path.getLast().isFinal();
	}

	public Path(LinkedList<Vertex> path, boolean hasCycle) {
		super();
		this.path = path;		
		this.hasCycle = hasCycle;
		this.isFinal = path.getLast().isFinal();
	}
	
	public Path(Vertex v) {
		path = new LinkedList<Vertex>();
		path.add(v);
		hasCycle = false;
		isFinal = v.isFinal();
	}

	public boolean hasVertex(Vertex v) {
		return path.contains(v);
	}
	
	public void addVertex(Vertex v) {
		path.add(v);	
		hasCycle = hasCycle();
		isFinal = v.isFinal();
	}
	
	public void addVertex(int index, Vertex v) {
		if(index == 0 && v.isFinal())
			throw new IllegalArgumentException("try to insert final node as path head");
		path.add(index, v);
		hasCycle = hasCycle();
	}
	
	public Vertex getFirst() {
		return path.getFirst();
	}
	
	public Vertex getLast() {
		return path.getLast();
	}
	
	public void setFinal(boolean b) {
		isFinal = b;
	}
	
	public boolean isFinal() {
		return path.getLast().isFinal() || isFinal;
	}
	
	public void setCyclic(boolean b) {
		hasCycle = b;
	}
	
	private boolean hasCycle() {
		for(int i = 0; i < path.size(); i++) {
			for(int j = i+1; j < path.size(); j++) {
				if(path.get(i).equals(path.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCyclic() {
		return hasCycle;
	}
	
	public boolean isCoveredByTest() {
		return coveredByTest;
	}

	public void setCoveredByTest(boolean coveredByTest) {
		this.coveredByTest = coveredByTest;
	}
	
	/*
	 * TODO repair this method. It not works for large paths that contains @p as subpath
	 * after first prefix occurrence.  
	 */
	public boolean isSubpath(Path p) {
		if(p.getSize() > path.size())
			return false;
		
		int i = path.indexOf(p.getFirst());
		if( i == -1 || i+p.getSize() > path.size())
			return false;
		
		for(int j = i; j < path.size() && (j-i) < p.getSize(); j++) {
			if(! p.get(j-i).equals( path.get(j) ) )
				return false;
		}
		return true; 
	}
	
	/*
	 * Return true if this path covers path p without detour neither side trip.
	 */
	public boolean covers(Path p) {
		boolean result = false;
		List<Vertex> temporalPath = path;
		int achieve = 0;
		do {
			if(p.getSize() > temporalPath.size())
				return false;

			int i = temporalPath.indexOf(p.getFirst());
			if( i == -1 || i+p.getSize() > temporalPath.size())
				return false;
			
			for(int j = i; j < temporalPath.size() && (j-i) < p.getSize(); j++) {
				if(! p.get(j-i).equals( temporalPath.get(j) ) )
					break;
				achieve = j+1; 
			}
			
			temporalPath = temporalPath.subList(achieve, temporalPath.size());
			
			if(achieve-i == p.getSize())
				result = true;
		}while(!result);
		
		return result;
	}
	
	@Override
	public Path clone() {
		LinkedList<Vertex> newPath = new LinkedList<Vertex>();
		for(Vertex v : path) {
			newPath.add(v.clone());
		}
		return new Path(newPath,hasCycle);
		
	}
	
	public int getSize() {
		return path.size();
	}
	
	public Vertex get(int i) {
		return path.get(i);
	}
	

	@Override
	public String toString() {
		if(isCyclic()) {
			return "Path [" + path + "]*";
		}
		if(isFinal) {
			return "Path [" + path + "]!";
		}
		return "Path [" + path + "]";
	}

	@Override
	public int compareTo(Object o) {
		return this.getSize() - ((Path)o).getSize();
	}
	
	
}
