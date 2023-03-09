import java.util.Scanner;

public class Main {
    public static String[] miMaBiao2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] miMaBiao = new String[26];
        for (int i = 0; i < 26; i++) {
            miMaBiao[i] = sc.next();
        }

        StringBuilder mmb2 = new StringBuilder();

        for (int i = 97; i < 123; i++) {
            for (int j = 0; j < 26; j++) {
                if (miMaBiao[j].charAt(0) == i) {
                    mmb2.append((char)(j+97));
                }
            }
        }
        miMaBiao2 = mmb2.toString().split("");



        String str0 = sc.next();
        String[] allStr0 = str0.split("");

        int front = str0.length() / 2;
        if (str0.length() % 2 == 1){
            front++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < front; i++) {
            sb.append(  jieMi( allStr0[i] )  );
        }
        for (int i = front; i < allStr0.length; i++) {
            sb.append(  allStr0[i]  );
        }



        String str = sb.toString();
        String[] allStr = str.split("");


        int[][] mapFSA = new int[26][allStr.length];
        int[] xFSA = new int[allStr.length];

        for (int i = 0; i < allStr.length; i++) {
            mapFSA[ str.charAt(i) - 97 ][i] = i+1;
        }

        for (int i = 1; i < allStr.length; i++) {

            xFSA[i] = mapFSA[ str.charAt(i) - 97 ][ xFSA[i-1] ];

            for (int letter = 0; letter < 26; letter++) {
                if ( mapFSA[letter][i] == 0) {
                    mapFSA[letter][i] = mapFSA[letter][ xFSA[i-1] ];
                }
            }
        }

        System.out.println(  str.length() - xFSA[xFSA.length-1]  );
    }

    public static String jieMi (String s){
        return miMaBiao2[ s.charAt(0) - 97 ];
    }
}