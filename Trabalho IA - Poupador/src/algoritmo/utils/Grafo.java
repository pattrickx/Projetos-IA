package algoritmo.utils;

import java.util.ArrayList;

public class Grafo{
    CVM cvm;
    public ArrayList<no> nos;
    public Grafo(CVM cvm){
        this.cvm = cvm;
        nos = new ArrayList<>();
    }


    public void MontarGrafo(){
        nos.clear();
        int id =0;
        for (int y = 0; y < cvm.MapPos.length; y++) {
            for (int x = 0; x < cvm.MapPos.length; x++) {
                no n = new no();
                n.x = x;
                n.y = y;
                if( cvm.MapObj[y][x] == null){
                    n.peso = 0.0;
                    n.tipo = 0;
                }else{
                    n.peso = cvm.MapObj[y][x].peso;
                    n.tipo = cvm.MapObj[y][x].tipo;
                }


                n.id = y*30+x; // formula para descobrir id
                n.h = 0;
                n.f = 0;
                if (x+1 > -1 && x+1 < 30 && y > -1 && y < 30 ) {
                        double peso = cvm.MapObj[y][x + 1]==null?0:cvm.MapObj[y][x + 1].peso;
                        n.arestas.add(new aresta(peso, y*30+(x+1),n.id ));
                }
                if (x-1 > -1 && x-1 < 30 && y > -1 && y < 30 ) {
                    double peso = cvm.MapObj[y][x - 1]==null?0:cvm.MapObj[y][x - 1].peso;
                    n.arestas.add(new aresta(peso, y*30+(x-1), n.id));
                }
                if (x > -1 && x < 30 && y+1 > -1 && y+1 < 30 ) {
                    double peso = cvm.MapObj[y + 1][x]==null?0:cvm.MapObj[y + 1][x].peso;
                    n.arestas.add(new aresta(peso, ((y + 1)*30)+x, n.id));
                }
                if (x > -1 && x < 30 && y-1 > -1 && y-1 < 30 ) {
                    double peso = cvm.MapObj[y - 1][x]==null?0:cvm.MapObj[y - 1][x].peso;
                    n.arestas.add(new aresta(peso, ((y - 1)*30)+x, n.id));
                }

                    nos.add(n);
            }
        }
    }
}