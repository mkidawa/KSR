package fuzzylogic;

public class TrapezoidalFunction implements MembershipFunction {
    private double a;
    private double b;
    private double m;
    private double n;

    public TrapezoidalFunction(double a, double b, double m, double n) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.n = n;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getMembership(double x) {
        if (x <= a || x >= b) {
            return 0.0;
        } else if (x > a && x <= m) {
            return (x - a) / (m - a);
        } else if (x > m && x <= n) {
            return 1.0;
        } else if (x > n && x < b) {
            return (b - x) / (b - n);
        } else return 0.0;
    }

    @Override
    public double cardinality() {
        return 0.5 * (b - a + (n - m));
    }

    @Override
    public double support() {
        return b - a;
    }
}

