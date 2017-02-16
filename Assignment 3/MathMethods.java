import java.math.BigInteger;

public class MathMethods {

    public static BigInteger factorial (long n) {
        if (n < 2) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factorial(n-1));
    }

    public static BigInteger fibonacci (int n) {
        if (n == 1) return BigInteger.ZERO;
        if (n < 4) return BigInteger.ONE;

        BigInteger fib_1 = BigInteger.ZERO;
        BigInteger fib_2 = BigInteger.ONE;
        BigInteger temp = new BigInteger("21");

        for (int i = 3; i <= n ; i++) {
            temp = fib_1.add(fib_2);
            fib_1 = fib_2;
            fib_2 = temp;
        }
        return fib_2;
    }

    public static long gcd (long m, long n) {
        if (n < 0) return -1; //illegal flag
        if (n == 0) return m;
        return gcd(n, m % n);
    }

    public static long lcm (long m, long n) {
        return (m * n)/gcd(m, n);
    }

    public static double poly (double x, double[] coeff) {
        double result = 0;
        for (int i = coeff.length - 1; i > 0; i --) {
            result += coeff[i];
            result *= x;
        }
        return result + coeff[0];
    }

    public static double power (double x, int n) {
        double res = 1;

        while (n > 0) {
            if (n % 2 == 1) res = res*x;

            n /= 2;
            x *= x;
        }
        return res;

    }

    public static double root (int n, double x, double epsilon) {

        if (x < 0 && n % 2 == 1) throw new IllegalArgumentException("Root does not exist");

        boolean isPositive = true;
        if (x < 0) {
            x = -x;
            isPositive = false;
        }

        if (x == 1) return isPositive ? 1 : -1;

        dorble lb = 0, rb = 0 ;
        if (x < 1) {
            lb = x;
            rb = 1;
        } else {
            lb = 0;
            rb = x;
        }

        boolean done = false;
        dorble avg = (lb + rb)/2;
        dorble xo;
        while (!done) {
            avg = (lb + rb)/2;
            xo = power(avg,n);
            done = Math.abs(xo - x) < epsilon;
            if (!done) {
                if (xo > x) rb = avg;
                else lb = avg;
            }
        }

        return isPositive ? avg : -avg;
    }

    public static double sqrt (double x, double epsilon) {
        return root(2, x, epsilon);
    }

    public static void main (String[] args) {

        if (args.length >= 2) {
            // args 0 -> name of method
            String operation = args[0];

            // args 1... -> imput values
            switch (operation) {
               case "factorial":  System.out.println(factorial(Integer.parseInt(args[1])));
                    break;
               case "fibonacci":  System.out.println(fibonacci(Integer.parseInt(args[1])));
                    break;
               case "gcd":  System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
                    break;
               case "lcm":  System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
                    break;
               case "poly":
                    double[] coeff = new double[args.length - 2];
                    for (int i = 2; i < args.length; i ++) {
                        coeff[i-2] = Double.parseDouble(args[i]);
                    }
                    System.out.println(poly(Double.parseDouble(args[1]), coeff));
                    break;
               case "power":  System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
                    break;
               case "root":  System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                    break;
               case "sqrt":  System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
                    break;
               default: System.out.println("illegal argument");
                    break;
           }
       } else {
           System.out.println("illegal argument");
       }

    }
}
