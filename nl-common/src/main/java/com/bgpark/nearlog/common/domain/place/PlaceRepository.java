package com.bgpark.nearlog.common.domain.place;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByTitleStartsWith(String title);

    @Query(value="SELECT p from Place p WHERE within(p.location, ?1) = true")
    List<Place> getLocationWithin(@Param("circle") Geometry circle);

    List<Place> findByIdIn(List<Long> placeIds);

}
