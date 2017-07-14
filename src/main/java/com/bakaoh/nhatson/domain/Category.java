package com.bakaoh.nhatson.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    private boolean newest;

    @Column
    private boolean home;

    @Column
    private Long parent;

    public Category() {
    }

    public Category(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNewest() {
        return newest;
    }

    public Long getParent() {
        return parent;
    }

    public boolean isHome() {
        return home;
    }

    @Override
    public String toString() {
        return "Category(" + getName() + ")";
    }
}