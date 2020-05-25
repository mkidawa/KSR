package controllers;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Summary;
import fuzzyruns.PredefinedSummarizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.RunsModel;
import utils.LaTeXGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.*;

public class MainController {
    private RunsModel model = new RunsModel();
    private List<ComboBox> comboBoxes;
    private int nrOfStartingComboBoxes = 1;
    private int nrOfCurrentComboBoxes = 1;
    private static final int COMBO_BOXES_LIMIT = 5;
    private String generatedSummary;

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
                    if (value != null) {
                        model.setQualifierType(value);
                    }
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
                    if (!generate.isVisible()) {
                        generate.setVisible(true);
                    }
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
            summaryTab.getChildren().add(comboBoxes.get(i));
        }
        summaryTab.getChildren().add(qualifier);

    }
    @FXML
    private Button generate = new Button();

    @FXML
    private Button fileGenerator = new Button();

    @FXML
    private Label result;

    @FXML
    private CheckBox shouldGenerateTables = new CheckBox();

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
    private AnchorPane summaryTab;

    public MainController() throws FileNotFoundException {
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
        fileGenerator.setVisible(false);
        generate.setVisible(false);
    }

    @FXML
    public void onClickGenerateResult() {
        String text = new String();
        String [][] values = new String[model.quantifier.getQuantifiers().size()][11];
        int size = model.qualifier == null ? nrOfCurrentComboBoxes : nrOfCurrentComboBoxes + 1;
        String [][] quantValues = new String[1][size];
        String [] quantifier = new String[model.quantifier.getQuantifiers().size()];
        List<String> colNames = new ArrayList<>();
        for(int i = 0; i < model.quantifier.getQuantifiers().size(); i++)
        {
            Summary<RunDao> summary = new Summary<>(model.quantifier.getQuantifiers().get(i), model.qualifier, model.runs, model.summarizers);
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
            // PLZ DON'T LOOK
            values[i][0] = Double.toString(T1);
            values[i][1] = Double.toString(T2);
            values[i][2] = Double.toString(T3);
            values[i][3] = Double.toString(T4);
            values[i][4] = Double.toString(T5);
            values[i][5] = Double.toString(T6);
            values[i][6] = Double.toString(T7);
            values[i][7] = Double.toString(T8);
            values[i][8] = Double.toString(T9);
            values[i][9] = Double.toString(T10);
            values[i][10] = Double.toString(T11);

            text += model.quantifier.getQuantifiers().get(i).getLinguisticVariableName() + " of runs ";
            if(model.qualifier != null) {
                text +=" having / being " + model.qualifier.getLinguisticVariableName() + " " + model.qualifier.getLabelName();
            }
            quantifier[i] = model.quantifier.getQuantifiers().get(i).getLinguisticVariableName();
            for (int j = 0; j < nrOfCurrentComboBoxes; j++){
                String connective = j > 0 ? " and " : "were with ";
                text +=  connective + model.summarizers.get(j).getLinguisticVariableName() + " " + model.summarizers.get(j).getLabelName();
                quantValues[0][j] = model.summarizers.get(j).getLinguisticVariableName() + " " + model.summarizers.get(j).getLabelName();
            }
            text += ".\n";
            if(!shouldGenerateTables.isSelected()){
                text += "[T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]. \n";
            }
        }
        for (int j = 0; j < nrOfCurrentComboBoxes; j++){
            colNames.add("Sumaryzator" + Integer.toString(j + 1));
            String connective = j > 0 ? " and " : " were with ";
            quantValues[0][j] = model.summarizers.get(j).getLinguisticVariableName() + " " + model.summarizers.get(j).getLabelName();
        }
        if (model.qualifier != null) {
            colNames.add("Kwalifikator");
            quantValues[0][colNames.size() - 1] = model.qualifier.getLinguisticVariableName()+ " " + model.qualifier.getLabelName();
        }
        result.setText(text);
        setGeneratedSummary(text);
        fileGenerator.setVisible(true);
        if(shouldGenerateTables.isSelected()) {
            LaTeXGenerator generator = new LaTeXGenerator(model.quantifier.getQuantifiers().size(), 12, Arrays.asList("Kwantyfikator","T1","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11"), "Miary jakości" ,values);
            LaTeXGenerator generator2 = new LaTeXGenerator(1, colNames.size(), colNames, "Parametry podsumowania" ,quantValues);
            generator.setQuantifier(quantifier);
            System.out.println(generator.generateLaTeXTable());
            System.out.println(generator2.generateLaTeXTable());
        }
    }

    @FXML
    public void onClickAddNewCombo() {
        if (nrOfCurrentComboBoxes < COMBO_BOXES_LIMIT) {
            summaryTab.getChildren().add(comboBoxes.get(nrOfCurrentComboBoxes));
            Label connective = new Label("and");
            connective.setId("label" + nrOfCurrentComboBoxes);
            double comboBoxX = comboBoxes.get(nrOfCurrentComboBoxes - 1).getLayoutX();
            double comboBoxWidth = comboBoxes.get(nrOfCurrentComboBoxes - 1).getPrefWidth();
            connective.setLayoutX(comboBoxX + comboBoxWidth + 10);
            connective.setPrefWidth(40);
            connective.setLayoutY(68);
            connective.setAlignment(Pos.CENTER);
            summaryTab.getChildren().add(connective);
            model.summarizers.add(new fuzzylogic.Label<RunDao>());
            nrOfCurrentComboBoxes++;
        }
    }

    @FXML
    public void onClickRemoveLastCombo() {
        if (nrOfCurrentComboBoxes > 1) {
            summaryTab.getChildren().remove(comboBoxes.get(nrOfCurrentComboBoxes - 1));
            int idOfLabelToRemove = nrOfCurrentComboBoxes - 1;
            summaryTab.getChildren().remove(summaryTab.lookup("#label"+idOfLabelToRemove));
            nrOfCurrentComboBoxes--;
        }
    }

    @FXML
    public void onClickSaveSummary() throws FileNotFoundException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
        String result = String.format(
                "summary-%1$tY-%1$tm-%1$td-%1$tk-%1$tM-%1$tS-%1$tp.txt", cal);

        PrintWriter out = new PrintWriter(new FileOutputStream(result), true);
        String text = getGeneratedSummary();
        out.println(text);
        out.close();
    }

    @FXML
    public void onClickClearQualifier() {
        qualifier.valueProperty().set(null);
        model.qualifier = null;
    }

}
