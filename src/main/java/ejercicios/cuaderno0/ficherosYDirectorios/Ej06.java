package ejercicios.cuaderno0.ficherosYDirectorios;

import java.nio.file.Path;

public class Ej06 {

	public static void main(String[] args) {
		Path base = Path.of("datos");//.toAbsolutePath();
		Path fichero = base.resolve("2026").resolve("notas.csv");
		
		System.out.println("Ruta: " + fichero);
		// Ruta: C:\Users\oolaq\eclipse-workspace\AccesoDatosGuiado\datos\2026\notas.csv
		
		System.out.println("Nombre: " + fichero.getFileName());
		
		System.out.println("Padre: " + fichero.getParent());
		//Padre: C:\Users\oolaq\eclipse-workspace\AccesoDatosGuiado\datos\2026
		
		System.out.println("Raíz: " + fichero.getRoot()); //es null porque la ruta es relativa
		//Raíz: C:\

		System.out.println("Segmentos: " + fichero.getNameCount()); //da 3 datos, 2026 y notas
		//Segmentos: 7

		Path rara = Path.of("datos/./2026/../2026/notas.csv");
		
		System.out.println("Sin normalizar: " + rara);
		System.out.println("Normalizada: " + rara.normalize());
		System.out.println("¿Iguales sin normalizar? " + rara.equals(fichero)); //da false proque compara los segmentos
		System.out.println("¿Iguales normalizada? " + rara.normalize().equals(fichero)); //con absolute path daria tamb false





	}

}
