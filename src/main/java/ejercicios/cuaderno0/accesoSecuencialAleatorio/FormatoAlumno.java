package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

/** El formato del registro, en un solo sitio: escribir y leer juntos. */
public final class FormatoAlumno {

	public static final int NOMBRE = 20; // caracteres
	public static final int TAMANO =
			1 // marca de borrado
			+ 4 // id
			+ NOMBRE * 2; // nombre, 2 bytes/car.

	private FormatoAlumno() { }

	public static long posicionDe(int numeroRegistro) {
		return (long) numeroRegistro * TAMANO;
	}

	public static void escribir(RandomAccessFile f, int id, String nombre) throws IOException {
		// 1. Validación de seguridad
		if (nombre.length() > FormatoAlumno.NOMBRE) {
			throw new IllegalArgumentException(
					"No cabe en " + FormatoAlumno.NOMBRE + " caracteres: " + nombre);
		}
		// 2. Marca de borrado (1 byte) -> false significa "está vivo"
		f.writeBoolean(false);
		// 3. El identificador (4 bytes)
		f.writeInt(id);
		// 4. Rellenar el nombre con espacios si es corto (Padding)
		StringBuilder b = new StringBuilder(nombre);
		while (b.length() < FormatoAlumno.NOMBRE) {
			b.append(' ');
		}
		// 5. Escribir el nombre carácter a carácter (40 bytes)
		f.writeChars(b.toString());
	}

	/** Devuelve "id nombre", o null si el registro está borrado. */
	public static String leer(RandomAccessFile f) throws IOException {
		// 1. Lee si está borrado o no
		boolean borrado = f.readBoolean();
		// 2. Lee el ID del alumno
		int id = f.readInt();
		// 3. Lee los 20 caracteres del nombre uno a uno
		StringBuilder b = new StringBuilder();

		for (int i = 0; i < FormatoAlumno.NOMBRE; i++) {
			b.append(f.readChar());
		}
		// 4. Si está borrado devuelve null; si no, devuelve el texto limpio de espacios
		return borrado ? null : id + " " + b.toString().strip();
	}
}