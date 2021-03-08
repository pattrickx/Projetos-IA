package algoritmo;

import algoritmo.utils.*;
import controle.Constantes;

// 0 Parado
// 1 Cima
// 2 Baixo
// 3 Direita
// 4 Esquerda

public class Poupador extends ProgramaPoupador {


	MapsAndConsts MapsConsts;
	utils Utils ;
	public Poupador(){
		MapsConsts= new MapsAndConsts();
		Utils = new utils(MapsConsts);
		Utils.Print(Math.random()+"Construtor Poupador\n");
		int id=0;
		for (int i = 0; i < MapsConsts.MapHap.length; i++) {
			for (int j = 0; j < MapsConsts.MapHap.length; j++){
				MapsConsts.ids[i][j]=id;
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
		for (int i = 0; i < MapsConsts.MapOlf.length; i++) {
			for (int j = 0; j < MapsConsts.MapOlf.length; j++) {
				MapsConsts.MapOlf[i][j] = temp[aux];
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
		for (int i = 0; i < MapsConsts.MapVis.length; i++) {
			for (int j = 0; j < MapsConsts.MapVis.length; j++) {
				MapsConsts.MapVis[i][j] = temp[aux];
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
		MapsConsts.NumeroDeMoedas = sensor.getNumeroDeMoedas();
		MapsConsts.X = Integer.valueOf((int) sensor.getPosicao().getX());
		MapsConsts.Y = Integer.valueOf((int) sensor.getPosicao().getY());
		updateOuf();
		updateVis();
		MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X] += -1;
		MapsConsts.Xb = (int) Constantes.posicaoBanco.getX()-1;
		MapsConsts.Yb = (int) Constantes.posicaoBanco.getY()-1;
		MapsConsts.MapPos[MapsConsts.Yb][MapsConsts.Xb] = 3;
	}
	public void UpdadeBank(){
		if (sensor.getNumeroDeMoedas() > 0) {
//			AreaNivel(MapsConsts.Xb, MapsConsts.Yb, 0);
			if (MapsConsts.MapHap[MapsConsts.Yb][MapsConsts.Xb] < 0) {
				Utils.AreaNivel(MapsConsts.Xb, MapsConsts.Yb, 0, 2);
				Utils.Area(MapsConsts.Xb, MapsConsts.Yb, 1000 * sensor.getNumeroDeMoedas(), 2);
			} else {
				Utils.Area(MapsConsts.Xb, MapsConsts.Yb, 1000 * sensor.getNumeroDeMoedas(), 2);
			}
		} else {
			if (MapsConsts.MapHap[MapsConsts.Yb][MapsConsts.Xb] > 0) {
				Utils.AreaNivel(MapsConsts.Xb, MapsConsts.Yb, 0, 2);
				Utils.Area(MapsConsts.Xb, MapsConsts.Yb, -1000, 2);
			} else {
				Utils.Area(MapsConsts.Xb, MapsConsts.Yb, -1000, 2);
			}
		}
	}

	@Override
	public int acao() {
		UpdateSensores();
		UpdadeBank();
		Grafo G = new Grafo(MapsConsts);
		Termico T = new Termico(MapsConsts);
		Astar Star = new Astar(MapsConsts,G);
		BestDir B = new BestDir(MapsConsts);
		Roleta R = new Roleta(MapsConsts);
		Utils.updateMaps();
		Utils.Happiness();
		G.MontarGrafo();
		T.ter(G);
		int Dir;

		// Salvando estado
//		String out = CallProcess("python3 src/algoritmo/Utils/Poupador.py 1 "+get_arg());
//		Print(out);

		// Roleta
		int Dir_Roleta = R.get_dir();

		// Maior Felicidade
		int Dir_BestDir = B.BestDir();

		// Print Mapa Hap
		for (int i = 0; i < MapsConsts.MapHap.length; i++) {
			Utils.Print("\n");
			for (int j = 0; j < MapsConsts.MapHap.length; j++)
				Utils.Print(MapsConsts.MapHap[i][j] + " ");
		}

		// Algoritmo termico
		int[][] MapT = new int[30][30];
		MapT = T.termico(MapT, MapsConsts.Xb, MapsConsts.Yb);

		// Busca A*
		G.MontarGrafo();
		int Dir_Busca = Star.get_dir();

		// Escolhendo resultado
		if (Dir_Busca>=0){
			Dir = Dir_Busca;
		}else{
			Dir = Dir_Roleta;
//			Dir = Dir_BestDir;
		}


		Utils.Print("\nFIM    aaaaaaaa\n");
		return Dir;
	}

}