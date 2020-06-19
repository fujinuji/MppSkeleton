package scs.mpp.exam.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import scs.mpp.exam.model.Player;
import scs.mpp.exam.model.Round;
import scs.mpp.exam.services.Observer;
import scs.mpp.exam.services.Services;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class GameController extends UnicastRemoteObject implements Observer, Serializable {
    private Services services;
    private Player player;

    @FXML
    private Button gameButton;

    @FXML
    private Pane quizPane;

    @FXML
    private Label currentCategoryLabel;

    @FXML
    private TextField answerTextField;

    @FXML
    private Button sendResponseButton;

    @FXML
    private Pane finishGamePane;

    @FXML
    private Label gameLabel;

    public GameController() throws RemoteException {
    }

    @FXML
    public void initialize() {
        quizPane.setVisible(false);
        gameButton.setDisable(true);
        finishGamePane.setVisible(false);
    }

    @FXML
    void gameButtonOnAction(ActionEvent event) {
        gameButton.setVisible(false);
        quizPane.setVisible(true);
    }

    @FXML
    void sendResponseOnAction(ActionEvent event) {
        Round round = new Round();
        round.setAnswer(answerTextField.getText());
        round.setPlayerName(player.getUserName());

        sendResponseButton.setDisable(true);
        services.sendQuizResponse(round);
    }

    @Override
    public void startGame(String category) throws RemoteException {
        Platform.runLater(() -> {
            gameButton.setDisable(false);
            currentCategoryLabel.setText(category);
        });

        quizPane.setVisible(false);
    }

    @Override
    public void nextRound(Map<String, Integer> points, String category) throws RemoteException {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String data = "";
            for (String x : points.keySet()) {
                data += "Player: " + x + " with points " + points.get(x) + "\n";
            }
            alert.setContentText("Round finished with points:\n" + data);
            alert.showAndWait();
            currentCategoryLabel.setText(category);
            nextRound();
        });
    }

    @Override
    public void top(List<String> top) throws RemoteException {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String data = "";
            for (int i = 0; i < top.size(); i += 2) {
                data += "Player: " + top.get(i) + " with points " + top.get(i + 1) + "\n";
            }
            alert.setContentText("Game finished with points:\n" + data);
            alert.showAndWait();
            quizPane.setVisible(false);
            finishGamePane.setVisible(true);
            gameLabel.setText("Game finished");
            gameLabel.setVisible(true);
            Platform.exit();
            System.exit(0);
        });

    }

    public void setService(Services services, Player player) {
        this.services = services;
        this.player = player;
    }

    private void nextRound() {
        answerTextField.clear();
        sendResponseButton.setDisable(false);
    }
}
