package ejercicios.cuaderno0.ficherosDePropiedades;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.io.Reader;

public class Ej36 {
	public static void main(String[] args) throws IOException {
		
		Path ruta = Path.of("datos", "centro.properties");
		
		Files.createDirectories(ruta.getParent());
		Files.writeString(ruta, "centro.nombre=IES Peñafiel\n",
				StandardCharsets.UTF_8);
		
		Properties conFlujo = new Properties();
		
		try (InputStream in = Files.newInputStream(ruta)) { //lee en ISO-8859-1
			conFlujo.load(in);
		}
		
		System.out.println("load(InputStream): "
				+ conFlujo.getProperty("centro.nombre"));
		Properties conLector = new Properties();
		
		try (Reader r =
				Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
			conLector.load(r);
		}
		
		System.out.println("load(Reader UTF-8): "
				+ conLector.getProperty("centro.nombre"));
	}
}