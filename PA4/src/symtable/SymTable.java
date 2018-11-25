package symtable;

import java.util.*;
import java.util.HashMap;
import java.io.*;

import symtable.Type;
import ast.node.*;

import exceptions.InternalException;

/** 
 * SymTable
 * ....
 * The symbol table also keeps a mapping of expression nodes to
 * types because that information is needed elsewhere especially
 * when looking up method call information.
 * 
 * @author mstrout
 * WB: Simplified to only expression types
 */
public class SymTable {

    private HashMap<Node,Type> mExpType = new HashMap<Node,Type>();
	private Stack<Scope> mScopeStack;
	private Stack<Integer> methodStack;
	private int scopeCount;
	private int steCount=0;
	private int portCount=0;
	private int progIndex;
	private PrintStream out;

    public SymTable(HashMap<Node, Type> st) {
		this.mExpType = st;
		this.mScopeStack = new Stack<Scope>();
    }
    
    public void setExpType(Node exp, Type t){
    	this.mExpType.put(exp, t);
    }
    
    public Type getExpType(Node exp) {
    	return this.mExpType.get(exp);
    }

	public void addScope(Scope s){
		mScopeStack.push(s);
	}

	public Scope removeScope(){
		return mScopeStack.pop();
	}

	public HashMap<Node, Type> getExpTypeMap(){
        Set keyset = mExpType.keySet();
		Iterator<Node> iter = keyset.iterator();
		while(iter.hasNext()){
			Node n = iter.next();
			Type t = mExpType.get(n);
			System.out.println(n.getClass().toString()+" "+t.toString());
		}
		return mExpType;
	}
/*
 */

    /** Lookup a scope containing classType in this symbol table.
     * Starts looking in innermost scope and then
     * look in enclosing scopes.
     * Returns null if scope is not found.
     */
    public Scope lookupScope(String classType) {
		Scope currentScope = mScopeStack.peek();
		if(currentScope.getName() != null && currentScope.getName() == classType){
				return currentScope;
		} else {
			//looks at other scopees if no return from current scope
			Iterator<Scope> iter = mScopeStack.iterator();
			while(iter.hasNext()){
				Scope scope = iter.next();
				printScope(scope);
				if(scope.getName() != null && scope.getName() == classType){
						return scope;
				}
			}
			return null;
		}
	}

    /** Lookup a symbol in this symbol table.
     * Starts looking in innermost scope and then
     * look in enclosing scopes.
     * Returns null if the symbol is not found.
     */
    public STE lookup(String sym) {
			Iterator<Scope> iter = mScopeStack.iterator();
			while(iter.hasNext()){
				Scope scope = iter.next();
				System.out.println("Scope: "+scope.getName());
				printScope(scope);
				if(scope.lookupInnermost(sym) != null){
					return scope.lookupInnermost(sym);
				}
			}
		return null;
    }

