package com.bgpark.nearlog.api.service;

import com.bgpark.nearlog.api.dto.TripDto;
import com.bgpark.nearlog.common.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public void save(TripDto.Save req) {
        tripRepository.save(req.toEntity());
    }
}
