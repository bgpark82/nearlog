package com.bgpark.nearlog.api.dto;

import com.bgpark.nearlog.common.domain.trip.Trip;
import com.bgpark.nearlog.common.domain.trip.TripType;
import lombok.Data;

import java.time.LocalDate;

public class TripDto {

    @Data
    public static class Save {

        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private Long userId;
        private TripType tripType;

        public Trip toEntity() {
            return Trip.builder()
                    .name(name)
                    .startDate(startDate)
                    .endDate(endDate)
                    .userId(userId)
                    .tripType(tripType)
                    .build();
        }
    }
}
