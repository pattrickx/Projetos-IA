package algoritmo.utils;

import java.util.ArrayList;

import static algoritmo.utils.MapsAndConsts.*;
import static algoritmo.utils.MapsAndConsts.Cima;

public class BestDir{
    public static int BestDir(){
        int Dir=0;
        double Menor = -Double.POSITIVE_INFINITY;
        ArrayList<Integer> DirPos= new ArrayList<>();
        if (utils.MovementIsPossible(1, 0) && MapHap[Y][X + 1] >= Menor) {
            DirPos.add(Direita);
            Menor = MapHap[Y][X + 1];
            Dir = Direita;
        }
        if (utils.MovementIsPossible(-1, 0) && MapHap[Y][X - 1] >= Menor) {
            if (MapHap[Y][X - 1] == Menor) {
                DirPos.add(Esquerda);
            } else {
                DirPos.clear();
                DirPos.add(Esquerda);
            }

            Menor = MapHap[Y][X - 1];
            Dir = Esquerda;
        }
        if (utils.MovementIsPossible(0, 1) && MapHap[Y + 1][X] >= Menor) {
            if (MapHap[Y + 1][X] == Menor) {
                DirPos.add(Baixo);
            } else {
                DirPos.clear();
                DirPos.add(Baixo);
            }
            Menor = MapHap[Y + 1][X];
            Dir = Baixo;
        }
        if (utils.MovementIsPossible(0, -1) && MapHap[Y - 1][X] >= Menor) {
            if (MapHap[Y - 1][X] == Menor) {
                DirPos.add(Cima);
            } else {
                DirPos.clear();
                DirPos.add(Cima);
            }
            Menor = MapHap[Y - 1][X];
            Dir = Cima;
        }
        if (Dir == 0) {
            Dir = (int) (Math.random() * 5);

        }
        if (DirPos.size() >= 1) {
            int aux = (int) (Math.random() * DirPos.size());
            Dir = DirPos.get(aux);
        }
        return Dir;
    }
}