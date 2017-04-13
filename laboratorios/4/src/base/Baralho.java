package base;

import base.cartas.Carta;
import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {

    private List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
    }

    public void add(Carta card) {
        if (cartas.size() < Util.MAX_CARDS) {
            cartas.add(card);
        }
    }

    public Carta comprar() {
        return cartas.remove(cartas.size() - 1);
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
        System.out.println(cartas);
        Collections.reverse(cartas);
    }
}
