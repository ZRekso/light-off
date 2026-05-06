package fr.univartois.butinfo.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
	@FXML
	private Label coupJouer;

    @FXML
    private GridPane lightGrid;
    private Button[][] lightButtons = new Button[5][5];
    private GameGrid gameGrid = new GameGrid();

    @FXML
    public void initialize() {
        for (Node child : lightGrid.getChildren()) {
            Integer row = GridPane.getRowIndex(child);
            if (row == null) row = 0;
            Integer col = GridPane.getColumnIndex(child);
            if (col == null) col = 0;
            if (child instanceof Button button) {
                lightButtons[row][col] = button;
            }
        }
        gameGrid.randomInit();
        updateView();
        lightGrid.setDisable(false);
    }

    private void updateView() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameGrid.isOn(i, j)) {
                    lightButtons[i][j].setText("*");
                    lightButtons[i][j].setStyle("-fx-background-color: E9FF00;");
                } else {
                    lightButtons[i][j].setText("");
                    lightButtons[i][j].setStyle("-fx-background-color: CECECE;");
                }
            }
        }
    }

    @FXML
    private void btnClick(ActionEvent event) {
        int nbCoups = Integer.parseInt(coupJouer.getText());
        coupJouer.setText(String.valueOf(nbCoups + 1));
        Button button = (Button) event.getSource();
        Integer row = GridPane.getRowIndex(button);
        if (row == null) {
            row = 0;
        }
        Integer col = GridPane.getColumnIndex(button);
        if (col == null) {
            col = 0;
        }
        gameGrid.switchAt(row, col);
        updateView();
        /* je voulais changer de scene quand toutes les lumière sont eteinte mais 
         * je n'arrivais pas j'ai donc demander de l'aide à une IA qui ma dit que je devais gérer le cas ou sa marche pas (TRY CATCH)
         * c'est pour sa le IOException pour le reste du if j'ai repis le code dans le main*/
        if (gameGrid.isOff()) {
        	checkVictory();
        }
    }
    
    @FXML
    private void relancer() {
    	gameGrid.randomInit();
        updateView();
        lightGrid.setDisable(false);
        coupJouer.setText(String.valueOf(0));
    }
    
    private void checkVictory() {
        if (gameGrid.isOff()) {
        	System.out.println("Vérification victoire... Toutes éteintes ? " + gameGrid.isOff());
            try {
            	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/ihm/Victoire.fxml"));                Parent viewContent = fxmlLoader.load();
                Stage stage = (Stage) lightGrid.getScene().getWindow();
                stage.setScene(new Scene(viewContent, 696, 579));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void easterEgg() {
    	gameGrid.init();
        updateView();
        checkVictory();
    }
}