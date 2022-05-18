package tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	//Criado uma matriz de peças.
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
	
	//Método vai na matriz de peças na linha e na coluna e atribui a peça que veio como argumento.
	public void lugarDaPeca(Peca peca, Posicao posicao) {
		pecas [posicao.getLinha()][posicao.getColuna()] = peca;
//Só e possivel acessar a posicão da peça diretamente pela referencia a baixo porque o atributo posição está como protected na classe "Peca"
//Que é do mesmo pacote.
		peca.posicao = posicao;
	}
}
