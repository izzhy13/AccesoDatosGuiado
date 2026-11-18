package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej25 {

	public static void main(String[] args) throws IOException {
		Path f = Path.of("datos", "tipos.bin"); // el del ejercicio 24
		
		try (DataInputStream in = new DataInputStream(
				new BufferedInputStream(Files.newInputStream(f)))) {
			
			// Mismo fichero, orden equivocado: primero la nota
			double nota = in.readDouble();
			int id = in.readInt();
			long fecha = in.readLong();
			
			System.out.println("nota = " + nota);
			System.out.println("id = " + id);
			System.out.println("fecha = " + fecha);
			System.out.println("Quedan " + in.available() + " bytes");
		}
	}
}