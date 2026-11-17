package ejercicios.cuaderno0.flujosCodifYExcep;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej13 {

	public static void main(String[] args) throws IOException {
		Path ruta = Path.of("datos", "apellido.txt");
		Files.createDirectories(ruta.getParent());
		Files.writeString(ruta, "€uro"); //🚀

		try (InputStream entrada = Files.newInputStream(ruta)) { //probamos a leer
			int b; //para guardar el byte
			int n = 0; //contador posicion

			while ((b = entrada.read()) != -1) {
				//Si el número es menor a 128 (letra normal), usa (char) b para traducirlo a letra (ej: 'P').
				String como = (b < 128) ? "'" + (char) b + "'" : "(no es ASCII)";
				
				System.out.printf("byte %d: %3d 0x%02X %s%n", n++, b, b, como);
				// %d  -> Imprime el número de posición (n++) en formato entero decimal.
				// %3d -> Imprime el valor del byte (b) ocupando un ancho fijo de 3 espacios (para alinear).
				// %02X -> Imprime el valor del byte (b) en hexadecimal, asegurando al menos 2 dígitos (con 0 delante si es menor).
				// %s  -> Imprime el texto de la variable 'como' (ej: 'P' o "(no es ASCII)").
				// %n  -> Salto de línea.			}
			}

			System.out.println("Letras: " + "€uro".length() + " Bytes: " + Files.size(ruta));

		}

	}

}
