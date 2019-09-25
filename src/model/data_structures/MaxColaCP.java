package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;




/**
 * Clase que representa una cola de elementos T.
 * @author Daniel del Castillo A.
 * @param <T> Tipo del cual ser� la cola.
 */
public class MaxColaCP <T extends Comparable<T>> implements IMaxPriorityQueue<T>
{
	
    private T[] pq;      // elements
    private int n;         // number of elements
    private int max;
    private Node<T> actual; 

    // set inititial size of heap to hold size elements
    public MaxColaCP() {
    	max=2;
        pq = (T[]) new Comparable[max+1];
        n = 0;
    }

    public boolean isEmpty()   
    { 
    	return n == 0; 
    }
    
    public int darNumElementos()          
    { 
    	return n;      
    }
    public void insert(T x)  
    { 
    	if(n == max)
		{
			max *= 2;
			T[] copia = pq;
			pq = (T[]) new Comparable[max+1];
			for(int i = 0; i <= n; i++)
				pq[i] = copia[i];
		}
    	
    	pq[n++] = x;   
    }

    public T delMax() 
    {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;
        exch(max, n-1);

        return pq[--n];
    }
    
    public T darMax() 
    {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;

        return pq[--n];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
    /**
     * Returns an iterator that iterates over the UBERTrips in this queue in FIFO order.
     *
     * @return an iterator that iterates over the UBERTrips in this queue in FIFO order
     */
    public Iterator<T> iterator()  
    {
		return (Iterator<T>) new ListIterator(actual);
    }
    
    private class ListIterator <T extends Comparable<T>> implements Iterator<T>
	{
		//---------------------------------
		// Atributos
		//---------------------------------

		Node<T> actual;

		Node<T> proximo;

		Node<T> anterior;

		//-----------------------------------
		// MÃ©todos
		//-----------------------------------

		ListIterator(Node<T> primero)
		{
			actual= null;
			proximo=primero;

		}


		@Override
		/**
		 * Retorna si el elemento acuatal, el Ãºltimo visitado tiene siguiente o no. 
		 */
		public boolean hasNext() 
		{
			boolean retorno = false;

			if (proximo != null)
			{
				retorno= true;
			}

			return retorno;
		}

		@Override
		/**
		 * Retorno el elemento almacenado en el nodo siguiente 
		 * Re asigna lso atributos de tal manera que ahora el actual sea el que antes fue prÃ³ximo.
		 */
		public T next() 
		{

			if (proximo == null)
			{
				throw new NoSuchElementException("No hay prÃ³ximo");
			}

			T elemento = proximo.darElemento();
			anterior = actual;
			actual= proximo;
			proximo = proximo.darSiguiente();

			return elemento;
		}
	}
    

}
