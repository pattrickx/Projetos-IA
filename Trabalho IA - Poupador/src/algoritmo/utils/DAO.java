package algoritmo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import algoritmo.utils.aresta;
import algoritmo.utils.grafo;
import algoritmo.utils.ponto;
public class DAO {
    private ArrayList<ponto> lista = new ArrayList<>();

    private grafo g[];
    public DAO(int MapHap[][], int MapPos[][], int x, int y) {

        int Starti = y-5>=0?y-5:0;
        int Startj = x-5>=0?x-5:0;
        int Finali = Starti+10;
        int Finalj = Startj+10;
        int id = 0;
        for(int i=Starti; i<=Finali;i++){
            for(int j=Startj; j<=Finalj;j++){
                ponto p = new ponto();
                p.x = i;
                p.y = j;
                p.peso = MapHap[j][i];
                p.type = MapPos[j][i];
                p.id = id;
                id++;

            }
        }





    }

    public grafo[] getGrafo(){

        return g;
    }







}