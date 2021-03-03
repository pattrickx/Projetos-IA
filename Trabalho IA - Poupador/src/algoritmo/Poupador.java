package algoritmo;
import algoritmo.utils.Termico;
import algoritmo.utils.aresta;
import controle.Constantes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Poupador extends ProgramaPoupador {
	double [][]MapHap = new double[30][30];
	double [][]MapPos = new double[30][30];
	int [][]MapVis = new int[5][5];
	int [][]MapOlf = new int[3][3];
	int []Proibido={-1,-2,1,200,210,220,230};
	public ArrayList<Integer> DirPos = new ArrayList<>();
	int moedas = 0;
	int X,Y;

	public void Print(String str){
		System.out.print(str);
	}

	public boolean MovementIsPossible(int x, int y){
		for(int i : Proibido) {
			if (i == MapVis[2 + x][2 + y])
				return false;
		}
		if (MapOlf[1+x][1+y]> 0)
			return false;

		return true;
	}
	public boolean Valid(int x,int y){
		if (x>-1 && x<30 && y>-1 && y<30 && MapPos[y][x]!=1)
			return true;
		return false;
	}
	public void Area(int x,int y,double peso,int size){
		for(int i = -size; i<=size; i++){
			for(int j = -size;j<=size;j++) {
				if (Valid(x+j,y+i) ) {

					int termo= Math.abs(i)+Math.abs(j)+1;
					MapHap[y + i][x + j]+= (int)(peso/termo);
//					if (i == j && i == 0) {
//						MapHap[y + i][x + j] += peso;
//					} else if ((i > -2 && j > -2) && (i < 2 && j < 2)) {
//						MapHap[y + i][x + j] += peso * 0.5;
//					} else {
//						MapHap[y + i][x + j] += peso * 0.25;
//					}
				}else{
					break;
				}
			}
		}
	}
	public void AreaNivel(int x,int y,double peso,int size){
		for(int i = -size; i<=size; i++){
			for(int j = -size;j<=size;j++) {
				if (Valid(x+j,y+i)) {
						MapHap[y + i][x + j] = peso;
				}else{
					break;
				}
			}
		}
	}
	public boolean LadraoVisible(){
		for (int y = 0; y < MapVis.length; y++) {
			for (int x = 0; x < MapVis.length; x++) {
				if(MapVis[y][x]>199){
					return true;
				}
			}
		}
		for (int y = 0; y < MapOlf.length; y++) {
			for (int x = 0; x < MapOlf.length; x++) {
				if(MapOlf[y][x]>3){
					return true;
				}
			}
		}
		return false;
	}
	public void Happiness(){

		for (int y = 0; y < MapVis.length; y++){
			for (int x = 0; x < MapVis.length; x++){
				if (Valid((X + x-2),(Y + y-2))){
					if (MapVis[y][x] > 199 ) {
						Area((X+x-2),(Y+y-2),-100*(sensor.getNumeroDeMoedas()+1),2);

					}else if (MapVis[y][x] == 4 || (MapVis[y][x] == 3 && moedas>0)){
						Area((X+x-2),(Y+y-2),10,2);

					}else if(MapVis[y][x] == 1 || MapVis[y][x] >100){
						MapHap[Y + y-2][X + x-2] = -Double.POSITIVE_INFINITY;
					}
					else if(MapVis[y][x] == 5){
						if(sensor.getNumeroDeMoedas()>5 && LadraoVisible()){
							Area((X+x-2),(Y+y-2),10*sensor.getNumeroDeMoedas(),0);

						}else{
							Area((X+x-2),(Y+y-2),-50*(sensor.getNumeroDeMoedas()+1),0);
						}
					}
				}

			}
		}


	}
//	public void Fear() {
//
//		for (int y = 0; y < MapVis.length; y++){
//			for (int x = 0; x < MapVis.length; x++){
//				if ((Y + y-2) > -1 && (Y + y-2) < 30 && (X + x-2) > -1 && (X + x-2) < 30){
//					if (MapVis[y][x] > 199 ) {
//						for(int i = y-2; i<3; i++){
//							for(int j = x-2;j<3;j++) {
//								if ((Y + i) > -1 && (Y + i) < 30 && (X + j) > -1 && (X + j) < 30)
//									MapHap[Y + i][X + j] += Double.POSITIVE_INFINITY;
//							}
//						}
//
//					}else if (MapVis[y][x] == 4){
//						MapHap[Y + y-2][X + x-2] = -100;
//					}else if(MapVis[y][x] ==0){
//						MapHap[Y + y-2][X + x-2] = -1;
//					}
//				}
//
//			}
//		}
//		for(int y=0;y<MapOlf.length;y++) {
//			for (int x = 0; x < MapOlf.length; x++) {
//				if ((Y + y-1) > -1 && (Y + y-1) < 30 && (X + x-1) > -1 && (X + x-1) < 30) {
//					if (MapOlf[y][x] > 2) {
//						MapHap[Y + y-1][X + x-1] += sensor.getNumeroDeMoedas();
//					} else if (MapVis[y+1][x+1] != 4) {
//						MapHap[Y + y-1][X + x-1] = MapHap[Y + y-1][X + x-1] >100? 100: MapHap[Y + y-1][X + x-1] ;
//
//					}
//				}
//			}
//		}
//
//	}
	public void updateMaps(){
		for (int y = 0; y < MapVis.length; y++) {
			for (int x = 0; x < MapVis.length; x++) {
				if (Valid((X + x - 2), (Y + y - 2))) {
					if(MapPos[(Y + y - 2)][(X + x - 2)]>199){
						if(MapVis[y][x]<199){
							AreaNivel((X + x - 2),(Y + y - 2),-10,2);
						}else{
							Area((X + x - 2),(Y + y - 2),10,2);
						}
					}else if(MapPos[(Y + y - 2)][(X + x - 2)]==4){
						if(MapVis[y][x]!=4){
							AreaNivel((X + x - 2),(Y + y - 2),-10,2);
						}
						else{
							Area((X + x - 2),(Y + y - 2),10,2);
						}
					}
					MapPos[(Y + y - 2)][(X + x - 2)]=MapVis[y][x];
				}

				if(MapVis[y][x]==1){
					MapPos[(Y + y - 2)][(X + x - 2)]=MapVis[y][x];
					MapHap[(Y + y - 2)][(X + x - 2)] = - Double.POSITIVE_INFINITY;
				}
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
	public void updateVis(){
		int [] temp = new int[25];
		int aux = 0;
		for(int x: sensor.getVisaoIdentificacao()) {
			temp[aux] = x;
			aux += aux + 1 == 12 ? 2 : 1;
		}

		aux = 0;
		for(int i=0; i<MapVis.length; i++) {
			for (int j = 0; j < MapVis.length; j++) {
				MapVis[i][j] = temp[aux];
				aux++;
			}
		}
	}
	public String CallProcess(String command){
		try {

			final Process sirDesc = Runtime.getRuntime().exec(command);

			final java.io.InputStream inStreamBase = sirDesc.getInputStream();

			final InputStreamReader inStreamISR = new InputStreamReader(inStreamBase);
			final BufferedReader inStream = new BufferedReader(inStreamISR);

			String inLine;
			final StringBuilder result = new StringBuilder();
			while (null != (inLine = inStream.readLine() ))
				result.append(inLine);

			return  result+"";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}
	@Override
	public int acao() {
		X =(int) sensor.getPosicao().getX();
		Y =(int) sensor.getPosicao().getY();
		MapHap[Y][X] +=-1;
		int Xb = (int)Constantes.posicaoBanco.getX();
		int Yb = (int)Constantes.posicaoBanco.getY();
		MapPos[Yb][Xb]=3;
		if(sensor.getNumeroDeMoedas()>0){
//			AreaNivel(Xb, Yb, 0);
			if(MapHap[Yb][Xb]<0) {
				AreaNivel(Xb, Yb, 0,2);
				Area(Xb, Yb, 100 * sensor.getNumeroDeMoedas(), 2);
			}else {
				Area(Xb, Yb, 100 * sensor.getNumeroDeMoedas(), 2);
			}
		}
		else{
			if(MapHap[Yb][Xb]>0) {
				AreaNivel(Xb, Yb, 0,2);
				Area(Xb, Yb, -100,2);
			}else {
				Area(Xb, Yb, -100,2);
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
		double Menor = - Double.POSITIVE_INFINITY;
		int Dir = 0;
		if (MovementIsPossible(0,1) && MapHap[Y][X+1]>=Menor){
			DirPos.add(3);
			Menor = MapHap[Y][X+1];
			Dir = 3;
		}
		if (MovementIsPossible(0,-1) && MapHap[Y][X-1]>=Menor ){
			if(MapHap[Y][X-1]==Menor){
				DirPos.add(4);
			}else{
				DirPos.clear();
				DirPos.add(4);
			}

			Menor = MapHap[Y][X-1];
			Dir = 4;
		}
		if (MovementIsPossible(1,0) && MapHap[Y+1][X]>=Menor){
			if(MapHap[Y+1][X]==Menor){
				DirPos.add(2);
			}else{
				DirPos.clear();
				DirPos.add(2);
			}
			Menor = MapHap[Y+1][X];
			Dir = 2;
		}
		if (MovementIsPossible(-1,0) && MapHap[Y-1][X]>=Menor ){
			if(MapHap[Y-1][X]==Menor){
				DirPos.add(1);
			}else{
				DirPos.clear();
				DirPos.add(1);
			}
			Menor = MapHap[Y-1][X];
			Dir = 1;
		}
		if (Dir == 0) {
			Dir = (int) (Math.random() * 5);

		}
		if (DirPos.size()>1){
			int aux = (int) (Math.random() * DirPos.size());
			Dir = DirPos.get(aux);
		}
//		for (int i = 0; i<array.length;i++)
//			for (int j = 0; j<array.length;j++)
//				array[i][j] = i;
		Print(Dir+"\n");
		Print("" + sensor.getPosicao());

		for (int i = 0; i<MapHap.length;i++) {
			Print("\n");
			for (int j = 0; j < MapHap.length; j++)
				Print(MapHap[i][j] + " ");
		}

		int [][]MapT = new int[30][30];
		MapT = Termico.termico(MapT,Xb,Yb);
		Print("\nTermico");
		for (int i = 0; i<MapT.length;i++) {
			Print("\n");
			for (int j = 0; j < MapT.length; j++)
				Print(MapT[i][j] + " ");
		}
//		Print("\n");
//		for (int i = 0; i<MapPos.length;i++) {
//			Print("\n");
//			for (int j = 0; j < MapHap.length; j++)
//				Print(MapPos[i][j] + " ");
//		}
		Print("\n");
		return Dir;
	}

}