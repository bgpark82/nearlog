package com.bgpark.nearlog.api.dto;

import com.bgpark.nearlog.common.domain.trip.TripType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class PlanDto {

    @Getter
    public static class Make {

        @Setter
        private Long id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private TripType tripType;
        private Long userId;
        private List<Schedule> schedules;
    }

    @Getter
    @Setter
    public static class Schedule {
        private Integer day;
        private List<Long> placeIds;
    }
}
