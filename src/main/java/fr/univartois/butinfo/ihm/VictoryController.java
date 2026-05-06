package fr.univartois.butinfo.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class VictoryController {

    @FXML
    private void relancer(ActionEvent event) { 
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graphique.fxml"));
            Parent viewContent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(viewContent, 696, 579));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}