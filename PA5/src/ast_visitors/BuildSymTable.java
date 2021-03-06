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
import exceptions.SemanticException;
import java.lang.RuntimeException;

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
    private String errorString = "";
    private int errors = 0;
    
   public BuildSymTable() {
	HashMap<Node, Type> map = new HashMap();
	this.symTable = new SymTable(map);
   }

    public SymTable getSymTable() {
        return this.symTable;
    }   

   
   public void defaultIn(Node node) {
    try{
	if (node instanceof Program) {
	    this.symTable.addScope(new Scope("Program", "Program"));
	    symTable.setExpType(node, Type.VOID);
	    
	} else if (node instanceof MainClass) {
	    MainClass mc = (MainClass) node;
	    symTable.setExpType(node, Type.MAINCLASS);
	    ClassSTE classSTE = new ClassSTE(mc.getName(), true, null, new Scope("MainClass", mc.getName()));
	    //search for "Program" stack containing other classes
	    Scope progScope = this.symTable.lookupScope("Program");
	    if (progScope != null && progScope.lookupInnermost(classSTE.getName()) != null){
		errors++;
		throw new SemanticException("Redefined symbol "+mc.getName(), mc.getLine(), mc.getPos());
	    } else if(progScope != null){
		progScope.add(classSTE.getName(), classSTE);
	    }
	    //push STE's scope onto stack
	    this.symTable.addScope(classSTE.getScope());
	    
	} else if (node instanceof TopClassDecl) {
	    //add to quicklookup table
	    TopClassDecl tcd = (TopClassDecl) node;
	    //search for duplicates of class
	    Scope progScope = this.symTable.lookupScope("Program");
	    if (progScope != null && progScope.lookupInnermost(tcd.getName()) != null){
		errors++;
		throw new SemanticException("Redefined symbol "+tcd.getName(), tcd.getLine(), tcd.getPos());
	    }
	    symTable.setExpType(node, Type.CLASS);
	    //create VarSTEs and add to Class scope
	    LinkedList<VarDecl> ll = tcd.getVarDecls();
	    Iterator<VarDecl> iter = ll.listIterator();
	    Scope scope = new Scope("TopClassDecl", tcd.getName());
	    int offset = 0;
	    while(iter.hasNext()){
		VarDecl vd = iter.next();
		VarSTE varSTE = new VarSTE(vd.getName(), vd.getType());
		varSTE.setBase("Z");
		varSTE.setOffset(offset);
		scope.add(vd.getName(), varSTE);
		Type type = VarSTE.iTypeToType(vd.getType());
		offset += type.getAVRTypeSize();
		symTable.setExpType(vd, type);
	    }
	    ClassSTE classSTE = new ClassSTE(tcd.getName(), false, null, scope);
	    classSTE.setObjectSize(offset);
	    //search for "Program" stack containing other classes
	    if (progScope != null) {
		progScope.add(classSTE.getName(), classSTE);
	    }
	    //push new scope on top of stack to add MethodSTEs to
	    this.symTable.addScope(classSTE.getScope());
	    //check for double defined vardecls
	    iter = ll.listIterator();
	    Iterator<VarDecl> iter2 = ll.listIterator();
	    while(iter.hasNext()){
		VarDecl vd = iter.next();
		while(iter2.hasNext()){
		    VarDecl vdcomp = iter2.next();
		    if(vd.getName().equals(vdcomp.getName()) && vd != vdcomp){
			errors++;
			throw new SemanticException("Redefined symbol "+vdcomp.getName(), vdcomp.getLine(), vdcomp.getPos());
		    }
		}
	    }
	    
	} else if (node instanceof MethodDecl){
	    MethodDecl md = (MethodDecl) node;
	    //get enclosing class scope
	    //Scope scope = this.symTable.lookupClosestScopeWith("TopClassDecl");
	    //Scope scope = symTable.peek();
	    //System.out.println("\nBuilding STE for MethodDecl "+md.getName());
	    TopClassDecl tcd = (TopClassDecl) node.parent();
	    //System.out.println("tcd null? "+(tcd == null));
	    //System.out.println("Looking up Scope for TCD "+tcd.getName());
	    Scope scope = this.symTable.lookupScope(tcd.getName());
	    //System.out.println("scope null? "+(scope == null));
	    Type t = VarSTE.iTypeToType(md.getType());
	    symTable.setExpType(md.getType(), t);
	    //create signature & VarSTEs
	    LinkedList<Formal> ll = md.getFormals();
	    String sig = "(";
	    Iterator<Formal> iter = ll.listIterator();
	    Iterator<Formal> iter2 = ll.listIterator();
	    Scope methodDeclScope = new Scope("MethodDecl", md.getName());
	    int offset=3;
	    while(iter.hasNext()){
		Formal f = iter.next();
		//check for redefined formals
		while(iter2.hasNext()){
		    Formal fcomp = iter2.next();
		    //System.out.println(fcomp.getName()+" .equals "+f.getName()+"?: "+(fcomp.getName().trim().equals(f.getName().trim())));
		    //System.out.println("fcomp != f : "+(fcomp != f));
		    if(fcomp.getName().trim().equals(f.getName().trim()) && fcomp != f){
			errors++;
			throw new SemanticException("Redefined symbol "+f.getName(), f.getLine(), f.getPos());
		    }
		}
		VarSTE varSTE = new VarSTE(f.getName(), f.getType());
		varSTE.setBase("Y");
		varSTE.setOffset(offset);
		methodDeclScope.add(f.getName(), varSTE);
		sig += printNodeName(f.getType().getClass().toString()) + " ";
		Type type = VarSTE.iTypeToType(f.getType());
		symTable.setExpType(f, type);
		offset += type.getAVRTypeSize();
	    }
	    sig = sig + ") return "+printNodeName(md.getType().getClass().toString());
	    //check for a redefined method
	    HashMap<String, STE> hm = scope.getDict();
	    Set<String> keys = hm.keySet();
	    Iterator<String> iter1 = keys.iterator();
	    while(iter1.hasNext()){
		String key = iter1.next();
		//check that it's actually a MethodSTE first
		MethodSTE mSTE;
		if(hm.get(key) instanceof MethodSTE){
		    mSTE = (MethodSTE) hm.get(key);
		    String[] sigsplit = mSTE.getmSignature().split(" ");
		    String returnType = sigsplit[sigsplit.length-1];
		    if(key == md.getName() && returnType == mSTE.getType().getClass().toString()){
			errors++;
			throw new SemanticException("Redefined symbol "+md.getName(), node.getLine(), node.getPos());
		    }
		}
	    }
	    //check for redefined VarDecls
	    Iterator<VarDecl> iterVD1 = md.getVarDecls().iterator();
	    Iterator<VarDecl> iterVD2 = md.getVarDecls().iterator();
	    while(iterVD1.hasNext()){
		VarDecl vd1 = iterVD1.next();
		while(iterVD2.hasNext()){
		    VarDecl vd2 = iterVD2.next();
		    /*System.out.println("Checking vardecls for method "+md.getName());
		    System.out.println("\t"+vd1.getName()+" .equals "+vd2.getName()+"?: "+(vd1.getName().trim().equals(vd2.getName().trim())));
		    System.out.println("\t"+"vd1 != vd2 : "+(vd1 != vd2));*/
		    if(vd1.getName().equals(vd2.getName()) && vd1 != vd2){
			errors++;
			throw new SemanticException("Redefined symbol "+vd2.getName(), vd2.getLine(), vd2.getPos());
		    }
		}
		VarSTE varSTE = new VarSTE(vd1.getName(), VarSTE.iTypeToType(vd1.getType()));
    		varSTE.setBase("Y");
		varSTE.setOffset(offset);
		Type type = VarSTE.iTypeToType(vd1.getType());
		offset += type.getAVRTypeSize();
		methodDeclScope.add(vd1.getName(), varSTE);
		symTable.setExpType(vd1, type);
	    }
	    //check VarDecls and Formals don't conflict
	    iterVD1 = md.getVarDecls().iterator();
	    Iterator<Formal> iterF1 = ll.iterator();
	    while(iterVD1.hasNext()){
		VarDecl vd = iterVD1.next();
		while(iterF1.hasNext()){
		    Formal f = iterF1.next();
		    //System.out.println("Checking vardecls and formals for method "+md.getName());
		    //System.out.println("\t"+vd.getName()+" .equals "+f.getName()+"?: "+(vd.getName().trim().equals(f.getName().trim())));
		    //System.out.println("\t"+"vd != f : "+(vd != f));
		    if(vd.getName().equals(f.getName())){
			errors++;
			throw new SemanticException("Redefined symbol "+f.getName(), f.getLine(), f.getPos());
		    }
		}
	    }
	    //make sure method doesn't already exist
	    if(scope.lookupInnermost(md.getName()) != null){
		errors++;
		throw new SemanticException("Redefined method "+md.getName(), node.getLine(), node.getPos());
	    }
	    //push STE's scope onto stack
	    MethodSTE methSTE = new MethodSTE(md.getName(), sig, scope, t);
	    scope.add(methSTE.getName(), methSTE);
	    this.symTable.addScope(methodDeclScope);
	    Type mdType = Type.stringToType(methSTE.getReturn());
	    symTable.setExpType(md, mdType);
	} else if (node instanceof VarDecl){
	    //ClassDecls are setup at ClassDecl node
	    //Method VarDecls are setup at MethodDecl node
	}
    }catch(SemanticException e){
	//System.out.println("SemanticException");
	System.out.println(e.getMessage());
    }
   } //end defaultIn


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
		MethodDecl n = (MethodDecl) node;
		if(n.getExp() == null){
		    symTable.setExpType(n, Type.VOID);
		}else{
		    Type t = symTable.getExpType(n.getExp());
		    t = VarSTE.iTypeToType(n.getType());
		    symTable.setExpType(n, t);
		}
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
	    } else if (node instanceof ColorType){
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
		    //System.out.println("IdLiteral: "+id.getLexeme()+" "+varSTE.getmType().toString());
		    this.symTable.setExpType(node, varSTE.getmType());
		} else {
		    this.symTable.setExpType(node, Type.VOID);
		}
	    } else if (node instanceof EqualExp){
		    symTable.setExpType(node, Type.BOOL);
	    } else if (node instanceof TrueLiteral){
		    symTable.setExpType(node, Type.BOOL);
	    } else if (node instanceof MeggyDelay){
		    symTable.setExpType(node, Type.VOID);
	    } else if (node instanceof MeggyToneStart){
		    symTable.setExpType(node, Type.VOID);
	    } else if (node instanceof MeggyCheckButton){
		    symTable.setExpType(node, Type.BUTTON);
	    } else if (node instanceof MeggyGetPixel){
		    symTable.setExpType(node, Type.COLOR);
	    } else if (node instanceof ToneLiteral){
		    symTable.setExpType(node, Type.TONE);
	    } else if (node instanceof WhileStatement){
		    symTable.setExpType(node, Type.VOID);
	    } else if (node instanceof ThisLiteral){
		    //symTable.setExpType(node, Type.CLASS);
	    } else if (node instanceof CallExp){
		CallExp callExp = (CallExp) node;
		MethodSTE methodSTE = (MethodSTE) this.symTable.lookup(callExp.getId());
		Type t = null;
		if(methodSTE != null){
		    String r = methodSTE.getReturn();
		    //System.out.println("CallExp "+callExp.getId()+" return: "+r);
		    symTable.setExpType(node, Type.stringToType(r));
		    symTable.setExpType(node, t);
		}
	    } else if (node instanceof MinusExp) {
		symTable.setExpType(node, Type.INT);
	    } else if(node instanceof AssignStatement){
		AssignStatement as = (AssignStatement) node;
		VarSTE varSTE = (VarSTE) this.symTable.lookup(as.getId());
		Type t = varSTE.getType();
		symTable.setExpType(node, t);
	    }else if(node instanceof BoolType){
		symTable.setExpType(node, Type.BOOL);
	    } else if (node instanceof Program){
		if(errors > 0){
		    throw new RuntimeException("Errors found while building symbol table");
		}
	    }
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
