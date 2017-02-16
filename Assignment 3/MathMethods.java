import java.math.BigInteger;
import java.util.Arrays;
import java.lang.Math;

public class MathMethods {


    public static BigInteger factorial(long n) {
        if (n <= 1) return BigInteger.ONE;
        BigInteger answer = BigInteger.valueOf(n).multiply(factorial(n-1));
        return answer;
    }

    public static BigInteger fibonacci(int n) {
        if (n == 1) return BigInteger.ZERO;
        if (n <= 3) return BigInteger.ONE;

        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;
        BigInteger temp = new BigInteger("21");

        for(int i = 3; i <= n ; i++) {
            temp = n1.add(n2);
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static long gcd(long m, long n) {
        if (n == 0) return m;
        long answer = gcd(n, m % n);
        return answer;
    }

    public static long lcm(long m, long n) {
        return (m * n)/gcd(m, n);
    }

    public static double poly(double x, double[] coeff) {
        double polEvaluated = 0;
        for(int i = coeff.length - 1; i > 0; i --) {
            polEvaluated = (polEvaluated + coeff[i]) * x;
        }
        polEvaluated += coeff[0];
        return polEvaluated;
    }

    public static double power(double x, int n) {
        return x*x;
    }

    public static double root(int n, double x, double epsilon) {
        //if (x == -1 && n % 2 != 0) {return -1;}

        if (x < 0 && n % 2 != 0) {
            throw new IllegalArgumentException("Root is complex");
        }

        boolean isPositive = true;
        if (x < 0) {
            x = -x;
            isPositive = false;
        }

        if (x == 1) {
            return isPositive ? 1 : -1;
        }

        double lb = 0, ub = 0 ;
        if (x < 1) {
            lb = x;
            ub = 1;
        } else {
            lb = 0;
            ub = x;
        }

        boolean done = false;
        double avg = (lb + ub)/2;
        double xo;
        while (!done) {
            avg = (lb + ub)/2;
            xo = power(avg,n);
            done = Math.abs(xo - x) < epsilon;
            if (!done) {
                if (xo > x) ub = avg;
                else lb = avg;
            }
        }

        return isPositive ? avg : -avg;
    }

    public static double sqrt(double x, double epsilon) {
        return root(2, x, epsilon);
    }

    public static void main(String[] args) {
        System.out.println(poly(2,new double[]{4,-8,6,-9,4}));
        System.out.println(factorial(5));
        System.out.println(gcd(12, 8));
        System.out.println(root(2,9,0.000001));
        System.out.println(fibonacci(7));

    }
}
