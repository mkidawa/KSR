import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.DBServer;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/view/StackPaneWindow.fxml"));
        Parent parent = loader.load();

        DBServer server = DBServer.getInstance();
        MainController controller = loader.getController();
        controller.setDataCollection(server.getRuns());
        controller.assignDataToModel();

        primaryStage.setScene(new Scene(parent));
        primaryStage.sizeToScene();
        primaryStage.setTitle("KSR zad 2");
        primaryStage.show();
    }

}
