package testRequirementsGenerator;

import org.jgrapht.graph.DefaultEdge;

public class Edge<V> extends DefaultEdge {

	private String label;
	private V source;
	private V target;
	
	public Edge(V v1, V v2, String label) {
		super();
		this.source = v1;
		this.target = v2;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}

	public V getSource() {
		return source;
	}
	
	public V getTarget() {
		return target;
	}
	
	
}
