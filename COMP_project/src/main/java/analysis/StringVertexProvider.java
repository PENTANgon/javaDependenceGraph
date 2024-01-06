package analysis;

import java.util.Map;

import org.jgrapht.ext.VertexProvider;

public class StringVertexProvider implements VertexProvider<String>{

	@Override
	public String buildVertex(String label, Map attributes) {
		// TODO Auto-generated method stub
		return label;
	}
}