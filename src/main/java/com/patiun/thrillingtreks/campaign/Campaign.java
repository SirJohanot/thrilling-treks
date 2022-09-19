package com.patiun.thrillingtreks.campaign;

import com.patiun.thrillingtreks.screen.Screen;
import com.patiun.thrillingtreks.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "campaign_name")
    private String name;

    @NotNull
    @NotEmpty
    @Column(name = "campaign_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;

    @ManyToOne
    @NotNull
    private Screen startScreen;

    protected Campaign() {
    }

    public Campaign(String name, String description, User author, Screen startScreen) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.startScreen = startScreen;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public Screen getStartScreen() {
        return startScreen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setStartScreen(Screen startScreen) {
        this.startScreen = startScreen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Campaign campaign = (Campaign) o;

        if (id != null ? !id.equals(campaign.id) : campaign.id != null) {
            return false;
        }
        if (name != null ? !name.equals(campaign.name) : campaign.name != null) {
            return false;
        }
        if (description != null ? !description.equals(campaign.description) : campaign.description != null)
            return false;
        if (author != null ? !author.equals(campaign.author) : campaign.author != null) {
            return false;
        }
        return startScreen != null ? startScreen.equals(campaign.startScreen) : campaign.startScreen == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (startScreen != null ? startScreen.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", startScreen=" + startScreen +
                '}';
    }
}
