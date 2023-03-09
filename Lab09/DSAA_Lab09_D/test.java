public class test {
    public static void main(String[] args) {
        int T = 20;
        System.out.println(T);
        while (T-- > 0){
            int N = (int) (Math.random()*7+1);
            int M = (int) (Math.random()*7+1);
            System.out.println(N+" "+M);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print((int) (Math.random()*88+1)+" ");
                }
                System.out.println();
            }
        }
    }
}