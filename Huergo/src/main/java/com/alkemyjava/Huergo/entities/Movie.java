package com.alkemyjava.Huergo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @Lob
    private String image;
    private String title;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private Integer rating;
    @ManyToMany
    private List<Character> characters;

}
