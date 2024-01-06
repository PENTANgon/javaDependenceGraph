package automation;

import java.awt.EventQueue;
import automation.AnalysisNoGUI;
import graphStructures.*;
import pdg.*;
import pdg_gui.*;
import java.io.File;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.PrintWriter;

public class CallBCB{
	
	private static ArrayList<Integer> runAnalysisOnDir(File[] files, ArrayList<Integer> totalSucc) {
		for(File currFile : files) {
			if(currFile.isDirectory()) {
				//Go into Subdir and look for files there
				System.out.println(currFile.getAbsolutePath());
				totalSucc = runAnalysisOnDir(currFile.listFiles(), totalSucc);
			}
			else if(currFile.isFile()){
				//run Analysis and Save file in logical location
				totalSucc.set(0, totalSucc.get(0)+1);
				System.out.println("\t" + currFile.getAbsolutePath());
				AnalysisNoGUI analysis = new AnalysisNoGUI(currFile);
				try {
					analysis.runAnalysisAndMakeGraph("UTF-8");
					analysis.exportDOT("dotOutputs" + getLastTwoFolder(currFile) + "/");
					totalSucc.set(1, totalSucc.get(1)+1);
				}
				catch(Throwable e){
					e.printStackTrace();
					if(e.getClass().equals(com.github.javaparser.TokenMgrError.class)) {
						analysis.runAnalysisAndMakeGraph("windows-31j");
						analysis.exportDOT("dotOutputs"+getLastTwoFolder(currFile) + "/");
						totalSucc.set(1, totalSucc.get(1)+1);
					}else {
						try {
							FileWriter out = new FileWriter("errors/error.txt", true);
							StringWriter sw = new StringWriter();
							e.printStackTrace(new PrintWriter(sw));
							String exceptionAsString = sw.toString();
							out.write(currFile.getAbsolutePath() + "\n" + exceptionAsString);
							out.close();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
		return totalSucc;
	}
	private static String getLastTwoFolder(File testParent) {
		return (new File(testParent.getParent())).getParent().substring(new File(testParent.getParent()).getParent().lastIndexOf("/")) + testParent.getParent().substring(testParent.getParent().lastIndexOf("/"));
	}
    public static void main(String[] args){
    	File entry = new File("/home/jannis/Documents/GitHub/BigCloneEval/ijadataset/bcb_reduced");
    	File[] files = entry.listFiles();
    	ArrayList<Integer> totalSucc = new ArrayList<Integer>();
    	totalSucc.add(0, 0);
    	totalSucc.add(1, 0);
    	totalSucc = runAnalysisOnDir(files, totalSucc);
    	System.out.println(totalSucc.get(1) + "/" + totalSucc.get(0) + " PDG created from files.");
    }
}