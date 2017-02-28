package PAQUETE_1;

import java.util.Arrays;
import java.util.Scanner;

public class ClasificacionArchivos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		int numeroDocumentos = Integer.parseInt(entrada);
		entrada = sc.nextLine();
		int[] documentos = new int [numeroDocumentos];
		int numeroArchivadores = Integer.parseInt(entrada);
		for(int j1 = 0; j1 < numeroDocumentos; j1 = j1 + 1){
			entrada = sc.nextLine();
			documentos[j1] = Integer.parseInt(entrada);
		}
		System.out.println(resolucionPerros(numeroArchivadores, documentos));
	}
	public static int resolucionPerros(int numeroEmpleados, int[] valoresPerros){
		if(valoresPerros.length < 2){
			return 0;
		}
		Arrays.sort(valoresPerros);
		int[] diferencias = new int[valoresPerros.length - 1];
		for(int j1 = 1; j1 < valoresPerros.length; j1 = j1 + 1){
			diferencias[j1 - 1] = valoresPerros[j1] - valoresPerros[j1 - 1];
		}
		Arrays.sort(diferencias);
		int diferenciaTotal = valoresPerros[valoresPerros.length - 1] - valoresPerros[0];
		for(int j1 = 0; j1 < numeroEmpleados - 1; j1 = j1 + 1){
			diferenciaTotal = diferenciaTotal - diferencias[diferencias.length - 1 - j1];
		}
		return diferenciaTotal;
	}
}