//package algoritmo.utils;
//
//import java.util.ArrayList;
//
//
//
//public class Termico {
//    public  MapsAndConsts MapsConsts =  new MapsAndConsts();
//    utils U;
//    public Termico(MapsAndConsts MapsConsts){
//
//        this.MapsConsts=MapsConsts;
//        U = new utils(MapsConsts);
//    }
//
//    public int[][] termico(Grafo gStar,int xi,int yi){
//        int[][] MapTermo = new int[30][30];
//        int bid = MapsConsts.ids[yi][xi];
//
//        no ni = gStar.nos.get(bid);
//        ni.pai=new no();
//        ni.pai.id =-1;
//
//        MapTermo[ni.y][ni.x] = 1;
//
//        ArrayList<no> nos = new ArrayList<>();
//        nos.add(ni);
//        no aux = new no();
//
//        while(!nos.isEmpty()) {
//
//            aux = nos.get(0);
//            aux.v = true;
//            nos.remove(0);
////            U.Print(MapsConsts.MapPos[aux.y][aux.x]+"\n");
//
//            for (int i = 0; i < aux.arestas.size(); i++) {
//                no fi = gStar.nos.get(aux.arestas.get(i).destino);
//                if (fi.type==1){
//                    MapTermo[fi.y][fi.x]= 0;
//                }
//                if(fi.type!=1 && !fi.v && MapTermo[fi.y][fi.x]==0){
////                    System.out.println(fi.type+":"+MapsConsts.MapPos[fi.y][fi.x]);
//                    MapTermo[fi.y][fi.x]=MapTermo[aux.y][aux.x]+1;
//                    nos.add(fi);
//                }
//
//            }
//        }
//
//
//        U.Print("\n");
//        for (int i = 0; i < MapTermo.length; i++) {
//            U.Print("\n");
//            for (int j = 0; j < MapTermo.length; j++)
//                U.Print(MapTermo[i][j] + " ");
//        }
//        return MapTermo;
//    }
//
//}