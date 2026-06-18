package com.learning.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Audited;

@Entity
@Getter
@Setter
@Audited
public class Book extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int price;



    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

}
