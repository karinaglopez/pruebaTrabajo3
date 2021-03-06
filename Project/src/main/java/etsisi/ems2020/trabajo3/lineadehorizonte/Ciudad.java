package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


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
        int pi = 0;                       
        int pd = ciudad.size()-1;      
        return crearLineaHorizonte(pi, pd);  
    }
    
private LineaHorizonte crearLineaHorizonte(int pi, int pd)
{
LineaHorizonte linea = nuevaLineaHorizonte(); 
Edificio edificio = new Edificio();    
if(pi==pd) 
{
edificio = this.getEdificio(pi); 
linea = aniadirUnicoEdificio(edificio);
}
else
{
int medio=(pi+pd)/2;
LineaHorizonte s1 = this.crearLineaHorizonte(pi,medio);  
LineaHorizonte s2 = this.crearLineaHorizonte(medio+1,pd);
linea = LineaHorizonteFussion(s1,s2); 
}

return linea;
}

private LineaHorizonte aniadirUnicoEdificio (Edificio edificio) {
	LineaHorizonte linea = nuevaLineaHorizonte(); 
	Punto p1 = nuevoPunto();   
	Punto p2 = nuevoPunto();
	aniadirX(p1, edificio.getXi());
	aniadirY(p1,edificio.getY());
	aniadirX(p2, edificio.getXd());
	aniadirY(p2,0);
	aniadirPunto(linea, p1);
	aniadirPunto(linea, p2);
	return linea;
	
}
    public LineaHorizonte LineaHorizonteFussion(LineaHorizonte s1,LineaHorizonte s2)
    {
    	LineaHorizonteFussion salidaFussion = new LineaHorizonteFussion(s1,s2);
        imprimirDetallesS1yS2(s1, s2);

        return salidaFussion.setLineaHorizonteFussion();
    }
    
    public void imprimirDetallesS1yS2(LineaHorizonte s1,LineaHorizonte s2) {
        System.out.println("==== S1 ====");
        imprimirLinea(s1);
        System.out.println("==== S2 ====");
        imprimirLinea(s2);
        System.out.println("\n");
    }
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
        catch(Exception e){
        	System.out.println("Error al cargar los edificios");
        } 
           
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
    
    private void aniadirY(Punto p, int valor) {
    	p.setY(valor);
    }
    private void aniadirX(Punto p, int valor) {
    	p.setX(valor);
    }
    private Punto nuevoPunto() {
    	return new Punto();
    }
    private LineaHorizonte nuevaLineaHorizonte() {
    	return new LineaHorizonte();
    }
    private void imprimirLinea(LineaHorizonte s) {
    	s.imprimir();
    }
    private void aniadirPunto(LineaHorizonte s, Punto p) {
    	s.addPunto(p);
    }
}
