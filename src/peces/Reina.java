package peces;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

public class Reina extends Peca {

	public Reina(MyColor pCol, Posicio pos) {
		super(pCol, 15, pos);		
	}
	
	private boolean casellesIntermitjesBuides(Tauler tauler, Posicio posicioNova) {
        int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1,y2);
        int maxY = Math.max(y1,y2);
        
        if(x1 == x2)
            for(int i = minY + 1; i< maxY; i++)
                if(tauler.getCaselles()[x1][i] instanceof AmbPeca)
                    return false;
               
        if(y1 == y2)
            for(int i = minX + 1; i< maxX; i++)
                if(tauler.getCaselles()[i][y1] instanceof AmbPeca)
                    return false;
        
        if(x1 - x2 == y1-y2)
            for(int i = minX + 1; i< maxX; i++)
                if(tauler.getCaselles()[i][minY-minX + i] instanceof AmbPeca)
                    return false;
               
        if(x1 - x2 == y2 - y1)
            for(int i = minX + 1; i< maxX; i++)
                if(tauler.getCaselles()[i][maxX + minY -i] instanceof AmbPeca)
                    return false;
                
        return true;
	}

	@Override
	public boolean[][] posicionsPosibles() {
		boolean [][] posicionsPosibles = new boolean [8][8];
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (i == x || j == y )
                    posicionsPosibles[i][j] = true;
                if (i-x == j-y|| i-x == y-j )
                    posicionsPosibles[i][j] = true;                
            }
        }
        posicionsPosibles[x][y] = false;
        return posicionsPosibles;
	}

	@Override
	public boolean movimentPosible(Tauler tauler, Posicio posicioNova) {
		int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();
        
        if(this.posicionsPosibles()[x2][y2]== false)
        	return false;    
        else
        	if (tauler.pecesDelMateixEquip(this.equip)[x2][y2])
        		return false;
        	else
        		if (this.casellesIntermitjesBuides(tauler, posicioNova) == false)
        			return false;
        return true;
    }
	
	@Override
	public String toString() {
		return "X"+super.toString();
	}
}
