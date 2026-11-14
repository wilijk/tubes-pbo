package tubes.util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner sc = new Scanner(System.in);

    public static int readInt() {
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public static double readDouble() {
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    public static String readLine() {
        return sc.nextLine();
    }
}
