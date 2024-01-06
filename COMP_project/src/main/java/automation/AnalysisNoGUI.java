package automation;

import java.awt.EventQueue;

import graphStructures.*;
import pdg.*;
import pdg_gui.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.StringEdgeNameProvider;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.DefaultDirectedGraph;

import com.github.javaparser.ParseException;

public class AnalysisNoGUI{
	private File file;
	
	private JTextArea consoleText = new JTextArea();
	
	private DirectedGraph<GraphNode, RelationshipEdge> hrefGraph;
	
	private PDGCore astPrinter = new PDGCore();
	
	public AnalysisNoGUI(File analysisFile) {
		file = analysisFile;
	}
	
	private void createGraph() {
		hrefGraph = new DefaultDirectedGraph<>(RelationshipEdge.class);
	}
    
	private boolean checkIfFolderExists(String location) {
        File theDir = new File(location);
        return !theDir.exists() && theDir.mkdirs();
    }
	
	public void exportDOT(String loc) {
		FileOutputStream out;
        try {
        	
        	String filename = file.getName() + "_res.dot";
        	checkIfFolderExists(loc);
            GraphNode.exporting = true;
            out = new FileOutputStream(loc + filename);
            @SuppressWarnings("rawtypes")
            NodeClassProvider ncp = new NodeClassProvider();
			DOTExporter<GraphNode, RelationshipEdge> exporter = new DOTExporter<>(
                    new StringNameProvider<>(), new NodeClassProvider<>(),
                    new StringEdgeNameProvider<>());
            System.out.println("Hierbinich");
            exporter.export(new OutputStreamWriter(out), hrefGraph);
            out.close();
        } catch (IOException e1) {
        	System.out.println("Fehler sind aufgetreten hihihi");
            e1.printStackTrace();
        }
        GraphNode.exporting = false;
	}
	
	public void runAnalysisAndMakeGraph(String encoding) {
        try {
            createGraph();
            GraphNode gn = new GraphNode(0, "Entry", "Entry");
            hrefGraph.addVertex(gn);
            if (astPrinter.addFile(new FileInputStream(file), hrefGraph, gn, consoleText, encoding)) {
            	
            }
        } catch (ParseException | IOException e1) {
            e1.printStackTrace();
        }
    }
	
}