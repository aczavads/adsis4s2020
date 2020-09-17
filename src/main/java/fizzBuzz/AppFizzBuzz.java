package fizzBuzz;

public class AppFizzBuzz {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            final boolean multipleOfThree = i%3==0;
            final boolean multipleOfFive = i%5==0;
            System.out.print(i + ": ");
            if (multipleOfFive && multipleOfThree) {
                System.out.println("FizzBuzz");
            } else if (multipleOfThree) {
                System.out.println("Fizz");
            } else if (multipleOfFive) {
                System.out.println("Buzz");
            } else {
                System.out.println("");
            }
        }
    }
    
}
