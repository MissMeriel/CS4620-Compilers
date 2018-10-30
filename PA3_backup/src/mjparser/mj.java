
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sat Oct 27 18:45:35 EDT 2018
//----------------------------------------------------

package mjparser;

import java_cup.runtime.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import ast.node.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sat Oct 27 18:45:35 EDT 2018
  */
public class mj extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public mj() {super();}

  /** Constructor which sets the default scanner. */
  public mj(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public mj(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\013\000\002\002\004\000\002\002\006\000\002\002" +
    "\004\000\002\006\023\000\002\005\004\000\002\005\002" +
    "\000\002\004\013\000\002\004\003\000\002\003\006\000" +
    "\002\003\003\000\002\003\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\053\000\006\017\005\070\006\001\002\000\004\002" +
    "\055\001\002\000\004\046\010\001\002\000\006\017\005" +
    "\070\006\001\002\000\004\002\uffff\001\002\000\004\044" +
    "\011\001\002\000\004\037\012\001\002\000\004\061\014" +
    "\001\002\000\004\002\000\001\002\000\004\011\015\001" +
    "\002\000\004\032\016\001\002\000\004\034\017\001\002" +
    "\000\004\036\020\001\002\000\004\016\021\001\002\000" +
    "\004\007\022\001\002\000\004\035\023\001\002\000\004" +
    "\041\024\001\002\000\004\042\025\001\002\000\004\061" +
    "\026\001\002\000\004\010\027\001\002\000\004\011\030" +
    "\001\002\000\010\012\ufffc\052\ufffc\070\ufffc\001\002\000" +
    "\010\012\033\052\035\070\032\001\002\000\010\012\ufffa" +
    "\052\ufffa\070\ufffa\001\002\000\004\012\054\001\002\000" +
    "\010\012\ufffd\052\ufffd\070\ufffd\001\002\000\004\007\036" +
    "\001\002\000\010\007\041\060\040\064\037\001\002\000" +
    "\006\010\ufff7\027\ufff7\001\002\000\006\010\ufff8\027\ufff8" +
    "\001\002\000\004\015\051\001\002\000\004\027\043\001" +
    "\002\000\010\007\041\060\040\064\037\001\002\000\004" +
    "\027\045\001\002\000\010\007\041\060\040\064\037\001" +
    "\002\000\004\010\047\001\002\000\004\044\050\001\002" +
    "\000\010\012\ufffb\052\ufffb\070\ufffb\001\002\000\004\010" +
    "\052\001\002\000\010\007\041\060\040\064\037\001\002" +
    "\000\006\010\ufff9\027\ufff9\001\002\000\004\002\ufffe\001" +
    "\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\053\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\002\006\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\006\012\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\005\030\001\001\000\004\004\033\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\003\041\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\003\043\001\001\000\002\001\001\000\004\003\045" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\003\052\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$mj$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$mj$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$mj$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    public PrintWriter out; 
    
    public String programName;

    public void unrecovered_syntax_error(Symbol cur_SymbolValue) {
        //System.out.println("cur_SymbolValue: "+ cur_SymbolValue.value);
		report_fatal_error("Fatal syntax error", cur_SymbolValue);
    }
    
    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
        done_parsing();
        Symbol sym = (Symbol)info;
        mjparser.SymbolValue tok = (mjparser.SymbolValue)sym.value;
        throw new mjparser.ParseException("Fatal parsing error", 
                                          tok.line, tok.pos);
    }
    
    public void report_error(String message, Object info) {
        Symbol sym = (Symbol)info;
        mjparser.SymbolValue tok = (mjparser.SymbolValue)sym.value;
		System.out.println("sym null? "+(sym == null));
		System.out.println("sym.sym: "+sym.sym);
		System.err.println("tok null? "+ (tok == null));
		System.err.println("tok.lexeme: "+ tok.lexeme);
        System.err.println("[" + tok.line + "," + tok.pos + "] "); 
                              System.err.println( message + " at " + tok.toString() );
    }
            

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$mj$actions {
  private final mj parser;

  /** Constructor */
  CUP$mj$actions(mj parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$mj$do_action(
    int                        CUP$mj$act_num,
    java_cup.runtime.lr_parser CUP$mj$parser,
    java.util.Stack            CUP$mj$stack,
    int                        CUP$mj$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$mj$result;

      /* select the action based on the action number */
      switch (CUP$mj$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // exp ::= COLOR_LITERAL 
            {
              IExp RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).right;
		SymbolValue c = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.peek()).value;
		
		RESULT = new ColorLiteral(c.line, c.pos, c.lexeme, c.value);
	
              CUP$mj$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // exp ::= INT_LITERAL 
            {
              IExp RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).right;
		SymbolValue i = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.peek()).value;
		
		RESULT = new IntLiteral(i.line, i.pos, i.lexeme, i.value);
		//RESULT = new IntLiteral(i.getLine(), i.getPos(), i.lexeme, i.value);
	
              CUP$mj$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // exp ::= LPAREN BYTE RPAREN exp 
            {
              IExp RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).right;
		IExp e = (IExp)((java_cup.runtime.Symbol) CUP$mj$stack.peek()).value;
			
		//IntLiteral intLiteral = new IntLiteral(e.getLine(), e.getPos(), e.lexeme, e.value);
		//IExp iExp = new IExp(e.line, e.pos);
		RESULT = new ByteCast(e.getLine(), e.getPos(), (IntLiteral)e);
	
              CUP$mj$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // statement ::= COMMENT 
            {
              IStatement RESULT =null;

              CUP$mj$result = parser.getSymbolFactory().newSymbol("statement",2, ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // statement ::= MEGGYSETPIXEL LPAREN exp COMMA exp COMMA exp RPAREN SEMI 
            {
              IStatement RESULT =null;
		int mspleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-8)).left;
		int mspright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-8)).right;
		SymbolValue msp = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-8)).value;
		int e1left = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-6)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-6)).right;
		IExp e1 = (IExp)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-6)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-4)).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-4)).right;
		IExp e2 = (IExp)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-4)).value;
		int e3left = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-2)).left;
		int e3right = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-2)).right;
		IExp e3 = (IExp)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-2)).value;
		
		RESULT = new MeggySetPixel(msp.line, msp.pos, e1, e2, e3);
	
              CUP$mj$result = parser.getSymbolFactory().newSymbol("statement",2, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-8)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // statement_list ::= 
            {
              List<IStatement> RESULT =null;
		
			RESULT = new LinkedList<IStatement>();
		
              CUP$mj$result = parser.getSymbolFactory().newSymbol("statement_list",3, ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // statement_list ::= statement_list statement 
            {
              List<IStatement> RESULT =null;
		int slleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)).left;
		int slright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)).right;
		List<IStatement> sl = (List<IStatement>)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-1)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).right;
		IStatement s = (IStatement)((java_cup.runtime.Symbol) CUP$mj$stack.peek()).value;
		
			sl.add(s);
			RESULT = sl;
		
              CUP$mj$result = parser.getSymbolFactory().newSymbol("statement_list",3, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // main_class ::= CLASS ID LBRACE PUBLIC STATIC VOID MAIN LPAREN STRING LBRACKET RBRACKET ID RPAREN LBRACE statement_list RBRACE RBRACE 
            {
              MainClass RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-16)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-16)).right;
		SymbolValue c = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-16)).value;
		int nameleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-15)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-15)).right;
		SymbolValue name = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-15)).value;
		int paramleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-5)).left;
		int paramright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-5)).right;
		SymbolValue param = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-5)).value;
		int lleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)).right;
		SymbolValue l = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-3)).value;
		int listleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-2)).left;
		int listright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-2)).right;
		List<IStatement> list = (List<IStatement>)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-2)).value;
		
			//IdLiteral nameIdLiteral = new IdLiteral(name.line, name.pos, name.lexeme);			
			//IdLiteral paramIdLiteral = new IdLiteral(param.line, param.pos, param.lexeme);
			//BlockStatement blockStatement = new BlockStatement(list.line, list.pos, list);
			BlockStatement blockStatement = new BlockStatement(l.line, l.pos, list);
			MainClass _mainClass_ = new MainClass(c.line, c.pos, name.lexeme, param.lexeme, blockStatement);
			RESULT = _mainClass_; 
		
              CUP$mj$result = parser.getSymbolFactory().newSymbol("main_class",4, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-16)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= COMMENT program 
            {
              Program RESULT =null;

              CUP$mj$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // program ::= IMPORT MEGGY SEMI main_class 
            {
              Program RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)).right;
		SymbolValue i = (SymbolValue)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-3)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).left;
		int mright = ((java_cup.runtime.Symbol)CUP$mj$stack.peek()).right;
		MainClass m = (MainClass)((java_cup.runtime.Symbol) CUP$mj$stack.peek()).value;
		
		RESULT = new Program(i.line, i.pos, m, null);
	
              CUP$mj$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-3)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          return CUP$mj$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)).right;
		Program start_val = (Program)((java_cup.runtime.Symbol) CUP$mj$stack.elementAt(CUP$mj$top-1)).value;
		RESULT = start_val;
              CUP$mj$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$mj$stack.elementAt(CUP$mj$top-1)), ((java_cup.runtime.Symbol)CUP$mj$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$mj$parser.done_parsing();
          return CUP$mj$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

