package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej29 {
	public static void main(String[] args) throws IOException {
		
		Path f = Path.of("datos", "alumnos.dat"); // el del ejercicio 26
		
		try (RandomAccessFile raf = new RandomAccessFile(f.toFile(), "rw")) {
			// Borrar el registro 2: solo se cambia el primer byte
			raf.seek(FormatoAlumno.posicionDe(2));
			raf.writeBoolean(true);
			raf.seek(0);
			int n = 0;
			
			while (raf.getFilePointer() < raf.length()) {
				String alumno = FormatoAlumno.leer(raf);
				System.out.println("Registro " + n++ + ": " + (alumno == null ? "(borrado)" : alumno));
			}
		}
		
		System.out.println("Tamaño: " + Files.size(f) + " bytes");
	}
}