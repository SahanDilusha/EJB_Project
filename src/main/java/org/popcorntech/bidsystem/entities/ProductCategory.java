package org.popcorntech.bidsystem.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_categorys")
public class ProductCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length = 50, nullable = false)
    private String name;

    public ProductCategory() {
    }

    public int getId() {
        return id;
    }

    public ProductCategory setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategory setName(String name) {
        this.name = name;
        return this;
    }
}
