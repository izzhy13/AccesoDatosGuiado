package ejercicios.cuaderno0.XML;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils {

	/**
	 * Devuelve el texto del primer elemento con esa etiqueta, 
	 * o null si no aparece (evitando NullPointerExceptions).
	 */
	public static String texto(Element padre, String etiqueta) {
		NodeList lista = padre.getElementsByTagName(etiqueta);
		if (lista.getLength() == 0) {
			return null;
		}
		return lista.item(0).getTextContent().trim();
	}

	/**
	 * Opcional: Un método seguro para leer atributos que pueden no existir.
	 */
	public static String atributo(Element elemento, String nombreAtributo) {
		if (elemento.hasAttribute(nombreAtributo)) {
			return elemento.getAttribute(nombreAtributo);
		}
		return null;
	}
}