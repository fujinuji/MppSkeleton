package scs.mpp.exam.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scs.mpp.exam.model.Player;
import scs.mpp.exam.services.Services;

public class LoginController {

    private Services services;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginOnAction(ActionEvent event) {
        String userName = usernameTextField.getText();
        String password = passwordField.getText();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource("gameView.fxml"));
            Parent root=loader.load();

            GameController ctrl = loader.getController();
            services.login(userName, password, ctrl);
            Player player = new Player();
            player.setUserName(userName);

            Stage primaryStage = new Stage();
            ctrl.setService(services, player);
            primaryStage.setScene(new Scene(root, 500, 350));
            primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            });
        }
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
