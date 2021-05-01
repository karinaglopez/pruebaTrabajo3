package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/*
 Clase fundamental.
 Sirve para hacer la lectura del fichero de entrada que contiene los datos de como
 están situados los edificios en el fichero de entrada. xi, xd, h, siendo. Siendo
 xi la coordenada en X origen del edificio iésimo, xd la coordenada final en X, y h la altura del edificio.
 
 */
public class Ciudad {
	
    private ArrayList <Edificio> ciudad;

    public Ciudad()
    {
		ciudad = new ArrayList <Edificio>();
		metodoRandom(5);   
		ciudad = new ArrayList <Edificio>();
}
    
        
    public Edificio getEdificio(int i) {
        return (Edificio)this.ciudad.get(i);
    }
     
    public void addEdificio (Edificio e)
    {
        ciudad.add(e);
    }
    public void removeEdificio(int i)
    {
        ciudad.remove(i);
    }
    
    public int size()
    {
        return ciudad.size();
    }
    
    public LineaHorizonte getLineaHorizonte()
    {
    	// pi y pd, representan los edificios de la izquierda y de la derecha.
        int pi = 0;                       
        int pd = ciudad.size()-1;      
        return crearLineaHorizonte(pi, pd);  
    }
    
public LineaHorizonte crearLineaHorizonte(int pi, int pd)
{
LineaHorizonte linea = new LineaHorizonte(); // LineaHorizonte de salida
Edificio edificio = new Edificio();    
//Caso base, la ciudad solo tiene un edificio, el perfil es el de ese edificio. 
if(pi==pd) 
{
edificio = this.getEdificio(pi); // Obtenemos el único edificio y lo guardo en b
linea = aniadirUnicoEdificio(edificio);
}
else
{
//Edificio mitad
int medio=(pi+pd)/2;
LineaHorizonte s1 = this.crearLineaHorizonte(pi,medio);  
LineaHorizonte s2 = this.crearLineaHorizonte(medio+1,pd);
linea = LineaHorizonteFussion(s1,s2); 
}

return linea;
}

private LineaHorizonte aniadirUnicoEdificio (Edificio edificio) {
	LineaHorizonte linea = new LineaHorizonte(); 
	Punto p1 = new Punto();   
	Punto p2 = new Punto();
	p1.setX(edificio.getXi());       
	p1.setY(edificio.getY());        // guardo la altura
	p2.setX(edificio.getXd());       
	p2.setY(0);                      // como el edificio se compone de 3 variables, en la Y de p2 le añadiremos un 0
	//Añado los puntos a la línea del horizonte
	linea.addPunto(p1);      
	linea.addPunto(p2);
	return linea;
	
}
    
    /**
     * Función encargada de fusionar los dos LineaHorizonte obtenidos por la técnica divide y
     * vencerás. Es una función muy compleja ya que es la encargada de decidir si un
     * edificio solapa a otro, si hay edificios contiguos, etc. y solucionar dichos
     * problemas para que el LineaHorizonte calculado sea el correcto.
     */
    public LineaHorizonte LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2)
    {
    	// en estas variables guardaremos las alturas de los puntos anteriores, en s1y la del s1, en s2y la del s2 
    	// y en prev guardaremos la previa del segmento anterior introducido
    	LineaHorizonteFussion salidaFussion = new LineaHorizonteFussion(s1,s2);
        imprimirDetallesS1yS2(s1, s2);

        return salidaFussion.setLineaHorizonteFussion();
    }
    
    public void imprimirDetallesS1yS2(LineaHorizonte s1,LineaHorizonte s2) {
        System.out.println("==== S1 ====");
        s1.imprimir();
        System.out.println("==== S2 ====");
        s2.imprimir();
        System.out.println("\n");
    }
    /*
     Método que carga los edificios que me pasan en el
     archivo cuyo nombre se encuentra en "fichero".
     El formato del fichero nos lo ha dado el profesor en la clase del 9/3/2020,
     pocos días antes del estado de alarma.
     */

    public void cargarEdificios (String fichero)
    {
    	
        try
        {
            Scanner sr = new Scanner(new File(fichero));

            while(sr.hasNext())
            {
                this.addEdificio(new Edificio(sr.nextInt(), sr.nextInt(), sr.nextInt()));
            }
        }
        catch(Exception e){} 
           
    }

    
    public void metodoRandom(int n)
    {
        int i=0;
        int xi,y,xd;
        for(i=0;i<n;i++)
        {
            xi=(int)(Math.random()*100);
            y=(int)(Math.random()*100);
            xd=(int)(xi+(Math.random()*100));
            this.addEdificio(new Edificio(xi,y,xd));
        }
    }
}
