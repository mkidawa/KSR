package controllers;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import fuzzylogic.Quantifier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.RunsModel;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private RunsModel model = new RunsModel();
    private List<ComboBox> comboBoxes;
    private int nrOfStartingComboBoxes = 2;
    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        model.setDataCollection(dataCollection);
    }

    public void assignDataToModel() {
        for (RunDao cur : model.getDataCollection().find()) {
            model.addRun(cur);
        }
        for (ComboBox iterator : comboBoxes) {
            iterator.getItems().addAll(model.summarizer.getAllSummarizerLabels());
            iterator.setValue(model.summarizer.getAllSummarizerLabels().get(0));
        }
        for (int i = 0; i < nrOfStartingComboBoxes; i++) {
            System.out.println(comboBoxes.get(i));
            pane.getChildren().add(comboBoxes.get(i));
        }

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
    private Pane pane;

    public MainController() {
    }

    void setComboBoxProperty(ComboBox box, double x) {
       box.setLayoutX(x);
       box.setLayoutY(66);
       box.setPrefWidth(200.0);
    }

    @FXML
    void initialize() {
        comboBoxes = new ArrayList<>();
        setComboBoxProperty(combo1, 32);
        setComboBoxProperty(combo2, 242);
        setComboBoxProperty(combo3, 452);
        setComboBoxProperty(combo4, 662);
        setComboBoxProperty(combo5, 872);
        comboBoxes.add(combo1);
        comboBoxes.add(combo2);
        comboBoxes.add(combo3);
        comboBoxes.add(combo4);
        comboBoxes.add(combo5);
    }

    @FXML
    public void onClickGenerateResult() {
        String text = new String();
        model.summarizers.add(model.summarizer1);
        model.summarizers.add(model.summarizer2);
        for(Quantifier<RunDao> quantifier : model.quantifier.getQuantifiers())
        {
            double T1 = model.measures.degreeOfTruth(quantifier, model.qualifier, model.summarizers, model.runs);
            double T2 = model.measures.degreeOfImprecision(model.summarizer2, model.runs);
            double T3 = model.measures.degreeOfCovering(model.qualifier, model.summarizer2, model.runs);
            double T4 = model.measures.degreeOfAppropriateness(model.qualifier, model.summarizer2, model.runs);
            double T5 = model.measures.lengthOfSummary(model.summarizer2);
            double T6 = model.measures.degreeOfQuantifierImprecision(quantifier, model.runs);
            double T7 = model.measures.degreeOfQuantifierCardinality(quantifier, model.runs);
            double T8 = model.measures.degreeOfSummarizerCardinality(model.summarizer2, model.runs);
            double T9 = model.measures.degreeOfQualifierImprecision(model.qualifier, model.runs);
            double T10 = model.measures.degreeOfQualifierCardinality(model.qualifier, model.runs);
            double T11 = model.measures.lengthOfQualifier(model.qualifier);

            //text += quantifier.getLinguisticVariableName() + " of horses being " + model.qualifier.getLinguisticVariableName() + " of: " + model.qualifier.getLabelName() + " were of " + model.summarizer2.getLinguisticVariableName() + ": " + model.summarizer2.getLabelName() + " [T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]. \n";
            // System.out.println(quantifier.getName() + " of horses being " + model.qualifier.getName() + " of: " + model.qualifier.getLabel() + " were of " + model.summarizer2.getName() + ": " + model.summarizer2.getLabel() + " [T1 = " + T1 + ", T2 = " + T2 + ", T3 = " + T3 + ", T4 = " + T4 + ", T5 = " + T5 + ", T6 = " + T6 + ", T7 = " + T7 + ", T8 = " + T8 + ", T9 = " + T9 + ", T10 = " + T10 + ", T11 = " + T11 + "]");
            text += quantifier.getLinguisticVariableName() + " of horses were of " + model.summarizers.get(0).getLinguisticVariableName() + ": " + model.summarizers.get(0).getLabelName() + " and of " + model.summarizers.get(1).getLinguisticVariableName() + ": " + model.summarizers.get(1).getLabelName() + " [T1 = " + T1 + "]";
            // System.out.println(quantifier.getName() + " of horses were of " + model.summarizer1.getName() + ": " + model.summarizer1.getLabel() + " or of " + model.summarizer2.getName() + ": " + model.summarizer2.getLabel() + " [" + model.measures.degreeOfTruth(quantifier, null, model.and, model.runs) + "]");
            // System.out.println(quantifier.getName() + " of horses were of " + model.summarizer1.getName() + ": " + model.summarizer1.getLabel() + " [" + model.measures.degreeOfTruth(quantifier, null , model.summarizer1, model.runs) + "]");
        }
        result.setText(text);
    }

    @FXML
    public void onClickAddNewCombo() {
        pane.getChildren().add(comboBoxes.get(nrOfStartingComboBoxes));
        nrOfStartingComboBoxes++;
    }
}
