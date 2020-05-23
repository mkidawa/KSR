package model;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Label;
import fuzzylogic.Measures;
import fuzzyruns.PredefinedQuantifier;
import fuzzyruns.PredefinedSummarizer;

import java.util.ArrayList;
import java.util.List;

public class RunsModel {
    public RunsModel() {};
    public List<RunDao> runs = new ArrayList<>();
    public void addRun(RunDao r) { runs.add(r); }
    private MongoCollection<RunDao> dataCollection;

    public Measures<RunDao> measures = new Measures<>();
    public PredefinedSummarizer summarizer = new PredefinedSummarizer();
    public PredefinedQuantifier quantifier = new PredefinedQuantifier();
    public Label<RunDao> summarizer1 = PredefinedSummarizer.ageYoung;
    public Label<RunDao> summarizer2 = PredefinedSummarizer.declaredWeightMedium;
    public List<Label<RunDao>> summarizers = new ArrayList<>();
    public Label<RunDao> qualifier = PredefinedSummarizer.ageYoung;

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        this.dataCollection = dataCollection;
    }

    public MongoCollection<RunDao> getDataCollection() {
        return dataCollection;
    }

}
