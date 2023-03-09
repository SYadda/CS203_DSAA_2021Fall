import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QWriter out = new QWriter();
        String str = sc.next();
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




        for (int i = 0; i < allStr.length; i++) {
            for (int j = 0; j < 26; j++) {
                out.print(mapFSA[j][i]+" ");
            }
            out.println("");
        }

        out.close();
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}