package algoritmo.utils;

import algoritmo.utils.grafo;
public class dijkstra {
    long time_ms;
    public int extract_min(float Q[],boolean S[]){
        int min=-1;
        for(int v=0; v<Q.length;v++){     //Theta(N)
            if(S[v]==false){
                if(min==-1)
                    min=v;
                else if(Q[v]<Q[min])
                    min=v;
            }
        }
        return min;
    }
    public void dijkstra(grafo g[],int inicio, int destino){

        //#########################inicialisando variaveis
        int[] fornecedores= new int[g.length];
        float[] distancia = new float[g.length];
        int[] pai = new int[g.length];
        boolean[] S = new boolean[g.length];
        for( int v=0;v< g.length;v++){                              //   N
            distancia[v] = Float.POSITIVE_INFINITY;
            S[v]=false;
            pai[v] = -1;
        }
        distancia[inicio] = 0;
        float[] Q= distancia.clone();
        //###############################################

        //arvore
        //####################################### dijkstra
        long start = System.currentTimeMillis();
        for(int i=0;i<g.length;i++){                                  // N+1
            int v_min = extract_min(Q,S);                             // N*(N+1)
            S[v_min]=true;                                            // N

            if(g[v_min]!=null)                                       //  N
                for(aresta a : g[v_min].arestas){                        // (N+1+1)N/2
                    if(distancia[a.destino]>distancia[v_min]+a.peso){
                        distancia[a.destino]=distancia[v_min]+a.peso;
                        pai[a.destino]=v_min;
                        fornecedores[a.destino]=a.origem;
                    }
                }

        }
        //####################################################     //O(N^2)
        time_ms = System.currentTimeMillis() - start;
        // ArrayList<Contrato> caminho = new ArrayList<>();
        //###################### montando contratos e exibindo
        int vertice_atual= destino;
        String show="";
        while(vertice_atual!=inicio){   // O(N)
            if(pai[vertice_atual]==0 || vertice_atual-1==pai[vertice_atual])
                show=(fornecedores[vertice_atual]+" "+(pai[vertice_atual]+1)+" "+(vertice_atual)+" "+(distancia[vertice_atual]-distancia[pai[vertice_atual]])+"\n")+show;
            else
                show=(fornecedores[vertice_atual]+" "+pai[vertice_atual]+" "+vertice_atual+" "+(distancia[vertice_atual]-distancia[pai[vertice_atual]])+"\n")+show;

            vertice_atual=pai[vertice_atual];
        }
        System.out.println(show);

        System.out.println("valor total: "+distancia[destino]);
        System.out.println("tempo: "+ time_ms+" ms");


    }
}
