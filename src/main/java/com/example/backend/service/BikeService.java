package com.example.backend.service;

import com.example.backend.model.Bike;

import java.util.List;

public interface BikeService {
    List<Bike> findAllBikes();
    Bike findBikeById(Long id);
    Bike addBike(Bike bike);
    void deleteBike(Bike bike);
}
