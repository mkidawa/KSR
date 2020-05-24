package controllers;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Quantifier;
import fuzzylogic.Summary;
import fuzzyruns.PredefinedSummarizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.RunsModel;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private RunsModel model = new RunsModel();
    private List<ComboBox> comboBoxes;
    private int nrOfStartingComboBoxes = 1;
    private int nrOfCurrentComboBoxes = 1;
    private static final int COMBO_BOXES_LIMIT = 5;
    private String generatedSummary = new String();

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        model.setDataCollection(dataCollection);
    }

    public void assignDataToModel() {
        for (RunDao cur : model.getDataCollection().find()) {
            model.addRun(cur);
        }
        int id = 0;
        model.summarizerGlobal = new PredefinedSummarizer(model.runs);
        //model.qualifier = model.summarizerGlobal.ageYoung;
        setComboBoxProperty(qualifier, 32,156);
        qualifier.getItems().addAll(model.summarizer.getAllSummarizerLabels());
        qualifier.setValue(model.summarizer.getAllSummarizerLabels().get(0));
        qualifier.setEditable(true);
        qualifier.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String value) {
                try {
                    model.setQualifierType(value);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });

        for (ComboBox iterator : comboBoxes) {
            iterator.getItems().addAll(model.summarizer.getAllSummarizerLabels());
            iterator.setValue(model.summarizer.getAllSummarizerLabels().get(0));
            iterator.setEditable(true);
            iterator.setId(String.valueOf(id));
            iterator.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String value) {
                    int comboBoxId = Integer.parseInt(iterator.getId());
                    try {
                        model.setSummarizerType(comboBoxId, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            });
            id++;
        }
        for (int i = 0; i < nrOfStartingComboBoxes; i++) {
            model.summarizers.add(new fuzzylogic.Label<RunDao>());
            pane.getChildren().add(comboBoxes.get(i));
        }
        pane.getChildren().add(qualifier);

    }
    @FXML
    private Button generate;

    @FXML
    private Button comboGenerator;

    @FXML
    private Label result;

    @FXML
    private ComboBox combo1 = new ComboBox();

    @FXML
    private ComboBox combo2 = new ComboBox();

    @FXML
    private ComboBox combo3 = new ComboBox();

    @FXML
    private ComboBox combo4 = new ComboBox();

    @FXML
    private ComboBox combo5 = new ComboBox();

    @FXML
    private ComboBox qualifier = new ComboBox();

    @FXML
    private Pane pane;

    public MainController() {
    }

    public String getGeneratedSummary() {
        return generatedSummary;
    }

    public void setGeneratedSummary(String generatedSummary) {
        this.generatedSummary = generatedSummary;
    }

    void setComboBoxProperty(ComboBox box, double x) {
       box.setLayoutX(x);
       box.setLayoutY(66);
       box.setPrefWidth(200.0);
    }

    void setComboBoxProperty(ComboBox box, double x, double y) {
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefWidth(200.0);
    }

    @FXML
    void initialize() {
        comboBoxes = new ArrayList<>();
        setComboBoxProperty(combo1, 32);
        setComboBoxProperty(combo2, 292);
        setComboBoxProperty(combo3, 552);
        setComboBoxProperty(combo4, 812);
        setComboBoxProperty(combo5, 1072);
        comboBoxes.add(combo1);
        comboBoxes.add(combo2);
        comboBoxes.add(combo3);
        comboBoxes.add(combo4);
        comboBoxes.add(combo5);
    }

    @FXML
    public void onClickGenerateResult() {
        String text = new String();

        for(Quantifier<RunDao> quantifier : model.quantifier.getQuantifiers())
        {
            Summary<RunDao> summary = new Summary<>(quantifier, model.qualifier, model.runs, model.summarizers);
            double T1 = Math.round(model.measures.degreeOfTruth(summary) * 100d) / 100d;
            double T2 = Math.round(model.measures.degreeOfImprecision(summary) * 100d) / 100d;
            double T3 = Math.round(model.measures.degreeOfCovering(summary) * 100d) / 100d;
            double T4 = Math.round(model.measures.degreeOfAppropriateness(summary) * 100d) / 100d;
            double T5 = Math.round(model.measures.lengthOfSummary(summary) * 100d) / 100d;
            double T6 = Math.round(model.measures.degreeOfQuantifierImprecision(summary) * 100d) / 100d;
            double T7 = Math.round(model.measures.degreeOfQuantifierCardinality(summary) * 100d) / 100d;
            double T8 = Math.round(model.measures.degreeOfSummarizerCardinality(summary) * 100d) / 100d;
            double T9 = Math.round(model.measures.degreeOfQualifierImprecision(summary) * 100d) / 100d;
            double T10 = Math.round(model.measures.degreeOfQualifierCardinality(summary) * 100d) / 100d;
            double T11 = Math.round(model.measures.lengthOfQualifier(summary) * 100d) / 100d;

            text += quantifier.getLinguisticVariableName() + " of runs ";
            if(model.qualifier != null) {
                text +=" having / being " + model.qualifier.getLinguisticVariableName() + " " + model.qualifier.getLabelName();
            }

            for (int i = 0; i < nrOfCurrentComboBoxes; i++){
                String connective = i > 0 ? " and " : " were with ";
                text +=  connective + model.summarizers.get(i).getLinguisticVariableName() + " " + model.summarizers.get(i).getLabelName();
            }

            text += "\n [T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]. \n";
        }
        result.setText(text);
    }

    @FXML
    public void onClickAddNewCombo() {
        if (nrOfCurrentComboBoxes < COMBO_BOXES_LIMIT) {
            pane.getChildren().add(comboBoxes.get(nrOfCurrentComboBoxes));
            Label connective = new Label("and");
            connective.setId("label" + nrOfCurrentComboBoxes);
            double comboBoxX = comboBoxes.get(nrOfCurrentComboBoxes - 1).getLayoutX();
            double comboBoxWidth = comboBoxes.get(nrOfCurrentComboBoxes - 1).getPrefWidth();
            connective.setLayoutX(comboBoxX + comboBoxWidth + 10);
            connective.setPrefWidth(40);
            connective.setLayoutY(68);
            connective.setAlignment(Pos.CENTER);
            pane.getChildren().add(connective);
            model.summarizers.add(new fuzzylogic.Label<RunDao>());
            nrOfCurrentComboBoxes++;
        }
    }

    @FXML
    public void onClickRemoveLastCombo() {
        if (nrOfCurrentComboBoxes > 1) {
            pane.getChildren().remove(comboBoxes.get(nrOfCurrentComboBoxes - 1));
            int idOfLabelToRemove = nrOfCurrentComboBoxes - 1;
            pane.getChildren().remove(pane.lookup("#label"+idOfLabelToRemove));
            nrOfCurrentComboBoxes--;
        }
    }
}
