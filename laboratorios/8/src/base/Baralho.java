package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import base.cartas.Carta;
import base.service.CartaService;
import base.service.impl.CartaServiceImpl;
import util.Util;

public class Baralho {

    private List<Carta> cartas;
    private CartaService cartaService;
    
    public Baralho() {
		cartas = new ArrayList<Carta>();
		cartaService = new CartaServiceImpl();
	}
    
    public Baralho(List<Carta> cartas) {
    	cartaService = new CartaServiceImpl();
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
    		cartas.add(cartaService.geraCartaAleatoria(gerador, maxMana, maxAtaque, maxVida, null));
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
