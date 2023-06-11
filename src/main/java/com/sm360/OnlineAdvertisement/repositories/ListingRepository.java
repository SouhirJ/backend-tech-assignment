package com.sm360.OnlineAdvertisement.repositories;

import com.sm360.OnlineAdvertisement.models.Listing;
import com.sm360.OnlineAdvertisement.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {
    List<Listing> findAllByDealerIdAndState(UUID dealerId, State state);

}
