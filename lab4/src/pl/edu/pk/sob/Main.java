package pl.edu.pk.sob;

public class Main {
    public static void main(String[] args) {
        int n = 60;
        System.out.println(pl.edu.pk.sob.version1.Fibonacci.fib(n));
        System.out.println(pl.edu.pk.sob.version2.Fibonacci.fib(n));
        System.out.println(pl.edu.pk.sob.version3.Fibonacci.fib(n));

        n = 100;
        System.out.println(pl.edu.pk.sob.version1.Factorial.fact(n));
        System.out.println(pl.edu.pk.sob.version2.Factorial.fact(n));
        System.out.println(pl.edu.pk.sob.version3.Factorial.fact(n));
    }
}
