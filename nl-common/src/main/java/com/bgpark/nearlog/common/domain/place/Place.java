package com.bgpark.nearlog.common.domain.place;

import com.bgpark.nearlog.common.domain.utils.GeoUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.locationtech.jts.geom.Point;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@EntityListeners(value = AuditingEntityListener.class)
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double lat;
    private double lng;
    private String description;
    private String thumbnail;
    private String phone;
    private String address;
    private String address2;
    private String zipCode;
    private Integer country;
    private Integer city;
    private Double rating;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Category> categories;

    @JsonIgnore
    @Column(name = "location", columnDefinition = "POINT")
    private Point location;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Transient
    @Builder.Default
    private double distance = 0.0;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    public double distanceTo(Place dest) {
        double distance = GeoUtils.distance(new LatLng(lat, lng), new LatLng(dest.getLat(), dest.getLng()));
        this.distance = distance;
        return distance;
    }

    public LatLng getLocation(){
        return new LatLng(location.getY(),location.getX());
    }
}
