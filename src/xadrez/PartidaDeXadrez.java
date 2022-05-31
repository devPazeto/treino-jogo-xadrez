package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean [][] possiveisMovimentos (PosicaoXadrez posicaoInicial){
		Posicao posicao = posicaoInicial.naPosicao();
		validarPosicaoInicial(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaDeXadrez executaMovimento(PosicaoXadrez posicaoInicial, PosicaoXadrez posicaoFinal) {
		Posicao posicaoOrigem = posicaoInicial.naPosicao();
		Posicao posicaoDestino = posicaoFinal.naPosicao();
		validarPosicaoInicial(posicaoOrigem);
		validarPosicaoFinal(posicaoOrigem, posicaoDestino);
		Peca pecaCapturada = mover(posicaoOrigem, posicaoDestino);
		
		if (checagemTeste(jogadorAtual)) {
			desfazerMovimento(posicaoOrigem, posicaoDestino, pecaCapturada);
			throw new XadrezException("Você não pode se colocar em check");
		}
		
		check = (checagemTeste(oponente(jogadorAtual))) ? true : false;

		if(checagemMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
		proximoTurno();
		}
		
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca mover(Posicao posicaoOrigem, Posicao posicaoDestino) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(posicaoOrigem);
		p.adicionarMovimentoContador();
		Peca pecaCapturada = tabuleiro.removerPeca(posicaoDestino);
		tabuleiro.lugarDaPeca(p, posicaoDestino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
		
	}
	
	private void desfazerMovimento(Posicao posicaoOrigem, Posicao posicaoDestino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(posicaoDestino);
		p.retirarMovimentoContador();
		tabuleiro.lugarDaPeca(p, posicaoOrigem);
		
		if (pecaCapturada != null) {
			tabuleiro.lugarDaPeca(pecaCapturada, posicaoDestino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	private void validarPosicaoInicial(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Não existe peça na posicao de origem ");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("Essa peça não é sua ");
		}
		if(!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new XadrezException("Não existe movimentos possiveis para peça escolhida ");
		}
	}
	
	private void validarPosicaoFinal(Posicao posicaoOrigem, Posicao posicaoDestino) {
		if(!tabuleiro.peca(posicaoOrigem).possiveisMovimentos(posicaoDestino)){
			throw new XadrezException("A peça escolhida não pode mover para posição de destino ");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
		
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private PecaDeXadrez Rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaDeXadrez)x).getCor()==cor).collect(Collectors.toList());
	    for (Peca p : list) {
	    	if(p instanceof Rei) {
	    		return (PecaDeXadrez)p;
	    	}
	    }
	    throw new IllegalStateException("Não existe " + cor + "Rei no tabuleiro");
	}
	
	private boolean checagemTeste(Cor cor) {
		Posicao posicaoRei = Rei(cor).getPosicaoXadrez().naPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x ->((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.possiveisMovimentos();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checagemMate(Cor cor) {
		if (!checagemTeste(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaDeXadrez)x).getCor()==cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao posicaoOrigem = ((PecaDeXadrez)p).getPosicaoXadrez().naPosicao();
						Posicao posicaoDestino = new Posicao(i, j);
						Peca pecaCapturada = mover(posicaoOrigem, posicaoDestino);
						boolean checagemTeste = checagemTeste(cor);
						desfazerMovimento(posicaoOrigem, posicaoDestino, pecaCapturada);
						if(!checagemTeste) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoXadrez(coluna, linha).naPosicao());
	    pecasNoTabuleiro.add(peca);
	}
	//Método vai iniciar a partida posicionando as peças no tabuleiro.
	public void setupInicial() {
	
		colocaNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
		colocaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.WHITE));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.WHITE));
		colocaNovaPeca('f', 1, new Bispo(tabuleiro,Cor.WHITE));
		colocaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
		colocaNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
		colocaNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
		colocaNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
		colocaNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
		colocaNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
		colocaNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
        colocaNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        colocaNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        colocaNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));

        colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
        colocaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        colocaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.BLACK));
        colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.BLACK));
        colocaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        colocaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
        colocaNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        colocaNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        colocaNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));
	}

	public boolean getCheckMate() {
		return checkMate;
	}
		
}
