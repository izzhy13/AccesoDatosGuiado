package ejercicios.cuaderno0.ficherosYDirectorios;

import java.nio.file.Path;

public class Ej05 {

	public static void main(String[] args) {
		Path relativa = Path.of("datos", "alumnos.txt");
		
		System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
		//la absoluta cambia segun donde la ejecutes
		System.out.println("Relativa: " + relativa);
		System.out.println("Absoluta: " + relativa.toAbsolutePath());
		System.out.println("¿Es absoluta? " + relativa.isAbsolute());
		System.out.println("¿Es absoluta? " + relativa.toAbsolutePath().isAbsolute());
		
	}

}
