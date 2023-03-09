import java.util.Scanner;

public class kmpMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str0 = sc.next();
        StringBuilder strBu = new StringBuilder(str0);
        StringBuilder strBu2 = new StringBuilder(str0);
        strBu2.reverse();
        strBu.append("{");
        strBu.append(strBu2);

        int max = 0;

        for (int z = 0; z < str0.length(); z++) {
            String str = strBu.toString();
            String[] allStr = str.split("");

            int[][] mapFSA = new int[27][allStr.length];
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

            max = Math.max(xFSA[ xFSA.length-1 ], max);

            strBu.deleteCharAt(0);
            strBu.deleteCharAt(strBu.length() - 1);
        }
        System.out.println(  Math.min( max, str0.length() )  );
    }
}