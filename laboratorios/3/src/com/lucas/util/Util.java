package com.lucas.util;

import com.lucas.base.CartaLacaio;


public class Util {
	public static int MAX_CARDS = 30;
	
	public static void buffar(CartaLacaio lac, int a){
		if(a > 0){
			lac.setAtaque(lac.getAtaque() + a);
			lac.setVidaAtual(lac.getVidaAtual() + a);
			lac.setVidaMaxima(lac.getVidaMaxima() + a);
			alteraNomeFortalecido(lac);
		}
	}
	
	public static void buffar(CartaLacaio lac, int a, int v){
		if(a > 0 && v > 0){
			lac.setAtaque(lac.getAtaque() + a);
			lac.setVidaAtual(lac.getVidaAtual() + v);
			lac.setVidaMaxima(lac.getVidaMaxima() + v);
			alteraNomeFortalecido(lac);
		}	
	}

	private static void alteraNomeFortalecido(CartaLacaio lac) {
		lac.setNome(lac.getNome() + " Fortalecido");
	}
}
