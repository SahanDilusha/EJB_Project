package org.popcorntech.GroceryOrderSystem.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @JoinColumn(name = "product_category_id", nullable = false)
    @ManyToOne
    private ProductCategory productCategory;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Product setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Product setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return this;
    }
}