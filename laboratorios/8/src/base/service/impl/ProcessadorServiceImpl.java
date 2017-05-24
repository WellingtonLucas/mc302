package base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import base.Jogada;
import base.Mesa;
import base.cartas.Carta;
import base.cartas.HabilidadesLacaio;
import base.cartas.Lacaio;
import base.cartas.magias.Buff;
import base.cartas.magias.Dano;
import base.cartas.magias.DanoArea;
import base.service.ProcessadorService;

public class ProcessadorServiceImpl implements ProcessadorService {
	/**
	 * Processa uma jogada
	 * 
	 * @param jogada
	 *            informações da jogada
	 * @param mesa
	 *            informações do estado atual da partida
	 */
	public boolean processar(Jogada jogada, Mesa mesa) {
		// decrementa mana do autor da jogada
		mesa.decMana(jogada.getJogada().getCustoMana(), jogada.getAutor());
		// informações do adversário
		ArrayList<Carta> lacaiosAlvo = (jogada.getAutor() == 'P') ? mesa.getLacaiosS() : mesa.getLacaiosP();
		int poderHeroiAlvo = (jogada.getAutor() == 'P') ? mesa.getPoderHeroiS() : mesa.getPoderHeroiP();
		char adversario = (jogada.getAutor() == 'P') ? 'S' : 'P';
		System.out.println(jogada);

		System.out.printf("PRE - Lacaios alvo (%d): %s\n Pode heroico alvo: %d\n", lacaiosAlvo.size(), lacaiosAlvo,
				poderHeroiAlvo);
		// verifica o tipo da carta jogada
		if (jogada.getJogada() instanceof DanoArea) {
			DanoArea da = (DanoArea) jogada.getJogada();
			for (Carta l : lacaiosAlvo) {
				da.usar(l);
			}
			mesa.decPoderHeroi(da.getDano(), adversario);
		} else if (jogada.getJogada() instanceof Dano) {
			Dano d = (Dano) jogada.getJogada();

			// verifica se existe algum lacaio com habilidade de provocar
			List<Carta> lacProv = lacaiosAlvo.stream()
					.filter(l -> ((Lacaio) l).getHabilidade() == HabilidadesLacaio.PROVOCAR)
					.collect(Collectors.toList());

			if (jogada.getAlvo() == null) {
				if (lacProv.size() > 0)
					d.usar(lacProv.get(0));
				else
					mesa.decPoderHeroi(d.getDano(), adversario);
			} else
				d.usar(jogada.getAlvo());
		} else if (jogada.getJogada() instanceof Buff) {
			// buff so pode ser usada em lacaio
			if (jogada.getAlvo() instanceof Lacaio)
				jogada.getJogada().usar(jogada.getAlvo());
		} else if (jogada.getJogada() instanceof Lacaio) {
			Lacaio l = (Lacaio) jogada.getJogada();

			// se está em exaustão, não pode ser usado nessa rodada
			if (l.getHabilidade() == HabilidadesLacaio.EXAUSTAO) {
				l.setHabilidade(HabilidadesLacaio.INVESTIDA);
				if (jogada.getAutor() == 'P')
					mesa.getLacaiosP().add(l);
				else
					mesa.getLacaiosS().add(l);
			} else {

				if (jogada.getAlvo() == null)
					mesa.decPoderHeroi(l.getAtaque(), adversario);
				else
					l.usar(jogada.getAlvo());
			}
		}
		// atualiza os lacaios
		mesa.setLacaiosP((ArrayList<Carta>) mesa.getLacaiosP().stream().filter(l -> ((Lacaio) l).getVidaAtual() > 0)
				.collect(Collectors.toList()));
		mesa.setLacaiosS((ArrayList<Carta>) mesa.getLacaiosS().stream().filter(l -> ((Lacaio) l).getVidaAtual() > 0)
				.collect(Collectors.toList()));
		poderHeroiAlvo = (jogada.getAutor() == 'P') ? mesa.getPoderHeroiS() : mesa.getPoderHeroiP();
		lacaiosAlvo = (jogada.getAutor() == 'P') ? mesa.getLacaiosS() : mesa.getLacaiosP();
		System.out.printf("POS - Lacaios alvo (%d): %s\n Poder heroico alvo: %d\n", lacaiosAlvo.size(), lacaiosAlvo,
				poderHeroiAlvo);

		System.out.println();
		return (poderHeroiAlvo == 0) ? true : false;
	}

}
