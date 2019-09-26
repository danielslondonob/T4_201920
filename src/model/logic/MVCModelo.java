package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	
	//CAMBIAR POR ESTRUCTURA A USAR
	
	private MaxHeapCP heap;
	private MaxColaCP cola;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		heap = new MaxHeapCP();
		cola= new MaxColaCP();
	}

	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return heap.darNumElementos();
	}
	
	public void cargarArchivos() throws IOException
	{
		CSVReader reader = null;
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));

			
			reader.readNext();

			
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heap.insert(actual);
				cola.insert(actual);
			}
//			int i=0;
//			while(i<200000)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(1, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				heap.insert(actual);
//				cola.insert(actual);
//				i++;
//			}
			

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));

			
			reader.readNext();

			
			for(String[] nextLine : reader)
			{
				TravelTime actual= new TravelTime(2,Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heap.insert(actual);
				cola.insert(actual);
			}
			
//			int i=0;
//			while(i<200000)
//			{
//				String [] nextLine= reader.readNext();
//				TravelTime actual= new TravelTime(2, Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
//				heap.insert(actual);
//				cola.insert(actual);
//				i++;
//			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
	
	public TravelTime[] generarMuestraHeap(int n)
	{
		 
		TravelTime[] muestra= new TravelTime[n];
		int i=0;
		
		//Sacando n tiempos del heap.
		while(i<n)
		{
			muestra[i]= (TravelTime) heap.delMax();
			i++;
			
		}
		
		//Devolviendo los tiempos para no afectar los datos
		i--;

		while(i>=0)
		{
			//En este momento se puede calcular el tiempo de agregar los datos a la cola
			heap.insert(muestra[i]);
			i--;
		}

		return muestra;
	}
	
	public TravelTime[] generarMuestraCola(int n)
	{
		 
		  
		TravelTime[] muestra= new TravelTime[n];
		int i=0;
		
		//Sacando n tiempos del heap.
		while(i<n)
		{
			muestra[i]= (TravelTime) cola.delMax();
			i++;
			
		}
		
		//Devolviendo los tiempos para no afectar los datos
		i--;

		while(i>=0)
		{
			//En este momento se puede calcular el tiempo de agregar los datos a la cola
			cola.insert(muestra[i]);
			i--;
		}

		return muestra;
	}
	
	public Object[] viajesMasLargosEnRango(int n, int horaInicial, int horaFinal)
	{
		Object[] muestras= new Object[2];
		
		muestras[0]= viajesMasLargosHeap(n,horaInicial,horaFinal);
		muestras[1]= viajesMasLargosCola(n,horaInicial,horaFinal);
		
		return muestras;
	}


	private TravelTime[] viajesMasLargosHeap(int n, int horaInicial, int horaFinal) 
	{
		TravelTime[] muestra= new TravelTime[n];
		TravelTime[] auxiliar= new TravelTime[darTamano()];
		
		int i=0;
		int j=0;
		
		while(i<n)
		{
			TravelTime actual= (TravelTime) heap.delMax();
			
			if(actual.getHod()>=horaInicial && actual.getHod()<=horaFinal)
			{
				muestra[i]= actual;
				
				i++;
			}
			auxiliar[j]=actual;
			j++;
		}
		
		j--;
		
		while(j>=0)
		{
			heap.insert(auxiliar[j]);
			j--;
		}
		
		return muestra;
	}


	private TravelTime[] viajesMasLargosCola(int n, int horaInicial, int horaFinal) 
	{
		TravelTime[] muestra= new TravelTime[n];
		TravelTime[] auxiliar= new TravelTime[darTamano()];
		
		int i=0;
		int j=0;
		
		while(i<n)
		{
			TravelTime actual= (TravelTime) cola.delMax();
			
			if(actual.getHod()>=horaInicial && actual.getHod()<=horaFinal)
			{
				muestra[i]= actual;
				
				i++;
			}
			auxiliar[j]=actual;
			j++;
		}
		
		j--;
		
		while(j>=0)
		{
			cola.insert(auxiliar[j]);
			j--;
		}
		
		return muestra;
	}
	
	public void eliminarMuestraHeap(MaxHeapCP h,int n)
	{
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio = System.currentTimeMillis();
		while(n>0)
		{
			h.delMax();
			n--;
		}
		TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		  System.out.println("Tiempo eliminar algoritmo heap:"+ tiempo+ " milisegundos");
		
	}

	public void eliminarMuestraCola(MaxColaCP c, int n)
	{
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio = System.currentTimeMillis();
		while(n>0)
		{
			c.delMax();
			n--;
		}
		TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		  System.out.println("Tiempo eliminar algoritmo cola:"+ tiempo+ " milisegundos");
	}

	public MaxHeapCP agregarMuestraHeap(int n)
	{
		MaxHeapCP h= new MaxHeapCP();
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio = System.currentTimeMillis();
		while(n>0)
		{
			
			h.insert(n);
			n--;
		}
		TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		  System.out.println("Tiempo agregar algoritmo heap:"+ tiempo+ " milisegundos");
		  
		  return h;
		
	}

	public MaxColaCP agregarMuestraCola(int n)
	{
		MaxColaCP c= new MaxColaCP();
		long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecuciÛn
		  TInicio = System.currentTimeMillis();
		while(n>0)
		{
			
			c.insert(n);
			n--;
		}
		TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizÛ el algoritmo y la almacenamos en la variable T
		  tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
		  System.out.println("Tiempo agregar algoritmo cola:"+ tiempo+ " milisegundos");
		  return c;
	}


}
