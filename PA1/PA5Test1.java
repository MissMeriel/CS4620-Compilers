import meggy.Meggy;

class PA5Test1 {

    public static void main(String[] args) {
		int ms;
		Meggy.Tone tone;
		bool = true;
		ms = 1000;
		tone = Meggy.Tone.A3;
		Meggy.toneStart(tone, ms);
    }

	class BoolTone {
		public Meggy.Tone boolTone(boolean bool){
			if(bool){
				Meggy.checkButton(Meggy.Button.A);
			} else {
				Meggy.checkButton(Meggy.Button.B);
			}
			return Meggy.Tone.C3;
		}
	}
}
