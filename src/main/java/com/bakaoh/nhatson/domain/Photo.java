package com.bakaoh.nhatson.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.lightadmin.api.config.annotation.FileReference;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Photo implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotBlank
    @Size(max = 255)
    @FileReference(baseDirectory = "/tserver/java-projects/nhatson")
    private String image;

    public Photo() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}