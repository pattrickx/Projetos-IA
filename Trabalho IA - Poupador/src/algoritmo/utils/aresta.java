
package algoritmo.utils;

public class aresta {
    public float peso;
    public int destino ;
    public int origem;
    public int dir;
    public aresta(float peso,int destino,int origem, int dir){
        this.peso=peso;
        this.destino=destino;
        this.origem=origem;
        this.dir = dir;
    }
}