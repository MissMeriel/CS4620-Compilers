import meggy.Meggy;

class PA5Test3 {

    public static void main(String[] args) {
		int i;
		i = 5;
		if(i < 8 && i > -1){
			Meggy.setPixel((byte) i, (byte) i, Meggy.Color.RED);
		}
    }

	class ByteMaker{ 
        public byte intToByte(int i){
            return (byte) i;
        }   
    }

}
