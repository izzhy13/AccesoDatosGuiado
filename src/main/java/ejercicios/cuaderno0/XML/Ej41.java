package ejercicios.cuaderno0.XML;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

public class Ej41 {
	
	static final String XML = """
			<?xml version="1.0" encoding="UTF-8"?>
			<!DOCTYPE centro [
			<!ENTITY nombreCentro "IES Peñafiel">
			]>
			<centro curso="2026-2027">
			<nombre>&nombreCentro;</nombre>
			</centro>
			""";
	
	public static void main(String[] args) throws Exception {
		// 1. Fábrica sin configurar
		DocumentBuilderFactory confiada = DocumentBuilderFactory.newInstance();
		Document d = confiada.newDocumentBuilder().parse(entrada());
		System.out.println("Sin configurar: <nombre> contiene \""
				+ d.getElementsByTagName("nombre").item(0).getTextContent()
				+ "\"");
		
		// 2. Fábrica que rechaza las definiciones de tipo
		DocumentBuilderFactory segura = DocumentBuilderFactory.newInstance();
		segura.setNamespaceAware(true);
		segura.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
		
		try {
			segura.newDocumentBuilder().parse(entrada());
		} catch (SAXParseException e) {
			System.out.println("Configurada: SAXParseException en la línea "
					+ e.getLineNumber());
			System.out.println(" " + e.getMessage());
		}
	}
	
	static ByteArrayInputStream entrada() {
		return new ByteArrayInputStream(XML.getBytes(StandardCharsets.UTF_8));
	}
}
