package com.kori1304.jpayouthdepartmentregister.entities;


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
