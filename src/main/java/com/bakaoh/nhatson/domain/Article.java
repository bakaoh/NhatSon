package com.bakaoh.nhatson.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.lightadmin.api.config.annotation.FileReference;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Entity
public class Article implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(DATE)
    @DateTimeFormat(iso = DATE_TIME)
    @Column(name = "created_at")
    private Date creationDate = new Date();

    @Temporal(DATE)
    @DateTimeFormat(iso = DATE_TIME)
    @Column(name = "updated_at")
    private Date modificationDate = new Date();

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    @Size(max = 255)
    @FileReference(baseDirectory = "/tserver/java-projects/nhatson")
    private String image;

    @Column
    @Lob
    @NotBlank
    private String detail;

    @NotNull
    @ManyToOne
    private Category category;

    @Column
    private boolean feature;

    public Article() {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }
}