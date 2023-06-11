package com.sm360.OnlineAdvertisement.repositories;


import com.sm360.OnlineAdvertisement.models.Dealer;
import com.sm360.OnlineAdvertisement.models.Listing;
import com.sm360.OnlineAdvertisement.models.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
public class ListingRepositoryTest {
    @Autowired
    ListingRepository listingRepository;
    @Autowired
    DealerRepository dealerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllListingsByDealerIdAndStateShouldFindTwo() {
        // Arrange
        Dealer dealer = new Dealer();
        dealer.setName("Suzie");
        dealer.setTierLimit(2);
        dealerRepository.save(dealer);

        // Create and save listings with the specified dealerId and state
        Listing listing1 = new Listing();
        listing1.setDealer(dealer);
        listing1.setState(State.PUBLISHED);
        listing1.setPrice(new BigDecimal(1000));
        listing1.setCreatedAt(new Date());
        listing1.setVehicle("Lamborghini");
        listingRepository.save(listing1);

        Listing listing2 = new Listing();
        listing2.setDealer(dealer);
        listing2.setState(State.PUBLISHED);
        listing2.setPrice(new BigDecimal(2000));
        listing2.setCreatedAt(new Date());
        listing2.setVehicle("Ferrari");
        listingRepository.save(listing2);

        // Create and save a listing with a different dealerId
        Dealer dealer3 = new Dealer();
        dealer3.setName("Fabien");
        dealer3.setTierLimit(3);
        dealerRepository.save(dealer3);
        Listing listing3 = new Listing();
        listing3.setDealer(dealer3);
        listing3.setState(State.PUBLISHED);
        listing3.setPrice(new BigDecimal(3000));
        listing3.setCreatedAt(new Date());
        listing3.setVehicle("Ferrari");
        listingRepository.save(listing3);

        // Act
        List<Listing> foundListings = listingRepository.findAllByDealerIdAndState(dealer.getId(), State.PUBLISHED);

        // Assert
        Assertions.assertEquals(2, foundListings.size());
        Assertions.assertTrue(foundListings.stream().anyMatch(listing -> listing.getId().equals(listing1.getId())));
        Assertions.assertTrue(foundListings.stream().anyMatch(listing -> listing.getId().equals(listing2.getId())));
    }

}
