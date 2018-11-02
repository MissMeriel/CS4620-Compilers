import meggy.Meggy;

class PA3Test3 {

    public static void main(String[] args) {
		Meggy.setPixel((byte) 7, (byte) 7, Meggy.Color.VIOLET);
		Meggy.delay(200);
		if(Meggy.getPixel((byte) 7, (byte) 7) == Meggy.Color.VIOLET){
			Meggy.setPixel((byte) 7, (byte) 7, Meggy.Color.YELLOW);
		} else {
			Meggy.setPixel((byte) 7, (byte) 7, Meggy.Color.DARK);
		}
    }
}
