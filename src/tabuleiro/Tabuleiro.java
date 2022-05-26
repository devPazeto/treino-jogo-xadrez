package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	//Criado uma matriz de pe�as.
	private Peca[][] pecas;
	
	public Tabuleiro (int linhas, int colunas){
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro: � necess�rio ter pelo menos 1 linha e 1 coluna" );
		}
		this.linhas=linhas;
		this.colunas=colunas;
		// Instanciando a matriz com linhas e colunas
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new TabuleiroException("N�o existe essa posi��o no tabuleiro" );
		}
		return pecas [linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("N�o existe essa posi��o no tabuleiro" );
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	//M�todo vai na matriz de pe�as na linha e na coluna e atribui a pe�a que veio como argumento.
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) {
			throw new TabuleiroException("j� existe uma pe�a na posi��o " + posicao);
		}
		 pecas [posicao.getLinha()][posicao.getColuna()] = peca;
//S� e possivel acessar a posic�o da pe�a diretamente pela referencia a baixo porque o atributo posi��o est� como protected na classe "Peca"
//Que � do mesmo pacote.
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("j� existe uma pe�a na posi��o ");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao=null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
//M�todo para testar se a posi��o existe.	
	public boolean posicaoExistente(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temUmaPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("N�o existe essa posi��o no tabuleiro" );
		}
		return peca(posicao) != null;
	}
}
