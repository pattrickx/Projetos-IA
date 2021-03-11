//package algoritmo.utils;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
//public class  utils{
//    public MapsAndConsts MapsConsts =  new MapsAndConsts();
//
//    public utils(MapsAndConsts MapsConsts){
//        this.MapsConsts=MapsConsts;
//
//    }
//
//    public  void Print(String str) {
//        System.out.print(str);
//    }
//
//    public  boolean Valid(int x, int y) {
//        if (x > -1 && x < 30 && y > -1 && y < 30 && MapsConsts.MapPos[y][x] != 1)
//            return true;
//        return false;
//    }
//
//    public  boolean LadraoVisible() {
//        for (int y = 0; y < MapsConsts.MapVis.length; y++) {
//            for (int x = 0; x < MapsConsts.MapVis.length; x++) {
//                if (MapsConsts.MapVis[y][x] > 199) {
//                    return true;
//                }
//            }
//        }
//        for (int y = 0; y < MapsConsts.MapOlf.length; y++) {
//            for (int x = 0; x < MapsConsts.MapOlf.length; x++) {
//                if (MapsConsts.MapOlf[y][x] > 3) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public  boolean MovementIsPossible(int x, int y) {
//        for (int i : MapsConsts.Proibido) {
//            if (i == MapsConsts.MapVis[2 + y][2 + x] )
//                return false;
//        }
//        if (MapsConsts.MapOlf[1 + y][1 + x] > 0)
//            return false;
//        if( MapsConsts.MapVis[2 + y][2 + x] == 5 && MapsConsts.NumeroDeMoedas<5)
//            return false;
////        if(x==0) {
////            for(int i =0;i<5;i++) {
////                if (MapsConsts.MapVis[2 + y][i] > 199 || MapsConsts.MapVis[2 + y * 2][i] > 199)
////                    return false;
////            }
////        }else{
////            for(int i =0;i<5;i++) {
////                if (MapsConsts.MapVis[i][2 + x] > 199 || MapsConsts.MapVis[i][2 + x * 2] > 199)
////                    return false;
////            }
////        }
//        if (!Valid(MapsConsts.X+x,MapsConsts.Y+y))
//            return false;
//        if ((LadraoVisible() && MapsConsts.MapVis[2 + y][2 + x] == 4))
//            return false;
//        return true;
//    }
//
//    public  String Direcao(int D){
//        if(D==MapsConsts.Direita)
//            return "Direita";
//        if(D==MapsConsts.Baixo)
//            return "Baixo";
//        if(D==MapsConsts.Esquerda)
//            return "Esquerda";
//        if(D==MapsConsts.Cima)
//            return "Cima";
//        return "Parado";
//    }
//
//    public  void Area(int x, int y, double peso, int size) {
////        Grafo G = new Grafo(MapsConsts);
////        G.MontarGrafo();
////        Termico T= new Termico(MapsConsts);
////        int[][] MapTermo = T.termico(G, x, y);
//        for (int i = -size; i <= size; i++) {
//            for (int j = -size; j <= size; j++) {
//                if (Valid(x + j, y + i)) {
//
//                    int termo = Math.abs(i) + Math.abs(j) + 1;//MapTermo[y + i][x + j];
//                    MapsConsts.MapHap[y + i][x + j] += (int) (peso / termo);
//                } else {
//                    break;
//                }
//            }
//        }
//    }
//
//    public  void AreaNivel(int x, int y, double peso, int size) {
//        for (int i = -size; i <= size; i++) {
//            for (int j = -size; j <= size; j++) {
//                if (Valid(x + j, y + i)) {
//                    MapsConsts.MapHap[y + i][x + j] = peso;
//                } else {
//                    break;
//                }
//            }
//        }
//    }
//
//    public  void Happiness() {
//
//        for (int y = 0; y < MapsConsts.MapVis.length; y++) {
//            for (int x = 0; x < MapsConsts.MapVis.length; x++) {
//                int X=MapsConsts.X + x - 2;
//                int Y=MapsConsts.Y + y - 2;
//                if (Valid(X, Y)) {
//                    if (MapsConsts.MapVis[y][x] > 199) {
//                        Area(X, Y, -100 * (MapsConsts.NumeroDeMoedas + 1), 2);
//
//                    } else if (MapsConsts.MapVis[y][x] == 4 || (MapsConsts.MapVis[y][x] == 3 && MapsConsts.NumeroDeMoedas > 0)) {
//                        Area(X, Y, 10, 2);
//
//                    } else if (MapsConsts.MapVis[y][x] == 1 || MapsConsts.MapVis[y][x] > 100) {
//                        MapsConsts.MapHap[Y][X] = -Double.POSITIVE_INFINITY;
//
//                    } else if (MapsConsts.MapVis[y][x] == 5) {
//                        if (MapsConsts.NumeroDeMoedas > 5 && LadraoVisible()) {
//                            Area(X, Y, 10 * MapsConsts.NumeroDeMoedas, 0);
//
//                        } else {
//                            Area(X, Y, -50 * (MapsConsts.NumeroDeMoedas + 1), 0);
//                        }
//                    }
//                }
//
//            }
//        }
//
//
//    }
//
//    public  void updateMaps() {
//        for (int y = 0; y < MapsConsts.MapVis.length; y++) {
//            for (int x = 0; x < MapsConsts.MapVis.length; x++) {
//                int X=MapsConsts.X + x - 2;
//                int Y=MapsConsts.Y + y - 2;
//                if (Valid(X, Y)) {
//                    if (MapsConsts.MapPos[Y][X] > 199) {
//                        if (MapsConsts.MapVis[y][x] < 199) {
//                            AreaNivel(X, Y, -10, 2);
//                        } else {
//                            Area(X, Y, 10, 2);
//                        }
//                    } else if (MapsConsts.MapPos[Y][X] == 4) {
//                        if (MapsConsts.MapVis[y][x] != 4) {
//                            AreaNivel(X, Y, -10, 2);
//                        } else {
//                            Area(X, Y, 10, 2);
//                        }
//                    }
//                    MapsConsts.MapPos[Y][X] = MapsConsts.MapVis[y][x];
//                }
//
//                if (MapsConsts.MapVis[y][x] == 1) {
//                    MapsConsts.MapPos[Y][X] = MapsConsts.MapVis[y][x];
//                    MapsConsts.MapHap[Y][X] = -Double.POSITIVE_INFINITY;
//
//                }
//            }
//        }
//
//    }
//
//    public String CallProcess(String command) {
//        try {
//
//            final Process sirDesc = Runtime.getRuntime().exec(command);
//
//            final java.io.InputStream inStreamBase = sirDesc.getInputStream();
//
//            final InputStreamReader inStreamISR = new InputStreamReader(inStreamBase);
//            final BufferedReader inStream = new BufferedReader(inStreamISR);
//
//            String inLine;
//            final StringBuilder result = new StringBuilder();
//            while (null != (inLine = inStream.readLine()))
//                result.append(inLine);
//
//            return result + "";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "ERROR";
//    }
//
//}