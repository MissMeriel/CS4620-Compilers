import meggy.Meggy;

class PA4Test1 {

    public static void main(String[] args) {
			if(true){
				Meggy.checkButton(Meggy.Button.A);
			} else {
				Meggy.checkButton(Meggy.Button.B);
			}
			Meggy.toneStart(Meggy.Tone.Cs3, 1000);
	}

	class BoolTone {
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
}
