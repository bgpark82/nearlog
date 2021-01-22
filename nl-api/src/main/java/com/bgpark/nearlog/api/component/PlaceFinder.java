package com.bgpark.nearlog.api.component;

import com.bgpark.nearlog.api.dto.PlaceDto;
import com.bgpark.nearlog.common.domain.place.Place;
import com.bgpark.nearlog.common.domain.place.PlaceRepository;
import com.bgpark.nearlog.common.domain.utils.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceFinder {

    private final PlaceRepository placeRepository;

    public List<Place> getPlaceByLocation(PlaceDto.Recommend req) {
        Geometry circle = GeoUtils.createCircle(req.getLng(), req.getLat(), req.getDistance());
        return placeRepository.getLocationWithin(circle);

    }
}
