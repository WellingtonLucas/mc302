package util;

import base.cartas.Lacaio;


public class Util {
    public static int MAX_CARDS = 30;

    public static void buffar(Lacaio lac, int a) {
        buffar(lac, a, a);
    }

    public static void buffar(Lacaio lacaio, int aumentoEmAtaque, int aumentoEmVida) {
        if (aumentoEmAtaque > 0 && aumentoEmVida > 0) {
            lacaio.setAtaque(lacaio.getAtaque() + aumentoEmAtaque);
            lacaio.setVidaAtual(lacaio.getVidaAtual() + aumentoEmVida);
            lacaio.setVidaMaxima(lacaio.getVidaMaxima() + aumentoEmVida);
            lacaio.setNome(lacaio.getNome() + " Fortalecido");
        }
    }
}
