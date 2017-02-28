package PAQUETE_1;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	public static void main(String[] args){
		boolean tengoBJ = false;
		int miSuma;
		int cartaInicial;
		int[] cartasDisponibles = new int[10];
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		if(entrada.equals("B")){
			tengoBJ = true;
			miSuma = 21;
		}
		else{
			miSuma = Integer.parseInt(entrada);
		}
		entrada = sc.nextLine();
		cartaInicial = Integer.parseInt(entrada);
		for(int j1 = 0; j1 < 10; j1 = j1 + 1){
			cartasDisponibles[j1] = Integer.parseInt(sc.nextLine());
		}
		double[] salida = probabilidadSiPlanto(miSuma, tengoBJ, cartaInicial, cartasDisponibles);
		System.out.println(((int) (salida[0] * 1000)) / 10.0);
		System.out.println(((int) (salida[1] * 1000)) / 10.0);
		System.out.println(((int) (salida[2] * 1000)) / 10.0);
	}
	public static double[] probabilidadSiPlanto(int miSuma, boolean tengoBJ, int cartaCroupier, int[] cartasDisponibles){
		double probabilidadGanar = 0.0;
		double probabilidadEmpatar = 0.0;
		double probabilidadPerder = 0.0;
		if(miSuma > 21){
			probabilidadPerder = 1.0;
		}
		else{
			ArrayList<ArrayList<Integer>> ramasCompletas = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> ramasParciales = new ArrayList<ArrayList<Integer>>();
			ramasParciales.add(new ArrayList<Integer>());
			ramasParciales.get(0).add(cartaCroupier);
			boolean funcionando = true;
			ArrayList<Integer> posicionesCompletas = new ArrayList<Integer>();
			int numeroPosiciones;
			ArrayList<Integer> nuevaRama;
			while(funcionando == true){
				for(int j1 = 0; j1 < ramasParciales.size(); j1 = j1 + 1){
					if(estaCompleta(ramasParciales.get(j1)) == true){
						posicionesCompletas.add(j1);
					}
				}
				for(int j1 = 0; j1 < posicionesCompletas.size(); j1 = j1 + 1){
					ramasCompletas.add(ramasParciales.get(posicionesCompletas.get(j1)));
				}
				for(int j1 = 0; j1 < posicionesCompletas.size(); j1 = j1 + 1){
					ramasParciales.remove(posicionesCompletas.get(j1) - j1);
				}
				posicionesCompletas.clear();
				numeroPosiciones = ramasParciales.size();
				for(int j1 = 0; j1 < numeroPosiciones; j1 = j1 + 1){
					for(int j2 = 1; j2 < 11; j2 = j2 + 1){
						nuevaRama = copiarArrayList(ramasParciales.get(j1));
						nuevaRama.add(j2);
						ramasParciales.add(nuevaRama);
					}
				}
				for(int j1 = 0; j1 < numeroPosiciones; j1 = j1 + 1){
					ramasParciales.remove(0);
				}
				if(ramasParciales.size() == 0){
					funcionando = false;
				}
			}
			ArrayList<ArrayList<Integer>> ramasGanadoras = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> ramasEmpate = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> ramasPerdedoras = new ArrayList<ArrayList<Integer>>();
			for(int j1 = 0; j1 < ramasCompletas.size(); j1 = j1 + 1){
				if((sumaTotal(ramasCompletas.get(j1)) >= 22) || (sumaTotal(ramasCompletas.get(j1)) < miSuma)){
					ramasGanadoras.add(ramasCompletas.get(j1));
				}
				else{
					if(sumaTotal(ramasCompletas.get(j1)) == miSuma){
						if((miSuma == 21) && (ramasCompletas.get(j1).size() == 2) && (tengoBJ == false)){
							ramasPerdedoras.add(ramasCompletas.get(j1));
						}
						else{
							if((ramasCompletas.get(j1).size() > 2) && (tengoBJ == true)){
								ramasGanadoras.add(ramasCompletas.get(j1));
							}
							else{
								ramasEmpate.add(ramasCompletas.get(j1));
							}
						}
					}
					else{
						ramasPerdedoras.add(ramasCompletas.get(j1));
					}
				}
			}
			for(int j1 = 0; j1 < ramasGanadoras.size(); j1 = j1 + 1){
				probabilidadGanar = probabilidadGanar + probabilidadLlegada(ramasGanadoras.get(j1), cartasDisponibles);
			}
			for(int j1 = 0; j1 < ramasEmpate.size(); j1 = j1 + 1){
				probabilidadEmpatar = probabilidadEmpatar + probabilidadLlegada(ramasEmpate.get(j1), cartasDisponibles);
			}
			for(int j1 = 0; j1 < ramasPerdedoras.size(); j1 = j1 + 1){
				probabilidadPerder = probabilidadPerder + probabilidadLlegada(ramasPerdedoras.get(j1), cartasDisponibles);
			}
		}
		double[] probabilidadesFinales = {probabilidadGanar, probabilidadEmpatar, probabilidadPerder};
		return probabilidadesFinales;
	}
	private static boolean estaCompleta(ArrayList<Integer> ramaEvaluar){
		ArrayList<Integer> auxiliar = new ArrayList<Integer>();
		for(int j1 = 0; j1 < ramaEvaluar.size(); j1 = j1 + 1){
			auxiliar.add(ramaEvaluar.get(j1));
		}
		ramaEvaluar = auxiliar;
		boolean encontrado = false;
		for(int j1 = 0; ((j1 < ramaEvaluar.size()) && (encontrado == false)); j1 = j1 + 1){
			if(ramaEvaluar.get(j1) == 1){
				ramaEvaluar.set(j1, 11);
				encontrado = true;
			}
		}
		Integer cuenta = 0;
		for(int j1 = 0; j1 < ramaEvaluar.size(); j1 = j1 + 1){
			cuenta = cuenta + ramaEvaluar.get(j1);
		}
		if(cuenta <= 21){
			if(cuenta >= 17){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			encontrado = false;
			for(int j1 = 0; ((j1 < ramaEvaluar.size()) && (encontrado == false)); j1 = j1 + 1){
				if(ramaEvaluar.get(j1) == 11){
					ramaEvaluar.set(j1, 1);
					encontrado = true;
				}
			}
			cuenta = 0;
			for(int j1 = 0; j1 < ramaEvaluar.size(); j1 = j1 + 1){
				cuenta = cuenta + ramaEvaluar.get(j1);
			}
			if(cuenta >= 17){
				return true;
			}
			else{
				return false;
			}
		}
	}
	private static ArrayList<Integer> copiarArrayList(ArrayList<Integer> original){
		ArrayList<Integer> salida = new ArrayList<Integer>();
		for(int j1 = 0; j1 < original.size(); j1 = j1 + 1){
			salida.add(original.get(j1));
		}
		return salida;
	}
	private static Integer sumaTotal(ArrayList<Integer> ramaSumar){
		ArrayList<Integer> auxiliar = new ArrayList<Integer>();
		for(int j1 = 0; j1 < ramaSumar.size(); j1 = j1 + 1){
			auxiliar.add(ramaSumar.get(j1));
		}
		ramaSumar = auxiliar;
		boolean encontrado = false;
		for(int j1 = 0; ((j1 < ramaSumar.size()) && (encontrado == false)); j1 = j1 + 1){
			if(ramaSumar.get(j1) == 1){
				ramaSumar.set(j1, 11);
				encontrado = true;
			}
		}
		Integer sumaSalida = 0;
		for(int j1 = 0; j1 < ramaSumar.size(); j1 = j1 + 1){
			sumaSalida = sumaSalida + ramaSumar.get(j1);
		}
		if(sumaSalida <= 21){
			return sumaSalida;
		}
		else{
			encontrado = false;
			for(int j1 = 0; ((j1 < ramaSumar.size()) && (encontrado == false)); j1 = j1 + 1){
				if(ramaSumar.get(j1) == 11){
					ramaSumar.set(j1, 1);
					encontrado = true;
				}
			}
			sumaSalida = 0;
			for(int j1 = 0; j1 < ramaSumar.size(); j1 = j1 + 1){
				sumaSalida = sumaSalida + ramaSumar.get(j1);
			}
			return sumaSalida;
		}
	}
	private static double probabilidadLlegada(ArrayList<Integer> ramaEntrada, int[] cartasDisponibles){
		int[] auxiliarDisponibles = new int [cartasDisponibles.length];
		for(int j1 = 0; j1 < cartasDisponibles.length; j1 = j1 + 1){
			auxiliarDisponibles[j1] = cartasDisponibles[j1];
		}
		cartasDisponibles = auxiliarDisponibles;
		double probabilidadFinal = 1.0;
		for(int j1 = 1; j1 < ramaEntrada.size(); j1 = j1 + 1){
			probabilidadFinal = probabilidadFinal * probabilidadCarta(ramaEntrada.get(j1), cartasDisponibles);
			if(cartasDisponibles[ramaEntrada.get(j1) - 1] > 0){
				cartasDisponibles[ramaEntrada.get(j1) - 1] = cartasDisponibles[ramaEntrada.get(j1) - 1] - 1;
			}
		}
		return probabilidadFinal;
	}
	private static double probabilidadCarta(int cartaEnCuestion, int[] cartasDisponibles){
		int totalCartasDisponiblesInt = 0;
		for(int j1 = 0; j1 < cartasDisponibles.length; j1 = j1 + 1){
			totalCartasDisponiblesInt = totalCartasDisponiblesInt + cartasDisponibles[j1];
		}
		double totalCartasDisponiblesDouble = (double) totalCartasDisponiblesInt;
		double probabilidadCarta = cartasDisponibles[cartaEnCuestion-1] / totalCartasDisponiblesDouble;
		return probabilidadCarta;
	}
}