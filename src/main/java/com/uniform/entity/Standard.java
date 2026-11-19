package com.uniform.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "standards")
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String standardName;
    private String sectionName;
    private String status;
    private LocalDateTime createdAt;

    // Intha fields puthusa add pandrom
    private int totalStudents = 0;
    private int totalTops = 0;
    private int totalBottoms = 0;

    // House-wise details vah JSON string-ah save pandrom (Red: 5, Green: 4...)
    @Column(columnDefinition = "TEXT")
    private String houseDetails; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    @JsonIgnore
    private School school;

    public Standard() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStandardName() { return standardName; }
    public void setStandardName(String standardName) { this.standardName = standardName; }

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }

    public int getTotalTops() { return totalTops; }
    public void setTotalTops(int totalTops) { this.totalTops = totalTops; }

    public int getTotalBottoms() { return totalBottoms; }
    public void setTotalBottoms(int totalBottoms) { this.totalBottoms = totalBottoms; }

    public String getHouseDetails() { return houseDetails; }
    public void setHouseDetails(String houseDetails) { this.houseDetails = houseDetails; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
}