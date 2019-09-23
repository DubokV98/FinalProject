package com.LeverX.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "message cannot be empty!")
    @JsonProperty
    private String message;

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate created_at;

    @NotBlank(message = "commentator name cannot be empty!")
    @JsonProperty
    private String commentatorName;

    @JsonProperty
    private boolean approve;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private GameObject gameObject;

    public Comment(String message, LocalDate created_at, String commentatorName,
                            boolean approved, User user, GameObject gameObject) {
        this.message = message;
        this.created_at = created_at;
        this.commentatorName = commentatorName;
        this.approve = approved;
        this.user = user;
        this.gameObject = gameObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return approve == comment.approve &&
                Objects.equals(id, comment.id) &&
                Objects.equals(message, comment.message) &&
                Objects.equals(created_at, comment.created_at) &&
                Objects.equals(commentatorName, comment.commentatorName) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(gameObject, comment.gameObject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message, created_at, commentatorName, approve, user, gameObject);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", created_at=" + created_at +
                ", commentatorName='" + commentatorName + '\'' +
                ", approve=" + approve +
                ", user=" + user +
                ", gameObject=" + gameObject +
                '}';
    }
}