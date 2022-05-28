package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

	public char coluna;
	public int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
	    if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
	    	throw new XadrezException("Erro ao instanciar a posição no xadrez. Posições válidas de a1 ate h8 ");
	    }
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	public Posicao naPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez paraPosicao(Posicao posicao) {
		return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
	
}


