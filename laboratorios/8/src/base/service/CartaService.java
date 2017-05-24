package base.service;

import java.util.Random;

import base.cartas.Carta;
import base.cartas.TipoCarta;

public interface CartaService {
	
	Carta geraCartaAleatoria(Random gerador, int maxMana, int maxAtaque, int maxVida, TipoCarta tipoCarta);
	
	int randInt(Random gerador, int min, int max);
}
