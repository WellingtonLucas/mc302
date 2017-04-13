import base.Baralho;
import base.cartas.Carta;
import base.cartas.Lacaio;
import base.cartas.magias.Buff;

public class Main {

    public static void main(String[] args) {
        Carta a = new Carta("Frodo Bolseiro", 2);
        Lacaio b = new Lacaio("Frodo Bolseiro", 2, 1, 2, 3);
        Lacaio c = new Lacaio("Sam", 1, 2, 2, 2);
        Lacaio d = new Lacaio("Sauron", 5, 5, 7, 7);
        Carta e = new Buff("magic-wand", 2, 2, 0);
        Baralho f = new Baralho();

        f.add(a);
        f.add(b);
        f.add(e);

        System.out.println(b);
        e.usar(b);
        System.out.println(b);

        System.out.println(d);
        c.usar(d);
        System.out.println(d);
    }
}
