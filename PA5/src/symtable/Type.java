package symtable;

import exceptions.*;
import java.util.*;

public class Type
{
  public static final Type BOOL = new Type();
  public static final Type INT = new Type();
  public static final Type BYTE = new Type();
  public static final Type COLOR = new Type();
  public static final Type BUTTON = new Type();
  public static final Type VOID = new Type();
  public static final Type CLASS = new Type();
  public static final Type MAINCLASS = new Type();
  public static final Type TONE = new Type();

  private Type()
  {

  }


    
/*
*/

  public String toString()
  {
    if(this == INT)
    {
      return "INT";
    }

    if(this == BOOL)
    {
      return "BOOL";
    }

    if(this == BYTE)
    {
      return "BYTE";
    }

    if(this == COLOR)
    {
      return "COLOR";
    }

    if(this == BUTTON)
    {
      return "BUTTON";
    }
    if(this == TONE)
    {
      return "TONE";
    }
    if(this == CLASS)
    {
      return "CLASS";
    }
    if(this == MAINCLASS)
    {
      return "MAINCLASS";
    }


    
/*
*/
    return "VOID";
  }
  
  public int getAVRTypeSize() {
      if(this == INT) { return 2; }
      if(this == BOOL) { return 1; }
      if(this == BYTE) { return 1; }
      if(this == COLOR) { return 1; }
      if(this == BUTTON) { return 1; }
      if(this == VOID) { return 0; }
      if(this == TONE) { return 1; }
      if(this == CLASS) { return 2; }
      if(this == MAINCLASS) { return 2; }
	 
      return 2; // class references are 2 bytes
  }

    
/*  
*/


  public static Type stringToType(String str) {
    if(str.equalsIgnoreCase("INT")) {
      return Type.INT;
    }
    if(str.equalsIgnoreCase("BOOL")){
      return BOOL;
    }
    if(str.equalsIgnoreCase("BYTE")) {
      return BYTE;
    }
    if(str.equalsIgnoreCase("COLOR")) {
      return COLOR;
    }
    if(str.equalsIgnoreCase("BUTTON"))
    {
      return BUTTON;
    }
    if(str.equalsIgnoreCase("TONE"))
    {
      return TONE;
    }
    if(str.equalsIgnoreCase("CLASS"))
    {
      return CLASS;
    }
    if(str.equalsIgnoreCase("MAINCLASS"))
    {
      return MAINCLASS;
    }
    return null;
  }

}
