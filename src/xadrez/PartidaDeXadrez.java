package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	
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
	
	
	// M�todo para a matriz percorrer o tabuleiro e fazer um downcast de "pe�as" para pe�as de xadrez.
	//O programa est� sendo feito em camadas por isso n�o tera acesso a matriz de pe�as, somente de "Pe�as de xadrez".
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
		proximoTurno();
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca mover(Posicao posicaoOrigem, Posicao posicaoDestino) {
		Peca p = tabuleiro.removerPeca(posicaoOrigem);
		Peca pecaCapturada = tabuleiro.removerPeca(posicaoDestino);
		tabuleiro.lugarDaPeca(p, posicaoDestino);
		return pecaCapturada;
		
	}
	private void validarPosicaoInicial(Posicao posicao) {
		if(!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("N�o existe pe�a na posicao de origem ");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("Essa pe�a n�o � sua ");
		}
		if(!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new XadrezException("N�o existe movimentos possiveis para pe�a escolhida ");
		}
	}
	
	private void validarPosicaoFinal(Posicao posicaoOrigem, Posicao posicaoDestino) {
		if(!tabuleiro.peca(posicaoOrigem).possiveisMovimentos(posicaoDestino)){
			throw new XadrezException("A pe�a escolhida n�o pode mover para posi��o de destino ");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
		
	}
	private void colocaNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoXadrez(coluna, linha).naPosicao());
	}
	//M�todo vai iniciar a partida posicionando as pe�as no tabuleiro.
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
