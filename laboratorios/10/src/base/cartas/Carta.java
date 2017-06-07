package base.cartas;

import base.ILaMaSerializable;
import io.Escritor;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public abstract class Carta implements ILaMaSerializable {
    private UUID id;
    private String nome;
    private int custoMana;

    public Carta(UUID id, String nome, int custoMana) {
        this.id = id;
        this.nome = nome;
        this.custoMana = custoMana;
    }

    public Carta(String nome, int custoMana) {
        this(UUID.randomUUID(), nome, custoMana);
    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public void setCustoMana(int custoMana) {
        this.custoMana = custoMana;
    }

    public void usar(Carta alvo) {
        System.out.println("usar de carta nao foi implementado ainda");
    }


    @Override
    public String toString() {
        return "Carta [nome=" + nome + ", custoMana=" + custoMana + "]";
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Carta && toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(toString());
        return hash;
    }

    @Override
    public void escreveAtributos(Escritor fw) throws IOException {
        fw.escreveAtributo("id", id.toString());
        fw.escreveAtributo("nome", nome);
        fw.escreveAtributo("custoMana", String.valueOf(custoMana) );
    }
}
