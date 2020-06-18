package scs.mpp.exam.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Player implements Serializable {
    //IN CASE OF EMERGENCY     @Column(columnDefinition="VARCHAR(64)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String userName;
    @Column
    private String password;

    public Player() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
