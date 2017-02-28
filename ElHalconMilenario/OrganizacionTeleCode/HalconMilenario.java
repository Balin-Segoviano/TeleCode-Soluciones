package PAQUETE_1;

import java.util.Scanner;

public class HalconMilenario {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		double salida;
		double coordenadaXOrigen = Double.parseDouble(entrada);
		entrada = sc.nextLine();
		double coordenadaYOrigen = Double.parseDouble(entrada);
		entrada = sc.nextLine();
		double coordenadaXDestino = Double.parseDouble(entrada);
		entrada = sc.nextLine();
		double coordenadaYDestino = Double.parseDouble(entrada);
		if(Math.abs(coordenadaYDestino - coordenadaYOrigen) == 0){
			salida = Math.abs(coordenadaXDestino - coordenadaXOrigen);
		}
		else{
			salida = longitudCurva(Math.abs(coordenadaXDestino - coordenadaXOrigen), Math.abs(coordenadaYDestino - coordenadaYOrigen));
		}
		salida = ((int) (salida * 100000)) / 100000.0;
		System.out.println(salida);
	}
	public static double longitudCurva(double Hinicial, double Linicial){
		double[] constantes = calcularConstantes(Hinicial, Linicial);
		double salida = (4 * constantes[1] * (1 - Math.cos(constantes[0] / 2)));
		return salida;
	}
	public static double[] calcularConstantes(double Hinicial, double Linicial){
		double[] salida = new double[2];
		salida[0] = calcularT(Linicial, Hinicial);
		salida[1] = calcularA(Hinicial, salida[0]);
		return salida;
	}
	public static double calcularA(double Hinicial, double Tinicial){
		return ((Hinicial) / (1 - Math.cos(Tinicial)));
	}
	public static double calcularT(double Linicial, double Hinicial){
		double valorAnteriorDeL = 0;
		double valorActualDeL = 0;
		double valorMinimoTVueltaAnterior = 0;
		double valorMaximoTVueltaAnterior = (2 * Math.PI);
		double valorAnteriorDeT = 0;
		double valorActualDeT = 0;
		while(true){
			if(((valorDeL(valorMaximoTVueltaAnterior, Hinicial)) - (valorDeL(valorMinimoTVueltaAnterior, Hinicial))) > 0.0000000000001){
				for(int j1 = 0; j1 <= 100; j1 = j1 + 1){
					valorAnteriorDeT = valorActualDeT;
					valorActualDeT = valorMinimoTVueltaAnterior + (((valorMaximoTVueltaAnterior - valorMinimoTVueltaAnterior) / 100) * j1);
					valorAnteriorDeL = valorActualDeL;
					valorActualDeL = valorDeL(valorActualDeT, Hinicial);
					if(valorActualDeL == Linicial){
						return valorActualDeT;
					}
					if((valorActualDeL > Linicial) && (valorAnteriorDeL < Linicial)){
						valorMinimoTVueltaAnterior = valorAnteriorDeT;
						valorMaximoTVueltaAnterior = valorActualDeT;
						break;
					}
				}
			}
			else{
				return ((valorMaximoTVueltaAnterior + valorMinimoTVueltaAnterior) / 2);
			}
		}
	}
	public static double valorDeL(double t, double h){
		double valorFinal;
		if(t == 0){
			valorFinal = 0;
		}
		else{
			valorFinal = h * ((t - Math.sin(t)) / (1 - Math.cos(t)));
		}
		return valorFinal;
	}
}