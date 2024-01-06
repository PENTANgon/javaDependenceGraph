package graphStructures;

import java.util.Objects;

/**
 * The Class GraphNode.
 */
public class GraphNode {
    
    /** The info. */
    private String info;
    
    /** The id. */
    private int id;
    
    private Object data;
    
    /** The exporting. */
    public static boolean exporting = false;

    /**
     * Instantiates a new graph node.
     *
     * @param id the id
     * @param string the string
     * @param data the class of node
     */
    public GraphNode(int id, String string, String data) {
        this.info = string;
        this.id = id;
        this.data = data;
    }
    public String getData() {
    	return data.toString();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	//System.out.println(this.data);
        if(!exporting)
            return ("[" + this.id + "] " + this.info);
        if(data.toString().equals("Entry"))
        	return "Entry";
        return "Line_" + this.id  + "_" + data;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!GraphNode.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final GraphNode other = (GraphNode) obj;
        return !(!Objects.equals(this.info, other.info) || other.info == null);
    }
}
