package MinMaxAlgo;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by ahmed on 08/05/2016.
 */
public class MinMax {
    int depth;
    public MinMax(int depth){
        this.depth=depth;
    }

    public APoint getMovment(Node parent){
        int a = excute(parent,0);
        //Log.i("aaaaaaaa",a+"");
        int index = 0;
        for (int i=0;i<parent.childs.size();i++){
            if (parent.childs.get(i).evaluation==a){
               index = i;
                break;
            }
        }
        int [] [] pa = parent.getState().getArray();
        int [] [] ch = parent.childs.get(index).getState().getArray();
        APoint p = null;
        for (int i=0;i<pa.length;i++){
            for(int j=0;j<pa[i].length;j++){
               if (pa[i][j]!=ch[i][j]){
                   p = new APoint(i,j);
               }
            }
        }
        return p;

    }

    public int excute(Node parent, int cur){
        if (parent.isLastNode()){
            HeristuicFunction f = new HeristuicFunction(2,1);
            parent.setEvaluation(f.h(parent.getState()));
            return f.h(parent.getState());}
        if ((parent.isLastParent())||(cur-1==depth)){
            parent.setEvaluations();
            //System.out.println(parent.getMax());
            if (cur%2==0){return parent.getMax();}
            return parent.getMin();
        }
        else{
            cur++;
            if (cur%2==1){
                int a [] = new int [parent.childs.size()];
                for (int i=0;i<parent.childs.size();i++){
                    a[i]=excute(parent.childs.get(i), cur);

                }
                Arrays.sort(a);
                parent.setEvaluation(a[parent.childs.size()-1]);
                return a[parent.childs.size()-1];
            }
            else{
                int a [] = new int [parent.childs.size()];
                if (cur%2==0){

                    for (int i=0;i<parent.childs.size();i++){
                        a[i]=excute(parent.childs.get(i), cur);

                    }
                    Arrays.sort(a);

                }
                parent.setEvaluation(a[0]);
                return a[0];
            }


        }



    }

}

