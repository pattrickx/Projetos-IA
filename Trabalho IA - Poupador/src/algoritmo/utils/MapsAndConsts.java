package algoritmo.utils;

import java.util.ArrayList;

public class MapsAndConsts {
    public static int Parado = 0;
    public static int Cima = 1;
    public static int Baixo = 2;
    public static int Direita = 3;
    public static int Esquerda = 4;
    public static double[][] MapHap = new double[30][30];
    public static int[][] MapPos = new int[30][30];
    public static int[][] ids = new int[30][30];
    public static int[][] MapVis = new int[5][5];
    public static int[][] MapOlf = new int[3][3];
    public static int[] Proibido = {-1, -2, 1};
    public static int NumeroDeMoedas= 0;
    public static int X;
    public static int Y;
    public static int Xb;
    public static int Yb;
    public static ArrayList<Integer> Dirs= new ArrayList<>();
}