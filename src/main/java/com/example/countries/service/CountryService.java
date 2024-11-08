package com.example.countries.service;

import org.springframework.stereotype.Service;

@Service
public class CountryService {
    public TripInformation getTripInformation(int totalBudget, int budgetPerCountry, int borders) {
        int budgetForOneTrip = (borders * budgetPerCountry);
        int numberOfTrips = totalBudget/(borders * budgetPerCountry);
        return new TripInformation(numberOfTrips, totalBudget-budgetForOneTrip*numberOfTrips);
    }
}
