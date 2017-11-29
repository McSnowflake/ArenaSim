package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import enums.Dice;

import javax.xml.bind.JAXBException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../app/ArenaApp.fxml"));
        primaryStage.setTitle("Arena");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws JAXBException {

        Dice high = Dice.HIGH;
        System.out.println(high.roll());

    }
}
