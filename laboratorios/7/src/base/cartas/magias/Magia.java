package base.cartas.magias;

import base.cartas.Carta;
import base.cartas.Lacaio;
import java.util.List;
import java.util.UUID;

public class Magia extends Carta {

    public Magia(UUID id, String nome, int custoMana) {
        super(id, nome, custoMana);
    }

    public Magia(String nome, int custoMana) {
        this(UUID.randomUUID(), nome, custoMana);
    }
}
