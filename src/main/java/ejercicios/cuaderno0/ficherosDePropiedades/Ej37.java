package ejercicios.cuaderno0.ficherosDePropiedades;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ej37 {
	public static void main(String[] args) throws IOException {
		// 1. Valores por omisión: dentro del empaquetado
		Properties porOmision = new Properties();
		String recurso = "/config-por-omision.properties";
		try (InputStream in = Ej37.class.getResourceAsStream(recurso);
				Reader r = new InputStreamReader(in, StandardCharsets.UTF_8)) {porOmision.load(r);
		}
		// 2. Fichero externo, encadenado a los de omisión
		Properties config = new Properties(porOmision);
		try (Reader r = Files.newBufferedReader(Path.of("config.properties"), StandardCharsets.UTF_8)) {
			config.load(r);
		}
		
		for (String clave : new String[] {
				"bd.pool.maximo", "bd.pool.minimo", "app.idioma" }) {
			System.out.printf("%-15s = %-4s (%s)%n", clave,
					valor(config, clave), origen(config, porOmision, clave));
		}
	}
	
	// 3. Una propiedad del sistema (-Dclave=valor) gana a todo
	static String valor(Properties config, String clave) {
		String delSistema = System.getProperty(clave);
		return delSistema != null ? delSistema : config.getProperty(clave);
	}
	static String origen(Properties config, Properties porOmision,
			String clave) {
		if (System.getProperty(clave) != null) return "línea de órdenes";
		if (config.containsKey(clave)) return "config.properties";
		if (porOmision.containsKey(clave)) return "por omisión";
		return "no definida";
	}
}
