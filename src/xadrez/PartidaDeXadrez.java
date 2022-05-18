package xadrez;

import tabuleiro.Posicao;
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
	//Método vai iniciar a partida posicionando as peças no tabuleiro.
	public void setupInicial() {
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(0, 0));
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 0));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(0, 4));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(7, 4));
	}
}
