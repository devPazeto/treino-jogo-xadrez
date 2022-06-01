package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        
        if (getCor() == Cor.WHITE) {
        	p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        	if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
        		mat[p.getLinha()][p.getColuna()] = true;
        	}
        	p.setValores(posicao.getLinha() - 2, posicao.getColuna());
        	Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
        	if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorMovimento() == 0) {
        		mat[p.getLinha()][p.getColuna()] = true;
        	}
        	
           	p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
           	if(getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
           		mat[p.getLinha()][p.getColuna()] = true;
            	}
           	p.setValores(posicao.getLinha() - 1, posicao.getColuna() +1);
           	if(getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
           		mat[p.getLinha()][p.getColuna()] = true;
           	}
           	
           	//#Movimento Especial en passant white
           	if (posicao.getLinha() == 3) {
           		Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
           		if (getTabuleiro().posicaoExistente(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulnerabilidadeEnPassant()) {
           			mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
          		}
           		Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
           		if (getTabuleiro().posicaoExistente(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulnerabilidadeEnPassant()) {
           			mat[direita.getLinha() - 1][direita.getColuna()] = true;
          		}
           	}
        }
        else {
           	p.setValores(posicao.getLinha() + 1, posicao.getColuna());
           	if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p)) {
          		mat[p.getLinha()][p.getColuna()] = true;
           	}
           	p.setValores(posicao.getLinha() + 2, posicao.getColuna());
           	Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
           	if(getTabuleiro().posicaoExistente(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().temUmaPeca(p2) && getContadorMovimento() == 0) {
           		mat[p.getLinha()][p.getColuna()] = true;
           	}
           	p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
          	if(getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
           		mat[p.getLinha()][p.getColuna()] = true;
               	}
          	p.setValores(posicao.getLinha() + 1, posicao.getColuna() +1);
          	if(getTabuleiro().posicaoExistente(p) && existePecaAdversaria(p)) {
           		mat[p.getLinha()][p.getColuna()] = true;
           		}
          	
          //#Movimento Especial en passant black
           	if (posicao.getLinha() == 4) {
           		Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
           		if (getTabuleiro().posicaoExistente(esquerda) && existePecaAdversaria(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getVulnerabilidadeEnPassant()) {
           			mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
          		}
           		Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
           		if (getTabuleiro().posicaoExistente(direita) && existePecaAdversaria(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getVulnerabilidadeEnPassant()) {
           			mat[direita.getLinha() + 1][direita.getColuna()] = true;
          		}
           	}
        }
        return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

	
}
