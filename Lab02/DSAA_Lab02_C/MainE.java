import java.util.Scanner;

public class MainE {
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
        double test;
        double e = Math.E;
        do {
            middle = 0.5 * (left + right);
            if (middle * Math.pow(e, 0.05*middle) > b){
                right = middle;
                test = left;
            } else {
                left = middle;
                test = right;
            }
        } while ( Math.abs(middle - test) > 0.001 );

        return middle;
    }
}