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
        this.nodeStack = new Stack<Integer>();
    } 

   /** Upon entering each node in AST, check of this node is the root
   to generate start of .dot file, output the dot output for the node.
   */
   public void defaultIn(Node node) {
       if (nodeStack.empty()) {
           //out.println("digraph ASTGraph {");
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
			+"\n\tcall _Z18MeggyJrSimpleSetupv");
       } 

       nodeDotOutput(node);
       
       // store this node id on the nodeStack
       // the call to nodeDotOutput increments for
       // the next guy so we have to decrement here
       nodeStack.push(nodeCount-1);    
   } //end defaultIn
  

 
   public void defaultOut(Node node) {

       nodeStack.pop();
     if (nodeStack.empty()) {
		out.println("/* epilogue start */"
			+"\n\tendLabel:"
			+"\n\tjmp endLabel"
			+"\n\tret"
			+"\n\t.size   main, .-main");
     }else{
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
			out.println("\n\t/* cast to byte */"
				+"\n\tpop r25"
				+"\n\tpop r24"
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
        }   
		/*else if(node instanceof MulExp){
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

		else if(node instanceof ){
			out.println(
				+"\n\t"
				+"\n\t"
				+"\n\t"
				+"\n\t"
				+"\n\t"
				+"\n\t"
				+"\n\t"
				);
		}*/

	 }
     out.flush();
   } //end defaultOut
   
   private void nodeDotOutput(Node node)
   {
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
			out.println("\n\t#### short-circuited && operation"
				);
		}
		else if(node instanceof WhileStatement){
		}
		/*else if(node instanceof ){
			out.println(""
				+"\n\t"
				+"\n\t"
				+"\n\t"
				);
		}*/
       nodeCount++;
   }//end nodeDotOutput called by defaultIn

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
                +"\n\t#WANT breq MJ_L"+ (++breakCount)
                +"\n\tbrne   "+ then_label
                +"\n\tjmp    " + else_label 
                +"\n"
                +"\n\t#then label for if" 
                +"\n"+then_label+":" 
            ); 
            node.getThenStatement().accept(this);
			out.println("\n\tjmp	"+done_label);
        }
        if(node.getElseStatement() != null)
        {
			out.println("\n\t# else label for if"
				+"\n"+else_label+":"
				);
		    node.getElseStatement().accept(this);
			out.println("\n\tjmp	"+done_label);
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
    public void visitMulExp(MulExp node)
    {    
        inMulExp(node);
        if(node.getLExp() != null)
        {    
			if(node.getLExp() instanceof IExp){
				node.getLExp().accept(this);
			} else {
				System.out.println("["+node.getLExp().getLine()+","+node.getLExp().getPos()+"] Invalid left operand type for operator *");
			}
        }    
        if(node.getRExp() != null)
        {    
            if(node.getRExp() instanceof IExp) {
				node.getRExp().accept(this);
            } else {
                System.out.println("["+node.getRExp().getLine()+","+node.getRExp().getPos()+"] Invalid right operand type for operator *");
            }   
        }    
        outMulExp(node);
    }

    @Override
    public void visitPlusExp(PlusExp node)
    {
        inPlusExp(node);
        if(node.getLExp() != null)
        {
            if(node.getLExp() instanceof IExp){
                node.getLExp().accept(this);
            } else {
                System.out.println("["+node.getLExp().getLine()+","+node.getLExp().getPos()+"] Invalid left operand type for operator +");
            }   
        }         
        if(node.getRExp() != null)
        {
            if(node.getRExp() instanceof IExp) {
                node.getRExp().accept(this);
            } else {
                System.out.println("["+node.getRExp().getLine()+","+node.getRExp().getPos()+"] Invalid right operand type for operator +");
            }
        }
        outPlusExp(node);
    }

    @Override
    public void visitMinusExp(MinusExp node)
    {
        inMinusExp(node);
        if(node.getLExp() != null)
        {
            if(node.getLExp() instanceof IExp){
                node.getLExp().accept(this);
            } else {
                System.out.println("["+node.getLExp().getLine()+","+node.getLExp().getPos()+"] Invalid left operand type for operator -");
            }
        }
        if(node.getRExp() != null)
        {
            if(node.getRExp() instanceof IExp) {
                node.getRExp().accept(this);
            } else {
                System.out.println("["+node.getRExp().getLine()+","+node.getRExp().getPos()+"] Invalid right operand type for operator -");
            }
        }
        outMinusExp(node);
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
