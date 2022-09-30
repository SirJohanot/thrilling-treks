package com.patiun.thrillingtreks.action;

import com.patiun.thrillingtreks.screen.Screen;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public Action() {
    }

    public Action(Long id, String message, Screen screen) {
        this.id = id;
        this.message = message;
        this.screen = screen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Action action = (Action) o;

        if (id != null ? !id.equals(action.id) : action.id != null) {
            return false;
        }
        if (message != null ? !message.equals(action.message) : action.message != null) {
            return false;
        }
        return screen != null ? screen.equals(action.screen) : action.screen == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", screen=" + screen +
                '}';
    }
}
