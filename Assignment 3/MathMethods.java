import java.math.BigInteger;
import java.util.Arrays;

public class MathMethods {

    public MathMethods() {
    }

    // public static BigInteger factorial(long n) {
    //     BigInteger nfac = BigInteger.valueOf(n);
    //     BigInteger nf = BigInteger.valueOf(n);
    //     BigInteger i = new BigInteger();
    //     for(i = nf - 1; i > 1; i-- ) {
    //         nfac = nfac.multiply(i);
    //         }
    //         return nfac;
    // }

    public static long gcd(long m, long n) {

        return -5;
    }

    public static long lcm(long m, long n) {

        return -5;
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
        return 0.5;
    }

    public static double sqrt(double x, double epsilon) {
        return root(2, x, epsilon);
    }

    public static void main(String[] args) {
        System.out.println(poly(2,new double[]{4,-8,6,-9,4}));

    }
}
