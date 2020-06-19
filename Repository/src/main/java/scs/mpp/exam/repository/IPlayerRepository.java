package scs.mpp.exam.repository;

import scs.mpp.exam.model.Player;

public interface IPlayerRepository extends Repository{
    Player checkUser(String user, String password) throws Exception;
}
