package com.sm360.OnlineAdvertisement.mapper;

import com.sm360.OnlineAdvertisement.dto.DealerDto;
import com.sm360.OnlineAdvertisement.dto.ListingDto;
import com.sm360.OnlineAdvertisement.models.Dealer;
import com.sm360.OnlineAdvertisement.models.Listing;
import org.springframework.stereotype.Component;

@Component
public class ObjectsMapper {

    public Listing toListing(ListingDto listingDto) {
        if (listingDto == null) {
            return null;
        }
        return Listing.builder()
                .id(listingDto.getId())
                .dealer(
                        Dealer.builder()
                                .id(listingDto.getDealerId())
                                .build())
                .vehicle(listingDto.getVehicle())
                .price(listingDto.getPrice())
                .state(listingDto.getState())
                .createdAt(listingDto.getCreatedAt())
                .build();
    }

    public ListingDto toListingDto(Listing listing) {
        if (listing == null) {
            return null;
        }
        return ListingDto.builder()
                .id(listing.getId())
                .dealerId(listing.getDealer().getId())
                .vehicle(listing.getVehicle())
                .price(listing.getPrice())
                .state(listing.getState())
                .createdAt(listing.getCreatedAt())
                .build();
    }

    public Dealer toDealer(DealerDto dealerDto) {
        if (dealerDto == null) {
            return null;
        }
        return Dealer.builder()
                .id(dealerDto.getId())
                .name(dealerDto.getName())
                .tierLimit(dealerDto.getTierLimit())
                .build();
    }

    public DealerDto toDealerDto(Dealer dealer) {
        if (dealer == null) {
            return null;
        }
        return DealerDto.builder()
                .id(dealer.getId())
                .name(dealer.getName())
                .tierLimit(dealer.getTierLimit())
                .build();
    }
}
