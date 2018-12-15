/**
 * We extend the DepthFirstAdapter.  
 * Visitors invoke a defaultCase method on each node they visit.  
 * We override this method so that it
 * prints out dot info about a node.
 */


/**
 * This ast walker generates AVR output for the AST.  
 */

package ast_visitors;

import java.io.*;
import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import ast.node.*;
import symtable.*;
import ast.visitor.*;

public class AVRgenVisitor extends DepthFirstVisitor
{
    private SymTable st; 
    private PrintWriter out; 
    private Stack<Integer> nodeStack;
	private int nodeCount = 0;
	private int breakCount = -1;

    public AVRgenVisitor(PrintWriter pw, SymTable st) 
    {   
        this.out = pw; 
        this.st = st;
		st.printST(); 
        this.nodeStack = new Stack<Integer>();
    } 

   /** Upon entering each node in AST, check of this node is the root
   to generate start of .dot file, output the dot output for the node.
   */
   public void defaultIn(Node node) {
       nodeDotOutput(node);
   } //end defaultIn
  

 
   public void defaultOut(Node node) {
		if(node instanceof MeggySetPixel){
			out.println("\n\t/*MeggySetPixel: pop args off of stack */"
				+"\n\tpop r20"
				+"\n\tpop r22"
				+"\n\tpop r24"
				+"\n\t/* Draw pixels, display slate */"
				+"\n\tcall _Z6DrawPxhhh"
				+"\n\tcall _Z12DisplaySlatev"
				);
		}
		else if(node instanceof ByteCast){
			out.println("\n\t# Casting int to byte by popping"
				+"\n\t# 2 bytes off stack and only pushing low order bits"
				+"\n\t# back on.  Low order bits are on top of stack."
				+"\n\tpop r24"
				+"\n\tpop r25"
				+"\n\tpush r24"
				);
		}
		else if(node instanceof MeggyGetPixel){
			out.println("\n\t### Meggy.getPixel(x,y) call"
				+"\n\t/* pop args off of stack */"
				+"\n\tpop r22"
				+"\n\tpop r24"
				+"\n\tcall _Z6ReadPxhh"
				+"\n\tpush r24"
				);
		}
		else if(node instanceof NotExp){
			out.println("\n\t# not operation"
				+"\n\tpop    r24"
				+"\n\tldi     r22, 1"
				+"\n\teor     r24,r22"
				+"\n\tpush   r24"
				+"\n\tpop	r24"
				+"\n\tpush   r24"
				);
		}
		else if(node instanceof MeggyDelay){
			out.println("\n\t### Meggy.delay() call"
				+"\n\tpop    r24"
				+"\n\tpop    r25"
				+"\n\tcall   _Z8delay_msj"
				);
		}
		else if(node instanceof MeggyCheckButton){
			String button = ((ButtonLiteral) ((MeggyCheckButton) node).getExp()).getLexeme();
			if(button.equals("Meggy.Button.A")){
				button = "Button_A";
			} else if(button.equals("Meggy.Button.B")){
				button = "Button_B";
			} else if(button.equals("Meggy.Button.Up")){
				button = "Button_Up";
			} else if(button.equals("Meggy.Button.Down")){
				button = "Button_Down";
			} else if(button.equals("Meggy.Button.Left")){
				button = "Button_Left";
			} else if(button.equals("Meggy.Button.Right")){
				button = "Button_Right";
			}
			out.println("\n\t### MeggyCheckButton"
				+"\n\tcall	_Z16CheckButtonsDownv"
				+"\n\tlds	r24, "+ button
				+"\n\ttst	r24"
				+"\n\tbreq	MJ_L"+ (++breakCount)
				+"\nMJ_L"+ (++breakCount)+":"
				+"\n\tldi	r24, 1"
				+"\n\tjmp	MJ_L"+(breakCount+1)
				+"\nMJ_L"+(breakCount-1)+":"
				+"\nMJ_L"+(++breakCount)+":"
				+"\n\tpush	r24"
				);
		}
        else if(node instanceof EqualExp){
            out.println("\n\t# equality check expression"
                +"\n\tpop    r18"
                +"\n\tpop    r24"
                +"\n\tcp    r24, r18"
                +"\n\tbreq MJ_L"+ (breakCount+2)
                +"\n"
                +"\n\t#result is false"
                +"\nMJ_L"+ (++breakCount) +":"
                +"\n\tldi     r24, 0"
                +"\n\tjmp      MJ_L"+(breakCount+2)
                +"\n"
                +"\n\t# result is true"
                +"\nMJ_L"+ (++breakCount) +":"
                +"\n\tldi     r24, 1"
                +"\n"
                +"\n\t# store result of equal expression"
                +"\nMJ_L"+ (++breakCount) +":"
                +"\n\tpush   r24"
                );  
/*		
		else if(node instanceof ){
			out.println(
				+"\n\t"
				+"\n\t"
				+"\n\t"
				+"\n"
				+"\n"
				+"\n"
				+"\n"
				);
		}
*/
	 }
     out.flush();
   } //end defaultOut
   
