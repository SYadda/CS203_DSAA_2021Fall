public class test {
    public static void main(String[] args) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                System.out.printf("%s\t", Main.number(i,j));
            }
            System.out.println();
        }
        System.out.println();
        Main.M = 3;
        System.out.println( Main.yanZhengMatrix2(-19073.0) );
    }
}