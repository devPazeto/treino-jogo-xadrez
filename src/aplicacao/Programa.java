package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.println("Posição de origem ");
			PosicaoXadrez posicaoOrigem = UI.lerPosicaoUsuario(sc);
			
			System.out.println();
			System.out.println("Posição de destino ");
			PosicaoXadrez posicaoDestino = UI.lerPosicaoUsuario(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMovimento(posicaoOrigem, posicaoDestino);
		}
		
		
	}

}
