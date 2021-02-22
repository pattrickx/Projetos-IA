package algoritmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Poupador extends ProgramaPoupador {
	int [][]Map = new int[30][30];
	int [][]MapVis = new int[5][5];
	int [][]MapOuf = new int[3][3];
	int []Proibido={-1,-2,1,5,3,200,210,220,230,100,110};
	int X,Y;

	public void Print(String str){
		System.out.print(str);
	}
	public boolean MovementIsPossible(int x, int y){
		for(int i : Proibido){
			if (i == MapVis[2+x][2+y] ||  MapVis[2+x*2][2+y*2]>199)
				return false;
			if(x==0) {
				if (MapVis[1][2 + y]>199 || MapVis[1][2 + y * 2]>199)
					return false;
				if (MapVis[3][2 + y]>199 || MapVis[3][2 + y * 2]>199)
					return false;
			}else{
				if (MapVis[2+x][1]>199 || MapVis[2+x*2][1]>199)
					return false;
				if (i == MapVis[2+x][3] || i == MapVis[2+x*2][3])
					return false;
			}
		}
		if (MapOuf[1+x][1+y]> 0)
			return false;

		return true;
	}
	public void updateOuf() {
		int[] temp = new int[9];
		int aux = 0;
		for (int x : sensor.getAmbienteOlfatoPoupador()) {
			temp[aux] = x;
			aux += aux + 1 == 4 ? 2 : 1;
		}

		aux = 0;
		for (int i = 0; i < MapOuf.length; i++) {
			for (int j = 0; j < MapOuf.length; j++) {
				MapOuf[i][j] = temp[aux];
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
		Map[Y][X]+= 1;
//		for ( int x : sensor.getVisaoIdentificacao()) {
//			Print("" + x + "\n");
//		}
//		for ( int x : sensor.getAmbienteOlfatoPoupador() ) {
//			Print("" + x + "\n");
//		}
		updateVis();
		updateOuf();


		String out = CallProcess("python3 src/algoritmo/Poupador.py");
		Print(out);
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