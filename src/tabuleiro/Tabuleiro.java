package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	//Criado uma matriz de peças.
	private Peca[][] pecas;
	
	public Tabuleiro (int linhas, int colunas){
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro: É necessário ter pelo menos 1 linha e 1 coluna" );
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
			throw new TabuleiroException("Não existe essa posição no tabuleiro" );
		}
		return pecas [linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Não existe essa posição no tabuleiro" );
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	//Método vai na matriz de peças na linha e na coluna e atribui a peça que veio como argumento.
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		if (temUmaPeca(posicao)) {
			throw new TabuleiroException("já existe uma peça na posição " + posicao);
		}
		 pecas[posicao.getLinha()][posicao.getColuna()] = peca;
//Só e possivel acessar a posicão da peça diretamente pela referencia a baixo porque o atributo posição está como protected na classe "Peca"
//Que é do mesmo pacote.
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("já existe uma peça na posição ");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao=null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
//Método para testar se a posição existe.	
	public boolean posicaoExistente(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temUmaPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Não existe essa posição no tabuleiro" );
		}
		return peca(posicao) != null;
	}
}
