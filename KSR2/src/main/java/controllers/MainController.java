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

import javax.xml.soap.Text;
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
    private List<TextField> weights;
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
        for (int i = 0; i < weights.size(); i++) {
            summaryTab.getChildren().add(weights.get(i));
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

    @FXML
    private TextField tf1 = new TextField();

    @FXML
    private TextField tf2 = new TextField();

    @FXML
    private TextField tf3 = new TextField();

    @FXML
    private TextField tf4 = new TextField();

    @FXML
    private TextField tf5 = new TextField();

    @FXML
    private TextField tf6 = new TextField();

    @FXML
    private TextField tf7 = new TextField();

    @FXML
    private TextField tf8 = new TextField();

    @FXML
    private TextField tf9 = new TextField();

    @FXML
    private TextField tf10 = new TextField();

    @FXML
    private TextField tf11 = new TextField();

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

    void setTextFieldProperty(TextField tf, double x, double y) {
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setPrefHeight(26.0);
        tf.setPrefWidth(130.0);
    }

    @FXML
    void checkWeights() {
        if (new Double(weights.get(0).getText()) > 1.0) weights.get(0).setText("1.0");
        List<Double> values = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < weights.size(); i++) {
            values.add(new Double(weights.get(i).getText()));
            sum += values.get(i);
        }
        if (sum != 1) {
            double diff = 1 - values.get(0);
            for (int i = 1; i < values.size(); i++) {
                double w = Math.round((diff / (values.size() - 1)) * 1000d) / 1000d ;
                weights.get(i).setText(String.valueOf(w));
            }
        }
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
        weights = new ArrayList<>();
        setTextFieldProperty(tf1, 1229.0, 125.0);
        setTextFieldProperty(tf2, 1229.0, 165.0);
        setTextFieldProperty(tf3, 1229.0, 205.0);
        setTextFieldProperty(tf4, 1229.0, 245.0);
        setTextFieldProperty(tf5, 1229.0, 285.0);
        setTextFieldProperty(tf6, 1229.0, 325.0);
        setTextFieldProperty(tf7, 1229.0, 365.0);
        setTextFieldProperty(tf8, 1229.0, 405.0);
        setTextFieldProperty(tf9, 1229.0, 445.0);
        setTextFieldProperty(tf10, 1229.0, 485.0);
        setTextFieldProperty(tf11, 1229.0, 525.0);
        tf1.setText("0.6");
        tf2.setText("0.04");
        tf3.setText("0.04");
        tf4.setText("0.04");
        tf5.setText("0.04");
        tf6.setText("0.04");
        tf7.setText("0.04");
        tf8.setText("0.04");
        tf9.setText("0.04");
        tf10.setText("0.04");
        tf11.setText("0.04");
        weights.add(tf1);
        weights.add(tf2);
        weights.add(tf3);
        weights.add(tf4);
        weights.add(tf5);
        weights.add(tf6);
        weights.add(tf7);
        weights.add(tf8);
        weights.add(tf9);
        weights.add(tf10);
        weights.add(tf11);
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
        List<Double> weightValues = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            weightValues.add(new Double(weights.get(i).getText()));
        }
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

            double T = Math.round(model.measures.goodnessOfTheSummary(summary, weightValues) * 100d) / 100d;

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
                text += "T = " + T + " [T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]. \n";
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
