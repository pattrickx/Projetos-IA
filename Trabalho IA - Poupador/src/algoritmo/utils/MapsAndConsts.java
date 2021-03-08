package algoritmo.utils;

import java.util.ArrayList;

public class MapsAndConsts {
    public int Parado = 0;
    public int Cima = 1;
    public int Baixo = 2;
    public int Direita = 3;
    public int Esquerda = 4;
    public int[] Proibido = {-1, -2, 1};
    public double[][] MapHap;
    public int[][] MapPos;
    public int[][] MapVis;
    public int[][] MapOlf;
    public int[][] ids;
    public int NumeroDeMoedas;
    public int X;
    public int Y;
    public int Xb;
    public int Yb;
    public ArrayList<Integer> Dirs;
    public MapsAndConsts(){
        System.out.println(Math.random()+" MAps e constantes");
        MapHap = new double[30][30];
        MapPos = new int[30][30];
        MapVis = new int[5][5];
        MapOlf = new int[3][3];
        ids = new int[30][30];
        Dirs= new ArrayList<>();
        NumeroDeMoedas= 0;
        X = 0;
        Y = 0;
        Xb = 0;
        Yb = 0;

    }
}