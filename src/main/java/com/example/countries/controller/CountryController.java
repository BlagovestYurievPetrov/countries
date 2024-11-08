package com.example.countries.controller;

import com.example.countries.restCountries.RESTCountriesService;
import com.example.countries.service.CountryService;
import com.example.countries.service.TripInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final RESTCountriesService restCountriesService;
    private final CountryService countryService;

    @Autowired
    public CountryController(RESTCountriesService restCountriesService, CountryService countryService) {
        this.restCountriesService = restCountriesService;
        this.countryService = countryService;
    }

    @PostMapping(value = "/trips", produces = "application/json")
    public ResponseEntity<CountryResponse> getTrips(@RequestBody CountryRequestBody body) {
        List<String> borderingCountries = restCountriesService.getNeighbouringCountries(body.startingCountry());
        if (borderingCountries.isEmpty()) {
            throw new NoBordersException();
        }

        TripInformation tripInformation = countryService.getTripInformation(body.totalBudget(), body.budgetPerCountry(), borderingCountries.size());
        CountryResponse countryResponse = new CountryResponse(tripInformation.numberOfTrips(), tripInformation.leftoverBudget(), borderingCountries);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }
}
