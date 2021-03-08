package aplicacao;

import controle.Constantes;
import gui.FramePrincipal;

public class Executa {

	public static void main(String[] args) {
		System.out.println("Iniciando jogo");
		FramePrincipal jogo = new FramePrincipal();
		Constantes.tempoInicialJogo=1000;
		jogo.iniciarJogo();
	}
}
