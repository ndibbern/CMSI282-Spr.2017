import java.math.BigInteger;

public class MathMethods {

    public static BigInteger factorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorials of negative numbers can not be computed");
        }
        if (n <= 1) return BigInteger.ONE;
        BigInteger answer = BigInteger.valueOf(n).multiply(factorial(n-1));
        return answer;
    }

    public static BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci of negative numbers can not be computed");
        }
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger n1 = BigInteger.ZERO;
        BigInteger n2 = BigInteger.ONE;
        BigInteger temp = new BigInteger("21"); // Place holder

        for (int i = 2; i <= n ; i++) {
            temp = n1.add(n2);
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static long gcd(long m, long n) {
        m = Math.abs(m);
        n = Math.abs(n);
        if (n == 0) return m;
        long answer = gcd(n, m % n);
        return answer;
    }

    public static long lcm(long m, long n) {
        return (m * n)/gcd(m, n);
    }

    public static double poly(double x, double[] coeff) {
        double polEvaluated = 0;
        for (int i = coeff.length - 1; i > 0; i --) {
            polEvaluated = (polEvaluated + coeff[i]) * x;
        }
        polEvaluated += coeff[0];
        return polEvaluated;
    }

    public static double power(double x, int n) {
        if (n < 0) {
            return power(1/x, -n); // Compute inverse
        }

        double pow = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                pow = pow * x;
            }
            n /= 2;
            x *= x;
        }
        return pow;
    }

    public static double root(int n, double x, double epsilon) {
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

    public static String instructions () {
        String instruction =  " # Instructions for program : \n";
        instruction += "java MathMethods [function] [parameters]...";
        return instruction;
    }


    public static void main(String[] args) {
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
               default: System.out.println(instructions());
                    break;
           }
       } else {
           System.out.println(instructions());
       }
    }
}
