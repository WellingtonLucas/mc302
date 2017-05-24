package base.cartas.magias;

import java.util.UUID;

import base.cartas.Carta;
import base.cartas.Lacaio;

public class Buff extends Magia {

    private int aumentoEmAtaque;
    private int aumentoEmVida;

    public Buff(UUID id, String nome, int custoMana, int aumentoEmAtaque, int aumentoEmVida) {
        super(id, nome, custoMana);
        this.aumentoEmAtaque = aumentoEmAtaque;
        this.aumentoEmVida = aumentoEmVida;
    }

    public Buff(String nome, int custoMana, int aumentoEmAtaque, int aumentoEmVida) {
        this(UUID.randomUUID(), nome, custoMana, aumentoEmAtaque, aumentoEmVida);
    }

    public int getAumentoEmAtaque() {
        return aumentoEmAtaque;
    }

    public void setAumentoEmAtaque(int aumentoEmAtaque) {
        this.aumentoEmAtaque = aumentoEmAtaque;
    }

    public int getAumentoEmVida() {
        return aumentoEmVida;
    }

    public void setAumentoEmVida(int aumentoEmVida) {
        this.aumentoEmVida = aumentoEmVida;
    }

    public void usar(Carta alvo) {        
        Lacaio lacaio = (Lacaio) alvo;
        
        lacaio.setAtaque(lacaio.getAtaque() + getAumentoEmAtaque());
        lacaio.setVidaAtual(lacaio.getVidaAtual() + getAumentoEmVida());
        lacaio.setVidaMaxima(lacaio.getVidaMaxima() + getAumentoEmVida());
    }

    @Override
    public String toString() {
        return String.format("%s [+%d/+%d]", super.toString(),
                getAumentoEmAtaque(), getAumentoEmVida());
    }
}
