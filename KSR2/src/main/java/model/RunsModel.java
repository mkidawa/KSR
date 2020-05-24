package model;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Label;
import fuzzylogic.Measures;
import fuzzyruns.PredefinedQuantifier;
import fuzzyruns.PredefinedSummarizer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RunsModel {
    public RunsModel() {};
    public List<RunDao> runs = new ArrayList<>();
    public void addRun(RunDao r) { runs.add(r); }
    private MongoCollection<RunDao> dataCollection;

    public Measures<RunDao> measures = new Measures<>();
    public PredefinedSummarizer summarizer = new PredefinedSummarizer(runs);
    public PredefinedSummarizer summarizerGlobal;
    public PredefinedQuantifier quantifier = new PredefinedQuantifier();
    public List<Label<RunDao>> summarizers = new ArrayList<>();
    public Label<RunDao> qualifier;

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        this.dataCollection = dataCollection;
    }

    public MongoCollection<RunDao> getDataCollection() {
        return dataCollection;
    }

    public void setSummarizerType(int id, String type) throws IllegalAccessException, NoSuchFieldException {
        Label<RunDao> label = (Label<RunDao>) summarizerGlobal.getClass().getField(type).get(summarizerGlobal);
        summarizers.set(id, label);
    }

}
