package base.cartas.magias;

import base.ILaMaSerializable;
import base.cartas.Carta;
import base.cartas.Lacaio;
import io.Escritor;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public abstract class Magia extends Carta implements ILaMaSerializable{

    public Magia(UUID id, String nome, int custoMana) {
        super(id, nome, custoMana);
    }

    public Magia(String nome, int custoMana) {
        this(UUID.randomUUID(), nome, custoMana);
    }

    @Override
    public void escreveAtributos(Escritor fw) throws IOException {
        super.escreveAtributos(fw);
    }
}
