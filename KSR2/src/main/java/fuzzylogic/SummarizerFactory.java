package fuzzylogic;

import dao.RunDao;

import java.util.List;

public class SummarizerFactory implements LabelFactory<Label<RunDao>> {


    @Override
    public Label<RunDao> CreateLabel(String Name, List<Double> params, boolean isAbsolute) {
        return null;
    }
}
