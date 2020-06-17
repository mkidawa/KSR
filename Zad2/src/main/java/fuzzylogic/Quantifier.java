package fuzzylogic;

public class Quantifier<T> extends Label<T> {
    private boolean absolute;

    public boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(boolean absolute) {
        this.absolute = absolute;
    }

    public Quantifier(String name, MembershipFunction membershipFunction, boolean absolute) {
        this.labelName = name;
        this.fuzzySet = new FuzzySet<T>(membershipFunction);
        this.absolute = absolute;
    }
}
