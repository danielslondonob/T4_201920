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
    
    

}
