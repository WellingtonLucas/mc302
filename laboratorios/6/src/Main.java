import base.cartas.*;
import base.cartas.magias.*;
import util.HearthStoneUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final int CARDS_COUNT = 50000;
    private static final Random random = new Random(13);

    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println("Equity Test");
        testCardEquity();
        System.out.println();

        Collection<Collection<Carta>> collections = Arrays.asList(
            new LinkedList<>(),
            new ArrayList<>(),
            new Vector<>(),
            new TreeSet<>(Comparator.comparing(Carta::getId)),
            new HashSet<>());

        System.out.println("Collections Accepts Card Repetitions Test");
        for (Collection<Carta> c : collections) {
            testCollectionAcceptsRepetitions(c);
            c.clear();
        }
        System.out.println();

        System.out.println("Collections Performance Test");
        for (Collection<Carta> c : collections) {
            testCollectionPerformance(c);
            c.clear();
        }
        System.out.println();

        System.out.println("Stream API Test");
        for (Collection<Carta> c : collections) {
            testCollectionStream(c);
            c.clear();
        }
    }

    private static void testCardEquity() {
        Carta a = new Lacaio("Wyvern", 2, 1, 2, 3);
        Carta b = new Lacaio("High Dragon", 4, 4, 2, 3);
        Carta c = new Dano("Great Flood", 2, 3);

        if (a == a) System.out.println("a == a");
        if (a.equals(a)) System.out.println("a.equals(a)");

        if (a != b) System.out.println("a != b");
        if (!a.equals(b)) System.out.println("!a.equals(b)");

        if (a != new Lacaio("Wyvern", 2, 1, 2, 3))
            System.out.println("a == new Lacaio(...)");

        if (a.equals(new Lacaio("Wyvern", 2, 1, 2, 3)))
            System.out.println("a.equals(new Lacaio(...))");

        if (!b.equals(c)) System.out.println("!b.equals(c)");
    }

    private static void testCollectionAcceptsRepetitions(Collection<Carta> cards) {
        Carta card = new Lacaio("Wyvern", 2, 1, 2, 3);
        Carta copiedCard = new Carta(card.getId(), card.getNome(), card.getCustoMana());

        cards.add(card);
        boolean accepts = cards.add(copiedCard);

        System.out.println(String.format("  * `%s` %s card repetitions",
            cards.getClass().getSimpleName(), accepts ? "accepts" : "does not accept"));
    }

    private static void testCollectionPerformance(Collection<Carta> cards) {
        long elapsed;

        for (int i = 0; i < CARDS_COUNT; i++) {
            cards.add(HearthStoneUtils.randomCard(8, 7, 7, random));
        }

        elapsed = 0;
        if (cards instanceof List) {
            List cardsAsList = (List) cards;
            for (int i = 0; i < CARDS_COUNT; i++) {
                long _s = System.nanoTime();
                cardsAsList.get(i);
                elapsed += (System.nanoTime() - _s);
            }
            report(cards.getClass().getSimpleName() + "#get(int index)", elapsed);
        }

        elapsed = 0;
        for (Carta c : cards) {
            long _s = System.nanoTime();
            cards.contains(c);
            elapsed += (System.nanoTime() - _s);
        }
        report(cards.getClass().getSimpleName() + "#contains(Object o)", elapsed);
    }

    private static void testCollectionStream(Collection<Carta> cards) {
        cards.add(new Lacaio("Vindictive Ghost", 3, 5, 2, 2));
        cards.add(new Lacaio("Goliast", 1, 2, 1, 1));
        cards.add(new Lacaio("Wyvern", 2, 1, 2, 2));
        cards.add(new Lacaio("High Dragon", 4, 4, 4, 4));
        cards.add(new Dano("Great Flood", 2, 3));
        cards.add(new Dano("Backstabbing", 1, 2));
        cards.add(new DanoArea("Children with Rocks", 3, 3));
        cards.add(new DanoArea("Cataclysm", 4, 5));

        long s = System.nanoTime();

        System.out.println("  * Most dangerous minion: "
            + cards.stream()
            .filter(c -> c instanceof Lacaio)
            .map(c -> (Lacaio) c)
            .sorted((l1, l2) -> l2.getAtaque() - l1.getAtaque())
            .findFirst()
            .orElse(null));

        System.out.println("    Group attack power: "
            + cards.stream()
            .filter(c -> c instanceof Lacaio)
            .map(c -> (Lacaio) c)
            .mapToInt(Lacaio::getAtaque)
            .sum());

        System.out.println("    Minions ordered by their life points: "
            + cards.stream()
            .filter(c -> c instanceof Lacaio)
            .sorted(Comparator.comparingInt(c -> ((Lacaio) c).getVidaAtual()))
            .collect(Collectors.toList()));

        report(cards.getClass().getSimpleName() + "#stream()...", System.nanoTime() - s);
    }

    private static void report(String operation, long elapsed) {
        System.out.println(String.format("  * `%s` took %f s", operation, (double) elapsed / 1000000000));
    }
}
