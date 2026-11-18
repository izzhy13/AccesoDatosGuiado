package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej28 {
	public static void main(String[] args) throws IOException {
		
		Path f = Path.of("datos", "alumnos.dat"); // el del ejercicio 26
		System.out.println("Antes: " + Files.size(f) + " bytes");
		
		try (RandomAccessFile raf = new RandomAccessFile(f.toFile(), "rw")) {
			raf.seek(FormatoAlumno.posicionDe(1));
			FormatoAlumno.escribir(raf, 2, "Iker Bengoetxea Ruiz");
			raf.seek(FormatoAlumno.posicionDe(1)); //seek de nuevo porque el cursor esta al final del registro
			System.out.println("Registro 1: " + FormatoAlumno.leer(raf));
		}
		
		System.out.println("Después: " + Files.size(f) + " bytes");
	}
}