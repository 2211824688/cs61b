import java.util.Scanner;

/**
 * Class that prints the Collatz sequence starting from a given number.
 * 
 * @author YOUR NAME HERE
 */
public class Collatz {

    /** Get the next number in collatz conjecture */
    public static int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3 * n + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Type number:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
        scanner.close();
    }
}
