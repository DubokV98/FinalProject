package com.LeverX.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "gameobject")
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String text;

    @JsonProperty
    private double price;

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate created_at;

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updated_at;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private GameObjectStatus status;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public GameObject(String title, String text, double price, LocalDate created_at, LocalDate updated_at, GameObjectStatus status, User user, Game game) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
        this.user = user;
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameObject)) return false;
        GameObject that = (GameObject) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text) &&
                Objects.equals(created_at, that.created_at) &&
                Objects.equals(updated_at, that.updated_at) &&
                status == that.status &&
                Objects.equals(user, that.user) &&
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, text, price, created_at, updated_at, status, user, game);
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", status=" + status +
                ", user=" + user +
                ", game=" + game +
                '}';
    }
}
