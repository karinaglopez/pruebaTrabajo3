package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class LineaHorizonte {
	
	private ArrayList <Punto> LineaHorizonte ;

    public LineaHorizonte()
    {
        LineaHorizonte = new ArrayList <Punto>();
    }
    public Punto getPunto(int i) {
        return (Punto)this.LineaHorizonte.get(i);
    }
    public void addPunto(Punto p)
    {
        LineaHorizonte.add(p);
    }    
    public void borrarPunto(int i)
    {
        LineaHorizonte.remove(i);
    }
    
    public int size()
    {
        return LineaHorizonte.size();
    }
    public boolean isEmpty()
    {
        return LineaHorizonte.isEmpty();
    }
    
    public void guardaLineaHorizonte (String fichero)
    {	
        try
        {
        	PrintWriter out = new PrintWriter (new FileWriter(fichero));
            actualizarFichero(out);
            out.close();
        }
        catch(Exception e){
        	System.out.println("Error al guardar la línea");
        }
    }
    
    public void actualizarFichero(PrintWriter out){
        Punto p = new Punto();
        for(int i=0; i<this.size(); i++)
        {
            p=(getPunto(i));
            out.print(p.getX());
            out.print(" ");
            out.print(p.getY());
            out.println();
        }
    }
    
    
    public void imprimir (){
    	
    	for(int i=0; i< LineaHorizonte.size(); i++ ){
    		System.out.println(cadena(i));
    	}
    }
    
    public String cadena (int i){
    	Punto p = LineaHorizonte.get(i);
    	String linea = p.toString();
		return linea;
    }
}
