package base.controle;

import static util.Util.MAO_INI;
import static util.Util.MAX_CARDS;

import java.util.List;
import java.util.Random;

import base.Baralho;
import base.Jogada;
import base.Mesa;
import base.cartas.TipoCarta;
import base.service.CartaService;
import base.service.JogadaService;
import base.service.impl.CartaServiceImpl;
import base.service.impl.JogadaServiceAgressivaImpl;
import base.service.impl.ProcessadorServiceImpl;

public class Controle {

	private Mesa mesa;
	private Baralho baralhoP;
	private Baralho baralhoS;
	private Random gerador;
	private ProcessadorControle processadorControle;
	private CartaService cartaService;
	private JogadaService jogadaService;

	int maxLacaios = 10;
	int maxMana = 2;
	int maxAtaque = 6;
	int maxVida = 6;
	int index;

	public Controle() {
		this.baralhoP = new Baralho();
		this.baralhoS = new Baralho();
		this.mesa = new Mesa();
		gerador = new Random();
		cartaService = new CartaServiceImpl();
		jogadaService = new JogadaServiceAgressivaImpl();
	}

	public void executa() {

		preencheBaralho();

		organizaMesa();

		processadorControle = new ProcessadorControle(new ProcessadorServiceImpl());

		List<Jogada> jogadas = jogadaService.criaJogada(mesa, 'P');
		for (Jogada jogada : jogadas) {
			if (processadorControle.processar(jogada, mesa)) {
				System.out.println("###### "+jogada.getAutor() + " venceu!");
				break;
			}
		}

	}

	private void preencheBaralho() {
		baralhoP.preencheAleatorio(gerador, MAX_CARDS, maxMana, maxAtaque, maxVida);
		baralhoS.preencheAleatorio(gerador, MAX_CARDS, maxMana, maxAtaque, maxVida);
	}

	private void organizaMesa() {
		for (int i = 0; i < maxLacaios; i++) {
			mesa.getLacaiosP()
					.add(cartaService.geraCartaAleatoria(gerador, maxMana, maxAtaque, maxVida, TipoCarta.LACAIO));
			mesa.getLacaiosS()
					.add(cartaService.geraCartaAleatoria(gerador, maxMana, maxAtaque, maxVida, TipoCarta.LACAIO));
		}

		for (int i = 0; i < MAO_INI; i++) {
			mesa.getMaoP().add(baralhoP.comprar());
			mesa.getMaoS().add(baralhoS.comprar());
		}

		mesa.getMaoS().add(baralhoS.comprar());
	}
}
