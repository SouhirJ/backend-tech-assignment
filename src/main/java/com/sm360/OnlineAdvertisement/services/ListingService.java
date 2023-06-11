package com.sm360.OnlineAdvertisement.services;

import com.sm360.OnlineAdvertisement.dto.ListingDto;
import com.sm360.OnlineAdvertisement.models.State;

import java.util.List;
import java.util.UUID;

/**
 * Service for managing listings
 *
 * @author Souhir Jedidi
 */
public interface ListingService extends AbstractService<ListingDto> {

    ListingDto publishListing(UUID listingId, String handling);

    ListingDto unPublishListing(UUID listingId);

    /**
     * Get all listings of a dealer with a given state
     *
     * @param dealerId
     * @param state
     * @return
     */
    List<ListingDto> searchListing(UUID dealerId, State state);

}
