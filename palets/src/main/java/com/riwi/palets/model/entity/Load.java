package com.riwi.palets.model.entity;

import com.riwi.palets.model.enums.LoadStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "load_table")
public class Load extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoadStatus status;

    @Column(nullable = false)
    private Long weight;

    @ManyToOne
    @JoinColumn(name = "pallet_id", nullable = true)
    private Pallet palletId;
}
