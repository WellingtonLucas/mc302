package base;

import base.cartas.Carta;
import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Baralho {

    private List<Carta> cartas;

    public Baralho() {
		cartas = new ArrayList<Carta>();
	}
    
    public Baralho(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Baralho adicionar(Carta card) {
        if (cartas.stream().filter(c -> c.equals(card)).count() < 2
            && cartas.size() < Util.MAX_CARDS) {
            cartas.add(card);
        }

        return this;
    }

    public Carta comprar() {
        return cartas.remove(cartas.size() - 1);
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
        System.out.println(cartas);
        Collections.reverse(cartas);
    }
    
   
    /**
     * Preenche o baralho com min(MAX_CARTAS, tamanho) cartas geradas aleatoriamente
     * 
     * @param gerador objeto da classe RANDOM
     * @param tamanho tamanho do baralho
     * @param maxMana limitante superior no gasto de mana
     * @param maxAtaque limitante superior no valor do ataque
     * @param maxVida limitante superior no valor de vida
     */
    public void preencheAleatorio(Random gerador, int tamanho, int maxMana, int maxAtaque, int maxVida) {
    	tamanho = (tamanho <= Util.MAX_CARDS) ? tamanho : Util.MAX_CARDS;
    	for(int i = 0; i < tamanho; ++i) {
    		cartas.add(Util.geraCartaAleatoria(gerador, maxMana, maxAtaque, maxVida, null));
    	}
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Baralho && cartas.equals(((Baralho) o).cartas);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = hash * 12 + Objects.hashCode(cartas);
        return hash;
    }
}
