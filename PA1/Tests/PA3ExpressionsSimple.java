/**
 * PA3Expressions.java
 * MMS 2/16/11, updated WB 7/15/11
 */

import meggy.Meggy;

class PA3ExpressionsSimple {
	public static void main(String[] whatever){
	    
	        Meggy.setPixel( (byte)((byte)1*(byte)6), (byte)(9-5), Meggy.Color.BLUE);

	        // Byte addition
	        Meggy.setPixel( (byte)((byte)((byte)3+(byte)2)+(byte)1), 
	                        (byte)((byte)((byte)0+(byte)0)+(byte)7), Meggy.Color.RED);

	        // Byte subtraction
	        Meggy.setPixel( (byte)((byte)((byte)3-(byte)2)-(byte)1), 
	                        (byte)((byte)((byte)0-(byte)7)+(byte)7),
	                        Meggy.Color.YELLOW);

                // Unary Minus
                // watch it! spaces significant
	        // Only Byte multiplicatio
	        Meggy.setPixel( (byte)((byte)3*(byte)2), (byte)(5), Meggy.Color.WHITE);
	    
    }
}
