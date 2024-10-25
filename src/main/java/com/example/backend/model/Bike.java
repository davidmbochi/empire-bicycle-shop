package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bike",schema = "public")
public class Bike{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bike_name")
    private String bikeName;

    @Column(name = "bike_price")
    private BigDecimal bikePrice;

    @Column(name = "bike_description")
    private String bikeDescription;

    @Column(name = "bike_image")
    private String bikeImage;

    public Bike(String bikeName,
                BigDecimal bikePrice,
                String bikeDescription) {
        this.bikeName = bikeName;
        this.bikePrice = bikePrice;
        this.bikeDescription = bikeDescription;
    }

}
