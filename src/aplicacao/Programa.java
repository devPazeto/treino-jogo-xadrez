package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while(true) {
			try {
				UI.limpaTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas());
				System.out.println();
				System.out.println("Posicao de origem: ");
				PosicaoXadrez posicaoOrigem = UI.lerPosicaoUsuario(sc);
				
				System.out.println();
				System.out.println("Posicao de destino: ");
				PosicaoXadrez posicaoDestino = UI.lerPosicaoUsuario(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMovimento(posicaoOrigem, posicaoDestino);
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}


