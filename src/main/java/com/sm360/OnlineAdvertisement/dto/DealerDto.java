package com.sm360.OnlineAdvertisement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealerDto {
    private UUID id;
    @NotEmpty(message = "Please type a name")
    @NotNull(message = "Please type a name")
    private String name;

    @NotNull(message = "Please type a tier Limit")
    private int tierLimit;

    //private List<ListingDto> listings;
}

