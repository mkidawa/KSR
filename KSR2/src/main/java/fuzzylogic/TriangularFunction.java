package fuzzylogic;

public class TriangularFunction implements MembershipFunction {
    private double a;
    private double b;
    private double m;

    public TriangularFunction(double a, double b, double m) {
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public double getMembership(double x) {
        if (x <= a || x >= b) {
            return 0.0;
        } else if (x > a && x <= m) {
            return (x - a) / (m - a);
        } else if (x > m && x < b) {
            return (b - x) / (b - m);
        } else return 0.0;
    }
}