   private void nodeDotOutput(Node node){
		if(node instanceof IntLiteral){
			IntLiteral iNode = (IntLiteral) node;
			out.println("\n\t# load int "+ iNode.getIntValue()
				+"\n\tldi r24,lo8(" + iNode.getIntValue() + ")"
				+"\n\tldi r25,hi8("+ iNode.getIntValue() +")"
				+"\n\t# push int "+ iNode.getIntValue() + " onto stack"
				+"\n\tpush r25"
				+"\n\tpush r24"
				);
		}
		else if(node instanceof ColorLiteral){
			ColorLiteral clNode = (ColorLiteral) node;
			out.println("\n\t/* load " + clNode.getIntValue() + " for "+ clNode.getLexeme() +" */"
				+"\n\tldi r22, " + clNode.getIntValue() 
				+"\n\tpush r22"
				);
		}
		else if(node instanceof IfStatement){
			out.println("\n\t#### if statement");
		}
		else if(node instanceof TrueLiteral){
			out.println("\n\t# True/1 expression"
				+"\n\tldi    r22, 1"
				+"\n\tpush	 r22"
			);
		}
		else if(node instanceof FalseLiteral){
			out.println("\n\t# True/1 expression"
				+"\n\tldi    r22, 0"
				+"\n\tpush	 r22"
			);
		}
		else if(node instanceof AndExp){
			out.println("\n\t#### short-circuited && operation");
		}
		else if(node instanceof WhileStatement){
		}
		else if(node instanceof NewExp){
		    NewExp ne = (NewExp) node;
		    Type t = st.getExpType(node);
		    ClassSTE classSTE = (ClassSTE) st.lookup(ne.getId());
		    int size = classSTE.getObjectSize();
			out.println("\n\n\t# NewExp"
				+"\n\tldi    r24, lo8("+size+")"
				+"\n\tldi    r25, lo8("+size+")"
				+"\n\t# allocating object of size "+size+" on heap"
				+"\n\tcall    malloc"
				+"\n\t# push object address"
				+"\n\t# push two byte expression onto stack"
				+"\n\tpush   r25"
				+"\n\tpush   r24"
				);
		} else if(node instanceof ThisLiteral){
			out.println("\n\t# loading the implicit \"this\""
			    +"\n\t# load a two byte variable from base+offset"
			    +"\n\tldd    r31, Y + 2" 
			    +"\n\tldd    r30, Y + 1" 
			    +"\n\t# push two byte expression onto stack"
			    +"\n\tpush 	   r31"
			    +"\n\tpush     r30"
			);
		}
		/*else if(node instanceof ){
			out.println(""
				+"\n\t"
				+"\n\t"
				+"\n\t"
				);
		}*/
   }//end nodeDotOutput (called by defaultIn)

