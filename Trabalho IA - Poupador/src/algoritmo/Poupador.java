package algoritmo;

import algoritmo.utils.*;

import static algoritmo.utils.MapsAndConsts.*;
import controle.Constantes;
import java.util.ArrayList;

// 0 Parado
// 1 Cima
// 2 Baixo
// 3 Direita
// 4 Esquerda

public class Poupador extends ProgramaPoupador {

	Astar Star = new Astar();

	public Poupador(){
		int id=0;
		for (int i = 0; i < MapHap.length; i++) {
			for (int j = 0; j < MapHap.length; j++){
				ids[i][j]=id;
				id++;
			}
		}
	}

	public void updateOuf() {
		int[] temp = new int[9];
		int aux = 0;
		for (int x : sensor.getAmbienteOlfatoPoupador()) {
			temp[aux] = x;
			aux += aux + 1 == 4 ? 2 : 1;
		}

		aux = 0;
		for (int i = 0; i < MapOlf.length; i++) {
			for (int j = 0; j < MapOlf.length; j++) {
				MapOlf[i][j] = temp[aux];
				aux++;
			}
		}
	}

	public void updateVis() {
		int[] temp = new int[25];
		int aux = 0;
		for (int x : sensor.getVisaoIdentificacao()) {
			temp[aux] = x;
			aux += aux + 1 == 12 ? 2 : 1;
		}

		aux = 0;
		for (int i = 0; i < MapVis.length; i++) {
			for (int j = 0; j < MapVis.length; j++) {
				MapVis[i][j] = temp[aux];
				aux++;
			}
		}
	}

	public String get_arg() {
		String stado = "";
		for (int x : sensor.getVisaoIdentificacao()) {
			stado +=(x+",");
		}
		for (int x : sensor.getAmbienteOlfatoPoupador()) {
			stado +=(x+",");
		}
		stado+="0,0,0,0,0";
		return stado;

	}

	public void UpdateSensores(){
		NumeroDeMoedas = sensor.getNumeroDeMoedas();
		X = Integer.valueOf((int) sensor.getPosicao().getX());
		Y = Integer.valueOf((int) sensor.getPosicao().getY());
		updateOuf();
		updateVis();
		MapHap[Y][X] += -1;
		Xb = (int) Constantes.posicaoBanco.getX()-1;
		Yb = (int) Constantes.posicaoBanco.getY()-1;
		MapPos[Yb][Xb] = 3;
	}
	public void UpdadeBank(){
		if (sensor.getNumeroDeMoedas() > 0) {
//			AreaNivel(Xb, Yb, 0);
			if (MapHap[Yb][Xb] < 0) {
				utils.AreaNivel(Xb, Yb, 0, 2);
				utils.Area(Xb, Yb, 1000 * sensor.getNumeroDeMoedas(), 2);
			} else {
				utils.Area(Xb, Yb, 1000 * sensor.getNumeroDeMoedas(), 2);
			}
		} else {
			if (MapHap[Yb][Xb] > 0) {
				utils.AreaNivel(Xb, Yb, 0, 2);
				utils.Area(Xb, Yb, -1000, 2);
			} else {
				utils.Area(Xb, Yb, -1000, 2);
			}
		}
	}

	@Override
	public int acao() {
		UpdateSensores();
		UpdadeBank();
		utils.updateMaps();
		utils.Happiness();
		int Dir;

		// Salvando estado
//		String out = CallProcess("python3 src/algoritmo/utils/Poupador.py 1 "+get_arg());
//		Print(out);

		// Roleta
		int Dir_Roleta = Roleta.get_dir();

		// Maior Felicidade
		int Dir_BestDir = BestDir.BestDir();

		// Print Mapa Hap
		for (int i = 0; i < MapHap.length; i++) {
			utils.Print("\n");
			for (int j = 0; j < MapHap.length; j++)
				utils.Print(MapHap[i][j] + " ");
		}

		// Algoritmo termico
		int[][] MapT = new int[30][30];
		MapT = Termico.termico(MapT, Xb, Yb);

		// Busca A*
		int Dir_Busca = Astar.get_dir();

		// Escolhendo resultado
		if (Dir_Busca>=0){
			Dir = Dir_Busca;
		}else{
			Dir = Dir_Roleta;
//			Dir = Dir_BestDir;
		}


		utils.Print("\nFIM    aaaaaaaa\n");
		return Dir;
	}

}