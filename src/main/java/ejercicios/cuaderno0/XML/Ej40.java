package ejercicios.cuaderno0.XML;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ej40 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		fabrica.setNamespaceAware(true);
		Document documento = fabrica.newDocumentBuilder().parse(Path.of("xml", "centro.xml").toFile());
		
		// Añadir el correo que le falta al segundo alumno
		Element iker = (Element) documento.getElementsByTagName("alumno").item(1);
		Element correo = documento.createElement("correo");
		correo.setTextContent("iker.bengoetxea@example.org");
		
		Element matriculas = (Element) iker.getElementsByTagName("matriculas").item(0);
		iker.insertBefore(correo, matriculas);
		
		Path destino = Path.of("datos", "centro-modificado.xml");
		Files.createDirectories(destino.getParent());
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		
		try (OutputStream salida = new BufferedOutputStream(Files.newOutputStream(destino))) {
			t.transform(new DOMSource(documento), new StreamResult(salida));
		}
		
		// Mostrar solo el segundo alumno del fichero escrito
		String xml = Files.readString(destino, StandardCharsets.UTF_8);
		int desde = xml.indexOf("<alumno id=\"2\">");
		System.out.println(xml.substring(desde, xml.indexOf("</alumno>", desde) + "</alumno>".length()));
	}
}