    @Override
    public void visitWhileStatement(WhileStatement node)
    {    
		String while_test = "MJ_L"+ (++breakCount);
		String while_body = "MJ_L"+ (++breakCount);
		String while_end = "MJ_L"+ (++breakCount);
		out.println("\n\t#### while statement"
			+"\n" + while_test + ":"
			);
        inWhileStatement(node);
        if(node.getExp() != null)
        {    
            node.getExp().accept(this);
			out.println("\n\t# if not(condition)"
				+"\n\tpop    r24"
				+"\n\tldi    r25,0"
				+"\n\tcp     r24, r25"
				+"\n\t# WANT breq "+while_end
				+"\n\tbrne   "+while_body
				+"\n\tjmp\t\t"+while_end
				);
        }    
        if(node.getStatement() != null)
        {    
			out.println("\n\t# while loop body"
				+"\n"+ while_body + ":"
				);
            node.getStatement().accept(this);
			out.println("\n\t#jump to while test"
				+"\n\tjmp	"+while_test);
        }    
        outWhileStatement(node);
		out.println("\n\t# end of while"
			+"\n"+while_end+":"
			);
    }  

    @Override
    public void visitIfStatement(IfStatement node)
    {
	String then_label = "MJ_L"+ (++breakCount);
	String else_label = "MJ_L"+ (++breakCount);
	String done_label = "MJ_L"+ (++breakCount);
        inIfStatement(node);
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
        if(node.getThenStatement() != null)
        {
            out.println("\n\t# load condition and branch if false"
                +"\n\tpop    r24"
                +"\n\t#load zero into reg"
                +"\n\tldi   r25, 0"
                +"\n"
                +"\n\t#use cp to set SREG"
                +"\n\tcp     r24, r25"
                +"\n\t#WANT breq "+ then_label
                +"\n\tbrne   "+ else_label 
                +"\n\tjmp    " + then_label
                +"\n"
                +"\n\t#then label for if" 
                +"\n"+else_label+":" 
            ); 
            node.getThenStatement().accept(this);
	    out.println("\n\tjmp	"+done_label);
        }
	out.println("\n\t# else label for if"
		+"\n"+then_label+":"
    	);
        if(node.getElseStatement() != null)
        {
	    node.getElseStatement().accept(this);
        }
	out.println("\n\t# done label for if"
		+"\n"+done_label+":"
		);
        outIfStatement(node);
    }

    @Override
    public void visitAndExp(AndExp node)
    {    
	String right_op_break = "MJ_L"+(++breakCount);
	String out_exp_break = "MJ_L"+(++breakCount);
        inAndExp(node);
        if(node.getLExp() != null)
        {    
	    out.println("\t# &&: left operand");
            node.getLExp().accept(this);
	    out.println("\n\t# &&: if left operand is false do not eval right"
		    +"\n\tpop	r24"
		    +"\n\tpush	r24"
		    +"\n\t# compare left exp with zero"
		    +"\n\tldi	r25, 0"
		    +"\n\tcp    r24, r25"
		    +"\n\t# Want this, breq "+right_op_break
		    +"\n\tbrne	"+out_exp_break
		    +"\n\tjmp	"+right_op_break
	    );
        }    
        if(node.getRExp() != null)
        {    
			out.println("\n"+ out_exp_break+":"
				+"\n\t# &&: right operand"
				+"\n\tpop	r24"
			); 
           node.getRExp().accept(this);
        }    
	out.println("\n"+right_op_break+":");
        super.outAndExp(node);
    }  

    @Override
    public void visitMulExp(MulExp node) {
        inMulExp(node);
        if(node.getLExp() != null) {
	    node.getLExp().accept(this);
        }    
        if(node.getRExp() != null) {
	    node.getRExp().accept(this);   
        }
	out.println("\t# MulExp"
	    +"\n\tpop    r18"
	    +"\n\tpop    r22"
	    +"\n\t# move low byte src into dest reg"
	    +"\n\tmov    r24, r18"
	    +"\n\tmov    r26, r22"
	    +"\n\t"
	    +"\n\t# Do mul operation of two input bytes"
	    +"\n\tmuls	r24,r26"
	    +"\n\t#push two byte exp onto stack"
	    +"\n\tpush	r1"
	    +"\n\tpush 	r0"
	    +"\n\t# clear r0 and r1"
	    +"\n\teor	r0,r0"
	    +"\n\teor	r1,r1"
	);
        outMulExp(node);
    }

