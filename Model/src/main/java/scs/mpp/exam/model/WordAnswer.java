package scs.mpp.exam.model;

import java.io.Serializable;

public class WordAnswer implements Serializable {
    private String gameId;
    private String category;
    private String word;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
