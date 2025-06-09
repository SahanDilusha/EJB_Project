package com.bid.bidsystem.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "bid")
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price", nullable = false)
    private double price;

    @JoinColumn(name = "product_id",nullable = false)
    @ManyToOne
    private Product product;

    @JoinColumn(name = "user_id",nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name = "bid_status_id",nullable = false)
    @ManyToOne
    private BidStatus bidStatus;

    public Bid() {
    }

    public int getId() {
        return id;
    }

    public Bid setId(int id) {
        this.id = id;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Bid setPrice(double price) {
        this.price = price;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Bid setProduct(Product product) {
        this.product = product;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Bid setUser(User user) {
        this.user = user;
        return this;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public Bid setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
        return this;
    }
}
