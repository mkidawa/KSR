package fuzzylogic;

import java.util.List;

public class Summary<T> {
    private Quantifier<T> quantifier;
    private Label<T> qualifier;
    private List<T> objects;
    private List<T> objects2;
    private List<Label<T>> summarizers;
    private int multiForm = 0;

    public Summary(Quantifier<T> quantifier, Label<T> qualifier, List<T> objects, List<Label<T>> summarizers) {
        this.quantifier = quantifier;
        if (qualifier != null) {
            this.qualifier = qualifier;
        }
        this.objects = objects;
        this.summarizers = summarizers;
    }

    public Summary(Quantifier<T> quantifier, Label<T> qualifier, List<T> subject1, List<T> subject2, List<Label<T>> summarizers) {
        this.quantifier = quantifier;
        if (qualifier != null) {
            this.qualifier = qualifier;
        }
        this.objects = subject1;
        this.objects2 = subject2;
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

    public List<T> getObjects2() {
        return objects2;
    }

    public void setObjects2(List<T> objects2) {
        this.objects2 = objects2;
    }

    public int getMultiForm() {
        return multiForm;
    }

    public void setMultiForm(int multiForm) {
        this.multiForm = multiForm;
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
