
package com.ma.bookies1.entity.book;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ma.bookies1.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String board;

    @Column(nullable = false)
    private String description;



    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String edition;

    @Column(nullable = false)
    private Integer quantity;


    @Column(nullable = false)
    private Double price;




    @Column(nullable = false, columnDefinition = "varchar(255) default false")
    private boolean listable=false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
