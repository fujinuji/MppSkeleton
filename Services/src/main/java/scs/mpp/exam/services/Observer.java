package scs.mpp.exam.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Observer extends Remote {
    void startGame(String category) throws RemoteException;
    void nextRound(Map<String, Integer> points, String category) throws RemoteException;
    void top(List<String> top) throws RemoteException;
}
