package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ej27 {
	public static void main(String[] args) throws IOException {

		// El fichero del ejercicio 26
		try (RandomAccessFile raf =	new RandomAccessFile("datos/alumnos.dat", "r")) {

//			for (int i = 0; i < listaRegistros.length; i++) {
//				int n = listaRegistros[i];
//				lo demas}
			
			for (int n : new int[] { 2, 0, 3, 7 }) {
				long pos = FormatoAlumno.posicionDe(n); // Multiplica n * 45

				if (pos >= raf.length()) {
					System.out.println("Registro " + n + ": no existe (posición " + pos + ")");
					continue;
				}

				raf.seek(pos); // El cursor salta directamente al byte exacto
				System.out.println("Registro " + n + " (posición " + pos + "): " 
						+ FormatoAlumno.leer(raf)); // Lee los 45 bytes de ese registr
			}
		}
	}
}