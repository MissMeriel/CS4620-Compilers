import meggy.Meggy;

class Exercise9 {
	public static void main(String[] args) {
		boolean t = true;
		new Drawer().draw();
	}

}
class Drawer{
	public void draw(){
		Meggy.setPixel((byte) 7, (byte) 3, Meggy.Color.BLUE);
	}
}
