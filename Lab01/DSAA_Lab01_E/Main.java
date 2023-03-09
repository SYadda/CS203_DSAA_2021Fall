import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Long> b = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            a.add(i);
        }
        for (int i: a) {
            String str = String.valueOf(i);
            String[] strSp = str.split("");
            int[] intSp = new int[strSp.length];

            boolean magic = true;

            for (int j = 0; j < strSp.length; j++) {
                intSp[j] = Integer.parseInt(strSp[j]);
                if (intSp[j] == 2 || intSp[j] == 3 || intSp[j] == 4 || intSp[j] == 5 || intSp[j] == 7) {
                    magic = false;
                    break;
                }
            }

            if ( magic ) {
                StringBuilder sb = new StringBuilder(str);
                for (int j = strSp.length - 1; j > -1; j--) {

                    if (strSp[j].equals("6")){
                        strSp[j] = "9";
                    }
                    if (strSp[j].equals("9")){
                        strSp[j] = "6";
                    }
                    sb.append(strSp[j]);
                }
                long l = Long.parseLong( sb.toString() );
                b.add(l);

                if ( intSp[intSp.length-1] == 0 || intSp[intSp.length-1] == 1 || intSp[intSp.length-1] == 8 ){
                    StringBuilder sb1 = new StringBuilder(str);
                    for (int j = strSp.length - 2; j > -1; j--) {
                        sb1.append(strSp[j]);
                    }
                    long l1 = Long.parseLong( sb1.toString() );
                    b.add(l1);
                }
            }
        }

        Collections.sort(b);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long left = sc.nextLong();
            long right = sc.nextLong();

            for (int i = 0; i < b.size(); i++) {
                if ( b.get(i) >= left ){
                    System.out.print( b.get(i)+" " );
                    break;
                }
            }

            for (int i = b.size()-1; i > -1; i--) {
                if ( b.get(i) <= right ){
                    System.out.println( b.get(i) );
                    break;
                }
            }
        }
    }
}