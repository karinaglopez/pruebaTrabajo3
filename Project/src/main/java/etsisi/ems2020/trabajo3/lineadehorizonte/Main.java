package etsisi.ems2020.trabajo3.lineadehorizonte;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
        Ciudad c = new Ciudad();
        c.cargarEdificios("ciudad1.txt");
        
        LineaHorizonte linea = c.getLineaHorizonte();
        
        linea.guardaLineaHorizonte("salida.txt");
        System.out.println("-- Proceso finalizado Correctamente --");
        Punto  p2 = new Punto(5,6);
        System.out.println(p2);
	}

}
