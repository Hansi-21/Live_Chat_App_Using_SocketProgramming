package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormUiController {
    public TextField txtName;
    public Button BtnJoin;

    public void JoinOnAction(ActionEvent actionEvent) throws IOException {
        JoinToChat();

    }

    public void JoinByEntered(ActionEvent actionEvent) throws IOException {
        JoinToChat();
    }

    public void JoinToChat() throws IOException {
        String username=txtName.getText();
        if(username.length()>0){

            if(username.equalsIgnoreCase("hansi")) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/ServerUi.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("LIVE CHAT");

                txtName.clear();

                Stage close = (Stage) BtnJoin.getScene().getWindow();
                close.close();
            }else{
                new Alert(Alert.AlertType.WARNING,"Incorrect UserName or Password !",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Please fill in required fields !",
                    ButtonType.OK,ButtonType.NO).show();
        }
    }
}
