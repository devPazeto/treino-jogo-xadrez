package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

//Classe Pe�a de xadrez que herda da classe pe�a.
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
