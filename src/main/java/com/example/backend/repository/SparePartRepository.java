package com.example.backend.repository;

import com.example.backend.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {
    SparePart findSparePartById(long id);
}
