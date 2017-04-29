import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Lucas David -- lucasolivdavid@gmail.com
 */
public class JogadorRA188972New extends JogadorRA188972 {
    public JogadorRA188972New(ArrayList<Carta> maoInicial, boolean primeiro) {
        super(maoInicial, primeiro);
    }

    @Override
    public ArrayList<Jogada> processarTurno(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente) {
        perceberAmbiente(mesa, cartaComprada, jogadasOponente);

        ArrayList<Jogada> jogadas = new ArrayList<>();

        List<CartaMagia> cards = mao.stream()
            .filter(c -> c instanceof CartaMagia && c.getMana() <= minhaMana)
            .map(c -> (CartaMagia) c)
            .filter(c -> c.getMagiaTipo() != TipoMagia.BUFF || !lacaios.isEmpty())
            .sorted((c1, c2) -> c2.getMagiaDano() - c1.getMagiaDano())
            .collect(Collectors.toList());

        cards.forEach(c -> jogadas.add(new Jogada(TipoJogada.MAGIA, c, null)));
        mao.removeAll(cards);
        minhaMana -= cards.stream().mapToInt(Carta::getMana).sum();

        List<CartaLacaio> lacaios = new ArrayList<>(this.lacaios);

        try {
            CartaLacaio cartaLacaio = mao.stream()
                .filter(c -> c instanceof CartaLacaio && c.getMana() <= minhaMana)
                .map(c -> (CartaLacaio) c)
                .sorted((c1, c2) -> c2.getAtaque() - c1.getAtaque())
                .findFirst()
                .get();

            mao.remove(cartaLacaio);

            jogadas.add(new Jogada(TipoJogada.LACAIO, cartaLacaio, null));
            minhaMana -= cartaLacaio.getMana();
        } catch (NoSuchElementException ignored) {
        }

        lacaiosOponente.stream()
            .sorted((l1, l2) -> l2.getAtaque() - l1.getAtaque())
            .forEach(alvo -> {
                try {
                    CartaLacaio atacante = lacaios.stream()
                        .filter(a -> a.getAtaque() >= alvo.getVidaAtual())
                        .sorted(Comparator.comparingInt(CartaLacaio::getAtaque))
                        .findFirst()
                        .get();

                    lacaios.remove(atacante);
                    jogadas.add(new Jogada(TipoJogada.ATAQUE, atacante, alvo));
                } catch (NoSuchElementException ex) {
                    // Nenhum de meus lacaios consegue matar esse alvo sozinho.
                    // Talvez um grupo de lacaios consiga?
                    List<CartaLacaio> lacaiosOrdenadosPorAtaque = lacaios.stream()
                        .sorted(Comparator.comparingInt(CartaLacaio::getAtaque))
                        .collect(Collectors.toList());

                    List<CartaLacaio> atacantesDesteAlvo = new ArrayList<>();

                    int ataqueDoGrupo = 0;
                    while (ataqueDoGrupo < alvo.getVidaAtual()
                        && !lacaiosOrdenadosPorAtaque.isEmpty()) {
                        atacantesDesteAlvo.add(lacaiosOrdenadosPorAtaque.remove(0));
                        ataqueDoGrupo = atacantesDesteAlvo.stream()
                            .mapToInt(CartaLacaio::getAtaque)
                            .sum();
                    }

                    if (ataqueDoGrupo >= alvo.getVidaAtual()) {
                        // Esta combinacao de lacaios consegue matar o alvo. Cria as jogadas
                        // e remove estes lacaios da lista de lacaios que atacara o heroi inimigo.
                        lacaios.removeAll(atacantesDesteAlvo);
                        atacantesDesteAlvo.forEach(l -> jogadas.add(new Jogada(TipoJogada.ATAQUE, l, alvo)));
                    }
                }
            });

        // Ataca o heroi inimigo com todos os lacaios restantes.
        lacaios.forEach(l -> jogadas.add(new Jogada(TipoJogada.ATAQUE, l, null)));

        if (minhaMana >= 2) {
            jogadas.add(new Jogada(TipoJogada.PODER, null, null));
        }

        return jogadas;
    }
}
