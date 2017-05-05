import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Jogador de Hearth Stone melhorado.
 *
 * @author Lucas David -- lucasolivdavid@gmail.com
 */
public class JogadorRA188972New extends JogadorRA188972 {
    public JogadorRA188972New(ArrayList<Carta> maoInicial, boolean primeiro) {
        super(maoInicial, primeiro);
    }

    @Override
    public ArrayList<Jogada> processarTurno(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente) {
        perceberAmbiente(mesa, cartaComprada, jogadasOponente);

        ArrayList<Jogada> moves = new ArrayList<>();

        mao.stream()
            .filter(c -> c instanceof CartaLacaio && c.getMana() <= this.minhaMana)
            .map(c -> (CartaLacaio) c)
            .sorted(Comparator.comparingInt(CartaLacaio::getAtaque).reversed())
            .collect(Collectors.toList())
            .forEach(creature -> {
                if (lacaios.size() < 7 && this.minhaMana >= creature.getMana()) {
                    lacaios.add(creature);
                    mao.remove(creature);
                    moves.add(new Jogada(TipoJogada.LACAIO, creature, null));
                    this.minhaMana -= creature.getMana();
                }
            });

        mao.stream()
            .filter(c -> c instanceof CartaMagia && c.getMana() <= this.minhaMana)
            .map(c -> (CartaMagia) c)
            .filter(c -> c.getMagiaTipo() != TipoMagia.BUFF || !lacaios.isEmpty())
            .sorted(Comparator.comparingInt(CartaMagia::getMagiaDano).reversed())
            .limit(2)
            .collect(Collectors.toList())
            .forEach(magic -> {
                if (this.minhaMana >= magic.getMana()) {
                    Carta target
                        = magic.getMagiaTipo() == TipoMagia.BUFF
                        ? lacaios.stream()
                            .min(Comparator.comparingInt(CartaLacaio::getVidaAtual))
                            .get()
                        : null;

                    mao.remove(magic);
                    moves.add(new Jogada(TipoJogada.MAGIA, magic, target));
                    this.minhaMana -= magic.getMana();

                    if (magic.getMagiaTipo() == TipoMagia.AREA) {
                        lacaiosOponente.forEach(l -> l.setVidaAtual(l.getVidaAtual() - magic.getMagiaDano()));
                        lacaiosOponente = lacaiosOponente.stream()
                            .filter(l -> l.getVidaAtual() > 0)
                            .collect(Collectors.toList());
                    }

                }
            });

        lacaiosOponente.stream()
            .sorted(Comparator.comparingInt(CartaLacaio::getAtaque).reversed())
            .forEach(alvo -> {
                try {
                    CartaLacaio attacker = lacaiosQuePodemAtacar.stream()
                        .filter(a -> a.getAtaque() >= alvo.getVidaAtual())
                        .min(Comparator.comparingInt(CartaLacaio::getAtaque))
                        .get();

                    lacaiosQuePodemAtacar.remove(attacker);
                    moves.add(new Jogada(TipoJogada.ATAQUE, attacker, alvo));
                } catch (NoSuchElementException ignored) {
                }
            });

        lacaiosQuePodemAtacar.forEach(l -> moves.add(new Jogada(TipoJogada.ATAQUE, l, null)));

        if (this.minhaMana >= 2) {
            moves.add(new Jogada(TipoJogada.PODER, null, null));
        }

        return moves;
    }
}
