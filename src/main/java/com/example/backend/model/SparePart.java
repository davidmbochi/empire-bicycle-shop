package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "spare_parts",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "spare_part_name")
    private String sparePartName;

    @Column(name = "spare_part_price")
    private BigDecimal sparePartPrice;

    @Column(name = "spare_part_description")
    private String sparePartDescription;

    @Column(name = "spare_part_image")
    private String sparePartImage;

    public SparePart(String sparePartName, BigDecimal sparePartPrice, String sparePartDescription) {
        this.sparePartName = sparePartName;
        this.sparePartPrice = sparePartPrice;
        this.sparePartDescription = sparePartDescription;
    }

}
