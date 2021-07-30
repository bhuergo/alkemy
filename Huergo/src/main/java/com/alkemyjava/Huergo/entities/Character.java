package com.alkemyjava.Huergo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    @Lob
    private byte[] image;
    private String name;
    private Integer age;
    private Double weight;
    private String story;
    @ManyToMany(mappedBy = "characters")
    private List<Movie> movies;
}
