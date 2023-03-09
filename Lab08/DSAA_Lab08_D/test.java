public class test {
    public static void main(String[] args) {
        System.out.println(500000);
        for (int i = 0; i < 500000; i++) {
            System.out.print((int)(Math.random()*10000000)+1 + " ");
        }
    }
}