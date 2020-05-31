package model;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Label;
import fuzzylogic.Measures;
import fuzzylogic.Quantifier;
import fuzzyruns.PredefinedQuantifier;
import fuzzyruns.PredefinedSummarizer;

import java.util.*;

public class RunsModel {
    public List<RunDao> runs = new ArrayList<>();
    public void addRun(RunDao r) { runs.add(r); }
    private MongoCollection<RunDao> dataCollection;

    public Measures<RunDao> measures = new Measures<>();
    public PredefinedSummarizer summarizerGlobal;
    public PredefinedQuantifier quantifier = new PredefinedQuantifier();
    public List<Quantifier<RunDao>> quantifiersAll = new ArrayList<>();
    public List<Label<RunDao>> summarizersAll = new ArrayList<>();
    public List<Label<RunDao>> selectedSummarizers = new ArrayList<>();
    public Label<RunDao> qualifier = null;
    public List<String> horseTypes = new ArrayList<>(Arrays.asList(
            "Gelding",
            "Colt",
            "Filly",
            "Horse",
            "Mare",
            "Rig"
    ));

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        this.dataCollection = dataCollection;
    }

    public MongoCollection<RunDao> getDataCollection() {
        return dataCollection;
    }

    public void setSummarizerType(int id, String type) {
        String[] summarizerData = type.split(", ");

        for(int i = 0; i < summarizersAll.size(); i++) {
            if( summarizersAll.get(i).getLinguisticVariableName().equals(summarizerData[0]) && summarizersAll.get(i).getLabelName().equals(summarizerData[1])){
                selectedSummarizers.set(id,summarizersAll.get(i));
            }
        }
    }

    public void setQualifierType(String type) {
        String[] qualifierData = type.split(", ");

        for(int i = 0; i < summarizersAll.size(); i++) {
            if( summarizersAll.get(i).getLinguisticVariableName().equals(qualifierData[0]) && summarizersAll.get(i).getLabelName().equals(qualifierData[1])){
               qualifier = summarizersAll.get(i);
            }
        }
    }

    public List<String> getAllLabelsNames() {
        List<String> labelsToReturn = new ArrayList<>();
        for(Label<RunDao> iterator : summarizersAll) {
            labelsToReturn.add(iterator.getLinguisticVariableName() + ", " + iterator.getLabelName());
        }
        return labelsToReturn;
    }

    public LinkedHashSet<String> getAllLinguisticVariablesNames() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(Label<RunDao> iterator : summarizersAll) {
            set.add(iterator.getLinguisticVariableName());
        }
        return set;
    }

    public List<String> getAllQuantifierNames() {
        List<String> namesToReturn = new ArrayList<>();
        for(Quantifier<RunDao> iterator: quantifiersAll) {
            namesToReturn.add(iterator.getLabelName());
        }
        return namesToReturn;
    }

    public List<String> getAllLinguisticVariableNames() {
        List<String> labelsToReturn = new ArrayList<>();
        for(Label<RunDao> iterator : summarizersAll) {
            labelsToReturn.add(iterator.getLinguisticVariableName() + ", " + iterator.getLabelName());
        }
        return labelsToReturn;
    }

    public Quantifier<RunDao> getQuantifierByName(String name) {
        for (int i = 0; i < quantifiersAll.size(); i++ ){
            if(quantifiersAll.get(i).getLabelName().equals(name)) {
                return quantifiersAll.get(i);
            }
        }
        return null;
    };

    public Label<RunDao> getSummarizerByName(String name) {
        String[] summarizerData = name.split(", ");
        for(int i = 0; i < summarizersAll.size(); i++) {
            if( summarizersAll.get(i).getLinguisticVariableName().equals(summarizerData[0]) && summarizersAll.get(i).getLabelName().equals(summarizerData[1])){
                return summarizersAll.get(i);
            }
        }
        return null;
    };

    public boolean checkIfQuantifierExists(String name) {
        List<String> names = getAllQuantifierNames();
        return names.contains(name);
    };

    public boolean checkIfSummarizerExists(String name) {
        List<String> names = getAllLabelsNames();
        return names.contains(name);
    }

    public int getIndexOfQualifier(String labelName) {
        for(int i = 0; i < quantifiersAll.size(); i++) {
            if(quantifiersAll.get(i).getLabelName().equals(labelName)){
                return i;
            }
        }
        return -1;
    }

}
