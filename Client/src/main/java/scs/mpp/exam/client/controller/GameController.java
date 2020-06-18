package scs.mpp.exam.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import scs.mpp.exam.model.Player;
import scs.mpp.exam.services.Observer;
import scs.mpp.exam.services.Services;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameController extends UnicastRemoteObject implements Observer, Serializable {
    private Services service;
    private Player player;

    @FXML
    private TextField aa;

    @FXML
    private TextField bb;

    public GameController() throws RemoteException {
    }

    @Override
    public void test() throws RemoteException {
        aa.setText("Someone logged in");
    }

    public void setService(Services service, Player player) {
        this.service = service;
        this.player = player;
    }
}
