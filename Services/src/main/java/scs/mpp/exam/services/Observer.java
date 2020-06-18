package scs.mpp.exam.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    void test() throws RemoteException;
}
