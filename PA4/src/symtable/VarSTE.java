package symtable;
import java.util.LinkedList;
import ast.node.*;

public class VarSTE extends STE {
	String mName;
	Type mType;
	String  mBase = "INVALID";
	int mOffset = 0;

	public VarSTE(String mName, Type mType){
		this.mName = mName;
		this.mType = mType;
	}
	
	public VarSTE(String mName, IType mType){
		this.mName = mName;
		this.mType = iTypeToType(mType);
	}

	public String getmName(){
		return mName;
	}

    public String getName(){
        return mName;
    } 

	public Type getmType(){
		return mType;
	}

	public String getmBase(){
		return mBase;
	}

	public void setmBase(String base){
		this.mBase = base;
	}

	public int getmOffset(){
		return mOffset;
	}

	public void setmOffset(int offset){
		this.mOffset = offset;
	}

	public static Type iTypeToType(IType itype){
		if(itype instanceof BoolType){
			return Type.BOOL;
		} else if (itype instanceof ButtonType) {
			return Type.BUTTON;
		} else if (itype instanceof ByteType) {
			return Type.BYTE;
		} else if (itype instanceof ClassType) {
			return Type.CLASS;
		} else if (itype instanceof ColorType) {
			return Type.COLOR;
		} else if (itype instanceof IntType) {
			return Type.INT;
		} else if (itype instanceof ToneType) {
			return Type.TONE;
		} else if (itype instanceof VoidType) {
			return Type.VOID;
		}
		return null;
	}
	
}
