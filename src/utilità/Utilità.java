package utilità;

import java.io.Serializable;
import java.util.ArrayList;

import interfacce.Clone;

/**
 * Classe di metodi static utili per diversi fini come l'incapsulamento etc...
 */
public class Utilità implements Serializable {

	private static final long serialVersionUID = -3671805104613844796L;

	/**
	 * Clona l'ArrayList passato e lo ritorna. Il tipo dell'ArrayList deve aver
	 * implementato l'interfaccia <code>Clone</code>. Se l'array passato è
	 * <code>null</code>, restituirà <code>null</code>;
	 * 
	 * @param <T>   una classe che ha implementato l'interfaccia <code>Clone</code>
	 * @param array da clonare
	 * @return array clonato
	 */
	public static <T extends Clone<T>> ArrayList<T> cloneArray(ArrayList<T> array) {
		if (array == null)
			return null;
		ArrayList<T> cloned = new ArrayList<>();
		for (T clone : array)
			cloned.add(clone.clone());
		return cloned;
	}

	/**
	 * Casta un oggetto di tipo <code>T</code> al tipo <code>F</code> se e solo se
	 * l'oggetto in precedenza era stato castato da <code>F</code> a <code>T</code>
	 *
	 * @param <F>
	 * @param <T>
	 * @param object un oggetto che in precedenza era di tipo <code>F</code> ed è
	 *               stato castato a <code>T</code>
	 * @param clazz  la classe di object prima del cast a <code>T</code>
	 * @return object castato a <code>F</code> oppure <code>null</code> se object in
	 *         precedenza non è stato castato da <code>F</code> a <code>T</code>
	 */
	public static <F, T> F rewindCast(T object, Class<F> clazz) {
		if (object.getClass() == clazz)
			return (F) object;
		return null;
	}

}
