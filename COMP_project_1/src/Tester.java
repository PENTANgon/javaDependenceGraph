import java.io.FileInputStream; 

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class Tester {
	public static void main(String[] args) throws Exception {
		// creates an input stream for the file to be parsed
		FileInputStream in = new FileInputStream("src//test.java");
		CompilationUnit cu;
		try {
		// parse the file
			cu = JavaParser.parse(in);
		} finally {
		    in.close();
		}
        // visit method nodes and print the methods names
       // new MethodVisitor().visit(cu, null);
        // visit expressions,variabledeclarationexpr and variabledeclarators nodes
        //print the above nodes using toString() 
        //new MyVisitor().visit(cu,null);
    
		new MethodVisitor().processNode(cu);
	}
	
}

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    class MethodVisitor extends VoidVisitorAdapter {
    	void processNode(Node child2){
    		
    		System.out.println(child2.getClass()+ "\n" + child2.toString());
    		System.out.println("--------------------------------");
    		
    		for(Node child: child2.getChildrenNodes()){
    			processNode(child);
    		}
    	}
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this 
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }
    

	class MyVisitor extends VoidVisitorAdapter
	{
	
	    @Override
	    public void visit (ExpressionStmt stmt, Object args)
	    {
	        System.out.println(stmt.toString()+" THIS IS AN EXPRESSION NODE");
	    	super.visit(stmt,args);
	    }
	
	    @Override
	    public void visit (VariableDeclarationExpr declarationExpr, Object args)
	    {
	    	System.out.println(declarationExpr.toString() + " THIS IS A VARIABLE DECLARATION NODE");
	    	super.visit(declarationExpr, args); 
	        
	    }
	
	    @Override
	    public void visit(VariableDeclarator declarator, Object args)
	    {
	    	System.out.println(declarator.toString() + " THIS IS A DECLARATOR NODE");
	    	super.visit(declarator,args);
	    }
	
	}