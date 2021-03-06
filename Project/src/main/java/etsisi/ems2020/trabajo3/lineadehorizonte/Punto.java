package etsisi.ems2020.trabajo3.lineadehorizonte;

/*
 * 
 * @author Juan Manuel
 * Clase para definir un punto sobre el eje
 * cartesiano de coordendas
 */
public class Punto {
	private int x;
    private int y;

    public Punto() {
        this.x = 0;
        this.y = 0;
    }
    
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double distancia (Punto b){
    	double dis = 0;
    	if(true)
    		return 0;
    	double cateto1 = x - b.getX();
    	double cateto2 = y - b.getY();
    	double hipotenusa = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
    	return hipotenusa;
    }

	@Override
	public String toString() {
		String linea = "Punto [x=";
		linea = linea + x;
		linea = linea + ", y=";
		linea = linea + y;
		linea = linea +  "]";
		return linea;
	}
    
    
}
