package MinMaxAlgo;

/**
 * Created by ahmed on 08/05/2016.
 */

import android.util.Log;

/**
 * Created by ahmed on 07/05/2016.
 */
public class HeristuicFunction {
    int player ;
    int opponent ;

    public HeristuicFunction(int player,int opponent){
        this.player = player;
        this.opponent = opponent;

    }
    public int h(State s){
        if (s.checkwon(player)){
            return 99999999;
        }
        else
        if (s.checkwon(opponent))
            return -99999999;
        else return evaluate(s.getArray());

    }

    public int evaluate(int [] [] a){
        int ev = 0;
        int playerAlmostWin = 0;
        int opponentAlmostWin = 0;
        //check rows
        for(int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                int countPlayer = 0;
                int countOpponent = 0;
                int missingP = 0;
                int missingO=0;
                if (a[i][j]==player){countPlayer++;}else{missingP=i;}
                if (a[i+1][j]==player){countPlayer++;}else{missingP=i+1;}
                if (a[i+2][j]==player){countPlayer++;}else{missingP=i+2;}
                if (a[i+3][j]==player){countPlayer++;}else{missingP=i+3;}
                if (a[i][j]==opponent){countOpponent++;}else{missingO=i;}
                if (a[i+1][j]==opponent){countOpponent++;}else{missingO=i+1;}
                if (a[i+2][j]==opponent){countOpponent++;}else{missingO=i+2;}
                if (a[i+3][j]==opponent){countOpponent++;}else{missingO=i+3;}
                ev+=countPlayer;
                ev-=countOpponent;
                if (countPlayer==3){ playerAlmostWin++;
                    if (j==5){
                        if (a[missingP][j]!=opponent){
                            ev+=6;

                        }}
                    else
                    if ((a[missingP][j+1]!=0)&&(a[missingP][j]!=opponent)){
                        ev+=6;

                    }
                    else if(a[missingP][j]==opponent){ev-=3;playerAlmostWin--;}
                }
                if (countOpponent==3){ opponentAlmostWin++;
                    if (j==5){
                        if (a[missingO][j]!=player){
                            ev-=24;

                        }}
                    else
                    if ((a[missingO][j+1]!=0)&&(a[missingO][j]!=player)){
                        ev-=24;

                    }
                    else if(a[missingO][j]==player){ev+=6;opponentAlmostWin--;}
                }

            }
        }
        //check columns
        for (int i=0;i<7;i++){
            for (int j=5;j>2;j--){
                if ((a[i][j]==player)&&(a[i][j-1]==player)&&(a[i][j-2]==player)&&(a[i][j-3]!=opponent)){
                    ev+=6;
                    playerAlmostWin++;
                }
                else
                if ((a[i][j]==player)&&(a[i][j-1]==player)&&(a[i][j-2]!=opponent)){
                    ev+=2;
                }


                if ((a[i][j]==opponent)&&(a[i][j-1]==opponent)&&(a[i][j-2]==opponent)&&(a[i][j-3]!=player)){
                    ev-=24;
                    opponentAlmostWin++;
                }
                else
                if ((a[i][j]==opponent)&&(a[i][j-1]==opponent)&&(a[i][j-2]!=player)){
                    ev-=4;
                }

            }
        }

