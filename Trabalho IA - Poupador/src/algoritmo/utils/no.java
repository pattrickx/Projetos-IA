package algoritmo.utils;
import algoritmo.utils.aresta;
import java.util.ArrayList;

public class no {
    public int x;
    public int y;
    public double peso;
    public int type;
    public int id;
    public int h;
    public int f;
    public no pai;

    public ArrayList<aresta> arestas;
    public no(){
        arestas=new ArrayList<>();
    }

}