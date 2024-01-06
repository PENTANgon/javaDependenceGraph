package automation;

import java.awt.EventQueue;
import automation.AnalysisNoGUI;
import graphStructures.*;
import pdg.*;
import pdg_gui.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class testing{
	public static void main(String[] args) throws IOException {
		//run Analysis and Save file in logical location
		File testParent = new File("/home/jannis/Documents/GitHub/BigCloneEval/ijadataset/bcb_reduced/31/default/116718.java");
		System.out.println("bruh "+(new File(testParent.getParent())).getParent().substring(new File(testParent.getParent()).getParent().lastIndexOf("/")) + testParent.getParent().substring(testParent.getParent().lastIndexOf("/")));
		AnalysisNoGUI analysis = new AnalysisNoGUI(testParent);
		try {
			analysis.runAnalysisAndMakeGraph("UTF-8");
			analysis.exportDOT("dotOutputs/");
		}
		catch(Throwable e){
			e.printStackTrace();
		}
	}
}