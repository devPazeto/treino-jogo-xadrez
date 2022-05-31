package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

//Classe Peça de xadrez que herda da classe peça.
public abstract class PecaDeXadrez extends Peca{
	
	private Cor cor;
	private int contadorMovimento;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	public int getContadorMovimento() {
		return contadorMovimento;
	}
	
	
	public void adicionarMovimentoContador() {
		contadorMovimento ++;
	}
	public void retirarMovimentoContador() {
		contadorMovimento --;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
	   return PosicaoXadrez.paraPosicao(posicao);
	}

	
	protected boolean existePecaAdversaria(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
		
}
