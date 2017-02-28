package PAQUETE_1;

import java.util.Scanner;

public class SenalesPeriodicas {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		while(entrada != null){
			System.out.println(searchPattern(entrada));
			entrada = sc.nextLine();
		}
	}
	public static String searchPattern(String signal){
		String substring = signal.substring(0,1);
		String substring2;
		int distance = 1;
		for(int j1 = 1; (j1 <= signal.length() - distance); j1 = j1 + 1){
			substring2 = signal.substring(j1, j1 + distance);
			if(substring2.equals(substring)){
				substring = signal.substring(0, j1);
				distance = j1;
				if(esPeriodico(signal.substring(0, j1), signal) == true){
					return signal.substring(0, j1);
				}
			}
			if((signal.length()-1-j1) < distance){
				distance = signal.length()-1-j1;
				substring = signal.substring(0, distance);
			}
		}
		return signal;
	}
	public static boolean esPeriodico(String substring, String signal){
		int periodicidad = substring.length();
		boolean funciona = true;
		for(int j1 = substring.length(); j1 < signal.length(); j1 = j1 + 1){
			if((signal.charAt(j1)) != (substring.charAt(j1 % periodicidad))){
				funciona = false;
				break;
			}
		}
		return funciona;
	}
}