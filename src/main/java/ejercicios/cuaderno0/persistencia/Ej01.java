package ejercicios.cuaderno0.persistencia;

public class Ej01 {

	private static int visitas = 0;

	public static void main(String[] args) {
		visitas++;
		System.out.println("Visitas: " + visitas);
		
		for (int i = 0; i < 5; i++) {
			visitas ++;
		}
		
		System.out.println("Visitas: " + visitas);

	}
}
