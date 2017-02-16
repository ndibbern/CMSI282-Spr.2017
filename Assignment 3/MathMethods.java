import java.math.BigInteger;
import java.util.Arrays;

public class MathMethods {


    public static BigInteger factorial(long n) {
        if (n <= 1) return BigInteger.ONE;
        BigInteger answer = BigInteger.valueOf(n).multiply(factorial(n-1));
        return answer;
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
        return x;
    }

    public static double root(int n, double x, double epsilon) {
        if(x == 1) {return 1;}
        if(x ==)
        if(x < 1 &&)
        while(error > epsilon) {
            rootValue = (lb + ub)/2;
            fp = power(rootValue,n);
            error = math.abs(fp - x);
            if(fb > n) {ub = rootValue;}
            else {lb = rootValue;}
        }

        return rootValue;
    }

    public static double sqrt(double x, double epsilon) {
        return root(2, x, epsilon);
    }

    public static void main(String[] args) {
        System.out.println(poly(2,new double[]{4,-8,6,-9,4}));
        System.out.println(factorial(5));
        System.out.println(gcd(12, 8));

    }
}
