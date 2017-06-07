import base.ILaMaSerializable;
import base.cartas.TipoCarta;
import io.Escritor;
import io.Leitor;
import util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random gerador = new Random();
		List<ILaMaSerializable> l = new ArrayList<>();
		l.add(Util.geraCartaAleatoria(gerador, 2, 10, 10, TipoCarta.LACAIO));
		l.add(Util.geraCartaAleatoria(gerador, 2, 10, 10, TipoCarta.BUFF));
		l.add(Util.geraCartaAleatoria(gerador, 2, 10, 10, TipoCarta.DANO));
		l.add(Util.geraCartaAleatoria(gerador, 2, 10, 10, TipoCarta.DANO_AREA));
		try {
			Escritor w = new Escritor("saida");
			for (ILaMaSerializable ilm : l){
				w.escreveDelimObj(ilm.getClass().getSimpleName());
				ilm.escreveAtributos(w);
				w.escreveDelimObj(ilm.getClass().getSimpleName());
			}
			w.finalizar();
		} catch (IOException e) {
			e.printStackTrace();
		}

        try {
            Leitor lt = new Leitor("saida");
            l = lt.leObjetos();
            System.out.println(l);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
