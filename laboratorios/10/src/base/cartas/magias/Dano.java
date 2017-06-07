package base.cartas.magias;

import java.io.IOException;
import java.util.UUID;

import base.ILaMaSerializable;
import base.cartas.*;
import io.Escritor;

public class Dano extends Magia implements ILaMaSerializable{

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

    @Override
    public void usar(Carta alvo) {
        Lacaio lacaio = (Lacaio) alvo;
		int novoValor = lacaio.getVidaAtual() - this.getDano();
		novoValor = (novoValor > 0) ? novoValor : 0;
		lacaio.setVidaAtual(novoValor);
    }

    @Override
    public void escreveAtributos(Escritor fw) throws IOException {
        super.escreveAtributos(fw);
        fw.escreveAtributo("dano", String.valueOf(dano));
    }
}
