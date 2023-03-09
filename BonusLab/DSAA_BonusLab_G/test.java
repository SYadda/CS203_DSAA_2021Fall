public class test {
    public static void main(String[] args) {
        System.out.println(1000+" "+1000+" "+200000+" "+34567);
        for (int i = 0; i < 200000; i++) {
            System.out.print((int)(Math.random() * 1000000));
            System.out.print(" ");
            System.out.print((int)(Math.random() * 1000000));
            System.out.println();
        }
    }
}