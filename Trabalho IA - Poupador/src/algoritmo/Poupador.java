package algoritmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Poupador extends ProgramaPoupador {
	double [][]Map = new double[30][30];
	int [][]MapVis = new int[5][5];
	int [][]MapOlf = new int[3][3];
	int []Proibido={-1,-2,1,5,3,200,210,220,230,100,110};
	int X,Y;

	public void Print(String str){
		System.out.print(str);
	}

	public boolean MovementIsPossible(int x, int y){
		for(int i : Proibido){
			if (i == MapVis[2+x][2+y] )
				return false;
			if(x==0) {
				if (MapVis[0][2 + y]>199 || MapVis[0][2 + y * 2]>199 )
					return false;
				if (MapVis[1][2 + y]>199 || MapVis[1][2 + y * 2]>199 )
					return false;
				if (MapVis[3][2 + y]>199 || MapVis[3][2 + y * 2]>199 )
					return false;
				if (MapVis[4][2 + y]>199 || MapVis[4][2 + y * 2]>199 )
					return false;
			}else{
				if (MapVis[2+x][0]>199 || MapVis[2+x*2][0]>199)
					return false;
				if (MapVis[2+x][1]>199 || MapVis[2+x*2][1]>199)
					return false;
				if (MapVis[2+x][3]>199 || MapVis[2+x*2][3]>199 )
					return false;
				if (MapVis[2+x][4]>199 || MapVis[2+x*2][4]>199 )
					return false;
			}
		}
		if (MapOlf[1+x][1+y]> 0)
			return false;

		return true;
	}
	public void Fear() {

		for (int y = 0; y < MapVis.length; y++){
			for (int x = 0; x < MapVis.length; x++){
				if ((Y + y-2) > -1 && (Y + y-2) < 30 && (X + x-2) > -1 && (X + x-2) < 30){
					if (MapVis[y][x] > 199 ) {
						for(int i = y-2; i<3; i++){
							for(int j = x-2;j<3;j++) {
								if ((Y + i) > -1 && (Y + i) < 30 && (X + j) > -1 && (X + j) < 30)
									Map[Y + i][X + j] += Double.POSITIVE_INFINITY;
							}
						}

					}else if (MapVis[y][x] == 4){
						Map[Y + y-2][X + x-2] = -100;
					}else if(MapVis[y][x] != 4 && Map[Y + y-2][X + x-2]<0){
						Map[Y + y-2][X + x-2] = 1;
					}
				}

			}
		}
		for(int y=0;y<MapOlf.length;y++) {
			for (int x = 0; x < MapOlf.length; x++) {
				if ((Y + y-1) > -1 && (Y + y-1) < 30 && (X + x-1) > -1 && (X + x-1) < 30) {
					if (MapOlf[y][x] > 2) {
						Map[Y + y-1][X + x-1] += sensor.getNumeroDeMoedas();
					} else if (MapVis[y+1][x+1] != 4) {
						Map[Y + y-1][X + x-1] = Map[Y + y-1][X + x-1] >100? 100: Map[Y + y-1][X + x-1] ;

					}
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
		Map[Y][X] += 1;
//		for ( int x : sensor.getVisaoIdentificacao()) {
//			Print("" + x + "\n");
//		}
//		for ( int x : sensor.getAmbienteOlfatoPoupador() ) {
//			Print("" + x + "\n");
//		}
		updateVis();
		updateOuf();


//		String out = CallProcess("python3 src/algoritmo/Poupador.py");
//		Print(out);
		Fear();
		double Menor = Double.POSITIVE_INFINITY;
		int Dir = 0;
		if (MovementIsPossible(0,1) && Map[Y][X+1]<Menor){
			Menor = Map[Y][X+1];
			Dir = 3;
		}
		if (MovementIsPossible(0,-1) && Map[Y][X-1]<Menor ){
			Menor = Map[Y][X-1];
			Dir = 4;
		}
		if (MovementIsPossible(1,0) && Map[Y+1][X]<Menor){
			Menor = Map[Y+1][X];
			Dir = 2;
		}
		if (MovementIsPossible(-1,0) && Map[Y-1][X]<Menor ){
			Menor = Map[Y-1][X];
			Dir = 1;
		}
		if (Dir == 0)
			Dir = (int) (Math.random() * 5);

//		for (int i = 0; i<array.length;i++)
//			for (int j = 0; j<array.length;j++)
//				array[i][j] = i;
		Print(Dir+"\n");
		Print("" + sensor.getPosicao());

		for (int i = 0; i<Map.length;i++) {
			Print("\n");
			for (int j = 0; j < Map.length; j++)
				Print(Map[i][j] + " ");
		}
		Print("\n");
		return Dir;
	}

}