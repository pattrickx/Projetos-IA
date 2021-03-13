package algoritmo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Roleta {
    CVM cvm;
    public Roleta(CVM cvm){
        this.cvm=cvm;
    }
    static class PesoDir{
        public int Dir;
        public double peso;
        public PesoDir(int Dir,double peso){
            this.Dir = Dir;
            this.peso = peso;
        }
    }
    public boolean Valido(int x, int y){
        if(x<0 || y<0 || x>29 || y>29)
            return false;
        if(cvm.MapObj[y][x]!=null) {
            if(cvm.MapObj[y][x].tipo == 1 ||cvm.MapObj[y][x].tipo>100)
                return false;
            if(cvm.NumeroDeMoedas<5 && cvm.MapObj[y][x].tipo==5)
                return false;
        }
        return true;
    }
    public  String Direcao(int D){
        if(D==cvm.Direita)
            return "Direita";
        if(D==cvm.Baixo)
            return "Baixo";
        if(D==cvm.Esquerda)
            return "Esquerda";
        if(D==cvm.Cima)
            return "Cima";
        return "Parado";
    }
    public int Roleta(){

        ArrayList<PesoDir> pesosDir= new ArrayList<>();
        int X = cvm.X;
        int Y = cvm.Y;
        double menor=0;
        if (Valido(X+1,Y)) {
            PesoDir aux = new PesoDir(cvm.Direita,cvm.MapFeli[Y][X+1]);
            pesosDir.add(aux);
            if(menor>cvm.MapFeli[Y][X+1]){
                menor=cvm.MapFeli[Y][X+1];
            }
        }
        if (Valido(X-1,Y)) {
            PesoDir aux = new PesoDir(cvm.Esquerda,cvm.MapFeli[Y][X-1]);
            pesosDir.add(aux);
            if(menor>cvm.MapFeli[Y][X-1]){
                menor=cvm.MapFeli[Y][X-1];
            }
        }
        if (Valido(X,Y-1)) {
            PesoDir aux = new PesoDir(cvm.Cima,cvm.MapFeli[Y-1][X]);
            pesosDir.add(aux);
            if(menor>cvm.MapFeli[Y-1][X]){
                menor=cvm.MapFeli[Y-1][X];
            }
        }
        if (Valido(X,Y+1)) {
            PesoDir aux = new PesoDir(cvm.Baixo,cvm.MapFeli[Y+1][X]);
            pesosDir.add(aux);
            if(menor>cvm.MapFeli[Y+1][X]){
                menor=cvm.MapFeli[Y+1][X];
            }
        }
        Collections.sort(pesosDir, new Comparator<PesoDir>(){
            public int compare(PesoDir n1, PesoDir n2){
                return  Integer.valueOf((int)(n2.peso)).compareTo((int)(n1.peso));
            }
        });
        Double Soma=0.0;
        for(PesoDir i:pesosDir){
            i.peso+=Math.abs(menor);
            i.peso+=(pesosDir.get(0).peso/2);
            //System.out.println("Dir "+Direcao(i.Dir)+" Peso: "+i.peso);
            Soma+= i.peso;
        }

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