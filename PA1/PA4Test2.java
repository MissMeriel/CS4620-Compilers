import meggy.Meggy;

class PA4Test2 {

    public static void main(String[] args) {
		if(Meggy.checkButton(Meggy.Button.B)){
			Meggy.toneStart(Meggy.Tone.Cs3, 3);
		}

	class ButtonVerifier{	
		public boolean pressed(Meggy.Button b){
				return Meggy.checkButton(b);
			}
		}
	}
}
