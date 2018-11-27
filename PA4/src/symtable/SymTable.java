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
	
	public SymTable(HashMap<Node, Type> st, Stack<Scope> ms){
		this.mExpType = st;
		this.mScopeStack = ms;
	}

	public SymTable clone(){
		return new SymTable((HashMap<Node, Type>)this.mExpType.clone(), (Stack<Scope>)this.mScopeStack.clone());
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

	public Scope lookupClosestScopeWith(String s) {
		//looks at other scopees if no return from current scope
		Iterator<Scope> iter = mScopeStack.iterator();
		while(iter.hasNext()){
			Scope scope = iter.next();
			//printScope(scope);
			if(scope.getName().contains(s) || scope.getType().contains(s)){
				return scope;
			}
		}
		return null;
	}


	public Scope peek(){
		return mScopeStack.peek();
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
				if(scope.lookupInnermost(sym) != null){
					return scope.lookupInnermost(sym);
				}
			}
		return null;
    }

    public Scope lookupClosestScope(String sym) {
			Iterator<Scope> iter = mScopeStack.iterator();
			while(iter.hasNext()){
				Scope scope = iter.next();
				if(scope.lookupInnermost(sym) != null){
					if(scope.lookupInnermost(sym) != null){
						return scope;
					}
				}
			}
		return null;
    }

	public void printST(){
		Iterator<Scope> iter = mScopeStack.iterator();
		while(iter.hasNext()){
			Scope scope = iter.next();
			System.out.println("Scope: "+scope.getName());
			printScope(scope);
		}
	}

	//print members of scope
	public void printScope(Scope scope){
		HashMap<String, STE> dict = scope.getDict();
		Set keyset = dict.keySet();
		Iterator<String> iter = keyset.iterator();
		while(iter.hasNext()){
			String str = iter.next();
			STE ste = dict.get(str);
			System.out.println("\t"+str+": "+ste.getClass());
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
			Iterator<Scope> iter =mScopeStack.iterator();
			Scope scope = null;
			while(iter.hasNext()){
				scope = iter.next();
				if (scope.getName().equals(id)){
					mScopeStack.remove(scope);
					break;
				}
			}
			mScopeStack.push(scope);
		}
    }

////////////////////////////////////////////////////////////////////////////////////////


   /** Upon entering each node in AST, check of this node is the root
   to generate start of .dot file, output the dot output for the node.
   */
	public void outputDot(PrintStream ps){
		//System.out.println("stack size: "+ mScopeStack.size());
		this.out = ps;
		out.println("digraph ASTGraph {");
		out.println("\tgraph [pad=\"0.5\", nodesep=\"0.5\", ranksep=\"2\"];");
		out.println("\tnode [shape=plain]");
		out.println("\trankdir=LR;");

		//make copy of stack to pop from so you don't obliterate your symtable
		Stack<Scope> scopeStackClone = (Stack<Scope>) mScopeStack.clone();
		while ( !scopeStackClone.empty() ) {
			scopeDotOutput(scopeStackClone.peek());
			scopeStackClone.pop();
		}
		if (scopeStackClone.empty()) {
		    out.println("}");
		}   
		out.flush();
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
				out.println("\t<tr><td>mBase="+val.getBase()+"</td></tr>");
				out.println("\t<tr><td>mOffset="+val.getOffset()+"</td></tr>");
				out.println("</table>>];");
				
				out.println(scope.getName()+":"+ (currScopeStart+1+iterCount) + " -> " + (steCount)+":"+(portCount)+ ";");
	       		}
			iterCount++;
			steCount++;
		}
       scopeCount++;
   }

}
