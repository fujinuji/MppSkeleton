package scs.mpp.exam.model;

import java.io.Serializable;

public class GameCategory implements Serializable {
    private String gameId;
    private String gameCategory;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
    }
}
