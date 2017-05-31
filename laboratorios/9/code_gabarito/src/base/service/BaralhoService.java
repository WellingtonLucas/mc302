package base.service;

import java.util.List;
import java.util.Random;

import base.cartas.Carta;

public interface BaralhoService {
	
	/**
     * Preenche o baralho com min(MAX_CARTAS, tamanho) cartas geradas aleatoriamente
     * 
     * @param gerador objeto da classe RANDOM
     * @param tamanho tamanho do baralho
     * @param maxMana limitante superior no gasto de mana
     * @param maxAtaque limitante superior no valor do ataque
     * @param maxVida limitante superior no valor de vida
     */
	List<Carta> preencheAleatorio(Random gerador, int tamanho, int maxMana, int maxAtaque, int maxVida);
}
