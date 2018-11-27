package symtable;

public class ClassSTE extends STE{

    String mName;
    boolean mMain = false;
	ClassSTE mSuperClass = null;
    Scope mScope;

    public ClassSTE(String mName, boolean mMain, ClassSTE mSuperClass, Scope mScope){
        this.mName = mName;
        this.mMain = mMain;
		this.mSuperClass = mSuperClass;
		this.mScope = mScope;
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

    public Scope getmScope(){
        return mScope;
    }   

    public void setmScope(Scope scope){
        this.mScope = scope;
    }  

}
