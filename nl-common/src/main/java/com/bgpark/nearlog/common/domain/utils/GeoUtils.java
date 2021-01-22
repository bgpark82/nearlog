package com.bgpark.nearlog.common.domain.utils;

import com.bgpark.nearlog.common.domain.place.LatLng;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.util.GeometricShapeFactory;

public final class GeoUtils {

    public static Geometry createCircle(double lng, double lat, double radius) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(lng, lat));
        shapeFactory.setSize((radius * 0.009) * 2); // 2* (10*0.009) expect 10km radius
        return shapeFactory.createCircle();
    }

    public static double distance(LatLng p1, LatLng p2) {
        return distance(p1, p2, "K");
    }

    public static double distance(LatLng p1, LatLng p2, String unit) {
        if((p1.getLat() == p2.getLat()) && (p2.getLng() == p2.getLng())) {
            return 0;
        }else {
            double theta = p1.getLng() - p2.getLng();
            double dist = Math.sin(Math.toRadians(p1.getLat())) * Math.sin(Math.toRadians(p2.getLat()))
                    + Math.cos(Math.toRadians(p1.getLat())) * Math.cos(Math.toRadians(p2.getLat())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515; //Miles
            if (unit == "K") {   //kioometer
                dist = dist * 1.609344;
            } else if (unit == "N") {   //Nautical Miles
                dist = dist * 0.8684;
            } else if (unit == "M") {   //meter
                dist = dist * 1609.344;
            }
            return (dist);
        }
    }

    public static Geometry wktToGeometry(String wellKnownText) {
        try {
            return new WKTReader().read(wellKnownText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
