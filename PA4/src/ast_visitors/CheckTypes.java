package ast_visitors;

/** 
 * CheckTypes
 * 
 * This AST visitor traverses a MiniJava Abstract Syntax Tree and checks
 * for a number of type errors.  If a type error is found a SymanticException
 * is thrown
 * 
 * CHANGES to make next year (2012)
 *  - make the error messages between *, +, and - consistent <= ??
 *
 * Bring down the symtab code so that it only does get and set Type
 *  for expressions
 */

import ast.node.*;
import ast.visitor.DepthFirstVisitor;
import java.util.*;

import symtable.SymTable;
import symtable.*;
import symtable.Type;
import exceptions.InternalException;
import exceptions.SemanticException;


public class CheckTypes extends DepthFirstVisitor
{
    
   private SymTable mCurrentST;
   
   public CheckTypes(SymTable st) {
     if(st==null) {
          throw new InternalException("unexpected null argument");
      }
      mCurrentST = st.clone();
   }
   
   //========================= Overriding the visitor interface

   public void defaultOut(Node node) {
       System.err.println("Node not implemented in CheckTypes, " + node.getClass());
   }
   
   public void outAndExp(AndExp node){
     if(this.mCurrentST.getExpType(node.getLExp()) != Type.BOOL) {
       throw new SemanticException(
         "Invalid left operand type "+this.mCurrentST.getExpType(node.getLExp())+"for operator &&",
         node.getLExp().getLine(), node.getLExp().getPos());
     }
     if(this.mCurrentST.getExpType(node.getRExp()) != Type.BOOL) {
       throw new SemanticException(
         "Invalid right operand type "+this.mCurrentST.getExpType(node.getRExp())+" for operator &&",
         node.getRExp().getLine(), node.getRExp().getPos());
     }
     this.mCurrentST.setExpType(node, Type.BOOL);
   }
  
   public void outPlusExp(PlusExp node){
		/*System.out.println("node.getLExp() null: "+(node.getLExp() == null));
		System.out.println("node.getRExp() null: "+(node.getRExp() == null));*/
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
		/*System.out.println("rexpType "+rexpType.toString());
		System.out.println("lexpType "+lexpType.toString());*/
       if ((lexpType==Type.INT  || lexpType==Type.BYTE) &&
           (rexpType==Type.INT  || rexpType==Type.BYTE)
          ){
           this.mCurrentST.setExpType(node, Type.INT);
       } else {
           throw new SemanticException(
                   "Operands to + operator must be INT or BYTE",
                   node.getLExp().getLine(),
                   node.getLExp().getPos());
       }

   }
   
	public void outLtExp(LtExp node){
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       if ((lexpType==Type.INT  || lexpType==Type.BYTE) &&
           (rexpType==Type.INT  || rexpType==Type.BYTE)
          ){
           this.mCurrentST.setExpType(node, Type.BOOL);
       } else {
           throw new SemanticException(
                   "Operands to LtExp must be INT or BYTE",
                   node.getLExp().getLine(),
                   node.getLExp().getPos());
       }

   }

   public void outMinusExp(MinusExp node){
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       if ((lexpType==Type.INT  || lexpType==Type.BYTE) &&
           (rexpType==Type.INT  || rexpType==Type.BYTE)
          ){
           this.mCurrentST.setExpType(node, Type.INT);
       } else {
           throw new SemanticException(
                   "Operands to - operator must be INT or BYTE",
                   node.getLExp().getLine(),
                   node.getLExp().getPos());
       }
   }

	@Override
	public void outEqualExp(EqualExp node){
       Type lexpType = this.mCurrentST.getExpType(node.getLExp());
       Type rexpType = this.mCurrentST.getExpType(node.getRExp());
       if ((lexpType==Type.INT  || lexpType==Type.BYTE || lexpType==Type.COLOR) &&
           (rexpType==Type.INT  || rexpType==Type.BYTE || rexpType==Type.COLOR)
          ){
           this.mCurrentST.setExpType(node, Type.BOOL);
       } else {
           throw new SemanticException(
                   "Operands to == operator must be INT or BYTE",
                   node.getLExp().getLine(),
                   node.getLExp().getPos());
		}
	}

///////////////////////////////////////////////////////////////

