import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {

    // main method
    public static void main(String[] args) {

        Scanner obj = new Scanner(System.in);
        System.out.print("\nFirst Human Choose the number : ");
        int userinput = obj.nextInt(); // taking input from user

        // rock=0
        // paper=1
        // scissor=2;

        Random rnd = new Random();
        int computerinput = rnd.nextInt(3);

        // choice of computer
        System.out.println("Computer choice is : " + computerinput);
        if (computerinput == 0) {
            System.out.println("Computer choice is rock");

        } else if (computerinput == 1) {
            System.out.println("Computer choice is Paper");
        } else {
            System.out.println("Computer choice is Scissor");
        }

        // logic of the program

        if (computerinput == userinput) {
            System.out.println("Draw");
        } else if (computerinput == 0 && userinput == 1 || computerinput == 1 && userinput == 2
                || computerinput == 2 && userinput == 0) {

            System.out.println("human win\n");
        } else {
            System.out.println("Computer win\n");
        }

        obj.close();
    }
}