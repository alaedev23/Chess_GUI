package peces;

import joc.MyColor;

public class AmbPeca extends Escac {
	private Peca peca;
	
	public AmbPeca(Peca p, MyColor c) {
		super(c, p.getPosicio().clone());
		this.peca = p;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	@Override
	public String toString() {
		return super.toString() + this.peca.toString();
	}
	
	
}
