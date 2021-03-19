package algoritmo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Aestrela{
    CVM cvm;
    public Aestrela(CVM cvm){
        this.cvm = cvm;
    }
    public no busca(Grafo grafo,int idi, int idf){
        no ni = grafo.nos.get(idi);
        ni.pai=new no();
        ni.pai.id =-1;
        ni.pai.v = true;

        ArrayList<no> nos = new ArrayList<>();
        nos.add(ni);
        no aux = new no();
        Termico t = new Termico(cvm);
        int [][] term = t.termico(grafo,8,8);
        grafo.MontarGrafo();
        while(!nos.isEmpty()) {
            aux = nos.get(0);
            aux.v = true;
            nos.remove(0);

            if(aux.id == idf || aux.tipo==4 ){
                return aux;
            }
            for (int i = 0; i < aux.arestas.size(); i++) {
                no fi = grafo.nos.get(aux.arestas.get(i).destino);
                if(fi.tipo==1 || fi.tipo>100){
                    fi.v=true;
                }

                if (!fi.v && fi.tipo!=1){
                    fi.pai = aux;
                    fi.h = term[fi.y][fi.x];
                    fi.peso+= aux.peso;
                    fi.f = fi.peso - fi.h;
//                    fi.h = Math.abs(fi.x - 8) + Math.abs(fi.y - 8);
//                    if(fi.peso==-Double.POSITIVE_INFINITY) {
//                        fi.f = fi.peso;
//                    }else {
//                        fi.f = fi.peso - fi.h;
//                    }
//                    System.out.println("id = "+(fi.y * 30 + fi.x)+" : Peso = "+fi.f);

                    nos.add(fi);
                }
            }
            Collections.sort(nos, new Comparator<no>(){
                public int compare(no n1, no n2){
                    return  Integer.valueOf((int)(n2.f)).compareTo((int)(n1.f));
                }
            });
        }
        return aux;
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
        public  int get_dir(no n){
//			U.Print("BUSCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            ArrayList<Integer> Dirs = new ArrayList<>();

			int tx=n.pai.x;
			int ty=n.pai.y;
			while (n.id>-1){
				int D=cvm.Parado;
				if(n.x>tx){
					D=cvm.Esquerda;
				}
				if(n.x<tx){
					D=cvm.Direita;
				}
				if(n.y>ty){
					D = cvm.Cima;
				}
				if(n.y<ty){
					D = cvm.Baixo;
				}
                Dirs.add(D);
//				U.Print(n.x+":"+n.y+"("+U.Direcao(D)+") \n");
				tx=n.x;
				ty=n.y;
				n = n.pai;
			}
//			if(Dirs.size()>1)
//				Dirs.remove(Dirs.size() - 1);


//		if (sensor.getNumeroDeMoedas()==0){
//			Dirs.clear();
//		}
        int Dir=-1;
            for(int i = 0;i<Dirs.size() ;i++){
                System.out.print(Direcao(Dirs.get(i))+" <- ");
            }
            System.out.println("");
		if(!Dirs.isEmpty()) {
			Dir = Dirs.get(Dirs.size() - 1);
            Dirs.remove(Dirs.size() - 1);
		}
            cvm.Dirs=Dirs;
		return Dir;
    }
}