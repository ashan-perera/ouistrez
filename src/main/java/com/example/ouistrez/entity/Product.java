package com.example.ouistrez.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_CLASS_ID", referencedColumnName = "ID", nullable = false)
    private ProductClass productClass;

}
