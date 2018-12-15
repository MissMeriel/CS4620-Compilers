/**
 * PA4raindrop.java
 * 
 * A blue pixel falls from top to bottom of the screen over and over in an
 * infinite loop.
 *   
 * MMS, 2/2/11
 */

import meggy.Meggy;

class PA4raindropSimpler {

    public static void main(String[] whatever){

        while (true) {
            new Cloud().rain((byte)3,(byte)7);
        }
    }
}

class Cloud {
    
    public void rain(byte x, byte y) {

        if (((byte)(0-1) < y)) {
            Meggy.setPixel(x, y, Meggy.Color.BLUE);
        } else {}
    }
}