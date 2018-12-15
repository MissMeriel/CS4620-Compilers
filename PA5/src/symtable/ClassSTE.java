package symtable;

public class ClassSTE extends STE{

    String mName;
    boolean mMain = false;
	ClassSTE mSuperClass = null;
    Scope mScope;
	int size;

    public ClassSTE(String mName, boolean mMain, ClassSTE mSuperClass, Scope mScope){
        this.mName = mName;
        this.mMain = mMain;
		this.mSuperClass = mSuperClass;
		this.mScope = mScope;
		this.size = 0;
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

    public boolean getmMain(){
        return mMain;
    }
	
	public void setmMain(boolean b){
		this.mMain = b;
	}

	public ClassSTE getmSuperClass(){
		return mSuperClass;
	}

	public void setmSuperClass(ClassSTE classSTE){
		this.mSuperClass = classSTE;
	}

    public Scope getScope(){
        return mScope;
    }   

    public void setScope(Scope scope){
        this.mScope = scope;
    }  

	public int getObjectSize(){
		return this.size;
	}

	public void setObjectSize(int i){
		this.size = i;
	}
}
