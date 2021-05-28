package com.blogapp.blogapp.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id()
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    private UUID id;

    private String commentator;
    @Column(nullable = false, length = 150)
    private String content;

    @CreationTimestamp
    private LocalDate dateCreated;

    public Comment(String content){
        this.content = content;
    }

}
