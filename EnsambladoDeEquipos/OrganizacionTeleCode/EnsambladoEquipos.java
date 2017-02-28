package PAQUETE_1;

import java.util.ArrayList;
import java.util.Scanner;

public class EnsambladoEquipos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada;
		int[] datos = new int[4];
		entrada = sc.nextLine();
		int numeroTestCases = Integer.parseInt(entrada);
		for(int j1 = 0; j1 < numeroTestCases; j1 = j1 + 1){
		entrada = sc.nextLine();
		datos[0] = Integer.parseInt(entrada);
		entrada = sc.nextLine();
		datos[1] = Integer.parseInt(entrada);
		entrada = sc.nextLine();
		datos[2] = Integer.parseInt(entrada);
		entrada = sc.nextLine();
		datos[3] = Integer.parseInt(entrada);
		System.out.println(numberTacos(datos));
		}
	}
	public static int numberTacos(int[] z){
    	int[] todos = {z[1], z[2], z[3]};
		int sumaMenores = z[1] + z[2] + z[3] - max2(todos);
		int salidaIngredientes;
		if(sumaMenores < max2(todos)){
			salidaIngredientes = sumaMenores;
		}
		else{
			todos = ordenar(todos);
			int diferencia = todos[1] - todos[2];
			salidaIngredientes = diferencia;
			todos[0] = todos[0] - diferencia;
			todos[1] = todos[1] - diferencia;
			if((todos[0]%2) == 0){
				salidaIngredientes = salidaIngredientes + todos[0];
				todos[1] = todos[1] - (todos[0]/2);
				salidaIngredientes = salidaIngredientes + todos[1];
			}
			else{
				salidaIngredientes = salidaIngredientes + todos[0];
				todos[1] = todos[1] - ((todos[0]+1)/2);
				salidaIngredientes = salidaIngredientes + todos[1];
			}
		}
		int salidaFinal = Math.min(salidaIngredientes, z[0]);
		return salidaFinal;
	}
	public static int[] ordenar(int[] datosOriginales){
		ArrayList<Integer> datosOriginales2 = new ArrayList<Integer>();
		for(int j1 = 0; j1 < 3; j1 = j1 + 1){
			datosOriginales2.add(datosOriginales[j1]);
		}
		int[] salida = new int [3];
		int maximo;
		for(int j1 = 0; j1 < 3; j1 = j1 + 1){
			maximo = max (datosOriginales2);
			salida[j1] = datosOriginales2.get(maximo);
			datosOriginales2.remove(maximo);
		}
		return salida;
	}
	public static int max (ArrayList<Integer> a){
		int max = 0;
		for(int i = 1; i < a.size(); i++){
			if(a.get(max)<a.get(i)){
				max = i;
			}
		}
		return max;
	}
	public static int max2 (int[] a){
		int max = 0;
		for(int i = 1; i < a.length; i++){
			if(a[max]<a[i]){
				max = i;
			}
		}
		return a[max];
	}
}