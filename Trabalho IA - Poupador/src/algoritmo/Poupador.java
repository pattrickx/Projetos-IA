package algoritmo;


import algoritmo.utils.*;
import controle.Constantes;

// 0 Parado
// 1 Cima
// 2 Baixo
// 3 Direita
// 4 Esquerda

public class Poupador extends ProgramaPoupador {
	CVM cvm;
	Roleta roleta;
	Grafo grafo;
	Termico termico;
	public Poupador(){
		cvm = new CVM();
		roleta = new Roleta(cvm);
		grafo = new Grafo(cvm);
		termico = new Termico(cvm);
	}

	public void UpdateVis(){
		int[] Vis = sensor.getVisaoIdentificacao();
		int aux = 0;
		for(int i = 0;i<cvm.MapVis.length;i++) {
			for (int j = 0; j < cvm.MapVis.length; j++) {
				if(!(i==j && i==2)){
					cvm.MapVis[i][j]= Vis[aux];
					aux++;
				}
			}
		}

	}
	public void UpdateOlf(){
		int[] Olf = sensor.getAmbienteOlfatoLadrao();
		int aux = 0;
		for(int i = 0;i<cvm.MapOlf.length;i++) {
			for (int j = 0; j < cvm.MapOlf.length; j++) {
				if(!(i==j && i==1)){
					cvm.MapOlf[i][j]= Olf[aux];
					aux++;
				}
			}
		}
	}

	public int Pesos(int tipo){
		switch (tipo){
			case 4: // moeda
				return 100;
			case 1: // parede
				return -100000;
			case 5: // pastilha
				return sensor.getNumeroDeMoedas()>4? 100* cvm.NumeroDeMoedas:-100;
			case 3: // banco
				return sensor.getNumeroDeMoedas()>0? 100* cvm.NumeroDeMoedas:-100;
			default: // Ladrão
				return -1000* (sensor.getNumeroDeMoedas()+1);
		}
	}
	public boolean Valido(int x, int y, int tipo){
		if(x<0 || y<0 || x>29 || y>29)
			return false;
		for(int invalido:cvm.Nvalido){
			if(tipo==invalido)
				return false;
		}
		return true;
	}
	public boolean PosValida(int x, int y){
		if(x<0 || y<0 || x>29 || y>29)
			return false;
		return true;
	}

	public void UpdateObj(){

		for(int i = 0;i<cvm.MapVis.length;i++) {
			for (int j = 0; j < cvm.MapVis.length; j++) {
				int x= (j-2) + cvm.X;
				int y= (i-2) + cvm.Y;

				if(Valido(x,y,cvm.MapVis[i][j]) && cvm.MapObj[y][x]==null) {
					cvm.MapObj[y][x] = new Objeto(cvm.MapVis[i][j],Pesos(cvm.MapVis[i][j]));
				}
				else if(cvm.MapVis[i][j]==0){
					cvm.MapObj[y][x] = null;
				}
			}
		}
		cvm.MapObj[cvm.Xb][cvm.Yb] = new Objeto(cvm.MapVis[cvm.Xb][cvm.Yb],Pesos(3));

	}

	public void AreaPeso(int x,int y, int tamanho,double peso){
		for(int i =-tamanho;i<=tamanho;i++){
			for(int j =-tamanho;j<=tamanho;j++){
				if (PosValida(x+i,y+j)) {
					if (cvm.MapObj[x + i][y + j] == null ||  cvm.MapObj[x + i][y + j].tipo != 1) {
						int distancia = Math.abs(i) + Math.abs(j) + 1;
						cvm.MapFeli[x + i][y + j] += peso / distancia;
					}
				}

			}
		}

	}
	public void AreaPesoTermico(int x,int y,double peso){
		grafo.MontarGrafo();
		int [][] ter = termico.termico(grafo,x,y);
		for(int i = 0;i<cvm.MapFeli.length;i++) {
			for (int j = 0; j < cvm.MapFeli.length; j++) {
				if(ter[i][j]>0)
					cvm.MapFeli[i][j]+=peso/ter[i][j];
			}
		}

	}
	public void UpdateFeli(){
		cvm.MapFeli = new double[30][30];
		for(int i = 0;i<cvm.MapFeli.length;i++) {
			for (int j = 0; j < cvm.MapFeli.length; j++) {
				cvm.MapFeli[i][j]+=cvm.MapPos[i][j];
				if(cvm.MapObj[i][j]!=null) {
					if (cvm.MapObj[i][j].tipo == 1) {
						cvm.MapFeli[i][j] = Double.NEGATIVE_INFINITY;
					}else{
//						AreaPeso(i, j, 2, cvm.MapObj[i][j].peso);
						AreaPesoTermico(i,j,cvm.MapObj[i][j].peso);
					}
				}
			}
		}

	}

	public void PrintFelicidadeTipo(){
		System.out.println("");
		for(int i = 0;i<cvm.MapObj.length;i++) {
			System.out.println("");
			for (int j = 0; j < cvm.MapObj.length; j++) {
				if(cvm.MapObj[i][j]!=null)
					System.out.print("("+cvm.MapObj[i][j].tipo+":"+String.format("%.2f", cvm.MapFeli[i][j])+") ");
				else if(j==cvm.X && i==cvm.Y)
					System.out.print("(X:"+String.format("%.2f", cvm.MapFeli[i][j])+") ");
				else
					System.out.print("(0:"+String.format("%.2f", cvm.MapFeli[i][j])+") ");
			}
		}
		System.out.println("");
		System.out.println("###### FIM #####");

	}
	public  String Direcao(int D){
		if(D==cvm.Direita)
			return "Direita";
		if(D==cvm.Baixo)
			return "Baixo";
		if(D==cvm.Esquerda)
			return "Esquerda";
		if(D==cvm.Cima)
			return "Cima";
		return "Parado";
	}
	@Override
	public int acao() {
		cvm.X = Integer.valueOf((int) sensor.getPosicao().getX());
		cvm.Y = Integer.valueOf((int) sensor.getPosicao().getY());
		cvm.MapPos[cvm.Y][cvm.X]+=-1;
		cvm.NumeroDeMoedas = sensor.getNumeroDeMoedas();
		cvm.Xb = (int) Constantes.posicaoBanco.getX()-1;
		cvm.Yb = (int) Constantes.posicaoBanco.getY()-1;
		UpdateVis();
		UpdateOlf();
		UpdateObj();
		UpdateFeli();

//		PrintFelicidadeTipo();
		// Termico
//		grafo.MontarGrafo();
//		termico.termico(grafo,8,8);


		int DirRoleta = roleta.Roleta();
//		System.out.println(Direcao(DirRoleta));
		return DirRoleta;//(int) (Math.random() * 5);
	}

}
