public class test {
    public static void main(String[] args) {
        int k = (int)(2000000000 * Math.random());
        int n = (int)(3000000 * Math.random());
        System.out.println(k);
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.print((int)(2000000000 * Math.random()+1)+"  ");
        }
    }
}