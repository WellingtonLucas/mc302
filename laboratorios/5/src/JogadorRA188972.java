import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucas David -- lucasolivdavid@gmail.com
 */
public class JogadorRA188972 extends Jogador {

    protected int minhaMana, minhaVida, vidaOponente;
    protected List<CartaLacaio> lacaios;
    protected List<CartaLacaio> lacaiosOponente;

    /**
     * @param maoInicial Contém a mão inicial do jogador. Deve conter o número de cartas correto dependendo se esta classe Jogador que está sendo construída é o primeiro ou o segundo jogador da partida.
     * @param primeiro   Informa se esta classe Jogador que está sendo construída é o primeiro jogador a iniciar nesta jogada (true) ou se é o segundo jogador (false).
     */
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
    }

    @Override
    public ArrayList<Jogada> processarTurno(Mesa mesa, Carta cartaComprada, ArrayList<Jogada> jogadasOponente) {
        perceberAmbiente(mesa, cartaComprada, jogadasOponente);

        ArrayList<Jogada> jogadas = new ArrayList<>();

        final int manaParaJogarMagias = minhaMana;
        CartaMagia cartaMagia = mao.stream()
            .filter(c -> c instanceof CartaMagia && c.getMana() <= manaParaJogarMagias)
            .map(c -> (CartaMagia) c)
            // Nao selecione buffs se eu nao tenho lacaios em campo.
            .filter(c -> !lacaios.isEmpty() || c.getMagiaTipo() != TipoMagia.BUFF)
            // Organiza em ordem decrescente pelo dano/buff.
            .sorted((c1, c2) -> c2.getMagiaDano() - c1.getMagiaDano())
            .findFirst()
            .orElse(null);

        final int manaParaBaixarLacaios;
        if (cartaMagia == null) {
            // Nao vou jogar nenhuma magia.
            manaParaBaixarLacaios = minhaMana;
        } else {
            mao.remove(cartaMagia);

            // Ainda preciso definir um alvo se a magia for de buff.
            Carta cartaAlvo
                = cartaMagia.getMagiaTipo() == TipoMagia.BUFF
                ? lacaios.stream().findAny().get()
                : null;

            jogadas.add(new Jogada(TipoJogada.MAGIA, cartaMagia, cartaAlvo));
            System.out.println("Jogando magia na mesa: " + cartaMagia);
            manaParaBaixarLacaios = minhaMana - cartaMagia.getMana();
        }

        CartaLacaio cartaLacaio = mao.stream()
            .filter(c -> c instanceof CartaLacaio && c.getMana() <= manaParaBaixarLacaios)
            .map(c -> (CartaLacaio) c)
            // Organiza em ordem decrescente pelo ataque.
            .sorted((c1, c2) -> c2.getAtaque() - c1.getAtaque())
            .findFirst()
            .orElse(null);

        if (cartaLacaio != null) {
            mao.remove(cartaLacaio);

            jogadas.add(new Jogada(TipoJogada.LACAIO, cartaLacaio, null));
            System.out.println("Jogando lacaio na mesa: " + cartaLacaio);
        }

        final int manaParaPoder
            = cartaLacaio == null
            ? manaParaBaixarLacaios
            : manaParaBaixarLacaios - cartaLacaio.getMana();

        if (manaParaPoder >= 2) {
            jogadas.add(new Jogada(TipoJogada.PODER, null, null));
        }

        // Ataca com todos os lacaios.
        lacaios.forEach(l -> jogadas.add(new Jogada(TipoJogada.ATAQUE, l, null)));

        return jogadas;
    }
}
