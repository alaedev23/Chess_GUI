package peces;

import joc.MyColor;
import joc.Posicio;

public class SensePeca extends Escac {
	
	public SensePeca(MyColor c, Posicio pos) {
		super(c, pos);
	}

	@Override
	public String toString() {
		return super.toString() + "  ";
	}
	
	
}
