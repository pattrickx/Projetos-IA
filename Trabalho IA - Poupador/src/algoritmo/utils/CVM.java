package algoritmo.utils;

import java.util.ArrayList;

public class CVM{
    public Objeto[][] MapObj = new Objeto[30][30];
    public int[][] MapPos = new int[30][30];
    public double[][] MapFeli = new double[30][30];
    public int[][] MapVis = new int[5][5];
    public int[][] MapOlf = new int[3][3];
    public int[] Nvalido = {-1,-2,0};
    public int X;
    public int Y;
    public int Xb;
    public int Yb;
    public int Parado = 0;
    public int Cima = 1;
    public int Baixo = 2;
    public int Direita = 3;
    public int Esquerda = 4;
    public int NumeroDeMoedas=0;
    public ArrayList<Integer> Dirs = new ArrayList<>();
}