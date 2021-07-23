package com.alkemyjava.Huergo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    @Lob
    private byte[] image;
    private String name;
    private Long age;
    private Long weight;
    private String history;
    @ManyToMany
    private List<Movie> movies;

}
