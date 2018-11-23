import meggy.Meggy;

class PA4Test1_2 {

    public static void main(String[] args) {
		this.boolTone(true);
	}

	public void boolTone(boolean t){
		if(t){
			Meggy.checkButton(Meggy.Button.A);
			Meggy.toneStart(Meggy.Tone.A3, 1000);
		} else {
			Meggy.checkButton(Meggy.Button.B);
			Meggy.toneStart(Meggy.Tone.C3, 1000);
		}
	}
}