    @Override
    public void visitMinusExp(MinusExp node) {
        inMinusExp(node);
        if(node.getLExp() != null)
        {
                node.getLExp().accept(this);
        }
        if(node.getRExp() != null)
        {
            node.getRExp().accept(this);
        }
	//if lexp is BYTE, convert to INT
	if(st.getExpType(node.getLExp()) == Type.BYTE){
	    out.println(" # load a one byte expression off stack"
		    +"\n\tpop    r18");
	} else {
	    	out.println(" # load two byte expression off stack"
		    +"\n\tpop    r18"
		    +"\n\tpop    r19");
	}
	//if rexp is BYTE, convert to INT
	if(st.getExpType(node.getRExp()) == Type.BYTE){
	    out.println(" # load a one byte expression off stack"
		    +"\n\tpop    r24");
	}  else {
	    	out.println(" # load two byte expression off stack"
		    +"\n\tpop    r24"
		    +"\n\tpop    r25");
	}
	if(st.getExpType(node.getRExp()) == Type.BYTE){
	    	    out.println("\t# promoting a byte to an int"
			+"\n\ttst     r24"
			+"\n\tbrlt     MJ_L"+(++breakCount)
			+"\n\tldi    r25, 0"
			+"\n\tjmp    MJ_L"+(breakCount +1)
			+"\nMJ_L"+breakCount+":"
			+"\n\tldi    r25, hi8(-1)"
			+"\nMJ_L"+(++breakCount)+":"
			);
	}
	if(st.getExpType(node.getLExp()) == Type.BYTE){
	    	    out.println("\t# promoting a byte to an int"
			+"\n\ttst     r18"
			+"\n\tbrlt     MJ_L"+(++breakCount)
			+"\n\tldi    r19, 0"
			+"\n\tjmp    MJ_L"+(breakCount +1)
			+"\nMJ_L"+breakCount+":"
			+"\n\tldi    r19, hi8(-1)"
			+"\nMJ_L"+(++breakCount)+":"
			);
	}

		out.println("\n\t#do INT sub operation"
			+"\n\tsub    r24, r18"
			+"\n\tsbc    r25, r19"
			+"\n\t# push hi order byte first"
			+"\n\t# push two byte expression onto stack"
			+"\n\tpush   r25"
			+"\n\tpush r24"
			);
        outMinusExp(node);
    }
    
    
    
    @Override
    public void visitPlusExp(PlusExp node) {
        inPlusExp(node);
        if(node.getLExp() != null)
        {
            node.getLExp().accept(this);
        }
	if(node.getRExp() != null)
        {
            node.getRExp().accept(this);
        }
	//if lexp is BYTE, convert to INT
	if(st.getExpType(node.getRExp()) == Type.BYTE){
	    out.println(" # load a one byte expression off stack"
		    +"\n\tpop    r18");
	    out.println("\t# promoting a byte to an int"
			+"\n\ttst     r18"
			+"\n\tbrlt     MJ_L"+(++breakCount)
			+"\n\tldi    r19, 0"
			+"\n\tjmp    MJ_L"+(breakCount +1)
			+"\nMJ_L"+breakCount+":"
			+"\n\tldi    r19, hi8(-1)"
			+"\nMJ_L"+(++breakCount)+":"
			);
	} else {
	    	out.println(" # load two byte expression off stack"
		    +"\n\tpop    r18"
		    +"\n\tpop    r19");
	}
	

	//if rexp is BYTE, convert to INT
	if(st.getExpType(node.getLExp()) == Type.BYTE){
	    out.println(" # load a one byte expression off stack"
		    +"\n\tpop    r24");
	    out.println("\t# promoting a byte to an int"
			+"\n\ttst     r24"
			+"\n\tbrlt     MJ_L"+(++breakCount)
			+"\n\tldi    r25, 0"
			+"\n\tjmp    MJ_L"+(breakCount +1)
			+"\nMJ_L"+breakCount+":"
			+"\n\tldi    r25, hi8(-1)"
			+"\nMJ_L"+(++breakCount)+":"
			);
	}  else {
	    	out.println(" # load two byte expression off stack"
		    +"\n\tpop    r24"
		    +"\n\tpop    r25");
	}
		out.println("\n\t#do add operation"
			+"\n\tadd    r24, r18"
			+"\n\tadc    r25, r19"
			+"\n\t# push two byte expression onto stack"
			+"\n\tpush   r25"
			+"\n\tpush r24"
			);
        outPlusExp(node);
    } 
    

