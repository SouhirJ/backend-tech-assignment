package com.sm360.OnlineAdvertisement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name= "dealerId")
    private Dealer dealer;
    @Column(nullable = false)
    private String vehicle;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state = State.DRAFT;


}
