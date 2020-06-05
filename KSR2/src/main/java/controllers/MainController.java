package controllers;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.*;
import fuzzyruns.PredefinedSummarizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private List<TextField> weights;
    private String generatedSummary;
    private String subject1Selected;
    private String subject2Selected;

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        model.setDataCollection(dataCollection);
    }

    public MainController() throws FileNotFoundException {
    }

    // Initalization methods //

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
        setComboBoxProperty(subject1, 1175.0, 677.0);
        setComboBoxProperty(subject2, 1175.0, 719.0);
        multiSubjectSummary.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                addMultiSubjectComboBoxes(newValue);
            }
        });
        fileGenerator.setVisible(false);
        generate.setVisible(false);
        quantifiersList.setVisible(false);
        linguisticList.setVisible(false);
        quantifierType.setVisible(false);
        aLabel.setVisible(false);
        bLabel.setVisible(false);
        mLabel.setVisible(false);
        nLabel.setVisible(false);
        aTextField.setVisible(false);
        bTextField.setVisible(false);
        mTextField.setVisible(false);
        nTextField.setVisible(false);

        quantifierType.getItems().addAll("Relative", "Absolute");
        functionType.getItems().addAll("Triangular", "Trapezoid");
        functionType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                if((int) new_value == 0) {
                    setTriangularFieldsVisible();
                } else setTrapezoidFieldsVisible();
            }
        });
    }

    public void setTriangularFieldsVisible() {
        aLabel.setVisible(true);
        bLabel.setVisible(true);
        mLabel.setVisible(true);
        nLabel.setVisible(false);
        aTextField.setVisible(true);
        bTextField.setVisible(true);
        bLabel.setLayoutY(250.0);
        bTextField.setLayoutY(250.0);
        mTextField.setVisible(true);
        nTextField.setVisible(false);
    }

    public void setTrapezoidFieldsVisible() {
        aLabel.setVisible(true);
        bLabel.setVisible(true);
        mLabel.setVisible(true);
        nLabel.setVisible(true);
        aTextField.setVisible(true);
        bTextField.setVisible(true);
        bTextField.setLayoutY(325.0);
        bLabel.setLayoutY(325.0);
        mTextField.setVisible(true);
        nTextField.setVisible(true);
    }

    public void initializeAfterDataLoading() {
        model.summarizerGlobal = new PredefinedSummarizer(model.runs);
        model.summarizersAll = model.summarizerGlobal.getAllLabels();
        model.quantifiersAll = model.quantifier.getQuantifiers();
        initializeGUI();
    }

    public void fillTextFieldsWithLabelData(fuzzylogic.Label<RunDao> label) {
        if (label.getFuzzySet().getMembershipFunction() instanceof TriangularFunction) {
            functionType.getSelectionModel().select(0);
            aTextField.setText(String.valueOf(((TriangularFunction) label.getFuzzySet().getMembershipFunction()).getA()));
            mTextField.setText(String.valueOf(((TriangularFunction) label.getFuzzySet().getMembershipFunction()).getM()));
            bTextField.setText(String.valueOf(((TriangularFunction) label.getFuzzySet().getMembershipFunction()).getB()));
        } else {
            functionType.getSelectionModel().select(1);
            aTextField.setText(String.valueOf(((TrapezoidalFunction) label.getFuzzySet().getMembershipFunction()).getA()));
            mTextField.setText(String.valueOf(((TrapezoidalFunction) label.getFuzzySet().getMembershipFunction()).getM()));
            nTextField.setText(String.valueOf(((TrapezoidalFunction) label.getFuzzySet().getMembershipFunction()).getN()));
            bTextField.setText(String.valueOf(((TrapezoidalFunction) label.getFuzzySet().getMembershipFunction()).getB()));
        }
    }

    public void initializeGUI() {
        quantifiersList.getItems().addAll(model.getAllQuantifierNames());
        quantifiersList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Quantifier<RunDao> fillerQuantifier = model.getQuantifierByName(quantifiersList.getSelectionModel().getSelectedItem());
                if (fillerQuantifier.isAbsolute()) {
                    quantifierType.getSelectionModel().select(1);
                } else {
                    quantifierType.getSelectionModel().select(0);
                }
                fillTextFieldsWithLabelData(fillerQuantifier);

                nameTextField.setText(fillerQuantifier.getLabelName());
            }
        });

        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(quantifiersList.isVisible() && model.checkIfQuantifierExists(newValue)){
                creationButton.setText("Update");
                quantifiersList.getSelectionModel().select(newValue);
                Quantifier<RunDao> fillerQuantifier = model.getQuantifierByName(quantifiersList.getSelectionModel().getSelectedItem());
                if (fillerQuantifier.isAbsolute()) {
                    quantifierType.getSelectionModel().select(1);
                } else {
                    quantifierType.getSelectionModel().select(0);
                }
                fillTextFieldsWithLabelData(fillerQuantifier);
            } else if (linguisticList.isVisible() && model.checkIfSummarizerExists( linguisticNameField.getSelectionModel().getSelectedItem() + ", " + newValue)) {
                creationButton.setText("Update");
                linguisticList.getSelectionModel().select(linguisticNameField.getSelectionModel().getSelectedItem() + ", " + newValue);
                fuzzylogic.Label<RunDao> fillerSummarizer = model.getSummarizerByName(linguisticList.getSelectionModel().getSelectedItem());
                fillTextFieldsWithLabelData(fillerSummarizer);
                nameTextField.setText(fillerSummarizer.getLabelName());
            } else {
                creationButton.setText("Create");
            }
        });

        linguisticNameField.getItems().addAll(model.getAllLinguisticVariablesNames());
        linguisticList.getItems().addAll(model.getAllSummarizersLabels());
        linguisticList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fuzzylogic.Label<RunDao> fillerSummarizer = model.getSummarizerByName(linguisticList.getSelectionModel().getSelectedItem());
                fillTextFieldsWithLabelData(fillerSummarizer);
                nameTextField.setText(fillerSummarizer.getLabelName());

                for (int i = 0; i < linguisticNameField.getItems().size(); i++) {
                    if (fillerSummarizer.getLinguisticVariableName().equals(linguisticNameField.getItems().get(i)))
                    {
                        linguisticNameField.getSelectionModel().select(i);
                    }
                }

                creationButton.setText("Update");
            }
        });
    }

    public void updateGUI() {
        quantifiersList.getItems().clear();
        linguisticList.getItems().clear();
        linguisticList.getItems().addAll(model.getAllSummarizersLabels());
        quantifiersList.getItems().addAll(model.getAllQuantifierNames());
        for (int i = 0; i < nrOfCurrentComboBoxes; i++) {
            comboBoxes.get(i).getItems().clear();
            comboBoxes.get(i).getItems().addAll(model.getAllSummarizersLabels());
            comboBoxes.get(i).setValue(model.getAllSummarizersLabels().get(0));
        }
        qualifier.getItems().clear();
        qualifier.getItems().addAll(model.getAllSummarizersLabels());
        qualifier.setValue(model.getAllSummarizersLabels().get(0));
    }

    public boolean checkIfAllInputsAreValid() {
        boolean isValid;
        boolean textFieldsInvalid;
        boolean nameInvalid;
        boolean lingVarInvalid;
        boolean quantTypeInvalid;
        boolean functionTypeInvalid;
        boolean aInvalid;
        boolean bInvalid;
        boolean mInvalid;
        boolean nInvalid;

        if(quantifiersList.isVisible()) {
            quantTypeInvalid = quantifierType.getSelectionModel().isEmpty();
            nameInvalid = nameTextField.getText() == null || nameTextField.getText().trim().isEmpty();
            functionTypeInvalid = functionType.getSelectionModel().isEmpty();
            if (functionTypeInvalid && functionType.getSelectionModel().getSelectedIndex() == 0) {
                aInvalid = aTextField.getText().trim().equals("");
                bInvalid = bTextField.getText().trim().equals("");
                mInvalid = mTextField.getText().trim().equals("");
                textFieldsInvalid = aInvalid || bInvalid || mInvalid;
            } else {
                aInvalid = aTextField.getText().trim().equals("");
                bInvalid = bTextField.getText().trim().equals("");
                mInvalid = mTextField.getText().trim().equals("");
                nInvalid = nTextField.getText().trim().equals("");
                textFieldsInvalid = aInvalid || bInvalid || mInvalid || nInvalid;
            }
            return !(quantTypeInvalid || nameInvalid || functionTypeInvalid || textFieldsInvalid);
        } else if (linguisticList.isVisible()) {
            nameInvalid = nameTextField.getText() == null || nameTextField.getText().trim().isEmpty();
            lingVarInvalid = linguisticNameField.getSelectionModel().isEmpty();
            functionTypeInvalid = functionType.getSelectionModel().isEmpty();
            if (functionTypeInvalid && functionType.getSelectionModel().getSelectedIndex() == 0) {
                aInvalid = aTextField.getText().trim().equals("");
                bInvalid = bTextField.getText().trim().equals("");
                mInvalid = mTextField.getText().trim().equals("");
                textFieldsInvalid = aInvalid || bInvalid || mInvalid;
            } else {
                aInvalid = aTextField.getText().trim().equals("");
                bInvalid = bTextField.getText().trim().equals("");
                mInvalid = mTextField.getText().trim().equals("");
                nInvalid = nTextField.getText().trim().equals("");
                textFieldsInvalid = aInvalid || bInvalid || mInvalid || nInvalid;
            }
            return !(nameInvalid || lingVarInvalid || functionTypeInvalid || textFieldsInvalid);
        }
        return false;
    }

    public void assignDataToModel() {
        for (RunDao cur : model.getDataCollection().find()) {
            model.addRun(cur);
        }
        int id = 0;
        initializeAfterDataLoading();
        setComboBoxProperty(qualifier, 32,156);
        qualifier.getItems().addAll(model.getAllSummarizersLabels());
        qualifier.setValue(model.getAllSummarizersLabels().get(0));
        qualifier.setEditable(true);
        qualifier.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String value) {
                if (value != null) {
                    model.setQualifierType(value);
                }
            }
        });

        for (ComboBox iterator : comboBoxes) {
            iterator.getItems().addAll(model.getAllSummarizersLabels());
            iterator.setValue(model.getAllSummarizersLabels().get(0));
            iterator.setEditable(true);
            iterator.setId(String.valueOf(id));
            iterator.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String value) {
                    if(value != "" && iterator.getItems().size() != 0){
                        if (!generate.isVisible()) {
                            generate.setVisible(true);
                        }
                        int comboBoxId = Integer.parseInt(iterator.getId());
                        model.setSummarizerType(comboBoxId, value);
                    }
                }
            });
            id++;
        }
        for (int i = 0; i < nrOfStartingComboBoxes; i++) {
            model.selectedSummarizers.add(new fuzzylogic.Label<RunDao>());
            summaryTab.getChildren().add(comboBoxes.get(i));
        }
        for (int i = 0; i < weights.size(); i++) {
            summaryTab.getChildren().add(weights.get(i));
        }
        summaryTab.getChildren().add(qualifier);

        subject1.getItems().addAll(model.horseTypes);
        subject2.getItems().addAll(model.horseTypes);
        subject1.setEditable(true);
        subject2.setEditable(true);
        subject1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                subject1Selected = newValue;
            }
        });
        subject2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                subject2Selected = newValue;
            }
        });
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
    public void createNewLabel() {
        if(checkIfAllInputsAreValid()){
            if(quantifiersList.isVisible()){
                QuantifierFactory quantifierFactory = new QuantifierFactory();
                if(!checkIfUpdate()){
                    ArrayList<Double> params = generateParams();
                    boolean isAbsolute = quantifierType.getSelectionModel().getSelectedIndex() != 0;
                    addNewQuantifier(quantifierFactory.CreateLabel(nameTextField.getText(),params, isAbsolute, null));
                } else {
                    ArrayList<Double> params = generateParams();
                    boolean isAbsolute = quantifierType.getSelectionModel().getSelectedIndex() != 0;
                    updateExistingQualifier(quantifierFactory.CreateLabel(nameTextField.getText(),params, isAbsolute, null), nameTextField.getText());
                }
            } else {
                SummarizerFactory summarizerFactory = new SummarizerFactory(model.summarizerGlobal);
                if(!checkIfUpdate()){
                    ArrayList<Double> params = generateParams();
                    addNewSummarizer(summarizerFactory.CreateLabel(nameTextField.getText(), params, false, linguisticNameField.getSelectionModel().getSelectedItem()));
                } else {
                    ArrayList<Double> params = generateParams();
                    updateExistingSummarizer(summarizerFactory.CreateLabel(nameTextField.getText(), params, false, linguisticNameField.getSelectionModel().getSelectedItem()), nameTextField.getText(), linguisticNameField.getSelectionModel().getSelectedItem());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Form incomplete, can't create a new object!", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
        }
    }

    public ArrayList<Double> generateParams() {
        if(functionType.getSelectionModel().getSelectedIndex() == 0) {
            return new ArrayList<Double>() {
                {
                    add(Double.valueOf(aTextField.getText()));
                    add(Double.valueOf(bTextField.getText()));
                    add(Double.valueOf(mTextField.getText()));
                }
            };
        } else {
            return new ArrayList<Double>() {
                {
                    add(Double.valueOf(aTextField.getText()));
                    add(Double.valueOf(bTextField.getText()));
                    add(Double.valueOf(mTextField.getText()));
                    add(Double.valueOf(nTextField.getText()));
                }
            };
        }
    }

    public void addNewSummarizer(fuzzylogic.Label<RunDao> label) {
        model.summarizersAll.add(label);
        updateGUI();
    }

    public void addNewQuantifier(Quantifier<RunDao> quantifier) {
        model.quantifiersAll.add(quantifier);
        updateGUI();
    }

    public void updateExistingQualifier(Quantifier<RunDao> quantifier, String labelName) {
        model.quantifiersAll.set(model.getIndexOfQualifier(labelName), quantifier);
        updateGUI();
    }

    public void updateExistingSummarizer(fuzzylogic.Label<RunDao> label, String labelName, String linguisticVariableName) {
        model.summarizersAll.set(model.getIndexOfSummarizer(labelName, linguisticVariableName), label);
        updateGUI();
    }

    public boolean checkIfUpdate() {
        return creationButton.getText().equals("Update");
    }

    @FXML
    public void onClickGenerateResult() {
        String text = new String();
        String [][] values = new String[model.quantifiersAll.size()][12];
        int size = model.qualifier == null ? nrOfCurrentComboBoxes : nrOfCurrentComboBoxes + 1;
        String [][] quantValues = new String[1][size];
        String [] quantifier = new String[model.quantifiersAll.size()];
        List<String> colNames = new ArrayList<>();
        List<Double> weightValues = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            weightValues.add(new Double(weights.get(i).getText()));
        }
        List<RunDao> objects1 = null;
        List<RunDao> objects2 = null;
        if(multiSubjectSummary.isSelected()) {
            objects1 = new ArrayList<>();
            objects2 = new ArrayList<>();
            for (int i = 0; i < model.runs.size(); i++) {
                if (model.runs.get(i).getHorseType().equals(subject1Selected)) {
                    objects1.add(model.runs.get(i));
                } else if (model.runs.get(i).getHorseType().equals(subject2Selected)) {
                    objects2.add(model.runs.get(i));
                }
            }
        }

        for(int i = 0; i < model.quantifiersAll.size(); i++) {
            Summary<RunDao> summary;
            if (model.qualifier != null && model.quantifiersAll.get(i).isAbsolute()) continue;
            if (multiSubjectSummary.isSelected()) {
                if (model.quantifiersAll.get(i).isAbsolute()) continue;
                summary = new Summary<>(model.quantifiersAll.get(i), model.qualifier, objects1, objects2 , model.selectedSummarizers);
                summary.setMultiForm(2);
            } else {
                summary = new Summary<>(model.quantifiersAll.get(i), model.qualifier, model.runs, model.selectedSummarizers);
            }
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

            double T = Math.round(model.measures.goodnessOfTheSummary(summary, weightValues) * 100d) / 100d;
            // PLZ DON'T LOOK
            values[i][0] = Double.toString(T);
            values[i][1] = Double.toString(T1);
            values[i][2] = Double.toString(T2);
            values[i][3] = Double.toString(T3);
            values[i][4] = Double.toString(T4);
            values[i][5] = Double.toString(T5);
            values[i][6] = Double.toString(T6);
            values[i][7] = Double.toString(T7);
            values[i][8] = Double.toString(T8);
            values[i][9] = Double.toString(T9);
            values[i][10] = Double.toString(T10);
            values[i][11] = Double.toString(T11);

            if (summary.getMultiForm() == 4) {
                text += "More runs ";
            } else {
                text += model.quantifiersAll.get(i).getLabelName() + " of runs ";
            }

            if (multiSubjectSummary.isSelected()) {
                if (summary.getMultiForm() == 4) {
                    text += "by " + subject1Selected + " horses than runs by " + subject2Selected + " horses ";
                } else if (summary.getMultiForm() == 3) {
                    text += "by " + subject1Selected + " horses ";
                } else {
                    text += "by " + subject1Selected + " horses compared to runs by " + subject2Selected + " horses ";
                }
            }

            if(model.qualifier != null) {
                text += "having / being " + model.qualifier.getLinguisticVariableName() + " " + model.qualifier.getLabelName();
            }

            if (summary.getMultiForm() == 3) {
                text += " compared to runs by " + subject2Selected + " horses";
            }

            quantifier[i] = model.quantifiersAll.get(i).getLabelName();
            for (int j = 0; j < nrOfCurrentComboBoxes; j++){
                String connective = j > 0 ? " and " : " were with ";
                text +=  connective + model.selectedSummarizers.get(j).getLinguisticVariableName() + " " + model.selectedSummarizers.get(j).getLabelName();
                quantValues[0][j] = model.selectedSummarizers.get(j).getLinguisticVariableName() + " " + model.selectedSummarizers.get(j).getLabelName();
            }
            text += ".\n";
            if(!shouldGenerateTables.isSelected()){
                if (multiSubjectSummary.isSelected()) {
                    text += " [T1 = " + T1 + "]\n";
                } else {
                    text += "T = " + T + " [T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]. \n";
                }
            }
            if (summary.getMultiForm() == 4) break;
        }
        for (int j = 0; j < nrOfCurrentComboBoxes; j++){
            colNames.add("Sumaryzator" + Integer.toString(j + 1));
            String connective = j > 0 ? " and " : " were with ";
            quantValues[0][j] = model.selectedSummarizers.get(j).getLinguisticVariableName() + " " + model.selectedSummarizers.get(j).getLabelName();
        }
        if (model.qualifier != null) {
            colNames.add("Kwalifikator");
            quantValues[0][colNames.size() - 1] = model.qualifier.getLinguisticVariableName()+ " " + model.qualifier.getLabelName();
        }
        result.setText(text);
        setGeneratedSummary(text);
        fileGenerator.setVisible(true);
        if(shouldGenerateTables.isSelected()) {
            LaTeXGenerator generator = new LaTeXGenerator(model.quantifiersAll.size(), 13, Arrays.asList("Kwantyfikator","T","T1","T2","T3","T4","T5","T6","T7","T8","T9","T10","T11"), "Miary jakości" ,values);
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
            model.selectedSummarizers.add(new fuzzylogic.Label<RunDao>());
            nrOfCurrentComboBoxes++;
        }
        updateGUI();
    }

    @FXML
    public void onClickRemoveLastCombo() {
        if (nrOfCurrentComboBoxes > 1) {
            summaryTab.getChildren().remove(comboBoxes.get(nrOfCurrentComboBoxes - 1));
            int idOfLabelToRemove = nrOfCurrentComboBoxes - 1;
            summaryTab.getChildren().remove(summaryTab.lookup("#label"+idOfLabelToRemove));
            nrOfCurrentComboBoxes--;
        }
        updateGUI();
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

    @FXML
    public void onclickAddNewQuantifier(){
        linguisticList.setVisible(false);
        quantifierType.setVisible(true);
        quantifierTypeLabel.setVisible(true);
        quantifiersList.setVisible(true);
        linguisticNameField.setVisible(false);
        linguisticNameLabel.setVisible(false);
    }

    @FXML
    public void onclickAddNewSummarizer(){
        quantifiersList.setVisible(false);
        quantifierType.setVisible(false);
        quantifierTypeLabel.setVisible(false);
        linguisticList.setVisible(true);
        linguisticNameField.setVisible(true);
        linguisticNameLabel.setVisible(true);
    }

    public void addMultiSubjectComboBoxes(Boolean checked) {
        if (checked) {
            summaryTab.getChildren().add(subject1);
            summaryTab.getChildren().add(subject2);
        } else {
            summaryTab.getChildren().remove(subject1);
            summaryTab.getChildren().remove(subject2);
        }
    }

    // FXML GUI OBJECTS //

    // Panes //

    @FXML
    private AnchorPane summaryTab;

    // Buttons //

    @FXML
    private Button generate = new Button();

    @FXML
    private Button fileGenerator = new Button();

    @FXML
    private Button creationButton = new Button();

    // Labels //

    @FXML
    private Label result;

    @FXML
    private Label aLabel;

    @FXML
    private Label mLabel;

    @FXML
    private Label bLabel;

    @FXML
    private Label nLabel;

    @FXML
    private Label quantifierTypeLabel;

    @FXML
    private Label linguisticNameLabel;

    // Combo Boxes

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
    private ComboBox subject1 = new ComboBox();

    @FXML
    private ComboBox subject2 = new ComboBox();

    // Checkboxes

    @FXML
    private CheckBox shouldGenerateTables = new CheckBox();

    @FXML
    private CheckBox multiSubjectSummary = new CheckBox();

    // Choice Box

    @FXML
    private ChoiceBox<String> quantifierType = new ChoiceBox();

    @FXML
    private ChoiceBox<String> functionType = new ChoiceBox();

    @FXML
    private ChoiceBox<String> linguisticNameField = new ChoiceBox();

    // List Views

    @FXML
    private ListView<String> quantifiersList = new ListView();

    @FXML
    private ListView<String> linguisticList = new ListView();

    // Text Fields

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

    @FXML
    private TextField aTextField;

    @FXML
    private TextField mTextField;

    @FXML
    private TextField bTextField;

    @FXML
    private TextField nTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private AnchorPane advancedUserTab;
}
