package com.alkemyjava.Huergo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    @Lob
    private byte[] image;
    private String title;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private Long rating;
    @ManyToMany
    private List<Personaje> characters;

}
