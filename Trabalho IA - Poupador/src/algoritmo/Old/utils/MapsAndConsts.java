package algoritmo.utils;

import java.util.ArrayList;

public class MapsAndConsts {
    public int Parado = 0;
    public int Cima = 1;
    public int Baixo = 2;
    public int Direita = 3;
    public int Esquerda = 4;
    public int[] Proibido = {-1, -2, 1};

    public double[][] MapHap = new double[30][30];
    public int[][] MapPos = new int[30][30];
    public int[][] MapTer = new int[30][30];
    public int[][] MapVis = new int[5][5];
    public int[][] MapOlf = new int[3][3];
    public int[][] ids = new int[30][30];

    public int NumeroDeMoedas = 0;
    public int X = 0;
    public int Y = 0;
    public int Xb = 0;
    public int Yb = 0;
    public ArrayList<Integer> Dirs = new ArrayList<>();
    public ArrayList<obj> Objs = new ArrayList<>();
    public MapsAndConsts(){
    }
}