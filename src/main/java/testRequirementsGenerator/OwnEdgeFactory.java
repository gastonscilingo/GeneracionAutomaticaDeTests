package testRequirementsGenerator;

import java.util.Set;

import org.jgrapht.graph.EdgeSetFactory;

public class OwnEdgeFactory<V,E> implements EdgeSetFactory<V, E> {

	private final Class<? extends E> edgeClass;
	
	
	
	
	public OwnEdgeFactory(Class<? extends E> edgeClass) {
		this.edgeClass = edgeClass;
	}

	public E createEdge(V arg0, V arg1) {
		try {
			return edgeClass.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Edge Factory Failed ",e);
		}
	}

	@Override
	public Set<E> createEdgeSet(V arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
