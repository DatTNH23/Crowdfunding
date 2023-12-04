package com.example.Crowdfunding.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fund")
@Setter
@Getter
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private int amount;

    @OneToOne(mappedBy = "fund")
    private ProjectOption projectOption;

    @ManyToOne
    @JoinColumn(name ="project_id")
    private Project project;
}
