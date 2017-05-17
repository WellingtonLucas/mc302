package util;

import java.util.Random;

import base.cartas.*;
import base.cartas.magias.*;


public class Util {
    public final static int MAX_CARDS = 30;
    public final static int MAX_NOME = 5;
    public final static int MAO_INI = 3;
    public final static int MAX_TURNOS = 10;
    public final static int PODER_HEROI = 10;
    public final static int MANA_INI = 1;

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
    
    public static int randInt(Random gerador, int min, int max) {
    	
        return gerador.nextInt((max - min) + 1) + min;

    }
    
     public static Carta geraCartaAleatoria(Random gerador, int maxMana, int maxAtaque, int maxVida, TipoCarta tc) {
    	int limiar = TipoCarta.values().length;
    	TipoCarta escolhido = (tc != null) ? tc : TipoCarta.values()[gerador.nextInt(limiar)];
    	limiar = HabilidadesLacaio.values().length;
    	HabilidadesLacaio habilidade = HabilidadesLacaio.values()[gerador.nextInt(limiar)];
    	RandomString stringGerador = new RandomString(gerador, MAX_NOME);
    	
    	String nome = stringGerador.nextString();
    	int custoMana =randInt(gerador, 1, maxMana);
    	int ataque =randInt(gerador, 1, maxAtaque);
    	int vida= randInt(gerador, 1, maxVida);
    	
    	switch (escolhido) {
		case LACAIO:
			return new Lacaio(nome, custoMana, ataque, vida, maxVida, habilidade);
		case BUFF:
			return new Buff(nome, custoMana, ataque, vida);
		case DANO:
			return new Dano(nome, custoMana, ataque);
		case DANO_AREA:
			return new DanoArea(nome, custoMana, ataque);
		default:
			return null;
		}
    	
    }
}
