package com.sm360.OnlineAdvertisement.services;

import com.sm360.OnlineAdvertisement.exceptions.TierLimitReachedException;
import com.sm360.OnlineAdvertisement.models.Dealer;
import com.sm360.OnlineAdvertisement.models.Listing;
import com.sm360.OnlineAdvertisement.models.State;
import com.sm360.OnlineAdvertisement.repositories.DealerRepository;
import com.sm360.OnlineAdvertisement.repositories.ListingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.*;


@SpringBootTest
@ActiveProfiles("test")
class ListingServiceTest {
    @Autowired
    ListingService listingService;
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    DealerRepository dealerRepository;


    @Test
    void publishListingWithValidListingIdAndHandlingTierLimitReached() {
        // Arrange
        String handling = "someOtherHandling";

        // Create a dealer with published listings at tier limit
        Dealer dealer = new Dealer();
        List<Listing> publishedListings = createPublishedListingsAtTierLimit();
        dealer.setListings(publishedListings);
        dealer.setTierLimit(2);
        dealer.setName("Diane");
        dealerRepository.save(dealer);
        publishedListings.forEach(listing -> listing.setDealer(dealer));
        listingRepository.saveAllAndFlush(publishedListings);

        // Create the listing to publish
        Listing listingToPublish = new Listing();
        listingToPublish.setState(State.DRAFT);
        listingToPublish.setDealer(dealer);
        listingToPublish.setCreatedAt(new Date());
        listingToPublish.setPrice(new BigDecimal(7000));
        listingToPublish.setVehicle("BMW");

        // Save the listing to publish in the repository
        listingRepository.save(listingToPublish);

        // Act & Assert
        Assertions.assertThrows(TierLimitReachedException.class,
                () -> listingService.publishListing(listingToPublish.getId(), handling));
    }

    private List<Listing> createPublishedListingsAtTierLimit() {
        List<Listing> publishedListings = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Listing listing = new Listing();
            listing.setState(State.PUBLISHED);
            listing.setCreatedAt(new Date());
            listing.setPrice(new BigDecimal(Math.random()));
            listing.setVehicle("");
            publishedListings.add(listing);
        }
        return publishedListings;
    }

}