    @Override
	/** containing class important */
    public void visitMethodDecl(MethodDecl node) {   
		//bring scope to top of scope stack
		this.st.pushScope(node.getName());
		//get unique function name
        String methodName = "";
        if(node.parent() != null){
	    if(node.parent() instanceof TopClassDecl){
		    methodName += ((TopClassDecl) node.parent()).getName();
	    } else if (node.parent() instanceof MainClass) {
		    //do nothing
	    }
        }
        methodName += "_" + node.getName();
		// function start
        out.println("\t.text"
                +"\n.global "+methodName
                +"\n\t.type  "+methodName+", @function"
                +"\n"+methodName+":"
                +"\n\tpush   r29"
                +"\n\tpush   r28"
                +"\n\t# make space for locals and params"
                +"\n\tldi    r30, 0");
        LinkedList<Formal> fs = node.getFormals();
	out.println("\tpush   r30"
		  +"\n\tpush   r30");
        for(int i = 0; i < fs.size(); i++){
	    Formal f = fs.get(i);
	    int size = this.st.getExpType(f).getAVRTypeSize();
	    for (int j = size; j >0; j--){
		out.println("\tpush   r30");
	    }
        }
        LinkedList<VarDecl> vds = node.getVarDecls();
	for(int i = 0; i < vds.size(); i++){
	    VarDecl vd = vds.get(i);
	    int size = this.st.getExpType(vd).getAVRTypeSize();
	    for (int j = size; j >0; j--){
		out.println("\tpush   r30");
	    }
        }
        out.print("\n\t"
            +"\n\t# Copy stack pointer to frame pointer"
            +"\n\tin     r28,__SP_L__"
            +"\n\tin     r29,__SP_H__"
            +"\n\t"
            +"\n\t# save off parameters");
	//start at 25 & 24 for this, decrement
	//1 or 2 registers loaded depends on type
        int avrReg = 25;// + (fs.size()+1)*2 + 1;
        int offset = 3;
        out.println("\n\tstd    Y + 2, r"+avrReg);
        avrReg--;
        out.println("\tstd    Y + 1, r"+avrReg);
        avrReg--;
        Iterator<Formal> iter = fs.listIterator();
        while(iter.hasNext()){
            Formal arg = iter.next();
            if(this.st.getExpType(arg) == Type.INT ){
                //pop 2 bytes off stack
				out.println("\tstd    Y + "+(offset+1)+", r"+avrReg);
				avrReg--;
				out.println("\tstd    Y + "+(offset)+", r"+avrReg);
				//update ST with offset
				VarSTE ste = (VarSTE) this.st.lookup(arg.getName());
				ste.setBase("Y");
				ste.setOffset(offset);
				avrReg--;
                offset += 2;
            }else{
                //pop 1 byte off stack
		avrReg--;
                out.println("\tstd    Y + "+offset+", r"+avrReg);
				VarSTE ste = (VarSTE) this.st.lookup(arg.getName());
				ste.setBase("Y");
				ste.setOffset(offset);
                avrReg --;
                offset++;
			}
        }
        out.print("/* done with function "+methodName+" prologue */\n");
        inMethodDecl(node);

        if(node.getType() != null) {
			node.getType().accept(this);
        }
    	{   
    	List<Formal> copy = new ArrayList<Formal>(node.getFormals());
	    for(Formal e : copy){
		    e.accept(this);
		    }
	    }
        {
	    List<VarDecl> copy = new ArrayList<VarDecl>(node.getVarDecls());
	    for(VarDecl e : copy) {
		    e.accept(this);
	    }
	}
	{
        List<IStatement> copy = new ArrayList<IStatement>(node.getStatements());
        for(IStatement e : copy)        {
            e.accept(this);
        }
        }
        if(node.getExp() != null) {
            node.getExp().accept(this);
	}
		out.println("\n\n/* epilogue start for  "+methodName+"*/");
		if(st.getExpType(node) == Type.VOID){
			out.println("\t# no return value");
		} else if (st.getExpType(node) == Type.CLASS || st.getExpType(node) == Type.INT){
			out.println("\t# handle return value");
		} else { //if (st.getExpType(node) == Type.BOOL){
			out.println("\t# handle return value");
			out.println("\t# promoting a byte to an int");
			out.println("\t# load a one byte expression off stack"
				+"\n\tpop    r24"
				+"\n\t# promoting a byte to an int"
				+"\n\ttst     r24"
				+"\n\tbrlt     MJ_L"+ (++breakCount)
				+"\n\tldi    r25, 0"
				+"\n\tjmp    MJ_L"+ (breakCount+1)
				+"\nMJ_L"+ breakCount+":"
				+"\n\tldi    r25, hi8(-1)"
				+"\nMJ_L"+ (++breakCount)+":"
				);
			
		}
		out.println("\t# pop space off stack for parameters and locals");
		{
		    out.println("\tpop		r30");
		    out.println("\tpop		r30");
		    List<Formal> copy = new ArrayList<Formal>(node.getFormals());
		    for(Formal e : copy){
		        int size = this.st.getExpType(e).getAVRTypeSize();
		        for (int j = size; j >0; j--){
		        	out.println("\tpop    r30");
		        }
		    }
	            vds = node.getVarDecls();
		    for(int i = 0; i < vds.size(); i++){
			VarDecl vd = vds.get(i);
			int size = this.st.getExpType(vd).getAVRTypeSize();
			for (int j = size; j >0; j--){
			    out.println("\tpop   r30");
			}
		    }
		}
		out.println("\n\t# restoring the frame pointer"
			+"\n\tpop    r28 "
			+"\n\tpop    r29 "
			+"\n\tret"
			+"\n\t.size "+methodName+", .-"+methodName+"\n"
			);
        outMethodDecl(node);
		this.st.removeScope();
    }
    
