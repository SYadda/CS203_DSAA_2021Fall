import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt = sc.next();  // S
        String str = sc.next();  // T
        if (txt.length() != str.length()) {
            System.out.println("No");
            return;
        }

        txt += txt;
        String[] allTxt = txt.split("");

        String[] allStr = str.split("");
        int[][] mapFSA = new int[26][allStr.length];
        int[] xFSA = new int[allStr.length+1];

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

        int pointerI = 0, pointerJ = 0;
        for (int i = 0; i < allTxt.length; i++) {
            if (  allTxt[pointerI].equals(allStr[pointerJ])  ){
                pointerJ++;
            } else if ( pointerJ != 0 ){
                pointerJ = xFSA[ pointerJ-1 ];
            }
            pointerI++;

            if ( pointerJ == str.length() ) {
                System.out.println("Yes");
                return;
            }
        }

        System.out.println("No");
    }
}