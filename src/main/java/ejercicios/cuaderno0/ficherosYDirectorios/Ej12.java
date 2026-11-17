package ejercicios.cuaderno0.ficherosYDirectorios;

import java.nio.file.Path;

public class Ej12 {
	//El orden es siempre: resolver, normalizar, comprobar, y solo entonces usar.

	public static void main(String[] args) {
		Path base = Path.of("datos", "expedientes").toAbsolutePath().normalize();
		System.out.println("Base: " + base);

		String[] peticiones = {
				"sara.txt",
				"2026/iker.txt",
				"../../../../../../etc/passwd",
				"../expedientes-viejos/sara.txt"
		};

		for (String nombre : peticiones) {
			Path real = base.resolve(nombre).normalize();
			boolean comoRuta = real.startsWith(base);
			boolean comoTexto = real.toString().startsWith(base.toString());
			System.out.println("--------------------------------------------- ");
			System.out.println(nombre);
			System.out.println(" normalizada: " + real);
			System.out.println(" Path.startsWith: " + comoRuta);
			System.out.println(" String.startsWith: " + comoTexto);

			if (real.startsWith(base)) {
				// ¡Todo bien! El usuario se ha quedado dentro de su carpeta. Dejamos leer el fichero.
				System.out.println("Acceso concedido.");
			} else {
				// ¡Alerta! El usuario ha intentado colarse fuera (con ".." o carpetas raras).
				System.out.println("¡Acceso denegado! Estás intentando salir de la carpeta.");
			}
		}
	}

}
