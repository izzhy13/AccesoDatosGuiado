package ejercicios.cuaderno0.accesoSecuencialAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Ej30 {
	public static void main(String[] args) throws IOException {
		
		try (RandomAccessFile raf =	new RandomAccessFile("datos/alumnos.dat", "r")) {
			
			// 1. Construir el índice: una pasada por todo el fichero
			Map<Integer, Integer> indice = new HashMap<>();
			int n = 0;
			
			while (raf.getFilePointer() < raf.length()) {
				long inicio = raf.getFilePointer(); // Guarda dónde empieza el registro
				boolean borrado = raf.readBoolean(); // Lee 1 byte (si está borrado)
				int id = raf.readInt(); // Lee 4 bytes (el identificador)
				
				if (!borrado) {
					indice.put(id, n);
				}
				
				n++;
				raf.seek(inicio + FormatoAlumno.TAMANO); // Salta el resto del registro (los 40 bytes del nombre) de golpe
			}
			
			System.out.println("Índice id -> registro: " + indice);
			// 2. Usarlo: ir directo, sin recorrer
			for (int id : new int[] { 4, 1, 3 }) {
				
//			int[] idsABuscar = { 4, 1, 3 };	
//			for (int i = 0; i < idsABuscar.length; i++) {
//				int id = idsABuscar[i]; // Sacamos el ID actual de la lista	
				
				Integer registro = indice.get(id);
				
				if (registro == null) {
					System.out.println("id " + id + ": no está");
					continue;
				}
				
				raf.seek(FormatoAlumno.posicionDe(registro));
				System.out.println("id " + id + " -> registro " + registro + ": " + FormatoAlumno.leer(raf));
			}
		}
	}
}
