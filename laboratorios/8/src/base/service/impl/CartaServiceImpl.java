package base.service.impl;

import static util.Util.MAX_NOME;

import java.util.Random;

import base.cartas.Carta;
import base.cartas.HabilidadesLacaio;
import base.cartas.Lacaio;
import base.cartas.TipoCarta;
import base.cartas.magias.Buff;
import base.cartas.magias.Dano;
import base.cartas.magias.DanoArea;
import base.service.CartaService;
import util.RandomString;

public class CartaServiceImpl implements CartaService {

	private RandomString stringGerador;
	private HabilidadesLacaio habilidade;
	private TipoCarta escolhido;

	@Override
	public Carta geraCartaAleatoria(Random gerador, int maxMana, int maxAtaque, int maxVida, TipoCarta tipoCarta) {
		int limiar = TipoCarta.values().length;

		escolhido = (tipoCarta != null) ? tipoCarta : TipoCarta.values()[gerador.nextInt(limiar)];
		limiar = HabilidadesLacaio.values().length;
		habilidade = HabilidadesLacaio.values()[gerador.nextInt(limiar)];
		stringGerador = new RandomString(gerador, MAX_NOME);

		String nome = stringGerador.nextString();
		int custoMana = randInt(gerador, 1, maxMana);
		int ataque = randInt(gerador, 1, maxAtaque);
		int vida = randInt(gerador, 1, maxVida);

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

	@Override
	public int randInt(Random gerador, int min, int max) {
		return gerador.nextInt((max - min) + 1) + min;
	}

}
