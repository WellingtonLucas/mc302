package base.service.impl;

import static util.Util.MAX_ATAQUE;
import static util.Util.MAX_LACAIOS;
import static util.Util.MAX_MANA;
import static util.Util.MAX_VIDA;

import java.util.Random;

import base.Baralho;
import base.Mesa;
import base.cartas.TipoCarta;
import base.exception.MesaNulaException;
import base.exception.ValorInvalidoException;
import base.service.CartaService;
import base.service.MesaService;

public class MesaServiceImpl implements MesaService {

	private CartaService cartaService;

	public MesaServiceImpl() {
		cartaService = new CartaServiceImpl();
	}

	@Override
	public Mesa adicionaLacaios(Mesa mesa, Random gerador, TipoCarta tipoCarta) throws MesaNulaException {
		if (mesa == null) {
			throw new MesaNulaException("A mesa nao pode ser nula.");
		}
		for (int i = 0; i < MAX_LACAIOS; i++) {
			mesa.getLacaiosP()
					.add(cartaService.geraCartaAleatoria(gerador, MAX_MANA, MAX_ATAQUE, MAX_VIDA, TipoCarta.LACAIO));
			mesa.getLacaiosS()
					.add(cartaService.geraCartaAleatoria(gerador, MAX_MANA, MAX_ATAQUE, MAX_VIDA, TipoCarta.LACAIO));
		}
		return mesa;
	}

	@Override
	public Mesa addMaoInicial(Mesa mesa, Baralho baralhoP, Baralho baralhoS, int maoIni) {
		if (maoIni<3) {
			throw new ValorInvalidoException(maoIni);
		}
		
		for (int i = 0; i < maoIni; i++) {
			mesa.getMaoP().add(baralhoP.comprar());
			mesa.getMaoS().add(baralhoS.comprar());
		}
		mesa.getMaoS().add(baralhoS.comprar());
		return mesa;
	}

}
