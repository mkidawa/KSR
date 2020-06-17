package fuzzylogic;

import java.util.List;

public interface LabelFactory<T> {
    public T CreateLabel(String Name, List<Double> params, boolean isAbsolute, String linguisticVariableName);
}
