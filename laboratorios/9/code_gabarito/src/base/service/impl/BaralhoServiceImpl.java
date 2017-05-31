package base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import base.cartas.Carta;
import base.exception.BaralhoVazioException;
import base.service.BaralhoService;
import base.service.CartaService;
import util.Util;

public class BaralhoServiceImpl implements BaralhoService {
	
	private CartaService cartaService;

	public BaralhoServiceImpl() {
		cartaService = new CartaServiceImpl();
	}
	
	@Override
	public List<Carta> preencheAleatorio(Random gerador, int tamanho, int maxMana, int maxAtaque, int maxVida) {
		tamanho = (tamanho <= Util.MAX_CARDS) ? tamanho : Util.MAX_CARDS;
		List<Carta> cartas = new ArrayList<>();

		for (int i = 0; i < tamanho; ++i) {
			cartas.add(cartaService.geraCartaAleatoria(gerador, maxMana, maxAtaque, maxVida, null));
		}
		if(cartas.isEmpty()){
			throw new BaralhoVazioException("Nao foram adicionadas cartas ao baralho.");
		}
		return cartas;
	}

}
