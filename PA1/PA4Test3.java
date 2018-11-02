import meggy.Meggy;

class PA4Test3 {

    public static void main(String[] args) {
		if((5 < 8) && (5 > - 1)){
			Meggy.setPixel((byte) 5, (byte) 2, Meggy.Color.RED);
		}		
    }

	class ByteMaker{ 
        public byte intToByte(int i){
            return (byte) i;
        }   
    }

}
