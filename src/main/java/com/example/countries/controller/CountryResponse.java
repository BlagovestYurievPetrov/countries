package com.example.countries.controller;

import java.util.List;

public class CountryResponse {
    private int numberOfTrips;
    private int numberOfNeighbours;
    private int leftover;
    private List<String> neighbours;

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public int getNumberOfNeighbours() {
        return numberOfNeighbours;
    }

    public int getLeftover() {
        return leftover;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }

    public CountryResponse(int numberOfTrips, int leftover, List<String> neighbours) {
        this.numberOfTrips = numberOfTrips;
        this.numberOfNeighbours = neighbours.size();
        this.leftover = leftover;
        this.neighbours = neighbours;
    }
}
