package aplicacao;

import controle.Constantes;
import gui.FramePrincipal;

public class Executa {

	public static void main(String[] args) {
		FramePrincipal jogo = new FramePrincipal();
		Constantes.tempoInicialJogo=10;
		jogo.iniciarJogo();
	}
}
