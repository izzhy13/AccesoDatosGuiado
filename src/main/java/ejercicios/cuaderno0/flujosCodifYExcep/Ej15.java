package ejercicios.cuaderno0.flujosCodifYExcep;

import java.nio.charset.Charset;

public class Ej15 {

	public static void main(String[] args) {

		System.out.println("Charset.defaultCharset(): "	+ Charset.defaultCharset());
		System.out.println("file.encoding: " + System.getProperty("file.encoding")); //codificación por omisión de Java
		System.out.println("native.encoding: " + System.getProperty("native.encoding")); //codificaciçon del sistema operativo
		System.out.println("stdout.encoding: " 	+ System.getProperty("stdout.encoding")); //codificaciçon de la consola 
		System.out.println("Prueba de consola: Peñafiel, camión, Ávila");
	}
}