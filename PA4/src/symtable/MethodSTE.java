package symtable;

import java.util.LinkedList;
import java.util.Iterator;
import ast.node.*;

public class MethodSTE extends STE{
	String mName;
	String mSignature;
	Scope mScope;

	public MethodSTE(String mName, String mSignature, Scope mScope){
		this.mName = mName;
		this.mSignature = mSignature;
		this.mScope = mScope;
	}

	public MethodSTE(MethodDecl md, Scope scope){
		this.mName = md.getName();
		this.mSignature = getSignature(md);
		this.mScope = scope;
	}
	
	public String getSignature(MethodDecl md){
		String sig = "";
		LinkedList<Formal> ll = md.getFormals();
		Iterator<Formal> iter = ll.listIterator();
		Scope scope = new Scope();
	       while(iter.hasNext()){
				Formal f = iter.next();
				sig += VarSTE.iTypeToType(f.getType()).toString() + " ";
				//sig += f.getType().getClass().toString() + " ";
           }   
        sig = sig + "return "+md.getType();
		return sig;
	}

	public String getmName(){
		return mName;
	}
	public String getName(){
		return mName;
	}

	public void setmName(String name){
		this.mName = name;
	}

	public String getmSignature(){
		return mSignature;
	}

	public void setmSignature(String sig){
		this.mSignature = sig;
	}

	public Scope getScope(){
		return mScope;
	}

	public Scope getmScope(){
		return mScope;
	}

	public void setmScope(Scope scope){
		this.mScope = scope;
	}
}
