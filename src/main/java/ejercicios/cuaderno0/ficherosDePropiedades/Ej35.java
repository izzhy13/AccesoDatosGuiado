package ejercicios.cuaderno0.ficherosDePropiedades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.io.Reader;

public class Ej35 {
	public static void main(String[] args) throws IOException {
		
		Path ruta = Path.of("config.properties"); // 1. Buscamos el fichero en la raíz
		Properties config = new Properties(); // 2. Creamos el "contenedor" vacío de propiedades
		
		// 3. Abrimos el lector del fichero con codificación UTF-8 y lo cargamos dentro del objeto 'config'
		try (Reader r =
				Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
			config.load(r);
		}
		
		for (String clave : config.stringPropertyNames().stream().sorted().toList()) {
			System.out.println(clave + " = " + config.getProperty(clave));
		}
		
		System.out.println("------------------------------------");
		System.out.println("bd.pool.minimo -> " + config.getProperty("bd.pool.minimo"));
		System.out.println("bd.pool.minimo con '2' -> " + config.getProperty("bd.pool.minimo", "2"));
		
		int maximo = Integer.parseInt(config.getProperty("bd.pool.maximo"));
		System.out.println("El doble del máximo: " + maximo * 2);
	}
}