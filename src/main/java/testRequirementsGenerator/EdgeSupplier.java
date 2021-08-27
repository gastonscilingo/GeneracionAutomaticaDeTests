package testRequirementsGenerator;

import java.util.function.Supplier;

public class EdgeSupplier implements Supplier<Edge<Vertex>> {

	@Override
	public Edge<Vertex> get() {
		return new Edge<Vertex>(new Vertex("default", 0), new Vertex("default", 0), "defaul label");
	}
	

	public Edge<Vertex> get(Vertex v1, Vertex v2) {
		return new Edge<Vertex>(v1, v2, "label: "+v1.toString()+","+v2.toString());
	}

}
