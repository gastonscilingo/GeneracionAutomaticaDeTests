package testRequirementsGenerator;

public class Vertex {
	
	private String line;
	private int lineNumber;
	private boolean root;
	private boolean finalVertex;
	
	public Vertex(String line, int n) {
		super();
		this.line = line;
		this.lineNumber = n;
	}
	
	public Vertex(String line, int n, boolean b) {
		super();
		this.line = line;
		this.lineNumber = n;
		this.finalVertex = b;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getLinenumber() {
		return lineNumber;
	}

	public void setLinenumber(int n) {
		this.lineNumber = n;
	}

	@Override
	public String toString() {
		//return lineNumber+": "+line;
		return line;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + lineNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		return true;
	}
	
	
	public boolean isFinal(){
		return finalVertex;
	}
	
	public void setFinal(boolean b) {
		finalVertex = b;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}
	
	public Vertex clone() {
		return new Vertex(line, lineNumber, finalVertex);
	}

}
