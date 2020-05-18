package model;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.LinguisticVariable;
import fuzzylogic.Measures;
import fuzzylogic.OrConnective;
import fuzzylogic.Quantifier;
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
    public LinguisticVariable<RunDao> summarizer1 = PredefinedSummarizer.ageYoung;
    public LinguisticVariable<RunDao> summarizer2 = PredefinedSummarizer.declaredWeightLight;
    public LinguisticVariable<RunDao> and = new OrConnective<>(summarizer1, summarizer2);
    public LinguisticVariable<RunDao> qualifier = PredefinedSummarizer.ageYoung;

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        this.dataCollection = dataCollection;
    }

    public MongoCollection<RunDao> getDataCollection() {
        return dataCollection;
    }

}
