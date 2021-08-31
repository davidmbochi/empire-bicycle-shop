package com.example.backend.service;

import com.example.backend.model.SparePart;
import com.example.backend.repository.SparePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SparePartServiceImpl implements SparePartService{
    private final SparePartRepository sparePartRepository;

    @Override
    public List<SparePart> findAllSpareParts() {
        return sparePartRepository.findAll();
    }

    @Override
    public SparePart addSparePart(SparePart sparePart) {
        return sparePartRepository.save(sparePart);
    }

    @Override
    public SparePart findSparePartById(Long id) {
        return sparePartRepository.findSparePartById(id);
    }

    @Override
    public void deleteSparePart(Long id) {
        SparePart sparePart = sparePartRepository.findSparePartById(id);
        sparePartRepository.delete(sparePart);
    }
}
