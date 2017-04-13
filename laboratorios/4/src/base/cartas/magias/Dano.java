package base.cartas.magias;

import java.util.UUID;
import base.cartas.*;

public class Dano extends Magia {

    private int dano;

    public Dano(String nome, int custoMana, int dano) {
        this(UUID.randomUUID(), nome, custoMana, dano);
    }

    public Dano(UUID id, String nome, int custoMana, int dano) {
        super(id, nome, custoMana);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void usar(Carta alvo) {
        Lacaio lacaio = (Lacaio) alvo;
        lacaio.setVidaAtual(lacaio.getVidaAtual() - this.getDano());
    }
}
