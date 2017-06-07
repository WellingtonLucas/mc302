package io;

import base.ILaMaSerializable;
import base.cartas.HabilidadesLacaio;
import base.cartas.Lacaio;
import base.cartas.magias.Buff;
import base.cartas.magias.Dano;
import base.cartas.magias.DanoArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by nael on 5/23/17.
 */
public class Leitor {
    File f;

    Leitor() {
        f = null;
    }

    public Leitor(String nomeArquivo) throws FileNotFoundException {
        f = new File(nomeArquivo);
    }

    private String leAtributo(Scanner entrada) {
        entrada.next();
        return entrada.next().trim();
    }

    public List<ILaMaSerializable> leObjetos() throws FileNotFoundException {
        Scanner entrada = new Scanner(f);
        String atributo = "";
        String carta = "";
        UUID id;
        String nome = "";
        int custoMana = -1;
        int ataque;
        int vidaAtual;
        int vidaMaxima;
        int dano;
        int aumentoEmAtaque;
        int aumentoEmVida;
        HabilidadesLacaio habilidade;
        List<ILaMaSerializable> l = new ArrayList<>();

        boolean obj = false;
        while (entrada.hasNext()) {
            atributo = entrada.next();
            switch (atributo) {
                case "obj":
                    if (obj) {
                        entrada.nextLine();
                    }
                    else {
                        carta = entrada.next();
                        switch (carta) {
                            case "Lacaio":
                                id = UUID.fromString(leAtributo(entrada));
                                nome = leAtributo(entrada);
                                custoMana = Integer.valueOf(leAtributo(entrada));
                                ataque = Integer.valueOf(leAtributo(entrada));
                                vidaAtual = Integer.valueOf(leAtributo(entrada));
                                vidaMaxima = Integer.valueOf(leAtributo(entrada));
                                habilidade = HabilidadesLacaio.valueOf(leAtributo(entrada));
                                l.add(new Lacaio(id, nome, custoMana, ataque, vidaAtual, vidaMaxima, habilidade));
                                break;
                            case "Buff":
                                id = UUID.fromString(leAtributo(entrada));
                                nome = leAtributo(entrada);
                                custoMana = Integer.valueOf(leAtributo(entrada));
                                aumentoEmAtaque = Integer.valueOf(leAtributo(entrada));
                                aumentoEmVida = Integer.valueOf(leAtributo(entrada));
                                l.add(new Buff(id, nome, custoMana, aumentoEmAtaque, aumentoEmVida));
                                break;
                            case "Dano":
                            case "DanoArea":
                                id = UUID.fromString(leAtributo(entrada));
                                nome = leAtributo(entrada);
                                custoMana = Integer.valueOf(leAtributo(entrada));
                                dano = Integer.valueOf(leAtributo(entrada));
                                if(carta.equals("Dano"))
                                    l.add(new Dano(id, nome, custoMana, dano));
                                else
                                    l.add(new DanoArea(id, nome, custoMana, dano));
                                break;
                            default:
                                break;
                        }
                    }
                    obj = !obj;
                    break;
            }
        }
        return l;
    }
}
