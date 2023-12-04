package com.example.Crowdfunding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project")
@Setter
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;
    @Column(name = "target")
    private int target;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectOption> options;

    @ManyToMany(mappedBy = "project")
    private List<Fund> funds;
}
