package scs.mpp.exam.server;

import scs.mpp.exam.model.Player;
import scs.mpp.exam.repository.PlayerRepository;
import scs.mpp.exam.services.Observer;
import scs.mpp.exam.services.Services;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ServicesImpl implements Services {

    private PlayerRepository playerRepository;
    private Map<String, Observer> loggedUsers;

    public ServicesImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        loggedUsers = new HashMap<>();
    }

    @Override
    public void test() {

    }

    @Override
    public void login(String user, String password, Observer observer) throws Exception {
        if (loggedUsers.containsKey(user)) {
            throw new Exception("User already logged in");
        }

        Player player = playerRepository.checkUser(user, password);

        if (player == null) {
            throw new Exception("Player not found");
        }

        loggedUsers.forEach((key, value) -> {
            try {
                value.test();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        loggedUsers.put(user, observer);
    }
}
