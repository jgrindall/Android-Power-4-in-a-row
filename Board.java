package MinMaxAlgo;

import android.app.Activity;
import android.util.Log;
import android.widget.Switch;

import SharePref.GamePref;

/**
 * Created by ahmed on 08/05/2016.
 */
public class Board {
    public static final int NUMX = 7;
    public static final int NUMY = 6;
    int curPlayer;
    State state ;
    int depth;
    public Board(Activity activity){
        int [] [] a = new int [NUMX][NUMY];
        state = new State(a);
        curPlayer = Players.PLAYER1;
        GamePref pref = new GamePref(activity);
        int diff = pref.getDiff();
        switch (diff){
            case 0:depth=2;
                break;
            case 1:depth=4;
                break;
            case 2:depth=5;
                break;
        }
    }
    public void setDifficulty(int d){

    }

    public int getPlayersGo(){

        return curPlayer;
    }

    public void reset(){
        for (int i=0;i<state.getArray().length;i++){
            for (int j=0;j<state.getArray()[i].length;j++){
                state.getArray()[i][j]=0;

            }
        }

    }

    public void alternateTurn(){
        if (curPlayer == Players.PLAYER1){
            curPlayer=Players.PLAYER2;
        }
        else{
            curPlayer=Players.PLAYER1;
        }

        Log.i("s",curPlayer+"");

    }

    public boolean colFull(int b){
        for (int i=0;i<state.getArray()[b].length;i++){
            if (state.getArray()[b][i]==0){
                return false;
            }

        }
        return true;

    }

    public int getNumSpaces(){
        int count =0;
        for (int i=0;i<state.getArray().length;i++){
            for(int j=0;j<state.getArray()[i].length;j++){
                if (state.getArray()[i][j]==0){
                    count++;
                }
            }
        }
        return count;

    }

    public int getStepsDown(int col){
        int count=0;
        for (int i=0;i<state.getArray()[0].length;i++){
            if (state.getArray()[col][i]==0){
                count++;
            }
        }

        return count-1;

    }

    public void pushCol(int x,boolean y){
         state.push(x,curPlayer);
        Log.i("psss", "push(" + x + "," + curPlayer + ")");


    }

    public APoint [] checkWin(){
        APoint [] a =state.checkWin(curPlayer);
        return a;
    }

    public APoint getBestPlay(){
        Node parent = new Node(null,state);
        TreeGenerator g = new TreeGenerator(depth);
        g.geneate(parent,0);

        MinMax minMax = new MinMax(depth);
       APoint point = minMax.getMovment(parent);
       Log.i("dd",point.toString());
        return point;

    }
}
