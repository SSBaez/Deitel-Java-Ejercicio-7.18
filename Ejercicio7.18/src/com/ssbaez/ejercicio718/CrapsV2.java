package com.ssbaez.ejercicio718;

import java.security.SecureRandom;

public class CrapsV2 {
	
	private enum Estado {CONTINUA, GANO, PERDIO};
	private static final SecureRandom numAleatorio =  new SecureRandom();
	
	private static final int DOS_UNOS 	= 2;
	private static final int TRES 		= 3;
	private static final int SIETE 		= 7;
	private static final int ONCE 		= 11;
	private static final int DOCE 		= 12;
	
	public static void main(String[] args) {
		
		int[] intentosG = new int[22];
		int[] intentosP = new int[22];
		int intento = 0;
		
		for(int i = 0; i < 1000000; i++) {
			
			intento = 0;
			
			int miPto = 0;
			Estado estadoJuego;
			int tiro = tirarDados();
			intento++;
			
			switch(tiro) {
			
			case SIETE:
			case ONCE:
				estadoJuego = Estado.GANO;
				break;
				
			case DOS_UNOS:
			case TRES:
			case DOCE:
				estadoJuego = Estado.PERDIO;
				break;
				
			default:
				estadoJuego = Estado.CONTINUA;
				miPto = tiro;
				break;
			}
			
			while(estadoJuego == Estado.CONTINUA) {
				
				tiro = tirarDados();
				intento++;
				
				if(tiro == miPto)
					estadoJuego = Estado.GANO;
				
				else
					if(tiro == SIETE)
						estadoJuego = Estado.PERDIO;
				
			}
			
			if(estadoJuego == Estado.GANO) {
				if(intento >= 21)
					++intentosG[21];
				else
					++intentosG[intento];
			}
			else {
				if(intento >= 21)
					++intentosP[21];
				else
					++intentosP[intento];
			}
			
		}//Fin de for
		
		System.out.println("Juegos ganados tirando: ");
		for(int i = 1; i < intentosG.length; i++) {
			System.out.printf("%2da: %,d%n", i, intentosG[i]);
		}
		
		System.out.println("\nJuegos perdido tirando: ");
		for(int i = 1; i < intentosP.length; i++) {
			System.out.printf("%2da: %,d%n", i, intentosP[i]);
		}
		
		int totalG = 0;
		int totalP = 0;
		
		for(int inten: intentosG) {
			totalG += inten;
		}
		
		System.out.printf("%nJuegos Ganados: %,d", totalG);
		
		for(int inten: intentosP) {
			totalP += inten;
		}
		
		System.out.printf("%nJuegos Perdidos: %,d", totalP);
		System.out.printf("%nJuegos realizados: %,d", totalG + totalP);
		double probG = ((float) totalG / (totalG + totalP))*100;
		System.out.printf("%n%nProbabilidad de ganar: %.2f", probG);
		System.out.print("%\n");
		double probP = ((float) totalP / (totalG + totalP))*100;
		System.out.printf("Probabilidad de perder: %.2f", probP);
		System.out.print("%\n");
		
	}//Fin de metodo main
	
	public static int tirarDados() {
		int dadoA = 1 + numAleatorio.nextInt(6);
		int dadoB = 1 + numAleatorio.nextInt(6);
		return dadoA + dadoB;
	}

}
