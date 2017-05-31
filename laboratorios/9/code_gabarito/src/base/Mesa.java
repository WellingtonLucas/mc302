package base;

import java.util.ArrayList;

import base.cartas.Carta;

/**
 * Classe para armazenar informações do estado atual da partida.
 * 
 * @author mc302
 *
 */
public class Mesa {
	// cartas nas mãos dos jogadores
	private ArrayList<Carta> maoP;
	private ArrayList<Carta> maoS;
	// lacaios baixados dos jogadores
	private ArrayList<Carta> lacaiosP;
	private ArrayList<Carta> lacaiosS;
	// poder heróico dos jogadores
	private int poderHeroiP;
	private int poderHeroiS;
	// mana disponível dos jogadores
	private int manaP;
	private int manaS;

	public Mesa() {
		maoP = new ArrayList<>();
		maoS = new ArrayList<>();
		lacaiosP = new ArrayList<>();
		lacaiosS = new ArrayList<>();
		poderHeroiP = poderHeroiS = util.Util.PODER_HEROI;
		manaP = manaS = util.Util.MANA_INI;
	}

	public Mesa(ArrayList<Carta> maoP, ArrayList<Carta> maoS, ArrayList<Carta> lacaiosP, ArrayList<Carta> lacaiosS,
			int poderHeroiP, int poderHeroiS, int manaP, int manaS) {
		this.maoP = maoP;
		this.maoS = maoS;
		this.lacaiosP = lacaiosP;
		this.lacaiosS = lacaiosS;
		this.poderHeroiP = poderHeroiP;
		this.poderHeroiS = poderHeroiS;
		this.manaP = manaP;
		this.manaS = manaS;
	}

	public ArrayList<Carta> getMaoP() {
		return maoP;
	}

	public void setMaoP(ArrayList<Carta> maoP) {
		this.maoP = maoP;
	}

	public ArrayList<Carta> getMaoS() {
		return maoS;
	}

	public void setMaoS(ArrayList<Carta> maoS) {
		this.maoS = maoS;
	}

	public ArrayList<Carta> getLacaiosP() {
		return lacaiosP;
	}

	public void setLacaiosP(ArrayList<Carta> lacaiosP) {
		this.lacaiosP = lacaiosP;
	}

	public ArrayList<Carta> getLacaiosS() {
		return lacaiosS;
	}

	public void setLacaiosS(ArrayList<Carta> lacaiosS) {
		this.lacaiosS = lacaiosS;
	}

	public int getPoderHeroiP() {
		return poderHeroiP;
	}

	public void setPoderHeroiP(int poderHeroiP) {
		this.poderHeroiP = poderHeroiP;
	}

	/**
	 * Decrementa o poder heróico do respectivo jogador.
	 * 
	 * @param dec
	 *            valor do decremento
	 * @param autor
	 *            em qual jogador deve ser decrementado o poder heróico
	 */
	public void decPoderHeroi(int dec, char autor) {
		if (autor == 'P') {
			int val = this.poderHeroiP - dec;
			this.poderHeroiP = (val < 0) ? 0 : val;
		} else {
			int val = this.poderHeroiS - dec;
			this.poderHeroiS = (val < 0) ? 0 : val;
		}
	}

	public int getPoderHeroiS() {
		return poderHeroiS;
	}

	public void setPoderHeroiS(int poderHeroiS) {
		this.poderHeroiS = poderHeroiS;
	}

	public int getManaP() {
		return manaP;
	}

	public void setManaP(int manaP) {
		this.manaP = manaP;
	}

	/**
	 * Decrementa a mana do respectivo jogador
	 * 
	 * @param dec
	 *            valor do decremento
	 * @param autor
	 *            em qual jogador deve ser decrementada a mana
	 */
	public void decMana(int dec, char autor) {
		if (autor == 'P') {
			int val = this.manaP - dec;
			this.manaP = (val < 0) ? 0 : val;
		} else {
			int val = this.manaS - dec;
			this.manaS = (val < 0) ? 0 : val;
		}
	}

	public int getManaS() {
		return manaS;
	}

	public void setManaS(int manaS) {
		this.manaS = manaS;
	}

	/**
	 * Saca uma carta do início da mão do respectivo jogador
	 * 
	 * @param autor
	 *            jogador
	 * @return carta sacada
	 */
	public Carta sacarCarta(char autor) {
		if (autor == 'P') {
			if (!maoP.isEmpty())
				return maoP.remove(0);
		} else {
			if (!maoS.isEmpty())
				return maoS.remove(0);
		}
		return null;
	}

	public int getMana(char autor) {
		return (autor == 'P') ? getManaP() : getManaS();
	}

	public void sacarCarta(Carta dano, char autor) {
		if (autor == 'P') {
			if (!maoP.isEmpty())
				maoP.remove(dano);
		} else {
			if (!maoS.isEmpty())
				maoS.remove(dano);
		}

	}

}
