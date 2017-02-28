package PAQUETE_1;

import java.util.Scanner;

public class MaximaLongitudPaquetes {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.nextLine();
		while(entrada != null){
			if(entrada.length() > 30){
				System.out.println("NO");
			}
			else{
				System.out.println("SI");
			}
			entrada = sc.nextLine();
		}
	}
}