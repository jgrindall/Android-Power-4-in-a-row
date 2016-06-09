package MinMaxAlgo;

/**
 * Created by ahmed on 07/05/2016.
 */
public class TreeGenerator {


    int depth;

    public TreeGenerator(int depth){
        this.depth = depth;
    }



    public void geneate(Node parent,int cur){
        // System.out.print(cur+"\n");
        if (cur==depth){
            //HeristuicFunction f = new HeristuicFunction(1,2);
            //  System.out.println(f.h(parent.getState()));
            return;
        }
        int player = 2;
        if (cur%2==1){
            player=1;
        }
        int[] [] a1=  copyArray(parent.getState().getArray());
        int[] [] a2=  copyArray(parent.getState().getArray());
        int[] [] a3=  copyArray(parent.getState().getArray());
        int[] [] a4=  copyArray(parent.getState().getArray());
        int[] [] a5=  copyArray(parent.getState().getArray());
        int[] [] a6= copyArray(parent.getState().getArray());
        int[] [] a7= copyArray(parent.getState().getArray());
        State s1 = new State(a1); Node n1 = new Node(parent,s1);

        State s2 = new State(a2); Node n2 = new Node(parent,s2);
        State s3=new State(a3);Node n3 = new Node(parent,s3);
        State s4 = new State(a4);Node n4 = new Node(parent,s4);
        State s5 =new State(a5);Node n5 = new Node(parent,s5);
        State s6 = new State(a6);Node n6 = new Node(parent,s6);
        State s7 = new State(a7);Node n7 = new Node(parent,s7);
        if(s1.push(0,player)){

            parent.addChild(n1);

        }
        if(s2.push(1,player)){

            parent.addChild(n2);



        }
        if(s3.push(2,player)){
            parent.addChild(n3);


        }
        if(s4.push(3,player)){
            parent.addChild(n4);


        }
        if(s5.push(4,player)){
            parent.addChild(n5);

        }
        if(s6.push(5,player)){
            parent.addChild(n6);

        }
        if(s7.push(6,player)){
            parent.addChild(n7);
        }
        cur++;
        if (!n1.isWinningNode(player)){geneate(n1,cur);}
        if (!n2.isWinningNode(player)){geneate(n2,cur);}
        if (!n3.isWinningNode(player)){geneate(n3,cur);}
        if (!n4.isWinningNode(player)){geneate(n4,cur);}
        if (!n5.isWinningNode(player)){geneate(n5,cur);}
        if (!n6.isWinningNode(player)){geneate(n6,cur);}
        if (!n7.isWinningNode(player)){geneate(n7,cur);}






    }

    public int [] [] copyArray(int [] [] a){
        int [] [] copy = new int [7][6];
        for(int i=0;i<copy.length;i++){
            for(int j=0;j<copy[i].length;j++){
                copy[i][j]=a[i][j];
            }
        }
        return copy;
    }
}

