package com.bakaoh.nhatson.domain;

import com.bakaoh.nhatson.util.Utils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Entity
public class Job implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(DATE)
    @DateTimeFormat(iso = DATE_TIME)
    @Column(name = "post_at")
    private Date postDate = new Date();

    @Temporal(DATE)
    @DateTimeFormat(iso = DATE_TIME)
    @Column
    private Date deadline = new Date();

    @Column
    @NotBlank
    private String title;

    @Column
    private String experience;

    @Column
    private String diploma;

    @Column
    private String type;

    @Column
    private String sex;

    @Column
    private Integer num;

    @Column
    private String area;

    @Column
    @Lob
    private String description;

    @Column
    @Lob
    private String requirement;

    @Column
    @Lob
    private String skill;

    @Column
    @Lob
    private String benefit;

    public Job() {
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getUrl() {
        return "/job/" + getId();
    }

    public String strPostDate() {
        return Utils.format(postDate);
    }

    public String strDeadline() {
        return Utils.format(deadline);
    }
}