/**
 * This ast walker generates dot output for the AST.  
 *
 * 6/06 - Modified from Brian Richard's ParserTest.Java.
 *        Michelle Strout
 */
package ast_visitors;

import java.io.PrintWriter;
import java.util.Stack;
import java.util.HashMap;
import java.util.*;

import ast.visitor.DepthFirstVisitor;
import ast.node.*;
import symtable.SymTable;
import symtable.Type;
import symtable.*;

/**
 * We extend the DepthFirstAdapter.  
 * Visitors invoke a defaultCase method on each node they visit.  
 * We override this method so that it
 * prints out dot info about a node.
 */

public class BuildSymTable extends DepthFirstVisitor
{
    private SymTable symTable;
    public SymTable getSymTable() {
        return this.symTable;
    }   

   /** Constructor takes a PrintWriter, and stores in instance var. */
   public BuildSymTable() {
	HashMap<Node, Type> map = new HashMap();
	this.symTable = new SymTable(map);
   }

   
   /** Upon entering each node in AST, check of this node is the root
   to generate start of .dot file, output the dot output for the node.
   */
   public void defaultIn(Node node) {
		if (node instanceof Program) {
			this.symTable.addScope(new Scope("Program"));
			symTable.setExpType(node, Type.VOID);
		} else if (node instanceof MainClass) {
				MainClass mc = (MainClass) node;
				symTable.setExpType(node, Type.MAINCLASS);
				
				ClassSTE classSTE = new ClassSTE(mc.getName(), true, null, new Scope("MainClass"+mc.getName()));
				//search for "Program" stack containing other classes
				Scope progScope = this.symTable.lookupScope("Program");
				if (progScope != null){
					progScope.add(classSTE.getName(), classSTE);
				}
				//push STE's scope onto stack
				this.symTable.addScope(classSTE.getScope());
		} else if (node instanceof TopClassDecl) {
				//add to quicklookup table
				TopClassDecl tcd = (TopClassDecl) node;
				symTable.setExpType(node, Type.CLASS);
				ClassSTE classSTE = new ClassSTE(tcd.getName(), false, null, new Scope("TopClassDecl"+tcd.getName()));
				//search for "Program" stack containing other classes
				Scope progScope = this.symTable.lookupScope("Program");
				if (progScope != null){
					progScope.add(classSTE.getName(), classSTE);
				}
				//push new scope on top of stack to add MethodSTEs to
			this.symTable.addScope(classSTE.getScope());
		}else if (node instanceof MethodDecl){
				MethodDecl md = (MethodDecl) node;
				Type t = VarSTE.iTypeToType(md.getType());
				symTable.setExpType(node, t);
				//create signature & VarSTEs
				LinkedList<Formal> ll = md.getFormals();
				String sig = "(";
				Iterator<Formal> iter = ll.listIterator();
				Scope scope = new Scope("MethodDecl"+md.getName());
				while(iter.hasNext()){
					Formal f = iter.next();
					scope.add(f.getName(), new VarSTE(f.getName(), f.getType()));
					sig += printNodeName(f.getType().getClass().toString()) + " ";
					Type type = VarSTE.iTypeToType(f.getType());
					symTable.setExpType(f, type);
				}
				sig = sig + ") return "+printNodeName(md.getType().getClass().toString());
				MethodSTE methSTE = new MethodSTE(md.getName(), sig, scope, t);
				//add STE to existing scope @ top of stack
				this.symTable.insert(methSTE);
				//push STE's scope onto stack
				this.symTable.addScope(scope);
		}
   }

	
   
   public void defaultOut(Node node) {
       if (node != null){
			if (node instanceof Formal){
				Formal f = (Formal) node;
				Type t = VarSTE.iTypeToType(f.getType());
				symTable.setExpType(node, t);
				
				
			} else if (node instanceof VarDecl) {
				VarDecl vd = (VarDecl) node;
				Type t = VarSTE.iTypeToType(vd.getType());
				symTable.setExpType(node, t);
			} else if (node instanceof MethodDecl) {
			}
			if (node instanceof NewExp){
				symTable.setExpType(node, Type.CLASS);
			} else if (node instanceof CallStatement){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof IntLiteral){
				symTable.setExpType(node, Type.INT);
			} else if (node instanceof ByteCast){
				symTable.setExpType(node, Type.BYTE);
			} else if (node instanceof ColorLiteral){
				symTable.setExpType(node, Type.COLOR);
			} else if (node instanceof MeggySetPixel){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof BlockStatement){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof VoidType){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof ByteType){
				symTable.setExpType(node, Type.BYTE);
			} else if (node instanceof Formal && symTable.getExpType(node) == null){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof IdLiteral){
				IdLiteral id = (IdLiteral) node;
				VarSTE varSTE = (VarSTE) symTable.lookup(id.getLexeme());
				if(varSTE != null){
					System.out.println("IdLiteral: "+id.getLexeme()+" "+varSTE.getmType().toString());
					this.symTable.setExpType(node, varSTE.getmType());
				} else {
					this.symTable.setExpType(node, Type.VOID);
				}
			} else if (node instanceof TrueLiteral){
				symTable.setExpType(node, Type.BOOL);
			} else if (node instanceof MeggyDelay){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof MeggyToneStart){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof MeggyCheckButton){
				symTable.setExpType(node, Type.BUTTON);
			} else if (node instanceof ToneLiteral){
				symTable.setExpType(node, Type.TONE);
			} else if (node instanceof WhileStatement){
				symTable.setExpType(node, Type.VOID);
			} else if (node instanceof ThisLiteral){
				symTable.setExpType(node, Type.CLASS);
			} else if (node instanceof CallExp){
				CallExp callExp = (CallExp) node;
/*				MethodSTE methodSTE = (MethodSTE) this.symTable.lookup(callExp.getId());
				System.out.println("callExp.getId(): "+callExp.getId());
				System.out.println("null ste? "+(methodSTE == null));
				System.out.println("null type? "+(methodSTE.getType() == null));
				symTable.setExpType(node, methodSTE.getType());
/*			if (node instanceof ){
				symTable.setExpType(node, Type.);
*/			} //else if (node instanceof ) {
				
		}
   }
 
   /** A helper that trims a node's class name before printing it.
    * (E.g., "node.Token" --> "Token".) 
    */
   private String printNodeName(String str) {
		if(str != null){
      String name = str.substring(str.lastIndexOf('.')+1);
      return (name);
	} else {return null;}
   } 
 
}
