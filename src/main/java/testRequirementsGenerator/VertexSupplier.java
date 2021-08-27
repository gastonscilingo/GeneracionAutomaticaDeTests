package testRequirementsGenerator;

import java.util.function.Supplier;

public class VertexSupplier implements Supplier<Vertex> {

	@Override
	public Vertex get() {
		return new Vertex("default line", 0);
	}
	
	public Vertex get(Vertex v) {
		return v;
	}
	

}
