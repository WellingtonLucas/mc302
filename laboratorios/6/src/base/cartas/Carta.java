package base.cartas;

import java.util.Objects;
import java.util.UUID;

public class Carta {

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

    static void MeuMetodoEstatico() {
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
        return this == o || o instanceof Carta && id.equals(((Carta) o).id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(id);
        return hash;
    }
}
