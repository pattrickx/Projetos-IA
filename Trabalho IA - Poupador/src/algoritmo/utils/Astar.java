package algoritmo.utils;
import algoritmo.utils.Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Astar {
    double[][] MapHap = new double[30][30];
    int[][] MapPos = new int[30][30];
    int[][] ids = new int[30][30];
    public Astar(double[][] MapHap,int[][] MapPos,int[][] ids){
        this.MapHap = MapHap;
        this.MapPos = MapPos;
        this.ids = ids;

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

    public no aStar(int idi, int idf) {
        Print("Star\n");
        Grafo gStar = new Grafo();
        Print("Montando Grafo\n");
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
        Print("Iniciando Busca\n");
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
}

