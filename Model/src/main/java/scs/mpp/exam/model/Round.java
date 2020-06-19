package scs.mpp.exam.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Round implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String playerName;
    @Column
    private String answer;
    @Column
    private String gameId;
    @Column
    private Integer round;
    @Column
    private Integer points;
    @Column
    private String category;

    public Round(Integer id, String playerName, String answer, String gameId, Integer round, Integer points, String category) {
        this.id = id;
        this.playerName = playerName;
        this.answer = answer;
        this.gameId = gameId;
        this.round = round;
        this.points = points;
        this.category = category;
    }

    public Round() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAnswer() {
        return answer;
    }

    public String getGameId() {
        return gameId;
    }

    public Integer getRound() {
        return round;
    }

    public Integer getPoints() {
        return points;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
