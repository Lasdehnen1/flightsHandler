package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.factory.FlightBuilder;
import com.gridnine.testing.service.impl.FlightFilterImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("All created flights");
        System.out.println(flights);
        System.out.println("*******************************************************************");

        System.out.println("Arrive before departure filtered");
        System.out.println(new FlightFilterImpl(flights).arriveBeforeDeparture().toListReturn());
        System.out.println("********************************************************************");

        System.out.println("Departure before now filtered");
        System.out.println(new FlightFilterImpl(flights).departureBeforeNow().toListReturn());
        System.out.println("********************************************************************");

        System.out.println("Awaiting on land more than two hours between flights filtered");
        System.out.println(new FlightFilterImpl(flights).awaitingTimeMoreThanTwoHours().toListReturn());
        System.out.println("********************************************************************");
    }
}