package com.sm360.OnlineAdvertisement.dto;

import com.sm360.OnlineAdvertisement.models.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingDto {
    private UUID id;
    @NotNull(message = "Please type a dealerId")
    private UUID dealerId;
    @NotEmpty(message = "Please type a vehicle")
    @NotNull(message = "Please type a vehicle")
    private String vehicle;

    @NotNull(message = "Please type a price")
    private BigDecimal price;

    private Date createdAt;
    private State state;

    public ListingDto(UUID dealerId, String vehicle, BigDecimal price, Date createdAt, State state) {
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.price = price;
        this.createdAt = createdAt;
        this.state = state;
    }
}
