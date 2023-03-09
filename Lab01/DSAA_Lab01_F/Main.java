import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] count = new String[n];
        for (int i = 0; i < n; i++) {
            count[i] = sc.next();
        }

        for (int i = 0; i < n; i++) {
            String[] split = count[i].split("\\+");
            boolean yes = true;

            for (int j = 0; j < split.length; j++) {
                String s = split[j];
                String[] oneSplit = s.split("");

                yes = oneSplit[0].equals("0") ;

                if (!yes) {break;}
            }

            if (yes) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}