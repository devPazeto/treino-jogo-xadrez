package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	//Criado uma matriz de pe�as.
	private Peca[][] pecas;
	
	public Tabuleiro (int linhas, int colunas){
		this.linhas=linhas;
		this.colunas=colunas;
		// Instanciando a matriz com linhas e colunas
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		return pecas [linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	//M�todo vai na matriz de pe�as na linha e na coluna e atribui a pe�a que veio como argumento.
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		pecas [posicao.getLinha()][posicao.getColuna()] = peca;
//S� e possivel acessar a posic�o da pe�a diretamente pela referencia a baixo porque o atributo posi��o est� como protected na classe "Peca"
//Que � do mesmo pacote.
		peca.posicao = posicao;
	}
}
