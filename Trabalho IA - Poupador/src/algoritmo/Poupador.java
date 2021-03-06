package algoritmo;

import algoritmo.utils.Termico;
import algoritmo.utils.no;
import algoritmo.utils.Astar;

import controle.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
// 0 Parado
// 1 Cima
// 2 Baixo
// 3 Direita
// 4 Esquerda

public class Poupador extends ProgramaPoupador {
	double[][] MapHap = new double[30][30];
	int[][] MapPos = new int[30][30];
	int[][] ids = new int[30][30];
	int[][] MapVis = new int[5][5];
	int[][] MapOlf = new int[3][3];
	int[] Proibido = {-1, -2, 1, 200, 210, 220, 230};
	public ArrayList<Integer> DirPos = new ArrayList<>();
	int moedas = 0;
	int X, Y;
	int DirOld=0;
	static int Parado = 0;
	static int Cima = 1;
	static int Baixo = 2;
	static int Direita = 3;
	static int Esquerda = 4;
	boolean t=true;
//	no n = new no();
	Astar Star = new Astar(MapHap,MapPos,ids);
	public Poupador(){
		int id=0;
		for (int i = 0; i < MapHap.length; i++) {
			for (int j = 0; j < MapHap.length; j++){
				ids[i][j]=id;
				id++;
			}
		}
	}
	public ArrayList<Integer> Dirs = new ArrayList<>();

	public void Print(String str) {
		System.out.print(str);
	}
	public boolean Valid(int x, int y) {
		if (x > -1 && x < 30 && y > -1 && y < 30 && MapPos[y][x] != 1)
			return true;
		return false;
	}

	public boolean MovementIsPossible(int x, int y) {
		for (int i : Proibido) {
			if (!Valid(x,y) || i == MapVis[2 + y][2 + x] || (LadraoVisible() && MapVis[2 + y][2 + x] == 4))
				return false;
		}
		if (MapOlf[1 + y][1 + x] > 0)
			return false;

		return true;
	}

