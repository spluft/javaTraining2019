package task02;

import java.math.BigInteger;

import static java.lang.Math.sqrt;

public class Calculator {

    public int add(int x, int y) {
        return x + y;
    }

    public double add(double x, double y) {
        return x + y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public long multiply(int x, int y) {
        return x * y;
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public double divide(int x, int y) {
        if (y == 0) {
            throw new IllegalArgumentException("Can't divide by zero!");
        }

        return (1d * x) / y;
    }

    public double divide(double x, double y) {
        if (y == 0) {
            throw new IllegalArgumentException("Can't divide by zero!");
        }

        return x / y;
    }

    public double root(double x, int root) {
        if (root < 0) {
            throw new IllegalArgumentException("We don't know negative roots!");
        }
        if (root == 0) return 1d;
        return Math.pow(x, 1d / root);
    }

    public double power(double x, int power) {
        if (x == 0 && power <= 0) {
            throw new IllegalArgumentException("We don't know negative powers with x = 0!");
        }

        if (power == 0) return 1d;

        return Math.pow(x, power);
    }

    // https://habr.com/ru/post/205318/
    public boolean isPrime(long n) {
        if (n <= 0) throw new IllegalArgumentException("The number can't be negative or zero");
        if (n == 1) {
            return false;
        }
        if (n % 2 == 0 && n != 2) {
            return false;
        }

        for (long i = 2; i <= sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }

    // https://habr.com/ru/post/261159/
    /*
     * Fast doubling method. Faster than the matrix method.
     * F(2n) = F(n) * (2*F(n+1) - F(n)).
     * F(2n+1) = F(n+1)^2 + F(n)^2.
     * This implementation is the non-recursive version. See the web page and
     * the other programming language implementations for the recursive version.
     */
    public BigInteger fastFibonacciDoubling(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        int m = 0;
        for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
            // Loop invariant: a = F(m), b = F(m+1)
            assert a.equals(slowFibonacci(m));
            assert b.equals(slowFibonacci(m+1));

            // Double it
            BigInteger d = multiply(a, b.shiftLeft(1).subtract(a));
            BigInteger e = multiply(a, a).add(multiply(b, b));
            a = d;
            b = e;
            m *= 2;
            assert a.equals(slowFibonacci(m));
            assert b.equals(slowFibonacci(m+1));

            // Advance by one conditionally
            if ((n & bit) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
                m++;
                assert a.equals(slowFibonacci(m));
                assert b.equals(slowFibonacci(m+1));
            }
        }
        return a;
    }

    /*
     * Simple slow method, using dynamic programming.
     * F(n+2) = F(n+1) + F(n).
     */
    private BigInteger slowFibonacci(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
        return a;
    }

    // Multiplies two BigIntegers. This function makes it easy to swap in a faster algorithm like Karatsuba multiplication.
    private BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }
}
