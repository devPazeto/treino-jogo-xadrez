package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

//Classe Peça de xadrez que herda da classe peça.
public class PecaDeXadrez extends Peca{
	
	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

		
}
