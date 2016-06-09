package MinMaxAlgo;

import android.util.Log;

/**
 * Created by ahmed on 07/05/2016.
 */
public class State {
    int a[][] = new int[7][6];
    public final int PLAYER_ONE = 1;
    public final int PLAYER_TWO = 2;

    public State(int[][] a) {
        this.a = a;

    }

    public int[][] getArray() {
        return a;
    }

    public boolean push(int y, int player) {

        for (int i = 5; i >= 0; i--) {
            if (a[y][i] == 0) {
                a[y][i] = player;
                return true;
            }
        }
        return false;
    }

    public boolean checkwon(int player) {
        //check rows
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i][j + 1] == player) && (a[i][j + 2] == player) && (a[i][j + 3] == player)) {
                    return true;
                }
            }
        }

        //check columns

        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 4; i++) {
                if ((a[i][j] == player) && (a[i + 1][j] == player) && (a[i +2][j] == player) && (a[i + 3][j] == player)) {
                    return true;
                }
            }
        }
        //check diagonal
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i + 1][j + 1] == player) && (a[i + 2][j + 2] == player) && (a[i + 3][j + 3] == player)) {
                    return true;
                }
            }
        }
        for (int i = 6; i > 2; i--) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i - 1][j + 1] == player) && (a[i - 2][j + 2] == player) && (a[i - 3][j + 3] == player)) {
                    return true;
                }
            }
        }
        return false;

    }

    public  APoint[] checkWin(int player){

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i][j + 1] == player) && (a[i][j + 2] == player) && (a[i][j + 3] == player)) {
                APoint [] p = new APoint[4];
                p[0] = new APoint(i,j);
                p[1] = new APoint(i,j+1);
                p[2] = new APoint(i,j+2);
                p[3] = new APoint(i,j+3);
                return p;
            }
        }
    }

    //check columns

        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 4; i++) {
                if ((a[i][j] == player) && (a[i + 1][j] == player) && (a[i +2][j] == player) && (a[i + 3][j] == player)) {

                APoint [] p = new APoint[4];
                p[0] = new APoint(i,j);
                p[1] = new APoint(i+1,j);
                p[2] = new APoint(i+2,j);
                p[3] = new APoint(i+3,j);
                    Log.i("win",p[0].toString()+" "+p[1].toString()+" "+p[2].toString()+" "+p[3].toString());
                return p;
            }
        }
    }
    //check diagonal
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i + 1][j + 1] == player) && (a[i + 2][j + 2] == player) && (a[i + 3][j + 3] == player)) {
                APoint [] p = new APoint[4];
                p[0] = new APoint(i,j);
                p[1] = new APoint(i+1,j+1);
                p[2] = new APoint(i+2,j+2);
                p[3] = new APoint(i+3,j+3);
                return p;
            }
        }
    }
        for (int i = 6; i > 2; i--) {
            for (int j = 0; j < 3; j++) {
                if ((a[i][j] == player) && (a[i - 1][j + 1] == player) && (a[i - 2][j + 2] == player) && (a[i - 3][j + 3] == player)) {
                APoint [] p = new APoint[4];
                p[0] = new APoint(i,j);
                p[1] = new APoint(i-1,j+1);
                p[2] = new APoint(i-2,j+2);
                p[3] = new APoint(i-3,j+3);
                return p;
            }
        }
    }

    return null;
}

}




