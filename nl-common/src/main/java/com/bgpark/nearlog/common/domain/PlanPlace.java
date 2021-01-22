package com.bgpark.nearlog.common.domain;

import com.bgpark.nearlog.common.domain.place.Place;
import com.bgpark.nearlog.common.domain.plan.Plan;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlanPlace {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer day;

    @ManyToOne
    private Plan plan;

    @ManyToOne
    private Place place;

    @Builder
    public PlanPlace(Integer day, Plan plan, Place place) {
        this.day = day;
        this.plan = plan;
        this.place = place;
    }
}

