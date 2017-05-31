package base.service;

import java.util.Random;

import base.Baralho;
import base.Mesa;
import base.cartas.TipoCarta;
import base.exception.MesaNulaException;

public interface MesaService {

	Mesa adicionaLacaios(Mesa mesa, Random gerador, TipoCarta tipoCarta) throws MesaNulaException;

	Mesa addMaoInicial(Mesa mesa, Baralho baralhoP, Baralho baralhoS, int maoIni);
	
}
