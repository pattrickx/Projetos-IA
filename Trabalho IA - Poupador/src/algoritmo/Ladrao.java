package algoritmo;

public class Ladrao extends ProgramaLadrao {
	int [][]Map = new int[30][30];
	int [][]MapVis = new int[5][5];
	int []Proibido={-1,-2,1,4,5,3,200,210,220,230,100};
	int X,Y;
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

	public boolean MovementIsPossible(int x, int y){
		for(int i : Proibido){
			if (i == MapVis[2+x][2+y])
				return false;

		}
//		if (MapOuf[1+x][1+y]> 0)
//			return false;

		return true;
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


		return Dir;
	}

}