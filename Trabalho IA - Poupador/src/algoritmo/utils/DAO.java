package algoritmo.utils;

import java.util.ArrayList;

import algoritmo.utils.Grafo;

public class DAO {
    private ArrayList<no> lista = new ArrayList<>();

    private Grafo g[];
    public DAO(int MapHap[][], int MapPos[][], int x, int y) {

        int Starti = y-5>=0?y-5:0;
        int Startj = x-5>=0?x-5:0;
        int Finali = Starti+10;
        int Finalj = Startj+10;
        int id = 0;
        for(int i=Starti; i<=Finali;i++){
            for(int j=Startj; j<=Finalj;j++){
                no p = new no();
                p.x = i;
                p.y = j;
                p.peso = MapHap[j][i];
                p.type = MapPos[j][i];
                p.id = id;
                id++;

            }
        }





    }

    public Grafo[] getGrafo(){

        return g;
    }







}