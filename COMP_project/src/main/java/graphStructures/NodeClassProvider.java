package graphStructures;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.VertexNameProvider;

public class NodeClassProvider<V> implements VertexNameProvider<V>{

	@Override
	public String getVertexName(V vertex) {
		return ((GraphNode) vertex).getData();
	}
	
}