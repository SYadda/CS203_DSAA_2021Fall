import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class test2 {
    static class node{
        int u;
        int v;
        int w;
        public node(int u,int v,int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }
        public String toString(){
            return u+" "+v+" "+w;
        }
        public String toString2(){
            return u+" "+v;
        }
    }
    public static void main(String[] args) {
        ArrayList<node> allNode = new ArrayList<>();
        Random in=new Random();
        int n=100;
        int m=100;
        int p=10;
        int k=2;
        for (int i = 1; i <n ; i++) {
            allNode.add(new node(i,i+1,in.nextInt(100)+1));
        }
//        for (int i = n; i < m+1 ; i++) {
//            allNode.add(new node(in.nextInt(n)+1,in.nextInt(n)+1,in.nextInt(100)+1));
//        }

        allNode.add(new node(n, 1, in.nextInt(100)+1));

        for (int i = 0; i < p+1 ; i++) {
            allNode.add(new node(in.nextInt(n)+1,in.nextInt(n)+1,in.nextInt(100)+1));
        }

//        Collections.shuffle(allNode);
        System.out.println(n+" "+m+" "+p+" "+k);
        for (int i = 0; i <m ; i++) {
            System.out.println(allNode.get(i));
        }
        for (int i = m; i <= m+p; i++) {
            System.out.println(allNode.get(i).toString2());
        }
    }
}
