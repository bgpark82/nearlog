package com.bgpark.nearlog.common.domain.plan;

import com.bgpark.nearlog.common.domain.PlanPlace;
import com.bgpark.nearlog.common.domain.trip.Trip;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Plan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Trip trip;

    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
    private List<PlanPlace> planPlaces;
}
