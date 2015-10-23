/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.model.Activity;
import main.util.CalendarUtil;

/**
 *
 * @author krisli
 */
public class CpiDialogController {
        @FXML
	public TextField firstCPI;
        @FXML
	public TextField secondCPI;
        @FXML
	public TextField thirdCPI;
        @FXML
	public TextField firstSPI;
        @FXML
	public TextField secondSPI;
        @FXML
	public TextField thirdSPI;
        @FXML
        public TextField d1TF;
        @FXML
        public TextField d2TF;
        @FXML
        public TextField d3TF;
        @FXML
	public Button save;
        @FXML
	public Button cancel;

	public Activity data;
	public Boolean saveClicked = false;
	private Stage dialogStage;

	@FXML
	private void initialize() {

	}

	public void setPromptText() {
		firstCPI.setText("" + data.getFirstCPI());
		secondCPI.setText("" + data.getSecondCPI());
		thirdCPI.setText("" + data.getThirdCPI());
		firstSPI.setText("" + data.getFirstSPI());
		secondSPI.setText("" + data.getSecondSPI());
		thirdSPI.setText("" + data.getThirdSPI());
                d1TF.setText(""+CalendarUtil.format(data.getFirstDate()));
                d2TF.setText(""+CalendarUtil.format(data.getSecondDate()));
                d3TF.setText(""+CalendarUtil.format(data.getThirdDate()));
	}

	@FXML
	public void handleSave() {
            if(isInputValid()){
		data.setFirstCPI(Double.parseDouble(firstCPI.getText()));
		data.setFirstSPI(Double.parseDouble(firstSPI.getText()));
		data.setSecondCPI(Double.parseDouble(secondCPI.getText()));
		data.setSecondSPI(Double.parseDouble(secondSPI.getText()));
		data.setThirdCPI(Double.parseDouble(thirdCPI.getText()));
		data.setThirdSPI(Double.parseDouble(thirdSPI.getText()));
                data.setFirstDate(CalendarUtil.parse(d1TF.getText()));
                data.setSecondDate(CalendarUtil.parse(d2TF.getText()));
                data.setThirdDate(CalendarUtil.parse(d3TF.getText()));
		saveClicked = true;
		dialogStage.close();
            }
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isSaveClicked() {
		return saveClicked;
	}

	public Activity getData() {
		return data;
	}

	public void setData(Activity data) {
		this.data = data;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}

	private boolean isInputValid() {
		String errorMessage = "";

		try {
			Double.parseDouble(firstCPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "CPI e parë nuk është e vlefshme (Duhet të jetë numër)\n";
		}

		try {
			Double.parseDouble(secondCPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "CPI e dytë nuk është e vlefshme (Duhet të jetë numër)\n";
		}

		try {
			Double.parseDouble(thirdCPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "CPI e tretë nuk është e vlefshme (Duhet të jetë numër)\n";
		}

		try {
			Double.parseDouble(firstSPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "SPI e parë nuk është e vlefshme (Duhet të jetë numër)\n";
		}

		try {
			Double.parseDouble(secondSPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "SPI e dytë nuk është e vlefshme (Duhet të jetë numër)\n";
		}

		try {
			Double.parseDouble(thirdSPI.getText());
		} catch (NumberFormatException e) {
			errorMessage += "SPI e tretë nuk është e vlefshme (Duhet të jetë numër)\n";
		}
                
                if(d1TF.getText() == null || d1TF.getText().contains("null") || d1TF.getText().length() == 0){
                    data.setFirstDate(null);
                }else{
                if (!CalendarUtil.validString(d1TF.getText())) {
                    errorMessage += "Ju lutem perdorni formatin dd-mm-vvvv!\n";
                     }
                }
                
                if(d2TF.getText() == null || d2TF.getText().contains("null") || d2TF.getText().length() == 0){
                    data.setSecondDate(null);
                }else{
                if (!CalendarUtil.validString(d2TF.getText())) {
                    errorMessage += "Ju lutem perdorni formatin dd-mm-vvvv!\n";
                     }
                }
                
                if(d3TF.getText() == null || d3TF.getText().contains("null") || d3TF.getText().length() == 0){
                    data.setThirdDate(null);
                }else{
                if (!CalendarUtil.validString(d3TF.getText())) {
                    errorMessage += "Ju lutem perdorni formatin dd-mm-vvvv!\n";
                     }
                }

		if (errorMessage.length() == 0) return true;
		else {
			//Trego mesazhin e errorit
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Fusha jo të vlefshme");
			alert.setHeaderText("Ju lutem korrigjoni fushat e pavlefshme");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}

	}
}