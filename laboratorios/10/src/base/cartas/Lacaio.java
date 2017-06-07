package base.cartas;

import base.ILaMaSerializable;
import io.Escritor;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.UUID;

public class Lacaio extends Carta implements ILaMaSerializable{

	private int ataque;
	private int vidaAtual;
	private int vidaMaxima;
	private HabilidadesLacaio habilidade;

	public Lacaio(String nome, int custoMana, int ataque, int vidaAtual, int vidaMaxima) {
		this(UUID.randomUUID(), nome, custoMana, ataque, vidaAtual, vidaMaxima);
	}

	public Lacaio(String nome, int custoMana, int ataque, int vidaAtual, int vidaMaxima, HabilidadesLacaio habilidade) {
		this(UUID.randomUUID(), nome, custoMana, ataque, vidaAtual, vidaMaxima);
		this.habilidade = habilidade;
	}
	public Lacaio(UUID id, String nome, int custoMana, int ataque, int vidaAtual, int vidaMaxima, HabilidadesLacaio habilidade) {
		this(id, nome, custoMana, ataque, vidaAtual, vidaMaxima);
		this.habilidade = habilidade;
	}

	public Lacaio(UUID id, String nome, int custoMana, int ataque, int vidaAtual, int vidaMaxima) {
		super(id, nome, custoMana);
		this.ataque = ataque;
		this.vidaAtual = vidaAtual;
		this.vidaMaxima = vidaMaxima;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getVidaAtual() {
		return vidaAtual;
	}

	public void setVidaAtual(int vidaAtual) {
		this.vidaAtual = vidaAtual;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public HabilidadesLacaio getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(HabilidadesLacaio habilidade) {
		this.habilidade = habilidade;
	}

	@Override
	public void usar(Carta alvo) {
		Lacaio lacaio = (Lacaio) alvo;
		int novoValor = lacaio.getVidaAtual() - getAtaque();
		novoValor = (novoValor > 0) ? novoValor : 0;
		lacaio.setVidaAtual(novoValor);
	}
	

	@Override
	public String toString() {
		return String.format("%s [%d/%d] Habilidade: %s", super.toString(), getAtaque(), getVidaAtual(), getHabilidade());
	}

	@Override
	public void escreveAtributos(Escritor fw) throws IOException {
		super.escreveAtributos(fw);
		fw.escreveAtributo("ataque", String.valueOf(ataque));
		fw.escreveAtributo("vidaAtual", String.valueOf(vidaAtual));
		fw.escreveAtributo("vidaMaxima", String.valueOf(vidaMaxima));
		fw.escreveAtributo("habilidade", habilidade.toString());
	}
}
