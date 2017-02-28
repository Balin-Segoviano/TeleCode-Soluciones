package PAQUETE_1;

import java.util.Scanner;

public class ApuestasDeportivas {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		double listaCantidadesApostar[];
		double beneficio;
		double cuotaEquivalente;
		int numeroDeCuotas = Integer.parseInt(entrada);
		entrada = sc.nextLine();
		int cantidadApostar = Integer.parseInt(entrada);
		double[] cuotasArray = new double[numeroDeCuotas];
		for(int j1 = 0; j1 < numeroDeCuotas; j1 = j1 + 1){
			entrada = sc.nextLine();
			cuotasArray[j1] = Double.parseDouble(entrada);
		}
		boolean esPosible = esPosibleApuestaEquivalente(cuotasArray);
		if(esPosible == false){
			System.out.println("IMPOSIBLE");
		}
		else{
			cuotaEquivalente = cuotaEquivalente(cuotasArray);
			beneficio = cantidadApostar * (cuotaEquivalente - 1);
			listaCantidadesApostar = apuestasRealizar(cuotasArray, beneficio);
			for(int j1 = 0; j1 < listaCantidadesApostar.length; j1 = j1 + 1){
				System.out.println(listaCantidadesApostar[j1]);
			}
		}
	}
	public static boolean esPosibleApuestaEquivalente(double [] listaDeCuotas){
		double [][] matrizDelSistema = new double [(listaDeCuotas.length) + 1][listaDeCuotas.length];
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			for(int j2 = 0; j2 < listaDeCuotas.length; j2 = j2 + 1){
				if(j2 == j1){
					matrizDelSistema[j2][j1] = (listaDeCuotas[j1])-1;
				}
				else{
					matrizDelSistema[j2][j1] = -1;
				}
			}
		}
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			matrizDelSistema[listaDeCuotas.length][j1] = 1;
		}
		matrizDelSistema = diagonalizar(matrizDelSistema);
		boolean todoPositivo = true;
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			if(matrizDelSistema[listaDeCuotas.length][j1] < 0){
				todoPositivo = false;
			}
		}
		return todoPositivo;
	}
	public static double cuotaEquivalente(double [] listaDeCuotas){
		double [][] matrizDelSistema = new double [(listaDeCuotas.length) + 1][listaDeCuotas.length];
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			for(int j2 = 0; j2 < listaDeCuotas.length; j2 = j2 + 1){
				if(j2 == j1){
					matrizDelSistema[j2][j1] = (listaDeCuotas[j1])-1;
				}
				else{
					matrizDelSistema[j2][j1] = -1;
				}
			}
		}
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			matrizDelSistema[listaDeCuotas.length][j1] = 1;
		}
		matrizDelSistema = diagonalizar(matrizDelSistema);
		double sumaDenominador = 0.0;
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			sumaDenominador = sumaDenominador + matrizDelSistema[listaDeCuotas.length][j1];
		}
		double cuotaEquivalente = ( 1 / sumaDenominador) + 1;
		return cuotaEquivalente;
	}
	public static double[] apuestasRealizar(double[] listaDeCuotas, double beneficio){
		double [][] matrizDelSistema = new double [(listaDeCuotas.length) + 1][listaDeCuotas.length];
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			for(int j2 = 0; j2 < listaDeCuotas.length; j2 = j2 + 1){
				if(j2 == j1){
					matrizDelSistema[j2][j1] = (listaDeCuotas[j1])-1;
				}
				else{
					matrizDelSistema[j2][j1] = -1;
				}
			}
		}
		for(int j1 = 0; j1 < listaDeCuotas.length; j1 = j1 + 1){
			matrizDelSistema[listaDeCuotas.length][j1] = 1;
		}
		matrizDelSistema = diagonalizar(matrizDelSistema);
		double[] listaDeApuestas = new double [listaDeCuotas.length];
		for(int j1 = 0; j1 < listaDeApuestas.length; j1 = j1 + 1){
			listaDeApuestas[j1] = (matrizDelSistema[listaDeCuotas.length][j1]) * beneficio;
		}
		for(int j1 = 0; j1 < listaDeApuestas.length; j1 = j1 + 1){
			listaDeApuestas[j1] = redondear(listaDeApuestas[j1], 2);
		}
		return listaDeApuestas;
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
			entrada[entrada.length - 1][j1] = redondear(entrada[entrada.length - 1][j1], 10);
		}
		return entrada;
	}
	private static double redondear(double numero, int decimales){
		return ((Math.round(numero * Math.pow(10, decimales))) / (Math.pow(10, decimales)));
	}
}