package fuzzylogic;

public class LinguisticVariable<T> {
    String name; // horse age
    String label; // young
    FuzzySet<T> set;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FuzzySet<T> getSet() {
        return set;
    }

    public LinguisticVariable() {}

    public LinguisticVariable(String name, String label, FuzzySet<T> set) {
        this.name = name;
        this.label = label;
        this.set = set;
    }

    public double getMembership(T obj) {
        return set.getMembership(obj);
    }
}
