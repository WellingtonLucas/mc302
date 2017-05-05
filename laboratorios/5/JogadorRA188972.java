import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Jogador de Hearth Stone Basico.
 *
 * @author Lucas David -- lucasolivdavid@gmail.com
 */
public class JogadorRA188972 extends Jogador {

    protected int minhaMana, minhaVida, vidaOponente;
    protected List<CartaLacaio> lacaios;
    protected List<CartaLacaio> lacaiosOponente;
    protected List<CartaLacaio> lacaiosQuePodemAtacar;

    public JogadorRA188972(ArrayList<Carta> maoInicial, boolean primeiro) {
        primeiroJogador = primeiro;
        mao = maoInicial;

        System.out.println("*Classe JogadorRA188972* Sou o " + (primeiro ? "primeiro" : "segundo")
            + " jogador (classe: " + getClass() + ")");
        System.out.println("Mao inicial:");
        System.out.println(mao);
    }

    protected void perceberAmbiente(Mesa mesa, Carta cartaComprada, List<Jogada> jogadasOponente) {
        if (cartaComprada != null)
            mao.add(cartaComprada);

        if (primeiroJogador) {
            minhaMana = mesa.getManaJog1();
            minhaVida = mesa.getVidaHeroi1();
            lacaios = mesa.getLacaiosJog1();
            vidaOponente = mesa.getVidaHeroi2();
            lacaiosOponente = mesa.getLacaiosJog2();
        } else {
            minhaMana = mesa.getManaJog2();
            minhaVida = mesa.getVidaHeroi2();
            lacaios = mesa.getLacaiosJog2();
            vidaOponente = mesa.getVidaHeroi1();
            lacaiosOponente = mesa.getLacaiosJog1();
        }

        lacaiosQuePodemAtacar = new ArrayList<>(lacaios);
    }

    @Override
    public ArrayList<Jogada> processarTurno(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente) {
        perceberAmbiente(mesa, cartaComprada, jogadasOponente);

        ArrayList<Jogada> moves = new ArrayList<>();

        try {
            CartaMagia card = mao.stream()
                .filter(c -> c instanceof CartaMagia && c.getMana() <= this.minhaMana)
                .map(c -> (CartaMagia) c)
                // Nao selecione buffs se eu nao tenho lacaios em campo.
                .filter(c -> !lacaios.isEmpty() || c.getMagiaTipo() != TipoMagia.BUFF)
                .max(Comparator.comparingInt(CartaMagia::getMagiaDano))
                .get();

            mao.remove(card);

            // Ainda preciso definir um alvo se a magia for de buff.
            Carta target
                = card.getMagiaTipo() == TipoMagia.BUFF
                ? lacaios.stream()
                    .max(Comparator.comparingInt(CartaLacaio::getVidaAtual))
                    .get()
                : null;

            moves.add(new Jogada(TipoJogada.MAGIA, card, target));
            this.minhaMana -= card.getMana();
        } catch (NoSuchElementException ignored) {
        }

        try {
            CartaLacaio card = mao.stream()
                .filter(c -> c instanceof CartaLacaio && c.getMana() <= this.minhaMana)
                .map(c -> (CartaLacaio) c)
                .max(Comparator.comparingInt(CartaLacaio::getAtaque))
                .get();

            mao.remove(card);
            moves.add(new Jogada(TipoJogada.LACAIO, card, null));
        } catch (NoSuchElementException ignored) {
        }

        // Ataca com todos os lacaios.
        lacaios.forEach(l -> moves.add(new Jogada(TipoJogada.ATAQUE, l, null)));

        return moves;
    }
}
