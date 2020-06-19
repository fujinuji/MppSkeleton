package scs.mpp.exam.services;

import scs.mpp.exam.model.GameCategory;
import scs.mpp.exam.model.Round;
import scs.mpp.exam.model.WordAnswer;

import java.util.List;

public interface Services {
    void sendQuizResponse(Round round);
    void login(String user, String password, Observer observer) throws Exception;
    List<WordAnswer> getWord(String gameId, String category);
    List<GameCategory> getCategories(String gameId);
}
