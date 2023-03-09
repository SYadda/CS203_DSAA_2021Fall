import java.io.*;
import java.util.*;

public class Main {
    private static boolean YES = true;
    public static void main(String[] args) {
        QReader in = new QReader();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();

            if (n % 2 == 1){
                System.out.println("NO");
                in.next();
                continue;
            }

            String strBrackets = in.next();
            String[] brackets = new String[n];      int bracketsPointer = -1;
            YES = true;

            for (int j = 0; j < n; j++) {
                if (bracketsPointer == -1){
                    bracketsPointer++;
                    brackets[bracketsPointer] = String.valueOf(  strBrackets.charAt(j)  );
                } else if ( strBrackets.charAt(j) == ')' ){
                    if (  !  brackets[bracketsPointer].equals("(")  ){
                        System.out.println("NO");
                        YES = false;
                        break;
                    } else {
                        bracketsPointer--;
                    }
                } else if ( strBrackets.charAt(j)==']' ){
                    if (  !  brackets[bracketsPointer].equals("[")  ){
                        System.out.println("NO");
                        YES = false;
                        break;
                    } else {
                        bracketsPointer--;
                    }
                } else if ( strBrackets.charAt(j)=='}' ){
                    if (  !  brackets[bracketsPointer].equals("{")  ){
                        System.out.println("NO");
                        YES = false;
                        break;
                    } else {
                        bracketsPointer--;
                    }
                } else {
                    bracketsPointer++;
                    brackets[bracketsPointer] = String.valueOf(  strBrackets.charAt(j)  );
                }
            }

            if (YES) {
                System.out.println("YES");
            }
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}