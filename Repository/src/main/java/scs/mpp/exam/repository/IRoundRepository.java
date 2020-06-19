package scs.mpp.exam.repository;

import scs.mpp.exam.model.Round;

import java.util.List;

public interface IRoundRepository extends Repository{
    void saveRound(Round round);
    List<Round> getByGameId(String gameId);
}
