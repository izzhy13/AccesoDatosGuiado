package ejercicios.cuaderno0.XML;

import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ej39 {
	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setNamespaceAware(true);
		Document documento = fabrica.newDocumentBuilder().parse(Path.of("xml", "centro.xml").toFile());
		
		//Busca en todo el XML todas las etiquetas que se llamen alumno y cogemos el primero [0]
		Element alumno = (Element) documento.getElementsByTagName("alumno").item(0);
		NodeList hijos = alumno.getChildNodes(); //coge todos los hijos directos que están dentro de ese alumno.
		System.out.println("Hijos de <alumno id=\"1\">: " + hijos.getLength());
		
		for (int i = 0; i < hijos.getLength(); i++) {
			Node hijo = hijos.item(i);
			// ¿Es una etiqueta real (un Elemento) o es un espacio/salto de línea (texto)?
			String tipo = (hijo.getNodeType() == Node.ELEMENT_NODE)
					? "elemento " + hijo.getNodeName() : "texto " + visible(hijo.getNodeValue());
			
			System.out.println(" " + i + ": " + tipo);
		}
		
		System.out.println("El bucle ingenuo:");
		
		try {
			
			for (int i = 0; i < hijos.getLength(); i++) {
				Element e = (Element) hijos.item(i);
				System.out.println(" " + e.getTagName());
			}
		} catch (ClassCastException e) {
			System.out.println(" ClassCastException en la primera vuelta");
		}
	}
	
	/** Hace visibles los saltos de línea y los espacios. */
	static String visible(String s) {
		return "[" + s.replace("\n", "\\n").replace(" ", "·") + "]";
	}
}