package base.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import base.Jogada;
import base.Mesa;
import base.cartas.Carta;
import base.cartas.Lacaio;
import base.cartas.magias.Dano;
import base.service.JogadaService;

public class JogadaServiceAgressivaImpl implements JogadaService {

	@Override
	public List<Jogada> criaJogada(Mesa mesa, char autor) {
		ArrayList<Carta> lacaios = (autor == 'P') ? mesa.getLacaiosP() : mesa.getLacaiosS();
		Collections.sort(lacaios, new LacaioComparator());
		List<Jogada> jogadas = new ArrayList<>();
		List<Carta> danosArea = new ArrayList<>();
		ArrayList<Carta> mao = (autor == 'P') ? mesa.getMaoP() : mesa.getMaoS();

		danosArea.addAll(mao.stream().filter(carta -> carta instanceof Dano).sorted(new DanoComparator())
				.collect(Collectors.toList()));

		for (Carta dano : danosArea) {
			if (mesa.getMana(autor) >= dano.getCustoMana()) {
				jogadas.add(new Jogada(dano, null, autor));
				mesa.sacarCarta(dano, autor);
				mesa.decMana(dano.getCustoMana(), autor);
			} else {
				break;
			}
		}

		for (Carta carta : lacaios) {
			jogadas.add(new Jogada(carta, null, autor));
		}

		return jogadas;
	}

	class LacaioComparator implements Comparator<Carta> {
		@Override
		public int compare(Carta lacaio1, Carta lacaio2) {
			return -1 * Integer.compare(((Lacaio) lacaio1).getAtaque(), ((Lacaio) lacaio2).getAtaque());
		}

	}

	class DanoComparator implements Comparator<Carta> {
		@Override
		public int compare(Carta dano1, Carta dano2) {
			return Integer.compare(dano1.getCustoMana(), dano2.getCustoMana());
		}

	}

}
