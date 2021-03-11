//package algoritmo.utils;
//
//import java.util.ArrayList;
//
//public class BestDir{
//    public  MapsAndConsts MapsConsts =  new MapsAndConsts();
//    utils U;
//    public BestDir(MapsAndConsts MapsConsts) {
//        this.MapsConsts = MapsConsts;
//        U = new utils(MapsConsts);
//    }
//    public int BestDir(){
//        int Dir=0;
//        double Menor = -Double.POSITIVE_INFINITY;
//        ArrayList<Integer> DirPos= new ArrayList<>();
//        if (U.MovementIsPossible(1, 0) && MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X + 1] >= Menor) {
//            DirPos.add(MapsConsts.Direita);
//            Menor = MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X + 1];
//            Dir = MapsConsts.Direita;
//        }
//        if (U.MovementIsPossible(-1, 0) && MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X - 1] >= Menor) {
//            if (MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X - 1] == Menor) {
//                DirPos.add(MapsConsts.Esquerda);
//            } else {
//                DirPos.clear();
//                DirPos.add(MapsConsts.Esquerda);
//            }
//
//            Menor = MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X - 1];
//            Dir = MapsConsts.Esquerda;
//        }
//        if (U.MovementIsPossible(0, 1) && MapsConsts.MapHap[MapsConsts.Y + 1][MapsConsts.X] >= Menor) {
//            if (MapsConsts.MapHap[MapsConsts.Y + 1][MapsConsts.X] == Menor) {
//                DirPos.add(MapsConsts.Baixo);
//            } else {
//                DirPos.clear();
//                DirPos.add(MapsConsts.Baixo);
//            }
//            Menor = MapsConsts.MapHap[MapsConsts.Y + 1][MapsConsts.X];
//            Dir = MapsConsts.Baixo;
//        }
//        if (U.MovementIsPossible(0, -1) && MapsConsts.MapHap[MapsConsts.Y - 1][MapsConsts.X] >= Menor) {
//            if (MapsConsts.MapHap[MapsConsts.Y - 1][MapsConsts.X] == Menor) {
//                DirPos.add(MapsConsts.Cima);
//            } else {
//                DirPos.clear();
//                DirPos.add(MapsConsts.Cima);
//            }
//            Menor = MapsConsts.MapHap[MapsConsts.Y - 1][MapsConsts.X];
//            Dir = MapsConsts.Cima;
//        }
//        if (Dir == 0) {
//            Dir = (int) (Math.random() * 5);
//
//        }
//        if (DirPos.size() >= 1) {
//            int aux = (int) (Math.random() * DirPos.size());
//            Dir = DirPos.get(aux);
//        }
//        return Dir;
//    }
//}