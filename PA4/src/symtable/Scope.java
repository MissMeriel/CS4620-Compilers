package symtable;
import java.util.HashMap;
public class Scope{

	String name = null;
	HashMap<String, STE> dict;
	String scopeType = null;

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

	public Scope(String type, String name){
		this.name = name;
		this.scopeType = type;
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

	public String getType(){
		return scopeType;
	}

	public void setType(String str){
		this.scopeType = str;
	}

/*	public void size(){
		return dict.keySet().size();
	}
*/	
	public void add(String str, STE ste){
		dict.put(str, ste);
	}

	public STE lookupInnermost(String sym){
		return dict.get(sym);
	}
}
