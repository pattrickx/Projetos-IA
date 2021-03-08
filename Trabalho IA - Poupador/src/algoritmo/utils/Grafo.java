package algoritmo.utils;
import java.util.ArrayList;

import algoritmo.utils.aresta;


public class Grafo{
    public int length;
    public ArrayList<no> nos;
    public MapsAndConsts MapsConsts =  new MapsAndConsts();
    utils U;
    public Grafo(MapsAndConsts MapsConsts){
        this.MapsConsts=MapsConsts;
        nos = new ArrayList<>();
        U = new utils(MapsConsts);
    }

    public void MontarGrafo(){
        U.Print("Montando Grafo\n");
        nos.clear();
        for (int y = 0; y < MapsConsts.MapPos.length; y++) {
            for (int x = 0; x < MapsConsts.MapPos.length; x++) {
                if (x > -1 && x < 30 && y > -1 && y < 30) {
                    no n = new no();
                    n.x = x;
                    n.y = y;
                    n.peso = MapsConsts.MapHap[y][x];
                    n.type = MapsConsts.MapPos[y][x];
                    n.id = MapsConsts.ids[y][x];
                    n.h = 0;
                    n.f = 0;

                    if (x+1 > -1 && x+1 < 30 && y > -1 && y < 30 ) {
                        n.arestas.add(new aresta(MapsConsts.MapHap[y][x + 1], MapsConsts.ids[y][x+1], MapsConsts.ids[y][x], 3));
                    }
                    if (x-1 > -1 && x-1 < 30 && y > -1 && y < 30 ) {
                        n.arestas.add(new aresta(MapsConsts.MapHap[y][x - 1], MapsConsts.ids[y][x-1], MapsConsts.ids[y][x], 4));
                    }
                    if (x > -1 && x < 30 && y+1 > -1 && y+1 < 30 ) {
                        n.arestas.add(new aresta(MapsConsts.MapHap[y + 1][x], MapsConsts.ids[y + 1][x], MapsConsts.ids[y][x], 2));
                    }
                    if (x > -1 && x < 30 && y-1 > -1 && y-1 < 30 ) {
                        n.arestas.add(new aresta(MapsConsts.MapHap[y - 1][x], MapsConsts.ids[y - 1][x], MapsConsts.ids[y][x], 1));
                    }

                    nos.add(n);

                }

            }
        }
    }

}

