package algoritmo.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static algoritmo.utils.MapsAndConsts.*;

public class  utils{

    public utils(){

    }

    public static void Print(String str) {
        System.out.print(str);
    }

    public static boolean Valid(int x, int y) {
        if (x > -1 && x < 30 && y > -1 && y < 30 && MapPos[y][x] != 1)
            return true;
        return false;
    }

    public static boolean LadraoVisible() {
        for (int y = 0; y < MapVis.length; y++) {
            for (int x = 0; x < MapVis.length; x++) {
                if (MapVis[y][x] > 199) {
                    return true;
                }
            }
        }
        for (int y = 0; y < MapOlf.length; y++) {
            for (int x = 0; x < MapOlf.length; x++) {
                if (MapOlf[y][x] > 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean MovementIsPossible(int x, int y) {
        for (int i : Proibido) {
            if (!Valid(X+x,Y+y) || i == MapVis[2 + y][2 + x] || (LadraoVisible() && MapVis[2 + y][2 + x] == 4))
                return false;
        }
        if (MapOlf[1 + y][1 + x] > 0)
            return false;
        if( MapVis[2 + y][2 + x] == 5 && NumeroDeMoedas<5)
            return false;
        if(x==0) {
            for(int i =0;i<5;i++) {
                if (MapVis[2 + y][i] > 199 || MapVis[2 + y * 2][i] > 199)
                    return false;
            }
        }else{
            for(int i =0;i<5;i++) {
                if (MapVis[i][2 + x] > 199 || MapVis[i][2 + x * 2] > 199)
                    return false;
            }
        }

        return true;
    }

    public static String Direcao(int D){
        if(D==Direita)
            return "Direita";
        if(D==Baixo)
            return "Baixo";
        if(D==Esquerda)
            return "Esquerda";
        if(D==Cima)
            return "Cima";
        return "Parado";
    }

    public static void Area(int x, int y, double peso, int size) {
        for (int i = -size; i <= size; i++) {
            for (int j = -size; j <= size; j++) {
                if (Valid(x + j, y + i)) {

                    int termo = Math.abs(i) + Math.abs(j) + 1;
                    MapHap[y + i][x + j] += (int) (peso / termo);
                } else {
                    break;
                }
            }
        }
    }

    public static void AreaNivel(int x, int y, double peso, int size) {
        for (int i = -size; i <= size; i++) {
            for (int j = -size; j <= size; j++) {
                if (Valid(x + j, y + i)) {
                    MapHap[y + i][x + j] = peso;
                } else {
                    break;
                }
            }
        }
    }



    public static void Happiness() {

        for (int y = 0; y < MapVis.length; y++) {
            for (int x = 0; x < MapVis.length; x++) {
                if (Valid((X + x - 2), (Y + y - 2))) {
                    if (MapVis[y][x] > 199) {
                        Area((X + x - 2), (Y + y - 2), -100 * (NumeroDeMoedas + 1), 2);

                    } else if (MapVis[y][x] == 4 || (MapVis[y][x] == 3 && NumeroDeMoedas > 0)) {
                        Area((X + x - 2), (Y + y - 2), 10, 2);

                    } else if (MapVis[y][x] == 1 || MapVis[y][x] > 100) {
                        MapHap[Y + y - 2][X + x - 2] = -Double.POSITIVE_INFINITY;

                    } else if (MapVis[y][x] == 5) {
                        if (NumeroDeMoedas > 5 && LadraoVisible()) {
                            Area((X + x - 2), (Y + y - 2), 10 * NumeroDeMoedas, 0);

                        } else {
                            Area((X + x - 2), (Y + y - 2), -50 * (NumeroDeMoedas + 1), 0);
                        }
                    }
                }

            }
        }


    }

    public static void updateMaps() {
        for (int y = 0; y < MapVis.length; y++) {
            for (int x = 0; x < MapVis.length; x++) {
                if (Valid((X + x - 2), (Y + y - 2))) {
                    if (MapPos[(Y + y - 2)][(X + x - 2)] > 199) {
                        if (MapVis[y][x] < 199) {
                            AreaNivel((X + x - 2), (Y + y - 2), -10, 2);
                        } else {
                            Area((X + x - 2), (Y + y - 2), 10, 2);
                        }
                    } else if (MapPos[(Y + y - 2)][(X + x - 2)] == 4) {
                        if (MapVis[y][x] != 4) {
                            AreaNivel((X + x - 2), (Y + y - 2), -10, 2);
                        } else {
                            Area((X + x - 2), (Y + y - 2), 10, 2);
                        }
                    }
                    MapPos[(Y + y - 2)][(X + x - 2)] = MapVis[y][x];
                }

                if (MapVis[y][x] == 1) {
                    MapPos[(Y + y - 2)][(X + x - 2)] = MapVis[y][x];
                    MapHap[(Y + y - 2)][(X + x - 2)] = -Double.POSITIVE_INFINITY;

                }
            }
        }

    }

    public String CallProcess(String command) {
        try {

            final Process sirDesc = Runtime.getRuntime().exec(command);

            final java.io.InputStream inStreamBase = sirDesc.getInputStream();

            final InputStreamReader inStreamISR = new InputStreamReader(inStreamBase);
            final BufferedReader inStream = new BufferedReader(inStreamISR);

            String inLine;
            final StringBuilder result = new StringBuilder();
            while (null != (inLine = inStream.readLine()))
                result.append(inLine);

            return result + "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}