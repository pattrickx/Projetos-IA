package algoritmo.utils;

import java.util.ArrayList;

public class Termico {
    CVM cvm;
    public Termico(CVM cvm){
        this.cvm=cvm;
    }
    public int[][] termico(Grafo grafo,int xi,int yi){
        int[][] MapTermo = new int[30][30];
        int bid = yi*30+xi;
        no ni = grafo.nos.get(bid);
        ni.pai=new no();
        ni.pai.id =-1;
        MapTermo[ni.y][ni.x] = 1;

        ArrayList<no> nos = new ArrayList<>();
        nos.add(ni);
        no aux = new no();
        while(!nos.isEmpty()) {
            aux = nos.get(0);
            aux.v = true;
            nos.remove(0);
//            System.out.println(aux.tipo+"");
            for (int i = 0; i < aux.arestas.size(); i++) {
                no fi = grafo.nos.get(aux.arestas.get(i).destino);
                if (fi.tipo==1){
//                    System.out.println(fi.tipo+" :tip");
                    MapTermo[fi.y][fi.x]= 0;
                }
                if(fi.tipo!=1 && !fi.v && MapTermo[fi.y][fi.x]==0){
//                    System.out.println(fi.tipo+"(:)");
                    MapTermo[fi.y][fi.x]=MapTermo[aux.y][aux.x]+1;
                    nos.add(fi);
                }
            }
        }
//        System.out.println("\n");
//        for (int i = 0; i < MapTermo.length; i++) {
//            System.out.println("\n");
//            for (int j = 0; j < MapTermo.length; j++)
//                System.out.print(MapTermo[i][j] + " ");
//        }
        return MapTermo;
    }
}