package symtable;

public abstract class STE {

	String mName;

    public String getmName(){
        return mName;
    }   

    public String getName(){
        return this.mName;
    }   

    public void setmName(String name){
        this.mName = name;
    }  

}
