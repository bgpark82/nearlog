package com.bgpark.nearlog.common.domain.trip;

import com.bgpark.nearlog.common.domain.plan.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Trip {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    @OneToMany(mappedBy = "trip")
    private List<Plan> plans;


}
