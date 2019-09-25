package model.data_structures;

public class Node<T extends Comparable<T>> implements Comparable<T>
{
	// Atributos
	
	/**
	 * Elemento genérico del nodo.
	 */
	private T elemento;
	
	/**
	 * Siguiente elemento genérico del nodo. 
	 */
	private Node<T> siguiente;
	
	// Constructor
	
	/**
	 * Construye el nodo para el elemento genérico. siguiente = null. 
	 * @param pElemento elemento genérico a almacenar dentro del nodo. 
	 */
	public Node(T pElemento)
	{
		elemento = pElemento;
		siguiente = null;
		check();
	}
	
	// Métodos
	
	/**
	 * Retorna el elemento genérico asignado al nodo. 
	 * @return el elemento genérico.
	 */
	public T darElemento()
	{
		return elemento;
	}
	
	/**
	 * Retorna el siguiente elemento genérico asignado.
	 * @return el siguiente elemento genérico.
	 */
	public Node<T> darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * Cambia el siguiente nodo del elemento genérico por el dado por parámetro.
	 * @param pSiguiente nodo del elemento genérico a asignar como siguiente. 
	 */
	public void cambiarSiguiente(Node<T> pSiguiente)
	{
		siguiente = pSiguiente;
	}
	
	// Invariante
	
	/**
	 * Contrato que se asegura de que no se agregue un elemento null.
	 */
	private void check()
	{
		assert elemento != null : "El elemento no puede ser null.";
	}

	@Override
	public int compareTo(T pNode) 
	{
		return elemento.compareTo(pNode);
	}
}
