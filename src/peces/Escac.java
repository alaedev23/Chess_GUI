package peces;

import joc.MyColor;
import joc.Posicio;

public abstract class Escac {
    protected MyColor color;
    protected Posicio posicio;
    
    public Escac (MyColor color, Posicio pos) {
    	this.color = color;
    	this.posicio = pos;
    }
     
    public MyColor getColor() {
        return this.color;
    }
    
    public Posicio getPosicio() {
    	return this.posicio;
    }

	@Override
	public String toString() {		
		return ((this.color == MyColor.BLANC) ? "b" : "n");
	}
    
}
