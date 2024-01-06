package analysis;

import java.util.Map;

import org.jgrapht.ext.EdgeProvider;
import graphStructures.RelationshipEdge;
public class RelationshipEdgeProvider implements EdgeProvider<String, RelationshipEdge<String>>{
	@Override
	public RelationshipEdge<String> buildEdge(String from, String to, String label, Map<String, String> attributes) {
		// TODO Auto-generated method stub
		return new RelationshipEdge(label);
	}
	
}