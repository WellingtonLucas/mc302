package util;

import base.cartas.Carta;
import base.cartas.Lacaio;
import base.cartas.magias.Buff;
import base.cartas.magias.Dano;
import base.cartas.magias.DanoArea;

import java.util.Random;


public class HearthStoneUtils {
    public static int MAX_CARDS = 30;
    public static int MAX_NOME = 25;

    public static final RandomStringGenerator strGen = new RandomStringGenerator(MAX_NOME);

    public static void buff(Lacaio lacaio, int statusIncrease) {
        buff(lacaio, statusIncrease, statusIncrease);
    }

    public static void buff(Lacaio lacaio, int attackIncrease, int lifeIncrease) {
        if (attackIncrease > 0 && lifeIncrease > 0) {
            lacaio.setAtaque(lacaio.getAtaque() + attackIncrease);
            lacaio.setVidaAtual(lacaio.getVidaAtual() + lifeIncrease);
            lacaio.setVidaMaxima(lacaio.getVidaMaxima() + lifeIncrease);
            lacaio.setNome(lacaio.getNome() + " Fortalecido");
        }
    }

    public static Carta randomCard(int maxMana, int maxAttack, int maxLife, Random r) {
        String name = strGen.nextString();
        int attack = r.nextInt(maxAttack + 1);
        int life = r.nextInt(maxLife + 1);
        int cost = r.nextInt(maxMana + 1);

        switch (r.nextInt(4)) {
            case 0:
                return new Lacaio(name, cost, attack, life, maxLife);
            case 1:
                return new Buff(name, cost, attack, life);
            case 2:
                return new Dano(name, cost, attack);
            case 3:
                return new DanoArea(name, cost, attack);
            default:
                return null;
        }
    }
}
