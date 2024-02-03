package com.gridnine.testing.service.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightFilter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Service for filtering flights
 */
public class FlightFilterImpl implements FlightFilter {

    private final List<Flight> flightList;

    public FlightFilterImpl(List<Flight> flights) {
        this.flightList = new ArrayList<>(flights);
    }
    /**
     * Method for getting filtered list
     */
    @Override
    public List<Flight> toListReturn() {
        return flightList;
    }
    /**
     * Method for filtering flights departing in the past
     */
    @Override
    public FlightFilter departureBeforeNow() {
        LocalDateTime now = LocalDateTime.now();
        flightList.removeIf(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate()
                        .isBefore(now))
        );
        return this;
    }
    /**
     * Method for filtering flights that departs before it arrives
     */
    @Override
    public FlightFilter arriveBeforeDeparture() {
        flightList.removeIf(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
                );
        return this;
    }
    /**
     * Method for filtering flights with more than two hours ground time
     */
    @Override
    public FlightFilter awaitingTimeMoreThanTwoHours() {
        flightList.removeIf(flight -> moreThanFilter(flight.getSegments(), 2L));
        return this;
    }
    /**
     * Method for getting flights with more than some hours on land
     * @param hoursToWait hours between arriving and departing
     */
    private Boolean moreThanFilter(List<Segment> segments, long hoursToWait) {
        long overallTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            overallTime = overallTime + Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate()).toHours();
        }
        return overallTime > hoursToWait;
    }
}
