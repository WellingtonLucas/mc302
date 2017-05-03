package base.cartas;

import java.util.UUID;

public class Lacaio extends Carta {

    private int ataque;
    private int vidaAtual;
    private int vidaMaxima;

    public Lacaio(String nome, int custoMana, int ataque, int vidaAtual, int vidaMaxima) {
        this(UUID.randomUUID(), nome, custoMana, ataque, vidaAtual, vidaMaxima);
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

    public void usar(Carta alvo) {
        Lacaio lacaio = (Lacaio) alvo;
        lacaio.setVidaAtual(lacaio.getVidaAtual() - getAtaque());
    }

    @Override
    public String toString() {
        return String.format("%s [%d/%d]", super.toString(), getAtaque(), getVidaAtual());
    }
}
