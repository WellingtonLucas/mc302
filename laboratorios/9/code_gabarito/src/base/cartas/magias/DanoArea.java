package base.cartas.magias;

import java.util.List;
import java.util.UUID;

import base.cartas.Carta;

public class DanoArea extends Dano {

    public DanoArea(String nome, int custoMana, int dano) {
        this(UUID.randomUUID(), nome, custoMana, dano);
    }

    public DanoArea(UUID id, String nome, int custoMana, int dano) {
        super(id, nome, custoMana, dano);
    }

    public void usar(List<Carta> alvos) {
        for (Carta alvo : alvos) {
            usar(alvo);
        }
    }
    
}
