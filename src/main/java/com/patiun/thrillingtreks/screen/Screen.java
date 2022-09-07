package com.patiun.thrillingtreks.screen;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "screen_text")
    private String text;

    @OneToMany
    @NotNull
    private Map<String, Screen> actions;

    protected Screen() {
    }

    public Screen(String text, Map<String, Screen> actions) {
        this.text = text;
        this.actions = actions;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Map<String, Screen> getActions() {
        return actions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setActions(Map<String, Screen> actions) {
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
        if (text != null ? !text.equals(screen.text) : screen.text != null) {
            return false;
        }
        return actions != null ? actions.equals(screen.actions) : screen.actions == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", actions=" + actions +
                '}';
    }
}
