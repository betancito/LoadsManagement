package com.riwi.palets.model.entity;

import com.riwi.palets.model.enums.PalletStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pallet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pallet extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long palletNumber;

    @Column(nullable = false)
    private Long MaximumWeight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PalletStatus status;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location Location;

    @OneToMany(mappedBy = "palletId", fetch = FetchType.LAZY)
    private List<Load> loads;

}
