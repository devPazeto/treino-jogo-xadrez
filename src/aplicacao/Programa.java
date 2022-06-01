package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturado = new ArrayList<>();
		
		while(!partidaDeXadrez.getCheckMate()) {
			try {	
				UI.limpaTela();
				UI.printPartida(partidaDeXadrez, capturado);
				System.out.println();
				System.out.print("Posição de origem: ");
				PosicaoXadrez posicaoOrigem = UI.lerPosicaoUsuario(sc);
				
				boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(posicaoOrigem);
				UI.limpaTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
				System.out.println();
				System.out.println("Posição de destino: ");
				PosicaoXadrez posicaoDestino = UI.lerPosicaoUsuario(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.executaMovimento(posicaoOrigem, posicaoDestino);
				
				if(pecaCapturada != null) {
					capturado.add(pecaCapturada);
				}
				
				if (partidaDeXadrez.getPromocao() != null) {
					System.out.print("Entre com a peça para promoção (B/C/Q/T): ");
					String type = sc.nextLine();
					partidaDeXadrez.trocaPecaPromocao(type);
				}
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
		UI.limpaTela();
		UI.printPartida(partidaDeXadrez, capturado);
	}
}


