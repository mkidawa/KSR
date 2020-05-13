package controllers;

import com.mongodb.client.MongoCollection;
import dao.RunDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.RunsModel;


public class MainController {
    private RunsModel model = new RunsModel();
    private MongoCollection<RunDao> dataCollection;

    public MongoCollection getDataCollection() {
        return dataCollection;
    }

    public void setDataCollection(MongoCollection<RunDao> dataCollection) {
        this.dataCollection = dataCollection;
    }

    public void assignDataToModel() {
        for (RunDao cur : dataCollection.find()) {
            model.addRun(cur);
        }
        records.setText(Integer.toString(model.runs.size()));
    }

    @FXML
    private Button generate;
    @FXML
    private TextField records;

    public MainController() {

    }

    @FXML
    public void onActionButton() {
        System.out.println("NO ELO");
    }

    @FXML
    void initialize() {
    }
}
