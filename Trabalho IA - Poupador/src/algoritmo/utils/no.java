package algoritmo.utils;

import java.util.ArrayList;

public class no {
    public int x;
    public int y;
    public double peso = 0.0;
    public int tipo = 0;
    public int id;
    public double h;
    public double f;
    public boolean v = false;
    public int dir;
    public algoritmo.utils.no pai;

    public ArrayList<aresta> arestas;

    public no() {
        arestas = new ArrayList<>();
    }
}