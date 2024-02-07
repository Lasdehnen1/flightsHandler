package com.gridnine.testing.service.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.factory.FlightBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterImplTest {
    List<Flight> flights = FlightBuilder.createFlights();
    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    @Test
    void returnAllFlightsTest() {
        List<Flight> expected = List.of(

                //A normal flight with two hour duration
                new Flight( List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)))),
                //A normal multi segment flight
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)))),
                //A flight departing in the past
                new Flight(List.of(
                        new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow))),
                //A flight that departs before it arrives
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6)))),
                //A flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)))),
                //Another flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                        new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)))));

        List<Flight> actual = flights;

        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    void departureBeforeNowTest() {
        List<Flight> expected = List.of(

                //A normal flight with two hour duration
                new Flight( List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)))),
                //A normal multi segment flight
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)))),
                //A flight that departs before it arrives
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6)))),
                //A flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)))),
                //Another flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                        new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)))));

        List<Flight> actual = new FlightFilterImpl(flights).departureBeforeNow().toListReturn();

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void arriveBeforeDepartureTest() {
        List<Flight> expected = List.of(

                //A normal flight with two hour duration
                new Flight( List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)))),
                //A normal multi segment flight
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)))),
                //A flight departing in the past
                new Flight(List.of(
                        new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow))),
                //A flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)))),
                //Another flight with more than two hours ground time
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4)),
                        new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)))));

        List<Flight> actual = new FlightFilterImpl(flights).arriveBeforeDeparture().toListReturn();

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void awaitingTimeMoreThanTwoHoursTest() {
        List<Flight> expected = List.of(

                //A normal flight with two hour duration
                new Flight( List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)))),
                //A normal multi segment flight
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                        new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)))),
                //A flight departing in the past
                new Flight(List.of(
                        new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow))),
                //A flight that departs before it arrives
                new Flight(List.of(
                        new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6)))));

        List<Flight> actual = new FlightFilterImpl(flights).awaitingTimeMoreThanTwoHours().toListReturn();

        assertEquals(expected.toString(), actual.toString());
    }
}