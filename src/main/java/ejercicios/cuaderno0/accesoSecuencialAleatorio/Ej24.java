package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Ej24 {
	public static void main(String[] args) throws IOException {
		Path f = Path.of("datos", "tipos.bin");
		Files.createDirectories(f.getParent());
		
		try (DataOutputStream out = new DataOutputStream( new BufferedOutputStream(Files.newOutputStream(f)))) {
			out.writeInt(1); // id - entero (ocupa siempre 4 bytes)
			out.writeLong(LocalDate.of(2004, 3, 11).toEpochDay()); // fecha siempre 8 bytes
			out.writeDouble(7.5); // nota - decimales siempre 8 bytes
			out.writeUTF("Peña"); // apellido - texto (guarda 2 bytes para indicar el tamaño + los bytes de la palabra)
		} //escribir
		
		System.out.println("Tamaño: " + Files.size(f) + " bytes");
		
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(f)))) {
			int id = in.readInt();
			LocalDate fecha = LocalDate.ofEpochDay(in.readLong());
			double nota = in.readDouble();
			String apellido = in.readUTF();
			System.out.println(id + " " + fecha + " " + nota + " "
					+ apellido);
		} //leer
	}
}

