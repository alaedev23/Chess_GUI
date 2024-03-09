package joc;

public class Posicio {
	private int x;
	private int y;
	
	public Posicio () {
		this(0,0);
	}
	
	public Posicio (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public Posicio clone() {
		return new Posicio(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "["+this.x+", "+this.y+"]";
	}
}