    @Override
    public void visitCallStatement(CallStatement node)
    {
	MethodSTE ste = (MethodSTE) this.st.lookup(node.getId());
	Scope scope = this.st.lookupClosestScope(node.getId());
	String methodName = scope.getName()+"_"+ste.getName();
	//System.out.println("Visiting method call "+methodName);
        inCallStatement(node);
	if(node.getId() != null){
	    String id = node.getId();
    	}
        if(node.getExp() != null)
        {
            node.getExp().accept(this);
        }
	{
            List<IExp> copy = new ArrayList<IExp>(node.getArgs());
            for(IExp e : copy)
            {
                e.accept(this);
            }
        }
	LinkedList<IExp> args = node.getArgs();
        int avrReg = 24 - args.size() * 2;
        out.println("\n\t#### function call"
            +"\n\t# put parameter values into appropriate registers");
        Iterator<IExp> iter = args.descendingIterator();
        while(iter.hasNext()){
            IExp arg = iter.next();
            if(st.getExpType(arg).getAVRTypeSize() == 2){
                //pop 2 bytes off stack
                out.println("\tpop    r"+avrReg);
                avrReg++;
                out.println("\tpop    r"+avrReg);
                avrReg++;
            }else{
                //pop 1 byte off stack
                out.println("\tpop    r"+avrReg);
                avrReg += 2;
            }   
        }
	
	        out.println("\n\t# receiver will be passed as first param"
            +"\n\tpop    r"+avrReg);
        avrReg++;
        out.println("\tpop    r"+avrReg);
        out.println("\n\tcall    "+methodName);
        
		Type returnType = this.st.getExpType(node);
		if(returnType == Type.VOID){
			//DO NOTHING
		} else if (returnType == Type.INT || returnType == Type.CLASS){
			out.println("\t# handle return value");
			out.println("\t# push two byte expression onto stack");
			out.println("\tpush	r24");
			out.println("\tpush	r25");
		} else {
			out.println("\t# handle return value");
			out.println("\t# push one byte expression onto stack");
			out.println("\tpush	r24");

		}
	
        outCallStatement(node);
    }
    

