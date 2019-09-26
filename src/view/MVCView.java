package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("Pulse 1 para generar muestra.");
			System.out.println("Pulse 2 para encontrar tiempos en un rango dado. ");
			System.out.println("Pulse 3 para agregar e eliminar muestra. ");
			System.out.println("Pulsa 4 para salir");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
