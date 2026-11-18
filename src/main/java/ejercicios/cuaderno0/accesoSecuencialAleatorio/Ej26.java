package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ej26 {
	public static void main(String[] args) throws IOException {
		Path f = Path.of("datos", "alumnos.dat");
		Files.createDirectories(f.getParent());
		Files.deleteIfExists(f);
		
		try (RandomAccessFile raf = new RandomAccessFile(f.toFile(), "rw")) {
			FormatoAlumno.escribir(raf, 1, "Sara Villalba");
			FormatoAlumno.escribir(raf, 2, "Iker Bengoetxea");
			FormatoAlumno.escribir(raf, 3, "Lucía Peñalver");
			FormatoAlumno.escribir(raf, 4, "Marcos Prieto");
		}
		
		System.out.println("Tamaño de un registro: "
				+ FormatoAlumno.TAMANO + " bytes");
		System.out.println("Tamaño del fichero: "
				+ Files.size(f) + " bytes");
		System.out.println("Registros: "
				+ Files.size(f) / FormatoAlumno.TAMANO);
	}
}