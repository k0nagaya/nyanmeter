package com.nyanmeter.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cat implements Comparable<Cat> {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private int rate;

    @Column(nullable = false, unique = true)
    private String url;

    public Cat(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.rate = 0;
    }

    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int compareTo(Cat another) {
        return Integer.compare(another.rate, this.rate);
    }
}
