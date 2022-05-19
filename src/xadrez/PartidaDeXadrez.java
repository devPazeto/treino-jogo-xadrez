package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	// Método para a matriz percorrer o tabuleiro e fazer um downcast de "peças" para peças de xadrez.
	//O programa está sendo feito em camadas por isso não tera acesso a matriz de peças, somente de "Peças de xadrez".
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez [][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for(int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoXadrez(coluna, linha).naPosicao());
	}
	//Método vai iniciar a partida posicionando as peças no tabuleiro.
	public void setupInicial() {
		colocaNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
        colocaNovaPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

        colocaNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));

		
	}
}
