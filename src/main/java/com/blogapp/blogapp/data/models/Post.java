package com.blogapp.blogapp.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.spi.CascadeStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@Entity
@Table(name = "blog_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String title;

    @Column(length = 1000)
    private String content;

    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Author author;

    @CreationTimestamp
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate dateModified;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void addComment(Comment... comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        this.comments.addAll(Arrays.asList(comment));
    }
}
