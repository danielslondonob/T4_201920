package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPriorityQueue<T extends Comparable<T>> implements IMaxPriorityQueue<T>
{

	//------------------------------------------------
	// Atributos
	//------------------------------------------------
	/*
	 * Atributo que modela el primer elemento de la cola
	 */
	private Node<T> first ;

	/*
	 * Atributo que modela el ultimo elemento de la cola.
	 */
	private Node<T> last;

	/*
	 * Atributo que modela el numero de elementos en la cola.
	 */
	private Integer size;


	//------------------------------------------------
	// Constructor
	//------------------------------------------------

	public MaxPriorityQueue()
	{
		first  =null;
		last= null;
		size=0;
	}

	//------------------------------------------------
	// M�todos
	//------------------------------------------------

	/**
	 * Retorna el tama�o de la cola de prioridad.
	 * @return El tama�o de la cola de prioridad. N >= 0.
	 */
	public int darNumElementos() 
	{
		return size;
	}
	/**
	 * Agrega un elemento a la cola. Si el elemento ya existe y tiene una prioridad
	 * diferente, el elemento debe actualizarse en la cola de prioridad. 
	 * La cola se rodena por  prioridad con la m�xima pripioridad al inicio. 
	 * @param v Elemento a introducir. 
	 */
	public void agregar(T v)
	{

		if (first ==null)
		{
			first  = new Node<T>(v);
			last=first ;
			size++;
		}
		//Si la lista tiene first  y el elemento a agregar es mayor  que el first :
		else if(first .compareTo(v)<0)
		{
			Node<T> nodoTem= new Node<T>(v);
			nodoTem.cambiarSiguiente(first );
			first  = nodoTem;
			size++;		

		}
		else if(first .compareTo(v)==0)
		{
			Node<T> nodoTem= new Node<T>(v);
			nodoTem.cambiarSiguiente(first .darSiguiente());
			first =nodoTem;
		}
		else if(last.compareTo(v)>0)
		{
			//Si es el �ltimo
			Node<T> nodoTem= new Node<T>(v);
			last.cambiarSiguiente(nodoTem);
			last=nodoTem;
			size++;
		}
		else
		{
			Node<T> it = first ;
			Node<T> itNext = it.darSiguiente();

			while(it.darSiguiente() !=null)
			{
				
				if(itNext.compareTo(v)<0)
				{
					Node<T> nodoTem= new Node<T>(v);
					nodoTem.cambiarSiguiente(itNext);
					it.cambiarSiguiente(nodoTem);
					size++;
					break;
				}
				//En esta comparaci�n se incluye en last
				else if ( itNext.compareTo(v)==0)
				{
					Node<T> nodoTem= new Node<T>(v);
					nodoTem.cambiarSiguiente(itNext.darSiguiente());
					it.cambiarSiguiente(nodoTem);
					break;
				}
					
				else
				{
					it=it.darSiguiente();
					itNext= it.darSiguiente();
				}
				
			}

		}
	}

	/**
	 * Obtiene el elemento m�ximo sin sacarlo de la cola .
	 * @return El elemento m�ximo de la cola, null si la cola est� vac�a. 
	 */
	public T max()
	{
		return last.darElemento();
	}

	/**
	 * Saca el elemento m�ximo de la cola y lo retorna.
	 * @return Elemento m�ximo de la cola, null si la cola est� vac�a.
	 */
	public T delMax()
	{
		T elem = null;
		if(first !=null) {
			elem = first .darElemento();
			first =first .darSiguiente();
			size--;
		}

		return elem;
	}

	/**
	 * Retorna si la cola est� vac�a.
	 * @return True si la cola est� vac�a, False de lo contrario. 
	 */
	public boolean isEmpty()
	{
		return size==0?true:false;
	}

	/**
	 * Retorna el iterador
	 */
	public Iterator<T> iterator() 
	{
		return new IteratorLista<T>(first );
	}

	class IteratorLista <T extends Comparable<T>> implements Iterator<T>
	{
		//---------------------------------
		// Atributos
		//---------------------------------

		Node<T> actual;

		Node<T> proximo;

		Node<T> anterior;

		//-----------------------------------
		// Métodos
		//-----------------------------------

		IteratorLista(Node<T> first )
		{
			actual= null;
			proximo=first ;

		}


		@Override
		/**
		 * Retorna si el elemento acuatal, el último visitado tiene siguiente o no. 
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
		 * Re asigna lso atributos de tal manera que ahora el actual sea el que antes fue próximo.
		 */
		public T next() 
		{

			if (proximo == null)
			{
				throw new NoSuchElementException("No hay próximo");
			}

			T elemento = proximo.darElemento();
			anterior = actual;
			actual= proximo;
			proximo = proximo.darSiguiente();

			return elemento;
		}
	}

}
