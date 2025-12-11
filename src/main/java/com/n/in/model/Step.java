package com.n.in.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "steps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderIndex;

    private String name;

    private String operationType;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String prompt;

    // 1. CORRECCIÓN de JoinColumn: Usa 'workflow_id' para coincidir con tu SQL.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private Workflow workflows;

    @ManyToOne(fetch = FetchType.EAGER) // <--- Cambio a EAGER
    @JoinColumn(name = "agent_id")
    private Agent agent;
}