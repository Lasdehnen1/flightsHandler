package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> toListReturn();
    FlightFilter departureBeforeNow();
    FlightFilter arriveBeforeDeparture();
    FlightFilter awaitingTimeMoreThanTwoHours();
}
