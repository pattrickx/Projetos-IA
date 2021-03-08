package algoritmo.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Roleta{
    MapsAndConsts MapsConsts;
    utils U;
    public Roleta(MapsAndConsts MapsConsts){
        U = new utils(MapsConsts);
        this.MapsConsts = MapsConsts;

    }
    static class PesoDir{
        public int Dir;
        public double peso;

        public PesoDir(int Dir,double peso){
            this.Dir = Dir;
            this.peso = peso;
        }
    }
    public int get_dir(){
        Double Soma=0.0;
        ArrayList<PesoDir> pesosDir= new ArrayList<>();
        if (U.MovementIsPossible(1,0)) {
            PesoDir aux = new PesoDir(MapsConsts.Direita,MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X+1]);
            pesosDir.add(aux);
            Soma+=MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X+1];
        }
        if (U.MovementIsPossible(-1,0)) {
            PesoDir aux = new PesoDir(MapsConsts.Esquerda,MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X-1]);
            pesosDir.add(aux);
            Soma+=MapsConsts.MapHap[MapsConsts.Y][MapsConsts.X-1];
        }
        if (U.MovementIsPossible(0,-1)) {
            PesoDir aux = new PesoDir(MapsConsts.Cima,MapsConsts.MapHap[MapsConsts.Y-1][MapsConsts.X]);
            pesosDir.add(aux);
            Soma+=MapsConsts.MapHap[MapsConsts.Y-1][MapsConsts.X];
        }
        if (U.MovementIsPossible(0,1)) {
            PesoDir aux = new PesoDir(MapsConsts.Baixo,MapsConsts.MapHap[MapsConsts.Y+1][MapsConsts.X]);
            pesosDir.add(aux);
            Soma+=MapsConsts.MapHap[MapsConsts.Y+1][MapsConsts.X];
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