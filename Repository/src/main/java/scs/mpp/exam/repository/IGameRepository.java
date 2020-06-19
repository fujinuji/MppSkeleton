package scs.mpp.exam.repository;

import scs.mpp.exam.model.Game;

public interface IGameRepository extends Repository{
    void saveGame(Game game);
}