	@Override
   public void outIntegerExp(IntLiteral node){   
	try{
     if(this.mCurrentST.getExpType(node) != Type.INT) {
		//System.out.println("Invalid type for IntLiteral "+node.getLine()+","+node.getPos());
       throw new SemanticException(
         "Invalid type for IntLiteral",
         node.getLine(), node.getPos());
     }   
     this.mCurrentST.setExpType(node, Type.INT);
	}catch(Exception e){
		//System.out.println(e.getMessage());
		e.printStackTrace();
	}
   } 


	@Override
	public void visitIntLiteral(IntLiteral node){
	inIntegerExp(node);
	outIntegerExp(node);
/*     if(this.mCurrentST.getExpType(node) != Type.INT) {
       throw new SemanticException(
         "Invalid type for ",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.INT);
*/	}

	@Override
   public void outMeggyGetPixel(MeggyGetPixel node){
     if(this.mCurrentST.getExpType(node) != Type.COLOR) {
       throw new SemanticException(
         "Invalid type for MeggyGetPixel",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.COLOR);
   }

///////////////////////////////////////////////////////////////

	@Override
   public void outColorExp(ColorLiteral node){
     if(this.mCurrentST.getExpType(node) != Type.COLOR) {
		//System.out.println("Invalid type for ColorLiteral "+node.getLine()+","+node.getPos());
       throw new SemanticException(
         "Invalid type for ColorLiteral",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.COLOR);
   }

	@Override
   public void outColorType(ColorType  node){
     if(this.mCurrentST.getExpType(node) != Type.COLOR) {
		//System.out.println("Invalid type for ColorLiteral "+node.getLine()+","+node.getPos());
       throw new SemanticException(
         "Invalid type for ColorType",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.COLOR);
   }
///////////////////////////////////////////////////////////////

	@Override
   public void outMainClass(MainClass node){
     if(this.mCurrentST.getExpType(node) != Type.MAINCLASS) {
		//System.out.println("Invalid type for MainClass "+node.getLine()+","+node.getPos());
       throw new SemanticException(
         "Invalid type for MainClass",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.MAINCLASS);
   }

	@Override
   public void outMeggySetPixel(MeggySetPixel node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for MeggySetPixel",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
   }

	@Override
   public void outVoidType(VoidType node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for VoidType",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
   }

	@Override
   public void outBlockStatement(BlockStatement node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for BlockStatement",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
   }

	@Override
	public void outMethodDecl(MethodDecl node){
	//System.out.println("MethodDecl: "+node.getName());
	Type returnType = this.mCurrentST.getExpType(node.getExp());
	if(node.getExp() == null){
		returnType = Type.VOID;
	}
	Type declType = VarSTE.iTypeToType(node.getType());
	//System.out.println("returnType: "+returnType+"; declType: "+declType);
     if(returnType != declType){
       throw new SemanticException(
         "Invalid type for MethodDecl",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, declType); 
	}

	@Override
	public void outTopClassDecl(TopClassDecl node){
     if(this.mCurrentST.getExpType(node) != Type.CLASS) {
       throw new SemanticException(
         "Invalid type for TopClassDecl",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.CLASS);
	}
	
	@Override
	public void outProgram(Program node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for Program",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
	}

	@Override
	public void outFormal(Formal node){
		Type t = VarSTE.iTypeToType(node.getType());
     if(this.mCurrentST.getExpType(node) == Type.MAINCLASS) {
       throw new SemanticException(
         "Invalid type for Formal",
         node.getLine(), node.getPos());
     }
     //this.mCurrentST.setExpType(node, Type.VOID);
	}

	@Override
	public void outCallStatement(CallStatement node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for CallStatement",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
	}

	@Override
	public void outIdLiteral(IdLiteral node){
	VarSTE ste = (VarSTE) this.mCurrentST.lookup(node.getLexeme());
	Type steType = ste.getType();
	 Type t = this.mCurrentST.getExpType(node);
	 //System.out.println("IdLiteral type: "+t.toString());
	if(t == null){
		//say something
		this.mCurrentST.setExpType(node, steType);
	} else if (t != steType) {
	 System.out.println("Type mismatch for "+node.getLexeme()+": quicklookup Type: "+t.toString()+"; STE type: "+steType.toString());
     this.mCurrentST.setExpType(node, steType);
       throw new SemanticException(
         "Invalid type for IdLiteral "+node.getLexeme(),
         node.getLine(), node.getPos());
     }
	}


	@Override
	public void outMeggyDelay(MeggyDelay node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for MeggyDelay",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
	}

	@Override
	public void outWhileStatement(WhileStatement node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for WhileStatement",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
	}

	@Override
	public void outCallExp(CallExp node){
        MethodSTE methodSTE = (MethodSTE) mCurrentST.lookup(node.getId().trim());
        //System.out.println("callExp.getId(): "+node.getId());
        //System.out.println("null ste? "+(methodSTE == null));
        //System.out.println("null type? "+(methodSTE.getType() == null));
		mCurrentST.setExpType(node, methodSTE.getType());
	/*
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for CallExp",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
*/	}

/////////////////////////////////////////////////////////////

	@Override
	public void visitColorLiteral(ColorLiteral node){
		inColorExp(node);
		outColorExp(node);
/*     if(this.mCurrentST.getExpType(node) != Type.COLOR) {
       throw new SemanticException(
         "Invalid type for ",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.COLOR);
*/	}

/////////////////////////////////////////////////////////////


/*
	@Override
	public void out(node){
     if(this.mCurrentST.getExpType(node) != Type.VOID) {
       throw new SemanticException(
         "Invalid type for ",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.VOID);
	}
*/
/////////////////////////////////////////////////////////////
	@Override
	public void outByteCast(ByteCast node){
	Type expType = this.mCurrentST.getExpType(node.getExp());
	//System.out.println("byteCast "+node.getExp().getClass().toString()+" expType: "+expType.toString());
	boolean expIsInt = expType  == Type.INT;
    if(this.mCurrentST.getExpType(node) != Type.BYTE || !expIsInt) {
       throw new SemanticException(
         "Invalid type "+" for ByteCast",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.BYTE);
	}

	@Override
	public void outByteType(ByteType node){
     if(this.mCurrentST.getExpType(node) != Type.BYTE) {
       throw new SemanticException(
         "Invalid type for ByteType",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.BYTE);
	}


/////////////////////////////////////////////////////////////

	@Override
	public void outNewExp(NewExp node){
     if(this.mCurrentST.getExpType(node) != Type.CLASS) {
       throw new SemanticException(
         "Invalid type for NewExp",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.CLASS);
	}

	@Override
	public void outThisExp(ThisLiteral node){
/*     if(this.mCurrentST.getExpType(node) != Type.CLASS) {
       throw new SemanticException(
         "Invalid type for ThisLiteral",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.CLASS);
*/	}

/////////////////////////////////////////////////////////////

	@Override
	public void outTrueExp(TrueLiteral node){
     if(this.mCurrentST.getExpType(node) != Type.BOOL) {
       throw new SemanticException(
         "Invalid type for TrueLiteral",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.BOOL);
	}

	@Override
	public void outIfStatement(IfStatement node){
       Type expType = this.mCurrentST.getExpType(node.getExp()); 
		//System.out.println("expType "+expType.toString());
     if(this.mCurrentST.getExpType(node.getExp()) != Type.BOOL) {
       throw new SemanticException(
         "Invalid type for IfStatement",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.BOOL);
	}

	@Override
	public void outBoolType(BoolType node){
     if(this.mCurrentST.getExpType(node) != Type.BOOL) {
       throw new SemanticException(
         "Invalid type for BoolType",
         node.getLine(), node.getPos());
     }
     this.mCurrentST.setExpType(node, Type.BOOL);
	}
}
