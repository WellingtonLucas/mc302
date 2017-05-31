package base.controle;

import static util.Util.MAO_INI;
import static util.Util.MAX_ATAQUE;
import static util.Util.MAX_CARDS;
import static util.Util.MAX_MANA;
import static util.Util.MAX_VIDA;

import java.util.List;
import java.util.Random;

import base.Baralho;
import base.Jogada;
import base.Mesa;
import base.cartas.TipoCarta;
import base.exception.BaralhoVazioException;
import base.exception.MesaNulaException;
import base.exception.ValorInvalidoException;
import base.service.BaralhoService;
import base.service.JogadaService;
import base.service.MesaService;
import base.service.ProcessadorService;
import base.service.impl.BaralhoServiceImpl;
import base.service.impl.JogadaServiceAgressivaImpl;
import base.service.impl.MesaServiceImpl;
import base.service.impl.ProcessadorServiceImpl;

public class Controle {

	private Mesa mesa;
	private Baralho baralhoP;
	private Baralho baralhoS;
	private Random gerador;
	private JogadaService jogadaService;
	private BaralhoService baralhoService;
	private MesaService mesaService;
	private ProcessadorService processadorService;

	public Controle() {
		this.baralhoP = new Baralho();
		this.baralhoS = new Baralho();
		this.mesa = new Mesa();
		gerador = new Random();
		jogadaService = new JogadaServiceAgressivaImpl();
		baralhoService = new BaralhoServiceImpl();
		mesaService = new MesaServiceImpl();
		processadorService = new ProcessadorServiceImpl();
	}

	public void executa() {
		try {

			try {
				baralhoP.addCartas(
						baralhoService.preencheAleatorio(gerador, MAX_CARDS, MAX_MANA, MAX_ATAQUE, MAX_VIDA));
				baralhoS.addCartas(
						baralhoService.preencheAleatorio(gerador, MAX_CARDS, MAX_MANA, MAX_ATAQUE, MAX_VIDA));
			} catch (BaralhoVazioException e) {
				System.err.println(e.getMessage());
			}

			try {
				mesa = mesaService.adicionaLacaios(mesa, gerador, TipoCarta.LACAIO);
				mesa = mesaService.addMaoInicial(mesa, baralhoP, baralhoS, MAO_INI);
			} catch (MesaNulaException | ValorInvalidoException e) {
				System.err.println(e.getMessage());
			}
			
			List<Jogada> jogadas = jogadaService.criaJogada(mesa, 'P');
			for (Jogada jogada : jogadas) {
				if (processadorService.processar(jogada, mesa)) {
					System.out.println("###### " + jogada.getAutor() + " venceu!");
					break;
				}
			}

		} finally {
			System.out.println("############ Partida encerrada ############");
		}
	}
}
