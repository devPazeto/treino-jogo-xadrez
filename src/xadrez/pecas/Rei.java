package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rei extends PecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		}

	@Override
	//O toString vai retornar o "R" de Rei na posição do tabuleiro.
	public String toString() {
		return "R";
	}
}
