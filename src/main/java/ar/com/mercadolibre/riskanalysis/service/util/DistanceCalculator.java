package ar.com.mercadolibre.riskanalysis.service.util;

import org.apache.commons.math3.util.Precision;

import java.util.List;

public class DistanceCalculator {
    public static double calculate(List<Double> latlng1, List<Double> latlng2) {
        final double lat1 = latlng1.get(0);
        final double lon1 = latlng1.get(1);
        final double lat2 = latlng2.get(0);
        final double lon2 = latlng2.get(1);

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }

        double theta = lon1 - lon2;
        double distance = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));

        distance = Math.acos(distance);
        distance = Math.toDegrees(distance);
        distance = distance * 60 * 1.1515 * 1.609344;

        return Precision.round(distance, 2);
    }
}
