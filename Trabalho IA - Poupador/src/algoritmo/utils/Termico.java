package algoritmo.utils;
public class Termico {
    public static int[][] termico(int[][] matriz, int x, int y){
        for(int i = 0;i<matriz.length;i++){
            for(int j = 0;j<matriz.length;j++){
                matriz[i][j]=40-(Math.abs(i-y)+Math.abs(j-x));
            }
        }
        return matriz;
    }


}