package com.lucas.base;

import com.lucas.util.Util;

public class Main {
	public static void main(String[] args) {
		System.out.println("------- Baralho array --------");
		CartaLacaio lac1 = new CartaLacaio(1, "Frodo Bolseiro", 2, 1, 1);
		CartaLacaio lac2 = new CartaLacaio(2, "Aragorn", 5, 7, 6);
		CartaLacaio lac3 = new CartaLacaio(3, "Legolas", 8, 4, 6);
		Baralho deck = new Baralho();
		deck.adicionarCarta(lac1);
		deck.adicionarCarta(lac2);
		deck.adicionarCarta(lac3);
		deck.embaralhar();

		// Baralho ArrayList
		System.out.println("------- Baralho ArrayList --------");
		BaralhoArrayList deckArrayList = new BaralhoArrayList();
		deckArrayList.adicionarCarta(lac1);
		deckArrayList.adicionarCarta(lac2);
		deckArrayList.adicionarCarta(lac3);
		deckArrayList.embaralhar();

		System.out.println("Carta comprada: " + deckArrayList.comprarCarta());

		Util.buffar(lac1, 10);
		System.out.println("Lacaio 1 bom buffer: " + lac1);
		Util.buffar(lac2, 3, 5);
		System.out.println("Lacaio 2 bom buffer: " + lac2);

	}
}
