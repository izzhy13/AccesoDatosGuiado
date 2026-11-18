package ejercicios.cuaderno0.XML;

import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ej38 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setNamespaceAware(true);
		//fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		DocumentBuilder constructor = fabrica.newDocumentBuilder();
		Document documento = constructor.parse(Path.of("xml", "centro.xml").toFile());
		
        // Recuperamos la etiqueta principal del XML (en este caso, <centro>).
		Element raiz = documento.getDocumentElement();
		System.out.println("Raíz: " + raiz.getTagName() + ", curso " + raiz.getAttribute("curso"));
		
        // getElementsByTagName busca en toda la descendencia del documento.
		NodeList alumnos = documento.getElementsByTagName("alumno");
		System.out.println("Alumnos: " + alumnos.getLength());
		
		for (int i = 0; i < alumnos.getLength(); i++) {
			// Casting ((Element)): Convertimos el nodo genérico (Node) a Element 
            // para poder usar métodos específicos como getAttribute() o buscar etiquetas dentro.
			Element alumno = (Element) alumnos.item(i);
			System.out.println(" " + alumno.getAttribute("id") + " "
					+ texto(alumno, "nombre")
					//XmlUtils.texto(alumno, "nombre")
					+ " / correo: " + texto(alumno, "correo"));
		}
	}
	
	static String texto(Element padre, String etiqueta) {
		NodeList lista = padre.getElementsByTagName(etiqueta);
		if (lista.getLength() == 0) {
			return null;
		}
		return lista.item(0).getTextContent().trim();
	}
}