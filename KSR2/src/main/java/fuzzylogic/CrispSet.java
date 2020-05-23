package fuzzylogic;

import java.util.List;

public class CrispSet<T> {
    private List<T> set;

    public CrispSet(List<T> set) {
        this.set = set;
    }

    public List<T> getSet() {
        return set;
    }

    public void setSet(List<T> set) {
        this.set = set;
    }
}
