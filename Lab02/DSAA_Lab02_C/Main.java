import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        double[] a = new double[count];
        for (int i = 0; i < count; i++) {
            a[i] = sc.nextDouble();
        }
        for (int i = 0; i < count; i++) {
            System.out.printf("%.10f\n", jiSuan(a[i]) );
        }
    }

    private static double jiSuan(double b) {
        double left = 0.8;
        double right = 300;
        double middle;
        double fx;
        do {
            middle = 0.5 * (left + right);
            fx = middle * Math.pow( 2.718281828459, 0.05*middle ) - b;
            if ( fx > 0.01 ){
                right = middle;
            } else {
                left = middle;
            }
        } while ( Math.abs(fx) > 0.01 );

        return middle;
    }
}