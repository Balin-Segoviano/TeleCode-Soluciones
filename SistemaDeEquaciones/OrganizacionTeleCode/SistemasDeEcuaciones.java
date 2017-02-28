package PAQUETE_1;

import java.util.Scanner;

public class SistemasDeEcuaciones {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		int numeroIncognitas = Integer.parseInt(entrada);
		String[] partes;
		double[][] coeficientes = new double[numeroIncognitas + 1][numeroIncognitas];
		for(int j1 = 0; j1 < numeroIncognitas; j1 = j1 + 1){
			entrada = sc.nextLine();
			partes = entrada.split(" ");
			for(int j2 = 0; j2 < partes.length; j2 = j2 + 1){
				coeficientes[j2][j1] = Double.parseDouble(partes[j2]);
			}
		}
		coeficientes = diagonalizar(coeficientes);
		for(int j1 = 0; j1 < numeroIncognitas; j1 = j1 + 1){
			System.out.println(((int) (coeficientes[numeroIncognitas][j1] * 100)) / 100.0);
		}
	}
	private static double[][] diagonalizar(double[][] entrada){
		double factor;
		for(int j1 = 0; j1 < (entrada.length - 2); j1 = j1 + 1){
			for(int j2 = j1 + 1; j2 < (entrada.length - 1); j2 = j2 + 1){
				if(entrada[j1][j2] != 0){
					factor = (entrada[j1][j1]) / (entrada[j1][j2]);
					for(int j3 = 0; j3 < entrada.length; j3 = j3 + 1){
						entrada[j3][j2] = (entrada[j3][j2]) * factor;
					}
					for(int j3 = 0; j3 < entrada.length; j3 = j3 + 1){
						entrada[j3][j2] = entrada[j3][j2] - entrada[j3][j1];
					}
				}
			}
		}
		for(int j1 = 1; j1 < (entrada.length - 1); j1 = j1 + 1){
			for(int j2 = 0; j2 < j1; j2 = j2 + 1){
				if(entrada[j1][j2] != 0){
					factor = (entrada[j1][j1]) / (entrada[j1][j2]);
					for(int j3 = 0; j3 < entrada.length; j3 = j3 + 1){
						entrada[j3][j2] = (entrada[j3][j2]) * factor;
					}
					for(int j3 = 0; j3 < entrada.length; j3 = j3  + 1){
						entrada[j3][j2] = entrada[j3][j2] - entrada[j3][j1];
					}
				}
			}
		}
		for(int j1 = 0; j1 < (entrada.length - 1); j1 = j1 + 1){
			factor = entrada[j1][j1];
			entrada [j1][j1] = 1.0;
			entrada[entrada.length - 1][j1] = (entrada[entrada.length - 1][j1]) / factor;
		}
		for(int j1 = 0; j1 < (entrada.length - 1); j1 = j1 + 1){
			entrada[entrada.length - 1][j1] = entrada[entrada.length - 1][j1];
		}
		return entrada;
	}
}