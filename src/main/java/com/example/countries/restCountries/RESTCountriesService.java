package com.example.countries.restCountries;

import com.example.countries.controller.CountryNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class RESTCountriesService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<String> getNeighbouringCountries(String countryName) {
        Country country = getCountry(countryName);
        List<String> borders = country.getBorders();
        return (borders == null || borders.isEmpty()) ? Collections.emptyList() : borders;
    }

    private Country getCountry(String countryName) {
        String countriesJson = fetchCountryData(countryName);
        List<Country> countries = parseCountries(countriesJson);
        return countries.getFirst();
    }

    public String fetchCountryData(String countryName) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://restcountries.com/v3.1/name/" + countryName + "?fullText=true"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new CountryNotFoundException();
            }
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new CountryNotFoundException();
        }
    }

    private List<Country> parseCountries(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<List<Country>>() {
            });
        } catch (JsonProcessingException e) {
            throw new CountryNotFoundException();
        }
    }
}
