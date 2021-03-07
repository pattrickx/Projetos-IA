package algoritmo.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static algoritmo.utils.MapsAndConsts.*;

public class Roleta{

    public Roleta(){

    }
    static class PesoDir{
        public int Dir;
        public double peso;
        public PesoDir(int Dir,double peso){
            this.Dir = Dir;
            this.peso = peso;
        }
    }
    public static int get_dir(){
        Double Soma=0.0;
        ArrayList<PesoDir> pesosDir= new ArrayList<>();
        if (utils.MovementIsPossible(1,0)) {
            PesoDir aux = new PesoDir(Direita,MapHap[Y][X+1]);
            pesosDir.add(aux);
            Soma+=MapHap[Y][X+1];
        }
        if (utils.MovementIsPossible(-1,0)) {
            PesoDir aux = new PesoDir(Esquerda,MapHap[Y][X-1]);
            pesosDir.add(aux);
            Soma+=MapHap[Y][X-1];
        }
        if (utils.MovementIsPossible(0,-1)) {
            PesoDir aux = new PesoDir(Cima,MapHap[Y-1][X]);
            pesosDir.add(aux);
            Soma+=MapHap[Y-1][X];
        }
        if (utils.MovementIsPossible(0,1)) {
            PesoDir aux = new PesoDir(Baixo,MapHap[Y+1][X]);
            pesosDir.add(aux);
            Soma+=MapHap[Y+1][X];
        }
        Collections.sort(pesosDir, new Comparator<PesoDir>(){
            public int compare(PesoDir n1, PesoDir n2){
                return  Integer.valueOf((int)(n2.peso)).compareTo((int)(n1.peso));
            }
        });
        double spin_whell = 0.0;
        for(int i = 0;i<pesosDir.size();i++){
            spin_whell+=(double) (Math.random() * Soma);
            if(spin_whell>=pesosDir.get(i).peso){
                return pesosDir.get(i).Dir;
            }
        }
        if(pesosDir.isEmpty())
            return (int) (Math.random() * 5);
        return pesosDir.get(pesosDir.size()-1).Dir;
    }
}