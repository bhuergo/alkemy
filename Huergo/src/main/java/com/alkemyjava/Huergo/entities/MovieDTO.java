package com.alkemyjava.Huergo.entities;

import lombok.Data;

import java.util.Date;

@Data
public class MovieDTO {
    private String image;
    private String title;
    private Date creationDate;
}
