package fuzzylogic;

import java.util.List;

public class Summary<T> {
    private Quantifier<T> quantifier;
    private Label<T> qualifier;
    private List<T> objects;
    private List<Label<T>> summarizers;

    public Summary(Quantifier<T> quantifier, Label<T> qualifier, List<T> objects, List<Label<T>> summarizers) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.objects = objects;
        this.summarizers = summarizers;
    }

    public Quantifier<T> getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(Quantifier<T> quantifier) {
        this.quantifier = quantifier;
    }

    public Label<T> getQualifier() {
        return qualifier;
    }

    public void setQualifier(Label<T> qualifier) {
        this.qualifier = qualifier;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public List<Label<T>> getSummarizers() {
        return summarizers;
    }

    public void setSummarizers(List<Label<T>> summarizers) {
        this.summarizers = summarizers;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "quantifier=" + quantifier +
                ", qualifier=" + qualifier +
                ", objects=" + objects +
                ", summarizers=" + summarizers +
                '}';
    }
}
