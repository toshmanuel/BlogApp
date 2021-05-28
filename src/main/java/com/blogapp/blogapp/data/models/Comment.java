package com.blogapp.blogapp.data.models;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Comment {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String commentator;
    @Column(nullable = false, length = 150)
    private String content;

    private LocalDate dateCreated;




}
