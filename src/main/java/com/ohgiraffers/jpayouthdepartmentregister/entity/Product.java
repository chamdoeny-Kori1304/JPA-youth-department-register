package com.ohgiraffers.jpayouthdepartmentregister.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter //lombok getter
@Setter //lombok setter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;


}
