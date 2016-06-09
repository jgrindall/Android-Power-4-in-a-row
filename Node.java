package MinMaxAlgo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ahmed on 07/05/2016.
 */
public class Node {
    Node parent ;
    ArrayList<Node> childs = new ArrayList<Node>();
    State state;
    int evaluation;

    public boolean isWinningNode(int player){return state.checkwon(player);}
    public State getState() {
        return state;
    }

    public Node(Node parent,State s){
        this.parent=parent;
        this.state = s;

    }
    public void addChild(Node n){
        childs.add(n);

    }
    public int getMax(){
        int [] a = new int[childs.size()];
        for (int i=0;i<childs.size();i++){
            a[i]=childs.get(i).evaluation;
        }
        Arrays.sort(a);
        this.evaluation = a[childs.size()-1];
        return a[childs.size()-1];

    }
    public int getMin(){
        int [] a = new int[childs.size()];
        for (int i=0;i<childs.size();i++){
            a[i]=childs.get(i).evaluation;
        }
        Arrays.sort(a);
        this.evaluation = a[0];
        return a[0];

    }

    public void setEvaluation(int a)
    {
        this.evaluation=a;
    }

    public void setEvaluations(){
        int ev =0;
        HeristuicFunction function = new HeristuicFunction(2, 1);
        for (int i=0;i<childs.size();i++){
            childs.get(i).setEvaluation(function.h(childs.get(i).getState()));
        }



    }

    public boolean isLastNode(){
        return childs.size()==0;
    }
    public boolean isLastParent(){
        for(int i=0;i<childs.size();i++){
            if (!childs.get(i).isLastNode()){
                return false;
            }
        }
        return true;
    }

}
