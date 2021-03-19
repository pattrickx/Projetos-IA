//package algoritmo.utils;
//import algoritmo.utils.Grafo;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//public class Astar {
//
//
//    public  MapsAndConsts MapsConsts =  new MapsAndConsts();
//    public  Grafo gStar;
//    utils U ;
//    public Astar(MapsAndConsts MapsConsts,Grafo gStar){
//        this.MapsConsts=MapsConsts;
//        this.gStar = gStar;
//        U = new utils(MapsConsts);
//    }
//    public void Print(String str) {
//        System.out.print(str);
//    }
//    public int dist(int[] xyi, int[] xyf){
//        return Math.abs(xyi[0] - xyf[0]) + Math.abs(xyi[1] - xyf[1]);
//    }
//
//    public int[] getXY(int id){
//        int x = id%30;
//        int y = (int)(id/29);
//
//        return new int[]{x, y};
//    }
//
//    public  no aStar(int idi, int idf) {
//        U.Print("Star\n");
//
//
////			h = (delta x + delta y)
////			f = peso + h
//
//        no ni = gStar.nos.get(idi);
//        ni.pai=new no();
//        ni.pai.id =-1;
//        no nf = gStar.nos.get(idf);
//        ArrayList<no> nos = new ArrayList<>();
//
//        nos.add(ni);
//        no aux = new no();
//        U.Print("Iniciando Busca\n");
//        while(!nos.isEmpty()){
//
//            aux = nos.get(0);
//            aux.v = true;
//            nos.remove(0);
////			Print("Atual: "+aux.id+"\n");
//
//
//            if(aux.id == idf ||aux.type==4){
//                return aux;
//            }
//
//            for (int i = 0; i < aux.arestas.size(); i++) {
//                no fi = gStar.nos.get(aux.arestas.get(i).destino);
//                if (fi.id != aux.pai.id && !fi.v){
//                    fi.dir = aux.arestas.get(i).dir;
//                    fi.pai = aux;
//                    fi.h = Math.abs(fi.x - nf.x) + Math.abs(fi.y - nf.y);
//                    if(fi.peso==-Double.POSITIVE_INFINITY) {
//                        fi.f = fi.peso;
//                    }else {
//                        fi.f = fi.peso - fi.h;
//                    }
//                    nos.add(fi);
//                }
//            }
//
////			Print("Antes\n");
////			for(int i=0; i < nos.size(); i++){
////				Print(nos.get(i).id+" "+nos.get(i).f+" * ");
////			}
////			Print("\n");
////			Print("\n");
//            Collections.sort(nos, new Comparator<no>(){
//                public int compare(no n1, no n2){
//                    return  Integer.valueOf((int)(n2.f)).compareTo((int)(n1.f));
//                }
//            });
//
//
////			Print("Depois\n");
////			for(int i=0; i < nos.size(); i++){
////				Print(nos.get(i).id+" "+nos.get(i).f+" * ");
////			}
////			Print("\n");
////			Print("\n");
//
//        }
//        return aux;
//
//
//    }
//
//    public  int get_dir(){
//        if (MapsConsts.ids[MapsConsts.Y][MapsConsts.X]!=MapsConsts.ids[MapsConsts.Yb][MapsConsts.Xb] && MapsConsts.MapHap[MapsConsts.Yb][MapsConsts.Xb]>0 && U.LadraoVisible()){//(MapHap[Yb][Xb]>0 && ids[Y][X]!=ids[Yb][Xb] && LadraoVisible()){
//			U.Print("BUSCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//            MapsConsts.Dirs.clear();
//			no n= aStar(MapsConsts.ids[MapsConsts.Y][MapsConsts.X],MapsConsts.ids[MapsConsts.Yb][MapsConsts.Xb]);
//
//			int tx=n.pai.x;
//			int ty=n.pai.y;
//			while (n.id>-1){
//				int D=MapsConsts.Parado;
//				if(n.x>tx){
//					D=MapsConsts.Esquerda;
//				}
//				if(n.x<tx){
//					D=MapsConsts.Direita;
//				}
//				if(n.y>ty){
//					D = MapsConsts.Cima;
//				}
//				if(n.y<ty){
//					D = MapsConsts.Baixo;
//				}
//                MapsConsts.Dirs.add(D);
//				U.Print(n.x+":"+n.y+"("+U.Direcao(D)+") \n");
//				tx=n.x;
//				ty=n.y;
//				n = n.pai;
//			}
////			if(Dirs.size()>1)
////				Dirs.remove(Dirs.size() - 1);
//		}
//
////		if (sensor.getNumeroDeMoedas()==0){
////			Dirs.clear();
////		}
//        int Dir=-1;
//		for(int i = 0;i<MapsConsts.Dirs.size() ;i++){
//			U.Print(U.Direcao(MapsConsts.Dirs.get(i))+" <- ");
//		}
//		if(!MapsConsts.Dirs.isEmpty()) {
//			Dir = MapsConsts.Dirs.get(MapsConsts.Dirs.size() - 1);
//            MapsConsts.Dirs.remove(MapsConsts.Dirs.size() - 1);
//		}
//        U.Print("\n Dir atual: "+U.Direcao(Dir)+"\n");
//		return Dir;
//    }
//}
//
