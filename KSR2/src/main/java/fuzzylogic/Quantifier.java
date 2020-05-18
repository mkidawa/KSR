package fuzzylogic;

public class Quantifier<T> extends LinguisticVariable<T> {
    private boolean absolute;

    public boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(boolean absolute) {
        this.absolute = absolute;
    }

    public Quantifier(String name, MembershipFunction membershipFunction, boolean absolute) {
        this.name = name;
        this.set = new FuzzySet<T>(membershipFunction);
        this.absolute = absolute;
    }

}
