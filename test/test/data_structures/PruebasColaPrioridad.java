package test.data_structures;

import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;



	public class PruebasColaPrioridad extends TestCase
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	//Clase que se va a probar
		///TRAVEL TIME
	private MaxColaCP<Integer> lista;

	private Integer [] elem= {120,30,15};

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 *  Escenario 1: Construye una nueva cola.
	 */
	public void setupEscenario1() 
	{
		lista = new MaxColaCP();
	}

	
	 // Escenario 2: Construye una nueva cola y agrega un elemento.
	 
	public void setupEscenario2() 
	{
		lista = new MaxColaCP();
		lista.insert(elem[0]);
	}

	 //Escenario 3: Construye una nueva cola y agregan dos elementos ordenadamente.
	 
	public void setupEscenario3() 
	{
		lista = new MaxColaCP();
		for (int i=0; i<2;i++)
		{
			lista.insert(elem[i]);
		}    
	}

	// Escenario 4: Construye una nueva cola y agregan dos elementos desordenadamente.
	public void setupEscenario4() 
	{
		lista = new MaxColaCP();
		lista.insert(elem[1]);
		lista.insert(elem[0]); 
	}

	//Escenario 5: Construye una nueva cola y agregan tres elementos ordenadamente.
	public void setupEscenario5() 
	{
		lista = new MaxColaCP();
		for (int i=elem.length-1; i>=0;i--)
		{
			lista.insert(elem[i]);
		} 
	}

	// Escenario 6: Construye una nueva cola y agregan tres elementos ordenadamente y desoidenadmente.
	public void setupEscenario6() 
	{
		lista = new MaxColaCP();
		
		//Elemento al final de la cola
		lista.insert(elem[1]);
		
		lista.insert(elem[2]);
		
		//Elemento al inicio de la lista
		lista.insert(elem[0]);
	}

	/**
	 * Prueba 1: verfica que se pueda crear una una cola y que tenga el tamaño adecuado <br>
	 * <b>Métodos a probar:</b> <br>
	 * MaxColaCP <br>
	 * darNumElementos<br>
	 * isEmpty<br>
	 * <b> Casos de prueba: </b><br>
	 * 1. La lista fue creada y el tamño es cero.
	 */
	public void testMaxColaCP()
	{
		setupEscenario1();
		assertNotNull("No crea la la cola",lista);
		assertTrue("El tamañaño de la lista ha ser cero", lista.darNumElementos()==0);
		assertTrue("La lista ha estar vacia", lista.isEmpty());
	}

	/**
	 * Prueba 2: verfica que se puedan agragr elementos a la lista y que queden ordenados <br>
	 * <b>Métodos a probar:</b> <br>
	 * insert<br>
	 * darNumElementos<br>
	 * isEmpty
	 * <b> Casos de prueba: </b><br>
	 * 1. Se agrega un elemento a la lista.
	 * 2. Se insertn dos elementos a la lista ordenado
	 * 3. Se agregan dos elementos a lista desordeandos.
	 * 4. Se agregan 3 elementos a lista ordenadamente
	 * 5. Se agregan 3 elementos a lista desordenadamente.
	 */
	public void testinsert()
	{
		//Caso de prueba 1:
		setupEscenario2();
		assertTrue("El tamañaño de la lista ha ser uno", lista.darNumElementos()==1);
		assertFalse("La lista no ha estar vacia", lista.isEmpty());

		//Caso de prueba 2:
		setupEscenario3();
		assertTrue("El tamañaño de la lista ha ser uno", lista.darNumElementos()==2);
		Iterator<Integer> it2 = lista.iterator();
		for (int i=0 ;i<2 && it2.hasNext();i++)
		{

			assertTrue("El elemento está en la posición equivocada",elem[i] == it2.next());
		} 

		//Caso de prueba 3:
		setupEscenario4();
		assertTrue("El tamañaño de la lista ha ser uno", lista.darNumElementos()==2);
		Iterator<Integer> it3 = lista.iterator();
		for (int i=0; i<2&& it3.hasNext();i++)
		{
			assertTrue("El elemento está en la posición equivocada",elem[i]== it3.next());
		} 


		//Caso de prueba 4:
		setupEscenario5();
		assertTrue("El tamañaño de la lista ha ser uno", lista.darNumElementos()==3);
		Iterator<Integer> it4 = lista.iterator();
		for (int i=0; i<elem.length&& it4.hasNext();i++)
		{

			assertTrue("El elemento está en la posición equivocada",elem[i]== it4.next());
		}

		//Caso de prueba 5:
		setupEscenario6();
		assertTrue("El tamañaño de la lista ha ser uno", lista.darNumElementos()==3);
		Iterator<Integer> it5 = lista.iterator();
		for (int i=0; i<elem.length&& it5.hasNext();i++)
		{
			assertTrue("El elemento está en la posición equivocada",elem[i]== it5.next());
		}
	}

	/**
	 * Prueba 2: verfica que se puedan agragr elementos a la lista y que queden ordenados <br>
	 * <b>Métodos a probar:</b> <br>
	 * delMax<br>
	 * darNumElementos<br>
	 * isEmpty
	 * <b> Casos de prueba: </b><br>
	 * 1. La lista está vacía
	 * 2. Solo hay un elemento en ela lsita
	 * 3. Hay más de un elemento en el la lista. 
	 */
	public void testDelMax()
	{
		//Caso de prueba 1:
		setupEscenario1();
		lista.delMax();
		assertTrue("La lista debería seguir vacía", lista.darNumElementos()==0);

		//Caso de prueba 2:
		setupEscenario2();
		lista.delMax();
		assertTrue("La lista debería estar vacia vacía", lista.darNumElementos()==0);

		//Caso de prueba 3:
		setupEscenario5();
		Integer tem = lista.darMax();
		lista.delMax();
		assertTrue("La lista debería estar vacia vacía", lista.darNumElementos()==2);
		assertFalse("Ese elemento de no debería estar en la lista", tem.compareTo(lista.delMax()) == 0);
		
	

	}
}
