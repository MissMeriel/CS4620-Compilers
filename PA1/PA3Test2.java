import meggy.Meggy;

class PA3Test2 {

    public static void main(String[] args) {
		while (Meggy.checkButton(Meggy.Button.Up)){
			Meggy.setPixel((byte) 1, (byte) 1, Meggy.Color.YELLOW);
			Meggy.delay(200);
			Meggy.setPixel((byte) 1, (byte) 1, Meggy.Color.DARK);
			Meggy.delay(200);
		}
    }
}
