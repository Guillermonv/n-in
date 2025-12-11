package com.n.in.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "workflows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "workflows", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Step> steps;
}