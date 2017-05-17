package base;

import base.cartas.Carta;

/**
 * Classe para armazenar informações de uma jogada
 * @author mc302
 *
 */
public class Jogada {

	// carta jogada
	private Carta jogada;
	// carta alvo
	private Carta alvo;
	// autor da jogada (P - primeiro, S - segundo)
	private char autor;

	public Jogada(Carta jogada, Carta alvo, char autor) {
		this.jogada = jogada;
		this.alvo = alvo;
		this.autor = autor;
	}

	public Carta getJogada() {
		return jogada;
	}

	public void setJogada(Carta jogada) {
		this.jogada = jogada;
	}

	public Carta getAlvo() {
		return alvo;
	}

	public void setAlvo(Carta alvo) {
		this.alvo = alvo;
	}

	public char getAutor() {
		return autor;
	}

	public void setAutor(char autor) {
		this.autor = autor;
	}
	
	@Override
	public String toString() {
		return String.format("Autor: %s\n Jogada: %s\n Alvo: %s\n", autor, jogada, alvo);
	}

}