    @Override
    public void visitCallExp(CallExp node) {
	try {
	    //get method name
	    //lookup class Scope containing call name
	    //with correct signature
	    MethodSTE ste = (MethodSTE) this.st.lookup(node.getId());
	    Scope scope = this.st.lookupClosestScope(node.getId());
	    String methodName = scope.getName()+"_"+ste.getName();
	    //System.out.println("Visiting method call "+methodName);
	    node.getExp().accept(this);
	    {
		List<IExp> copy = new ArrayList<IExp>(node.getArgs());
		for(IExp e : copy)
		{
		    e.accept(this);
		}
	    }
	    LinkedList<IExp> args = node.getArgs();
	    int avrReg = 24 - args.size() * 2;
	    out.println("\n\t#### function call"
		+"\n\t# put parameter values into appropriate registers");
	    Iterator<IExp> iter = args.descendingIterator();
	    while(iter.hasNext()){
		IExp arg = iter.next();
		if(st.getExpType(arg).getAVRTypeSize() == 2){
		    //pop 2 bytes off stack
		    out.println("\n\tpop    r"+avrReg);
		    avrReg++;
		    out.println("\n\tpop    r"+avrReg);
		    avrReg++;
		}else{
		    //pop 1 byte off stack
		    out.println("\n\tpop    r"+avrReg);
		    avrReg += 2;
		}   
	    }
	    out.println("\n\t# receiver will be passed as first param"
		+"\n\tpop    r"+avrReg);
	    avrReg++;
	    out.println("\n\tpop    r"+avrReg);
	    out.println("\n\tcall    "+methodName);
		Type returnType = this.st.getExpType(node);
		if(returnType == Type.VOID){
			//DO NOTHING
		} else if (returnType == Type.INT || returnType == Type.CLASS){
			out.println("\t# handle return value");
			out.println("\t# push two byte expression onto stack");
			out.println("\tpush	r24");
			out.println("\tpush	r25");
		} else {
			out.println("\t# handle return value");
			out.println("\t# push one byte expression onto stack");
			out.println("\tpush	r24");
    
		}
		outCallExp(node);
	} catch(Exception e){
		System.out.println("exception in visitCallExp");
		e.printStackTrace();
	}
    }


    @Override
    public void visitLtExp(LtExp node)
    {   
        inLtExp(node);
        if(node.getLExp() != null) {
                node.getLExp().accept(this);
        }   
        if(node.getRExp() != null) {   
            if(node.getRExp() instanceof IExp) {
                node.getRExp().accept(this);
            } else {
                System.out.println("["+node.getRExp().getLine()+","+node.getRExp().getPos()+"] Invalid right operand type for operator <");
            }   
        }   
	out.println("\n\t# less than expression"
		+"\n\t# load a one byte expression off stack"
		+"\n\tpop    r18"
		+"\n\tpop    r24"
		+"\n\tcp    r24, r18"
		+"\n\tbrlt MJ_L"+(++breakCount + 1)
	);
	out.println("\n\t# load false"
		+"\nMJ_L"+breakCount+":"
		+"\n\tldi     r24, 0"
		+"\n\tjmp      MJ_L"+(++breakCount + 1)
		+"\n\t"
		+"\n\t# load true"
		+"\nMJ_L"+breakCount+":"
		+"\n\tldi    r24, 1"
		+"\n"
		+"\n\t# push result of less than"
		+"\nMJ_L"+(++breakCount)+":"
		+"\n\t# push one byte expression onto stack"
		+"\n\tpush   r24"
	);
        outLtExp(node);
    } 


    @Override
    public void visitMainClass(MainClass node){   
	out.println("\t.file  \"main.java\""
		+"\n__SREG__ = 0x3f"
		+"\n__SP_H__ = 0x3e"
		+"\n__SP_L__ = 0x3d"
		+"\n__tmp_reg__ = 0"
		+"\n__zero_reg__ = 1"
		+"\n\t.global __do_copy_data"
		+"\n\t.global __do_clear_bss"
		+"\n\t.text"
		+"\n.global main"
		+"\n\t.type   main, @function"
		+"\nmain:"
		+"\n\tpush r29"
		+"\n\tpush r28"
		+"\n\tin r28,__SP_L__"
		+"\n\tin r29,__SP_H__"
		+"\n/* prologue: function */"
		+"\n\tcall _Z18MeggyJrSimpleSetupv"
		+"\n\t/* Need to call this so that the meggy library gets set up */"
	);
        inMainClass(node);
        if(node.getStatement() != null) {
	    System.out.println("Main statement accepting AVRgenVisitor");
            node.getStatement().accept(this);
        }
        outMainClass(node);
    	this.st.pushScope(node.getName());
    	this.st.removeScope();
    	out.println("\n/* epilogue start */"
	        +"\n\tendLabel:"
    		+"\n\tjmp endLabel"
	    	+"\n\tret"
		+"\n\t.size   main, .-main\n"
	);
    }

