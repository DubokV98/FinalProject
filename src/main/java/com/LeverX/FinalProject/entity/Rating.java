package com.LeverX.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty
    private double rating;

    @JsonProperty
    private int ratingcol;

    @JsonProperty
    private double avg_rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return Double.compare(rating1.rating, rating) == 0 &&
                ratingcol == rating1.ratingcol &&
                Double.compare(rating1.avg_rating, avg_rating) == 0 &&
                Objects.equals(id, rating1.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rating, ratingcol, avg_rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", ratingcol=" + ratingcol +
                ", avg_rating=" + avg_rating +
                '}';
    }
}
