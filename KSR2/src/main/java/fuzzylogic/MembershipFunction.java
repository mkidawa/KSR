package fuzzylogic;

public interface MembershipFunction {
    double getMembership(double x);
    double cardinality();
    double support();
}