	public void printScope(Scope scope){
		HashMap<String, STE> dict = scope.getDict();
		Set keyset = dict.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()){
			String str = iter.next();
			
			System.out.println(str);
		}
	}

    /** Lookup a symbol in innermost scope only.
     * return null if the symbol is not found
     */
    public STE lookupInnermost(String sym) {
        Scope currentScope = mScopeStack.peek();
        return currentScope.lookupInnermost(sym);
    }


    /** When inserting an STE will just insert
     * it into the scope at the top of the scope stack.
     */
    public void insert( STE ste) {
        /* WRITE ME */
		Scope currentScope = mScopeStack.peek();
		currentScope.add(ste.getmName(), ste);
    }


    /**
     * Lookup the given method scope and make it the innermost
     * scope.  That is, make it the top of the scope stack.
     */
    public void pushScope(String id) {
        /* WRITE ME */
		//THIS IS AMBIGUOUS -- WOULDN'T IT MAKE MORE SENSE TO POP UNTIL
		// DESIRED SCOPE IS ON TOP?
		if(this.lookup(id) != null){
			Scope currentScope = mScopeStack.peek();
			Iterator<Scope> iter =mScopeStack.iterator();
			
			while(iter.hasNext()){

			}
		}
    }



   /** Upon entering each node in AST, check of this node is the root
   to generate start of .dot file, output the dot output for the node.
   */
	public void outputDot(PrintStream ps){
		System.out.println("stack size: "+ mScopeStack.size());
		this.out = ps;
		out.println("digraph ASTGraph {");
		out.println("\tgraph [pad=\"0.5\", nodesep=\"0.5\", ranksep=\"2\"];");
		out.println("\tnode [shape=plain]");
		out.println("\trankdir=LR;");

		while ( !mScopeStack.empty() ) {
			scopeDotOutput(mScopeStack.peek());
			mScopeStack.pop();
		}
		if (mScopeStack.empty()) {
		    out.println("}");
		}   
		out.flush();
		// store this node id on the nodeStack
		// the call to nodeDotOutput increments for
		// the next guy so we have to decrement here
		//mScopeStack.push(scopeCount-1);
	}   



   /** A helper output routine that generates the dot node and the 
	*  dot edges from the parent to the node.
    */
   private void scopeDotOutput(Scope scope)
   {
		// print out scope and its members
		//as you pop scopes and approach broadest scope level
		//connect new scope members to their previous scopes
		HashMap<String, STE> hm = scope.getDict();
		Set keyset = hm.keySet();
/*		if(scope.getName() != null){
			System.out.println("scope name: " + scope.getName());
		} else {
			System.out.println("scope name: null");
		}
		System.out.println("keys in scope: " + keyset.size());
*/		
		int currScopeStart = portCount;
		//print out scope
		Iterator<String> iter = keyset.iterator();
		out.println(scope.getName() + " [ label=<");
		out.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">");
		out.println("\t<tr><td port=\""+scope.getName()+"0"+"\"><i>Scope "+scope.getName()+"</i></td></tr>"); 
		iter = keyset.iterator();
		int iterCount = 1;
		while(iter.hasNext()){
			String key = iter.next();
			STE value = hm.get(key);
			out.println("\t<tr><td port=\""+(scope.getName())+iterCount+"\">mDict["+value.getName()+"]</td></tr>");
			iterCount++;
		}
		out.println("</table>>];");

		//print members of scope
		iter = keyset.iterator();
		iterCount = 0;
		while(iter.hasNext()){
			String key = iter.next();
			STE value = hm.get(key);
			if (value instanceof ClassSTE) {
				ClassSTE val = (ClassSTE) value;
				out.println(val.getName()+ " [ label=<");
				out.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">");
				out.println("\t<tr><td port=\""+val.getName()+iterCount+"\"><i>ClassSTE</i></td></tr>");	
				out.println("\t<tr><td>mName="+val.getName()+"</td></tr>");
				out.println("\t<tr><td>mMain="+val.getmMain()+"</td></tr>");
				if(val.getmSuperClass() == null){
					out.println("\t<tr><td>mSuperClass=NULL</td></tr>");
				} else {
					out.println("\t<tr><td>mSuperClass="+val.getmSuperClass()+"</td></tr>");
				}
				out.println("\t<tr><td port=\""+(portCount++)+"\">mScope</td></tr>");
				out.println("</table>>];");
				out.println(scope.getName()+":"+scope.getName() +iterCount + " -> " + val.getName()+":"+val.getName() + iterCount + ";");

				//arrow from this to previous (narrower) scope
				out.println(val.getName()+":"+ (portCount-1) + " -> " + val.getScope().getName()+":"+val.getScope().getName()+"0");
				
			} else if (value instanceof MethodSTE) {
				MethodSTE val = (MethodSTE) value;
				out.println("\n"+val.getName() + " [ label=<");
				out.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">");
				out.println("\t<tr><td port=\""+val.getName()+"0"+"\"><i>MethodSTE</i></td></tr>");
				out.println("\t<tr><td>mName="+val.getName()+"</td></tr>");
				out.println("\t<tr><td>mSignature="+val.getmSignature()+"</td></tr>");
				out.println("\t<tr><td port=\""+(portCount++)+"\">mScope</td></tr>");
				out.println("</table>>];");

				out.println(scope.getName()+":"+ scope.getName()+iterCount + " -> " + val.getName()+":"+val.getName()+"0"+ ";");
				//arrow from this to previous (narrower) scope
				out.println(val.getName()+":"+ (portCount-1) + " -> " + val.getScope().getName()+":"+val.getScope().getName()+"0");

			} else if (value instanceof VarSTE) {
				VarSTE val = (VarSTE) value;
				out.println("\n"+steCount+ " [ label=<");
				out.println("<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">");
				out.println("\t<tr><td port=\""+(portCount++)+"\"><i>VarSTE</i></td></tr>");	
				out.println("\t<tr><td>mName="+val.getName()+"</td></tr>");
				out.println("\t<tr><td>mType="+val.getmType().toString()+"</td></tr>");
				out.println("\t<tr><td>mBase="+val.getmBase()+"</td></tr>");
				out.println("\t<tr><td>mOffset="+val.getmOffset()+"</td></tr>");
				out.println("</table>>];");
				
				out.println(scope.getName()+":"+ (currScopeStart+1+iterCount) + " -> " + (steCount)+":"+(portCount)+ ";");
	       		}
			iterCount++;
			steCount++;
		}

       // increment for ourseves
       scopeCount++;
   }

}
