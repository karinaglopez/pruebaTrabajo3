package etsisi.ems2020.trabajo3.lineadehorizonte;

public class LineaHorizonteFussion {
	private LineaHorizonte s1;
	private LineaHorizonte s2;
	private LineaHorizonte salida;
	private int s1y;
	private int s2y;
	private int prev;
	
	public LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2) {
    	this.s1y = -1;
    	this.s2y = -1;
    	this.prev = -1;
        this.salida = nuevaLineaHorizonte(); // LineaHorizonte de salida
        this.s1 = s1;
        this.s2 = s2;
	}

    public LineaHorizonte setLineaHorizonteFussion()
    {
        comenzarFussion();
        vaciarS1();
        vaciarS2();
        return this.salida;
    }
    private void comenzarFussion() {
        Punto p1 = crearPunto(0,0);       
        Punto p2 = crearPunto(0,0);  
        Punto paux = crearPunto(0,0);
    	
        while ((!isLineaVacia(s1)) && (!isLineaVacia(s2))) 
        {
            paux = crearPunto(0,0);  // Inicializamos la variable paux
            p1 = obtenerPunto(this.s1,0); // guardamos el primer elemento de s1
            p2 = obtenerPunto(this.s2,0); // guardamos el primer elemento de s2
            comprobarValorX(p1,p2);
        }
    }
    private void vaciarS1() {
    	Punto paux = crearPunto(0,0);
        while ((!isLineaVacia(s1))) //si aun nos quedan elementos en el s1
        {
            paux=obtenerPunto(this.s1,0); // guardamos en paux el primer punto
            actualizarPunto(paux);
            borrarPuntoAux(s1,0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
        }
    }
    private void vaciarS2() {
    	Punto paux = crearPunto(0,0);
        while((!isLineaVacia(this.s2))) //si aun nos quedan elementos en el s2
        {
            paux=obtenerPunto(this.s2,0); // guardamos en paux el primer punto
            actualizarPunto(paux);
            borrarPuntoAux(this.s2,0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
        }
    }
    private void actualizarPunto(Punto paux) {
        if (obtenerY(paux)!=this.prev) // si este maximo no es igual al del segmento anterior
        {
            aniadirPunto(this.salida, paux); // añadimos el punto al LineaHorizonte de salida
            this.prev = obtenerY(paux);    // actualizamos prev
        }
    }
    private void actualizarS1(Punto p1) {
    	Punto paux = crearPunto(obtenerX(p1), Math.max(obtenerY(p1), this.s2y));
        
        actualizarPunto(paux);

        this.s1y = obtenerY(p1);   // actualizamos la altura s1y
        borrarPuntoAux(this.s1,0); // en cualquier caso eliminamos el punto de s1 (tanto si se añade como si no es valido)
    
    }
    private void actualizarS2(Punto p2){
    	Punto paux = crearPunto(obtenerX(p2), Math.max(obtenerY(p2), this.s1y));
       
        actualizarPunto(paux);
    
        this.s2y = obtenerY(p2);   // actualizamos la altura s2y
        borrarPuntoAux(this.s2,0); // en cualquier caso eliminamos el punto de s2 (tanto si se añade como si no es valido)
    }
    private void actualizarS1yS2(Punto p1, Punto p2) {
        if ((obtenerY(p1) > obtenerY(p2)) && (obtenerY(p1)!=this.prev)) // guardaremos aquel punto que tenga la altura mas alta
        {
        	aniadirPunto(this.salida, p1);
            this.prev = obtenerY(p1);
        }
        if ((obtenerY(p1) <= obtenerY(p2)) && (obtenerY(p2)!=prev))
        {
        	aniadirPunto(this.salida, p2);
            this.prev = obtenerY(p2);
        }
        this.s1y = obtenerY(p1);   // actualizamos la s1y e s2y
        this.s2y = obtenerY(p2);
        borrarPuntoAux(this.s1,0);
        borrarPuntoAux(this.s2,0);
    }
    private void comprobarValorX(Punto p1, Punto p2) {
        if (obtenerX(p1) < obtenerX(p2)) // si X del s1 es menor que la X del s2
        {
        	actualizarS1(p1);
        }
        else if (obtenerX(p1) > obtenerX(p2)) // si X del s1 es mayor que la X del s2
        {
        	actualizarS2(p2);
        }
        else // si la X del s1 es igual a la X del s2
        {
        	actualizarS1yS2(p1, p2);

        }
    }
    private int obtenerY(Punto p) {
    	return p.getY();
    }
    private int obtenerX(Punto p) {
    	return p.getX();
    }
    private Punto crearPunto(int x, int y) {
    	Punto punto = new Punto(x, y);
        return punto;
    }
    private LineaHorizonte nuevaLineaHorizonte () {
    	return new LineaHorizonte();
    }
    private Punto obtenerPunto(LineaHorizonte s, int valor) {
    	return s.getPunto(valor);
    }
    private boolean isLineaVacia(LineaHorizonte s) {
    	return s.isEmpty();
    }
    private void borrarPuntoAux(LineaHorizonte s, int i) {
    	s.borrarPunto(i);
    }
    private void aniadirPunto(LineaHorizonte s, Punto p) {
    	s.addPunto(p);
    }

}