        //check diagonal
        for (int i=0;i<4;i++){
            for (int j=0;j<3;j++){
                int playerCounter = 0;
                int opponentCounter= 0;
                int missingIP=0;
                int missingJP = 0;
                int missingIO = 0;
                int missingJO = 0;
                if (a[i][j]==player){playerCounter++;}else{missingIP=i;missingJP=j;}
                if(a[i+1][j+1]==player){playerCounter++;}else{missingIP=i+1;missingJP=j+1;}
                if (a[i+2][j+2]==player){playerCounter++;}else{missingIP=i+2;missingJP=j+1;}
                if(a[i+3][j+3]==player){playerCounter++;}else {missingIP=i+3;missingJP=j+3;}
                if (a[i][j]==opponent){opponentCounter++;}else{missingIO=i;missingJO=j;}
                if(a[i+1][j+1]==opponent){opponentCounter++;}else{missingIO=i+1;missingJO=j+1;}
                if (a[i+2][j+2]==opponent){opponentCounter++;}else{missingIO=i+2;missingJO=j+1;}
                if(a[i+3][j+3]==opponent){opponentCounter++;}else {missingIO=i+3;missingJO=j+3;}
                ev+=playerCounter;
                ev+=opponentCounter;
                if (playerCounter==3){playerAlmostWin++;
                    if (missingJP==5){
                        if(a[missingIP][missingJP]!=opponent){
                            ev+=6;

                        }
                    }
                    else
                    if ((a[missingIP][missingJP+1]!=0)&&(a[missingIP][missingJP]!=opponent)){
                        ev+=6;

                    }
                    else
                    if(a[missingIP][missingJP]==opponent){ev-=3;playerAlmostWin--;}


                }
                if (opponentCounter==3){
                    opponentAlmostWin++;
                   // Log.i("warinig","cc");
                    //Log.i("d",missingI+" "+missingJ);
                    if (missingJO==5){
                        if(a[missingIO][missingJO]!=player){
                          //  Log.i("warinig","cc32");
                            ev-=24;

                        }
                    }
                    else
                    if ((a[missingIO][missingJO+1]!=0)&&(a[missingIO][missingJO]!=player)){
                        //Log.i("warinig","23");
                        ev-=24;

                    }
                    else
                    if(a[missingIO][missingJO]==player){ev+=3;opponentAlmostWin--;}

                }
            }
        }
        for (int i = 6; i > 2; i--) {
            for (int j = 0; j < 3; j++) {
                int playerCounter = 0;
                int opponentCounter= 0;
                int missingIP=0;
                int missingJP = 0;
                int missingIO = 0;
                int missingJO = 0;
                if (a[i][j]==player){playerCounter++;}else{missingIP=i;missingJP=j;}
                if(a[i-1][j+1]==player){playerCounter++;}else{missingIP=i-1;missingJP=j+1;}
                if (a[i-2][j+2]==player){playerCounter++;}else{missingIP=i-2;missingJP=j+1;}
                if(a[i-3][j+3]==player){playerCounter++;}else {missingIP=i-3;missingJP=j+3;}
                if (a[i][j]==opponent){opponentCounter++;}else{missingIO=i;missingJO=j;}
                if(a[i-1][j+1]==opponent){opponentCounter++;}else{missingIO=i-1;missingJO=j+1;}
                if (a[i-2][j+2]==opponent){opponentCounter++;}else{missingIO=i-2;missingJO=j+1;}
                if(a[i-3][j+3]==opponent){opponentCounter++;}else {missingIO=i-3;missingJO=j+3;}
                ev+=playerCounter;
                ev+=opponentCounter;
                if (playerCounter==3){
                    playerAlmostWin++;
                    if (missingJP==5){
                        if(a[missingIP][missingJP]!=opponent){
                            ev+=6;

                        }
                    }
                    else
                    if ((a[missingIP][missingJP+1]!=0)&&(a[missingIP][missingJP]!=opponent)){
                        ev+=6;

                    }
                    else
                    if(a[missingIP][missingJP]==opponent){ev-=3;playerAlmostWin--;}


                }
                if (opponentCounter==3){
                    opponentAlmostWin++;
                   // Log.i("warinig","cc2");
                    if (missingJO==5){
                        if(a[missingIO][missingJO]!=player){
                            ev-=24;

                     //       Log.i("warinig","cc3");
                        }
                    }
                    else
                    if ((a[missingIO][missingJO+1]!=0)&&(a[missingIO][missingJO]!=player)){
                        ev-=24;

                       // Log.i("warinig","cc3");
                    }
                    else
                    if(a[missingIO][missingJO]==player){ev+=6;opponentAlmostWin--;}

                }
            }
        }
        if (playerAlmostWin>=2){
            ev+=25;
        }
        if (opponentAlmostWin>=2){
            ev-=100;
        }

        return ev;
    }
}
