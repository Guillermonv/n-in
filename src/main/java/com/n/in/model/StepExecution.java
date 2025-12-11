package com.n.in.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "step_executions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CORRECCIÓN 1: Se debe añadir @ManyToOne para mapear la entidad Step.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id") // Coincide con tu DDL SQL
    private Step step;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id") // Corregido el nombre de la columna para que coincida con tu DDL SQL
    private Execution executions;

    @Column(columnDefinition = "LONGTEXT")
    private String output;

}