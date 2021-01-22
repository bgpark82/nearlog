package com.bgpark.nearlog.api.service;

import com.bgpark.nearlog.api.component.PlaceFinder;
import com.bgpark.nearlog.api.dto.PlaceDto;
import com.bgpark.nearlog.common.domain.place.Place;
import com.bgpark.nearlog.common.domain.place.PlaceRepository;
import com.bgpark.nearlog.common.domain.utils.MapperUtils;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final static Integer PLACE_PAGE_SIZE = 4;

    private final PlaceRepository placeRepository;
    private final PlaceFinder placeFinder;

    public List<PlaceDto.PlaceRes> getPlaceByTitle(String title){
        List<Place> places = placeRepository.findByTitleStartsWith(title);
        List<PlaceDto.PlaceRes> placeRes = MapperUtils.mapAll(places, PlaceDto.PlaceRes.class);
        return placeRes;
    };

    public List<PlaceDto.Schedule> getRecommendPlace(PlaceDto.Recommend req) {
        List<Place> places = placeFinder.getPlaceByLocation(req);

        // 필터
        places = places.stream()
                .filter(p -> (!req.getExclusions().contains(p.getId())))
                .collect(Collectors.toList());

        // 랜덤
        Collections.shuffle(places);

        // 여행 기간
        long days = DAYS.between(req.getStartDate(), req.getEndDate());
        long limit = (days + 1) * PLACE_PAGE_SIZE;

        // 개수 제한
        places = places.stream().limit(limit).collect(Collectors.toList());

        // 거리 세팅
        places = places.stream().sorted((a,b) -> {
            a.distanceTo(b);
            return 0;
        }).collect(Collectors.toList());
        List<PlaceDto.PlaceRes> placeRes = MapperUtils.mapAll(places, PlaceDto.PlaceRes.class);

        final AtomicInteger counter = new AtomicInteger(1);
        List<PlaceDto.Schedule> schedules
                = Lists.partition(placeRes, PLACE_PAGE_SIZE).stream()
                .map(p -> new PlaceDto.Schedule(counter.getAndIncrement(), p))
                .collect(Collectors.toList());

        return schedules;
    }

    public Place save(PlaceDto.Save req) {
        return placeRepository.save(req.toEntity());
    }
}
