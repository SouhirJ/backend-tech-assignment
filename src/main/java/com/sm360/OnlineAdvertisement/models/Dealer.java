package com.sm360.OnlineAdvertisement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dealer {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int tierLimit;
    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Listing> listings;

}
