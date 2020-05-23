package fuzzylogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Label<T> {
    protected String linguisticVariableName; // horse age
    protected String labelName; // young
    protected FuzzySet<T> set;

    public String getLinguisticVariableName() {
        return linguisticVariableName;
    }

    public void setLinguisticVariableName(String linguisticVariableName) {
        this.linguisticVariableName = linguisticVariableName;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public FuzzySet<T> getSet() {
        return set;
    }

    public Label() {}

    public Label(String linguisticVariableName, String labelName, FuzzySet<T> set) {
        this.linguisticVariableName = linguisticVariableName;
        this.labelName = labelName;
        this.set = set;
    }

    public Label(String linguisticVariableName, String labelName, MembershipFunction membershipFunction, Function<T, Double> function) {
        this.linguisticVariableName = linguisticVariableName;
        this.labelName = labelName;
        this.set = new FuzzySet<T>(membershipFunction, function);
    }

    public double getMembership(T obj) {
        return set.getMembership(obj);
    }

    public List<Label<T>> getAll() {
        return new ArrayList<>(Collections.singletonList(this));
    }

    public static <T> double andConnective(List<Label<T>> summarizers, T obj) {
        double min = 1.0;
        for (Label<T> summarizer : summarizers) {
            double membership = summarizer.getMembership(obj);
            if (membership < min) {
                min = membership;
            }
        }
         return min;
    }
}
