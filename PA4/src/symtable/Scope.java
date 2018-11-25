package symtable;
import java.util.HashMap;
public class Scope{

	String name = null;
	HashMap<String, STE> dict;

	public Scope(){
		dict = new HashMap<String, STE>();
	}

	public Scope(HashMap<String, STE> dict){
		this.dict = dict;
	}

	public Scope( String name){
		this.name = name;
		dict = new HashMap<String, STE>();
	}

	public HashMap<String, STE> getDict(){
		return dict;
	}

	public void setDict(HashMap<String, STE> dict){
		this.dict = dict;
	}

	public String getName(){
		return name;
	}
	
	public void add(String str, STE ste){
	//	System.out.println("dict null? "+(dict == null));
		dict.put(str, ste);
	}

	public STE lookupInnermost(String sym){
		return dict.get(sym);
	}
}
