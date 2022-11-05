package com.patiun.thrillingtreks.screen;

import com.patiun.thrillingtreks.action.Action;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    @NotEmpty
    @OneToMany(mappedBy = "screen")
    private List<Action> actions;

    protected Screen() {
    }

    public Screen(String text, List<Action> actions) {
        this.message = text;
        this.actions = actions;
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Screen screen = (Screen) o;

        if (id != null ? !id.equals(screen.id) : screen.id != null) {
            return false;
        }
        if (message != null ? !message.equals(screen.message) : screen.message != null) {
            return false;
        }
        return actions != null ? actions.equals(screen.actions) : screen.actions == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", text='" + message + '\'' +
                ", actions=" + actions +
                '}';
    }
}