    @Override
    public void visitIdLiteral(IdLiteral node){
	//System.out.println("Visiting IdLiteral "+node.getLexeme());
        inIdLiteral(node);
	out.println("\n\t# IdExp"
		+"\n\t# load value for variable "+node.getLexeme()
	);
	VarSTE ste = (VarSTE) this.st.lookup(node.getLexeme());
	int offset = 0;
	String base = "";
	if(ste != null){
		offset = ste.getOffset();
		base = ste.getBase();
	} else {
		System.err.println(node.getLexeme() + "not found in symbol table");
	}
	if(base == "Z"){
	    out.println("\n\t# loading the implicit \"this\""
		+"\n\t# load a two byte variable from base+offset"
		+"\n\tldd    r31, Y + 2"
		+"\n\tldd    r30, Y + 1"
		+"\n\t# variable is a member variable"
	    );
	}
	if(st.getExpType(node) == Type.INT || st.getExpType(node) == Type.CLASS){
	    out.println("\t# variable is a local or param variable"
		    +"\n\n\t# load two byte variable from base+offset"
		    +"\n\tldd    r25, "+base+" + "+(offset+1)
		    +"\n\tldd    r24, "+base+" + "+offset
		    +"\n\t# push one byte expression onto stack"
		    +"\n\tpush  r25"
		    +"\n\tpush  r24"
	    );
	} else {
	    out.println("\t# variable is a local or param variable"
		    +"\n\n\t# load a one byte variable from base+offset"
		    +"\n\tldd    r24, "+base+" + "+offset
		    +"\n\t# push one byte expression onto stack"
		    +"\n\tpush	r24"
	    );
	}
        outIdLiteral(node);
    }    

    @Override
    public void visitVarDecl(VarDecl node) {
        inVarDecl(node);
        if(node.getType() != null)
        {
            node.getType().accept(this);
        }
        outVarDecl(node);
    }
    
    @Override
    public void visitAssignStatement(AssignStatement node) {
        inAssignStatement(node);
        if(node.getExp() != null) {
            node.getExp().accept(this);
        }
	out.println("\n\t### AssignStatement"
	    +"\n\t# load rhs exp");
	
	//look up id getting assigned to get size
	VarSTE ste = (VarSTE) this.st.lookup(node.getId());
	if(ste.getType().getAVRTypeSize() == 2){
	    out.println("\tpop    r24"
	        +"\n\tpop    r25"
	    );
	} else {
	    out.println("\tpop    r24");
	}
	
	
	//then store new val into ST
	// number of registers depends on Type
	if(ste.getBase() == "Z"){
		out.println("\n\t# loading the implicit \"this\""
		    +"\n\t# load a two byte variable from base+offset"
		    +"\n\tldd    r31, Y + 2" 
		    +"\n\tldd    r30, Y + 1"
		);
	}
	out.println("\t# store rhs into var "+node.getId());
	if(ste.getType().getAVRTypeSize() == 2){
	    out.print("\tstd    "+ste.getBase()+" + "+(ste.getOffset()+1)+", r25"
		+"\n\tstd    "+ste.getBase()+"+"+ste.getOffset()+", r24"
	    );
	} else {
	    out.print("\tstd	"+ste.getBase()+"+"+ste.getOffset()+", r24");
	}
        outAssignStatement(node);
    }
    



   /** A helper that trims a node's class name before printing it.
    * (E.g., "node.Token" --> "Token".) 
    */
   private void printNodeName(Node node) {
      String fullName = node.getClass().getName();
      String name = fullName.substring(fullName.lastIndexOf('.')+1);
      
      out.print(name);
   }
   
}
