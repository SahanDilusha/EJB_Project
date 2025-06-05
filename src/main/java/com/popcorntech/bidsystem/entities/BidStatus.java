package com.popcorntech.bidsystem.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "bid_status")
public class BidStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",length = 20, nullable = false)
    private String name;

    public BidStatus() {
    }

    public int getId() {
        return id;
    }

    public BidStatus setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BidStatus setName(String name) {
        this.name = name;
        return this;
    }
}
