package com.example.backend.service;

import com.example.backend.model.Bike;
import com.example.backend.repository.BikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BikeServiceImpl implements BikeService {
    private final BikeRepository bikeRepository;

    @Override
    public List<Bike> findAllBikes() {
        log.info(String.valueOf(bikeRepository.findAll()));
        return bikeRepository.findAll();
    }

    @Override
    public Bike findBikeById(Long id) {
        return bikeRepository.findBikeById(id);
    }

    @Override
    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public void deleteBike(Bike bike) {
        bikeRepository.delete(bike);
    }


}
