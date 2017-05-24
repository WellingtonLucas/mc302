package base.controle;

import base.Jogada;
import base.Mesa;
import base.service.ProcessadorService;
import base.service.impl.ProcessadorServiceImpl;

public class ProcessadorControle {
	private ProcessadorService processadorService;

	public ProcessadorControle(ProcessadorService processadorService) {
		this.processadorService = processadorService;
	}

	public ProcessadorControle() {
		processadorService = new ProcessadorServiceImpl();
	}

	public boolean processar(Jogada jogada, Mesa mesa) {
		return processadorService.processar(jogada, mesa);
	}

}
