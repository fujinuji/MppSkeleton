package scs.mpp.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Game implements Serializable {
    @Id
    @Column(columnDefinition="VARCHAR(64)")
    private String id;
    @Column
    private String firstPlayer;
    @Column
    private String secondPlayer;
    @Column
    private String thirdPlayer;
    @Column
    private Integer firstPlayerPoints;
    @Column
    private Integer secondPlayerPoints;
    @Column
    private Integer thirdPlayerPoints;

    public Game(String id, String firstPlayer, String secondPlayer, String thirdPlayer, Integer firstPlayerPoints, Integer secondPlayerPoints, Integer thirdPlayerPoints) {
        this.id = id;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.thirdPlayer = thirdPlayer;
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
        this.thirdPlayerPoints = thirdPlayerPoints;
    }

    public Game() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getThirdPlayer() {
        return thirdPlayer;
    }

    public void setThirdPlayer(String thirdPlayer) {
        this.thirdPlayer = thirdPlayer;
    }

    public Integer getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public void setFirstPlayerPoints(Integer firstPlayerPoints) {
        this.firstPlayerPoints = firstPlayerPoints;
    }

    public Integer getSecondPlayerPoints() {
        return secondPlayerPoints;
    }

    public void setSecondPlayerPoints(Integer secondPlayerPoints) {
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public Integer getThirdPlayerPoints() {
        return thirdPlayerPoints;
    }

    public void setThirdPlayerPoints(Integer thirdPlayerPoints) {
        this.thirdPlayerPoints = thirdPlayerPoints;
    }

}
