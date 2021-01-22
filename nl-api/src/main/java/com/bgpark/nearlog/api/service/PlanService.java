package com.bgpark.nearlog.api.service;

import com.bgpark.nearlog.api.dto.PlanDto;
import com.bgpark.nearlog.common.domain.PlanPlace;
import com.bgpark.nearlog.common.domain.place.Place;
import com.bgpark.nearlog.common.domain.place.PlaceRepository;
import com.bgpark.nearlog.common.domain.plan.Plan;
import com.bgpark.nearlog.common.domain.plan.PlanRepository;
import com.bgpark.nearlog.common.domain.trip.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;

    public PlanDto.Make autoMake(PlanDto.Make req) {

        Trip trip = Trip.builder()
                .name(req.getName())
                .tripType(req.getTripType())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .build();

        Plan plan = Plan.builder()
                .trip(trip)
                .userId(req.getUserId())
                .build();

        List<PlanPlace> planPlaces = new ArrayList<>();

        for (PlanDto.Schedule schedule : req.getSchedules()) {
            List<Place> places = placeRepository.findByIdIn(schedule.getPlaceIds());
            for(Place place: places) {
                planPlaces.add(PlanPlace.builder()
                        .day(schedule.getDay())
                        .place(place)
                        .plan(plan)
                        .build());
            }
        }

        plan.setPlanPlaces(planPlaces);
        planRepository.save(plan);
        req.setId(plan.getId());
        return req;
    }
}
