import java.io.*;
import java.util.*;

public class Main {
    private static String temp;
    private static int foodPointer = -1;
    private static String[] food = new String[10000];
    private static ArrayList<String> student = new ArrayList<>();
    private static String inNext;
    private static boolean progress = true;

    public static void main(String[] args) {
        QReader in = new QReader();

        int n = in.nextInt();
        boolean wait = false;


        for (int i = 0; i < n; i++) {
            inNext = in.next();
            if ( inNext.equals("NewFood") ){
                foodPointer++;
                food[foodPointer] = in.next();

                if (wait) {
                    takeFood();
                    wait = false;
                }
            } else if ( inNext.equals("NewComer") ){
                student.add(in.next());
            } else{
                if (foodPointer != -1){ // food stack is not empty
                    takeFood();
                } else { // food stack is empty
                    wait = true;
                }
            }
        }

        if ( student.isEmpty() ) {
            System.out.println("Qi Fei!");
        } else {
            while (progress){
                int stuSize = student.size();
                progress = false;
                for (int i = 0; i < stuSize; i++) {
                    takeFood();
                }
            }


            if ( student.isEmpty() ) {
                System.out.println("Qi Fei!");
            } else {
                System.out.println(student.size());
            }
        }

    }

    static void takeFood(){
        temp = student.get(0);
        student.remove(0);

        if ( temp.equals( food[foodPointer] ) ){    // current food is favorite
            foodPointer--;
            progress = true;
        } else {                                   // current food is not favorite
            student.add(temp);
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