	public  String Direcao(int D){
		if(D==Direita)
			return "Direita";
		if(D==Baixo)
			return "Baixo";
		if(D==Esquerda)
			return "Esquerda";
		if(D==Cima)
			return "Cima";
		return "Parado";
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

	public boolean LadraoVisible() {
		for (int y = 0; y < MapVis.length; y++) {
			for (int x = 0; x < MapVis.length; x++) {
				if (MapVis[y][x] > 199) {
					return true;
				}
			}
		}
		for (int y = 0; y < MapOlf.length; y++) {
			for (int x = 0; x < MapOlf.length; x++) {
				if (MapOlf[y][x] > 3) {
					return true;
				}
			}
		}
		return false;
	}

	public void Area(int x, int y, double peso, int size) {
		for (int i = -size; i <= size; i++) {
			for (int j = -size; j <= size; j++) {
				if (Valid(x + j, y + i)) {

					int termo = Math.abs(i) + Math.abs(j) + 1;
					MapHap[y + i][x + j] += (int) (peso / termo);
//					if (i == j && i == 0) {
//						MapHap[y + i][x + j] += peso;
//					} else if ((i > -2 && j > -2) && (i < 2 && j < 2)) {
//						MapHap[y + i][x + j] += peso * 0.5;
//					} else {
//						MapHap[y + i][x + j] += peso * 0.25;
//					}
				} else {
					break;
				}
			}
		}
	}

	public void AreaNivel(int x, int y, double peso, int size) {
		for (int i = -size; i <= size; i++) {
			for (int j = -size; j <= size; j++) {
				if (Valid(x + j, y + i)) {
					MapHap[y + i][x + j] = peso;
				} else {
					break;
				}
			}
		}
	}

	public void updateXY(){
		X = Integer.valueOf((int) sensor.getPosicao().getX());
		Y = Integer.valueOf((int) sensor.getPosicao().getY());
//		if(DirOld==1 && Y>0)
//			Y-=1;
//		if(DirOld==2 && Y<29)
//			Y+=1;
//		if(DirOld==3 && X<29)
//			X+=1;
//		if(DirOld==4 && X>0)
//			X-=1;
	}

	public void Happiness() {

		for (int y = 0; y < MapVis.length; y++) {
			for (int x = 0; x < MapVis.length; x++) {
				if (Valid((X + x - 2), (Y + y - 2))) {
					if (MapVis[y][x] > 199) {
						Area((X + x - 2), (Y + y - 2), -100 * (sensor.getNumeroDeMoedas() + 1), 2);

					} else if (MapVis[y][x] == 4 || (MapVis[y][x] == 3 && moedas > 0)) {
						Area((X + x - 2), (Y + y - 2), 10, 2);

					} else if (MapVis[y][x] == 1 || MapVis[y][x] > 100) {
						MapHap[Y + y - 2][X + x - 2] = -Double.POSITIVE_INFINITY;

					} else if (MapVis[y][x] == 5) {
						if (sensor.getNumeroDeMoedas() > 5 && LadraoVisible()) {
							Area((X + x - 2), (Y + y - 2), 10 * sensor.getNumeroDeMoedas(), 0);

						} else {
							Area((X + x - 2), (Y + y - 2), -50 * (sensor.getNumeroDeMoedas() + 1), 0);
						}
					}
				}

			}
		}


	}

	public void updateMaps() {
		for (int y = 0; y < MapVis.length; y++) {
			for (int x = 0; x < MapVis.length; x++) {
				if (Valid((X + x - 2), (Y + y - 2))) {
					if (MapPos[(Y + y - 2)][(X + x - 2)] > 199) {
						if (MapVis[y][x] < 199) {
							AreaNivel((X + x - 2), (Y + y - 2), -10, 2);
						} else {
							Area((X + x - 2), (Y + y - 2), 10, 2);
						}
					} else if (MapPos[(Y + y - 2)][(X + x - 2)] == 4) {
						if (MapVis[y][x] != 4) {
							AreaNivel((X + x - 2), (Y + y - 2), -10, 2);
						} else {
							Area((X + x - 2), (Y + y - 2), 10, 2);
						}
					}
					MapPos[(Y + y - 2)][(X + x - 2)] = MapVis[y][x];
				}

				if (MapVis[y][x] == 1) {
					MapPos[(Y + y - 2)][(X + x - 2)] = MapVis[y][x];
					MapHap[(Y + y - 2)][(X + x - 2)] = -Double.POSITIVE_INFINITY;

				}
			}
		}

	}

	public String CallProcess(String command) {
		try {

			final Process sirDesc = Runtime.getRuntime().exec(command);

			final java.io.InputStream inStreamBase = sirDesc.getInputStream();

			final InputStreamReader inStreamISR = new InputStreamReader(inStreamBase);
			final BufferedReader inStream = new BufferedReader(inStreamISR);

			String inLine;
			final StringBuilder result = new StringBuilder();
			while (null != (inLine = inStream.readLine()))
				result.append(inLine);

			return result + "";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

//	public int roleta(int x, int y){
//		Double Soma;
//
//
//		(int) (Math.random() * Soma);
//
//	}
	@Override
	public int acao() {

		updateXY();
		MapHap[Y][X] += -1;
		int Xb = (int) Constantes.posicaoBanco.getX()-1;
		int Yb = (int) Constantes.posicaoBanco.getY()-1;
		MapPos[Yb][Xb] = 3;
		if (sensor.getNumeroDeMoedas() > 0) {
//			AreaNivel(Xb, Yb, 0);
			if (MapHap[Yb][Xb] < 0) {
				AreaNivel(Xb, Yb, 0, 2);
				Area(Xb, Yb, 10 * sensor.getNumeroDeMoedas(), 2);
			} else {
				Area(Xb, Yb, 10 * sensor.getNumeroDeMoedas(), 2);
			}
		} else {
			if (MapHap[Yb][Xb] > 0) {
				AreaNivel(Xb, Yb, 0, 2);
				Area(Xb, Yb, -10, 2);
			} else {
				Area(Xb, Yb, -10, 2);
			}
		}

//		if (sensor.getNumeroDeMoedas()>moedas){
//			moedas = sensor.getNumeroDeMoedas();
//			Area(X,Y,-100);
//		}

		updateVis();
		updateOuf();
		updateMaps();
		Happiness();

//		String out = CallProcess("python3 src/algoritmo/Poupador.py");
//		Print(out);
		///Fear();
		double Menor = -Double.POSITIVE_INFINITY;
		int Dir = Parado;
		if (MovementIsPossible(1, 0) && MapHap[Y][X + 1] >= Menor) {
			DirPos.add(Direita);
			Menor = MapHap[Y][X + 1];
			Dir = Direita;
		}
		if (MovementIsPossible(-1, 0) && MapHap[Y][X - 1] >= Menor) {
			if (MapHap[Y][X - 1] == Menor) {
				DirPos.add(Esquerda);
			} else {
				DirPos.clear();
				DirPos.add(Esquerda);
			}

			Menor = MapHap[Y][X - 1];
			Dir = Esquerda;
		}
		if (MovementIsPossible(0, 1) && MapHap[Y + 1][X] >= Menor) {
			if (MapHap[Y + 1][X] == Menor) {
				DirPos.add(Baixo);
			} else {
				DirPos.clear();
				DirPos.add(Baixo);
			}
			Menor = MapHap[Y + 1][X];
			Dir = Baixo;
		}
		if (MovementIsPossible(0, -1) && MapHap[Y - 1][X] >= Menor) {
			if (MapHap[Y - 1][X] == Menor) {
				DirPos.add(Cima);
			} else {
				DirPos.clear();
				DirPos.add(Cima);
			}
			Menor = MapHap[Y - 1][X];
			Dir = Cima;
		}
		if (Dir == 0) {
			Dir = (int) (Math.random() * 5);

		}
		if (DirPos.size() >= 1) {
			int aux = (int) (Math.random() * DirPos.size());
			Dir = DirPos.get(aux);
		}
//		for (int i = 0; i<array.length;i++)
//			for (int j = 0; j<array.length;j++)
//				array[i][j] = i;
		Print(Direcao(Dir) + "\n");


		for (int i = 0; i < MapHap.length; i++) {
			Print("\n");
			for (int j = 0; j < MapHap.length; j++)
				Print(MapHap[i][j] + " ");
		}
		// Algoritmo termico
		int[][] MapT = new int[30][30];
		MapT = Termico.termico(MapT, Xb, Yb);

		// Busca A*
		Print("\n" + sensor.getPosicao());
//		Print("\nOrigem: "+ids[Y][X]+"  Destino: "+ids[Yb][Xb]+"\n");
		Print("\nOrigem: "+X+":"+Y+"  Destino: "+Yb+":"+Xb+"\n");
		if (ids[Y][X]!=ids[Yb][Xb] && MapHap[Yb][Xb]>0){//(MapHap[Yb][Xb]>0 && ids[Y][X]!=ids[Yb][Xb] && LadraoVisible()){
			Print("BUSCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			Dirs.clear();
			no n= Star.aStar(ids[Y][X],ids[Yb][Xb]);

			int tx=n.pai.x;
			int ty=n.pai.y;
			while (n.id>-1){
				int D=Parado;
				if(n.x>tx){
					D=Esquerda;
				}
				if(n.x<tx){
					D=Direita;
				}
				if(n.y>ty){
					D = Cima;
				}
				if(n.y<ty){
					D = Baixo;
				}
				Dirs.add(D);
				Print(n.x+":"+n.y+"("+Direcao(D)+") \n");
				tx=n.x;
				ty=n.y;
				n = n.pai;
			}
//			if(Dirs.size()>1)
//				Dirs.remove(Dirs.size() - 1);
		}

//		if (sensor.getNumeroDeMoedas()==0){
//			Dirs.clear();
//		}
		Print("\n Dir atual: "+Direcao(Dir)+"\nDir anterior: "+Direcao(DirOld)+"\n");
		for(int i = 0;i<Dirs.size() ;i++){
			Print(Direcao(Dirs.get(i))+" <- ");
		}
		if(!Dirs.isEmpty()) {
			Dir = Dirs.get(Dirs.size() - 1);
			Dirs.remove(Dirs.size() - 1);
		}
		Print("\n Dir atual: "+Direcao(Dir)+"\nDir anterior: "+Direcao(DirOld)+"\n");
		Print("\nFIM    aaaaaaaa\n");

		return Dir;
	}

}