package algoritmo.utils;
import algoritmo.utils.Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static algoritmo.utils.MapsAndConsts.*;
public class Astar {



    public Astar(){

    }
    public void Print(String str) {
        System.out.print(str);
    }
    public int dist(int[] xyi, int[] xyf){
        return Math.abs(xyi[0] - xyf[0]) + Math.abs(xyi[1] - xyf[1]);
    }

    public int[] getXY(int id){
        int x = id%30;
        int y = (int)(id/29);

        return new int[]{x, y};
    }

    public static no aStar(int idi, int idf) {
        utils.Print("Star\n");
        Grafo gStar = new Grafo();
        utils.Print("Montando Grafo\n");
        for (int y = 0; y < MapPos.length; y++) {
            for (int x = 0; x < MapPos.length; x++) {
                if (x > -1 && x < 30 && y > -1 && y < 30) {
                    no n = new no();
                    n.x = x;
                    n.y = y;
                    n.peso = MapHap[y][x];
                    n.type = MapPos[y][x];
                    n.id = ids[y][x];
                    n.h = 0;
                    n.f = 0;

                    if (x+1 > -1 && x+1 < 30 && y > -1 && y < 30 ) {
                        n.arestas.add(new aresta(MapHap[y][x + 1], ids[y][x+1], ids[y][x], 3));
                    }
                    if (x-1 > -1 && x-1 < 30 && y > -1 && y < 30 ) {
                        n.arestas.add(new aresta(MapHap[y][x - 1], ids[y][x-1], ids[y][x], 4));
                    }
                    if (x > -1 && x < 30 && y+1 > -1 && y+1 < 30 ) {
                        n.arestas.add(new aresta(MapHap[y + 1][x], ids[y + 1][x], ids[y][x], 2));
                    }
                    if (x > -1 && x < 30 && y-1 > -1 && y-1 < 30 ) {
                        n.arestas.add(new aresta(MapHap[y - 1][x], ids[y - 1][x], ids[y][x], 1));
                    }

                    gStar.nos.add(n);

                }

            }
        }
//			h = (delta x + delta y)
//			f = peso + h

        no ni = gStar.nos.get(idi);
        ni.pai=new no();
        ni.pai.id =-1;
        no nf = gStar.nos.get(idf);
        ArrayList<no> nos = new ArrayList<>();

        nos.add(ni);
        no aux = new no();
        utils.Print("Iniciando Busca\n");
        while(!nos.isEmpty()){

            aux = nos.get(0);
            aux.v = true;
            nos.remove(0);
//			Print("Atual: "+aux.id+"\n");


            if(aux.id == idf ||aux.type==4){
                return aux;
            }

            for (int i = 0; i < aux.arestas.size(); i++) {
                no fi = gStar.nos.get(aux.arestas.get(i).destino);
                if (fi.id != aux.pai.id && !fi.v){
                    fi.dir = aux.arestas.get(i).dir;
                    fi.pai = aux;
                    fi.h = Math.abs(fi.x - nf.x) + Math.abs(fi.y - nf.y);
                    if(fi.peso==-Double.POSITIVE_INFINITY) {
                        fi.f = fi.peso;
                    }else {
                        fi.f = fi.peso - fi.h;
                    }
                    nos.add(fi);
                }
            }

//			Print("Antes\n");
//			for(int i=0; i < nos.size(); i++){
//				Print(nos.get(i).id+" "+nos.get(i).f+" * ");
//			}
//			Print("\n");
//			Print("\n");
            Collections.sort(nos, new Comparator<no>(){
                public int compare(no n1, no n2){
                    return  Integer.valueOf((int)(n2.f)).compareTo((int)(n1.f));
                }
            });


//			Print("Depois\n");
//			for(int i=0; i < nos.size(); i++){
//				Print(nos.get(i).id+" "+nos.get(i).f+" * ");
//			}
//			Print("\n");
//			Print("\n");

        }
        return aux;


    }

    public static int get_dir(){
        if (ids[Y][X]!=ids[Yb][Xb] && MapHap[Yb][Xb]>0 && utils.LadraoVisible()){//(MapHap[Yb][Xb]>0 && ids[Y][X]!=ids[Yb][Xb] && LadraoVisible()){
			utils.Print("BUSCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			Dirs.clear();
			no n= aStar(ids[Y][X],ids[Yb][Xb]);

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
				utils.Print(n.x+":"+n.y+"("+utils.Direcao(D)+") \n");
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
        int Dir=-1;
		for(int i = 0;i<Dirs.size() ;i++){
			utils.Print(utils.Direcao(Dirs.get(i))+" <- ");
		}
		if(!Dirs.isEmpty()) {
			Dir = Dirs.get(Dirs.size() - 1);
			Dirs.remove(Dirs.size() - 1);
		}
        utils.Print("\n Dir atual: "+utils.Direcao(Dir)+"\n");
		return Dir;
    }
}

