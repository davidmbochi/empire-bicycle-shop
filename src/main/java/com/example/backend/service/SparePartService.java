package com.example.backend.service;

import com.example.backend.model.SparePart;

import java.util.List;

public interface SparePartService {
    List<SparePart> findAllSpareParts();

    SparePart addSparePart(SparePart sparePart);

    SparePart findSparePartById(Long id);

    void deleteSparePart(Long id);
}
