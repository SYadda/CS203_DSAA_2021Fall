public class test {
    public static void main(String[] args) {
        System.out.println(10+" "+100);
        for (int i = 0; i < 100; i++) {
            System.out.println(
                    (int)(Math.random() * 10 + 1)
                    +" "+
                    (int)(Math.random() * 10 + 1)
                    +" "+
                    (int)(Math.random() * 10 + 1)
            );
        }
    }
}
