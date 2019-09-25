package controller;

import java.io.IOException;
import java.util.Scanner;

import model.logic.MVCModelo;
import model.logic.TravelTime;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		
		try {
			modelo.cargarArchivos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					System.out.println("Ingrese un número de viajes."); 
					int n= lector.nextInt();
					TravelTime[] muestra=modelo.generarMuestra(n);
					
					System.out.println("--------- \n Generar muestra \n---------"); 
					
					int i=0;
					while(i<n)
					{
						System.out.println("Tiempo promedio: " +muestra[i].getMeanTravelTime()); 
						i++;
					}
					
					System.out.println("");
					
					break;

				case 2:
					
					System.out.println("Ingrese un numero de viajes.");
					n=lector.nextInt();
					
					System.out.println("Ingrese la hora menor de un rango.");
					int hI= lector.nextInt();
					System.out.println("Ingrese la hora mayor de un rango.");
					int hF= lector.nextInt();
					
					Object[] muestras= modelo.viajesMasLargosEnRango(n, hI, hF);
					
					TravelTime[] heap= (TravelTime[]) muestras[0];
					TravelTime[] cola= (TravelTime[]) muestras[1];
					
					i=0;
					System.out.println("Tiempos Heap");
					
					while(i<n)
					{
						System.out.println(heap[i].getMeanTravelTime());	
						i++;
					}
					
					i=0;
					System.out.println("Tiempos Cola");
					
					while(i<n)
					{
						System.out.println(cola[i].getMeanTravelTime());	
						i++;
					}
					
					break;
				case 3: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
