package interfacce;

/**
 * Interfaccia per permettere di creare un metodo clone generic ed usarlo in più
 * situazioni rispetto al clone di <code>Object</code>, in quanto esso è
 * protected
 */
public interface Clone<T> extends Cloneable {
	/**
	 * Metodo generic di clone, maggiore visibilità rispetto al clone di
	 * <code>Object</code>
	 * 
	 * @return l'oggetto chiamante clonato
	 */
	public T clone();

}
