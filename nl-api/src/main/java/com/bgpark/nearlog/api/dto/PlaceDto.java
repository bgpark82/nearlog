package com.bgpark.nearlog.api.dto;

import com.bgpark.nearlog.common.domain.place.Category;
import com.bgpark.nearlog.common.domain.place.LatLng;
import com.bgpark.nearlog.common.domain.place.Place;
import com.bgpark.nearlog.common.domain.place.PlaceType;
import com.bgpark.nearlog.common.domain.utils.GeoUtils;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlaceDto {

    @Data
    public static class PlaceRes {
        private Long id;
        private double lng;
        private double lat;
        private String title;
        private Double distance;
        private LatLng location;
        private PlaceType placeType;
        private Set<Category> categories;
    }

    @Getter
    @Setter
    public static class Schedule {
        private Integer day;
        public List<PlaceRes> waypoints;

        public Schedule(Integer day, List<PlaceRes> waypoints) {
            this.day = day;
            this.waypoints = waypoints;
        }
    }

    @Getter
    @Setter
    public static class Recommend {
        private Double lat;
        private Double lng;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer distance;
        private List<Long> exclusions = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class NearFindReq {
        private Double lat;
        private Double lng;
        private Integer distance;

        public Integer getDistance() {
            return distance == null ? 10 : distance;
        }
    }

    @Getter
    @Setter
    public static class Save {
        @NotNull
        private String title;
        @NotNull
        private double lat;
        @NotNull
        private double lng;
        private String description;
        private String thumbnail;
        private String phone;
        private String address;
        private String address2;
        private String zipCode;
        private PlaceType placeType;
        private Set<Category> categories;

        public Place toEntity() {

            Geometry geometry = GeoUtils.wktToGeometry(String.format("POINT (%s %s)", lng, lat));
            Point location = (Point) geometry;

            return Place.builder()
                    .title(title)
                    .lat(lat)
                    .lng(lng)
                    .description(description)
                    .thumbnail(thumbnail)
                    .address(address)
                    .address2(address2)
                    .zipCode(zipCode)
                    .placeType(placeType)
                    .categories(categories)
                    .location(location)
                    .build();
        }
    }
}
