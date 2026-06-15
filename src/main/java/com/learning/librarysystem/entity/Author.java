package com.learning.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )

    private List<Book> books=new ArrayList<>();


}
