package aplicacao;

import xadrez.PartidaDeXadrez;

public class Programa {

	public static void main(String[] args) {
		//Testando impress�o do tabuleiro.
		PartidaDeXadrez partidadexadrez = new PartidaDeXadrez();
		UI.printTabuleiro(partidadexadrez.getPecas());
	}

}
