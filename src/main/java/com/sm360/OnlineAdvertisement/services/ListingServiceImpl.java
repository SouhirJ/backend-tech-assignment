package com.sm360.OnlineAdvertisement.services;

import com.sm360.OnlineAdvertisement.dto.ListingDto;
import com.sm360.OnlineAdvertisement.exceptions.TierLimitReachedException;
import com.sm360.OnlineAdvertisement.mapper.ObjectsMapper;
import com.sm360.OnlineAdvertisement.models.Dealer;
import com.sm360.OnlineAdvertisement.models.Listing;
import com.sm360.OnlineAdvertisement.models.State;
import com.sm360.OnlineAdvertisement.repositories.ListingRepository;
import com.sm360.OnlineAdvertisement.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;
    private final ObjectsMapper mapper;
    private final ObjectsValidator<ListingDto> validator;

    @Override
    public ListingDto create(ListingDto listingDto) {
        validator.validate(listingDto);
        Listing listingToSave = mapper.toListing(listingDto);
        if (listingToSave.getState() == null) {
            listingToSave.setState(State.DRAFT);
        }
        listingToSave.setCreatedAt(new Date());
        return mapper.toListingDto(listingRepository.save(listingToSave));
    }

    @Override
    public ListingDto update(UUID id, ListingDto listingDto) {
        validator.validate(listingDto);
        Listing existingListing = listingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Listing not found with id: " + id));

        existingListing.setVehicle(listingDto.getVehicle());
        existingListing.setPrice(listingDto.getPrice());
        existingListing.setState(listingDto.getState());
        return mapper.toListingDto(listingRepository.save(existingListing));
    }

    @Override
    public ListingDto findById(UUID id) {
        return listingRepository.findById(id)
                .map(mapper::toListingDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ListingDto> findAll() {
        return listingRepository.findAll()
                .stream()
                .map(mapper::toListingDto)
                .collect(Collectors.toList());
    }


    public ListingDto publishListing(UUID listingId, String handling) {
        Listing listingToPublish = listingRepository.findById(listingId)
                .orElseThrow(EntityNotFoundException::new);
        Dealer dealer = listingToPublish.getDealer();

        List<Listing> publishedListings = dealer.getListings().stream()
                .filter(l -> State.PUBLISHED.equals(l.getState()))
                .collect(Collectors.toList());

        if (publishedListings.size() >= dealer.getTierLimit()) {
            if ("unPublishOldest".equals(handling)) {
                // UnPublish the oldest listing
                Listing oldestListing = publishedListings.stream()
                        .min(Comparator.comparing(Listing::getCreatedAt))
                        .orElseThrow(EntityNotFoundException::new);
                oldestListing.setState(State.DRAFT);
                listingRepository.save(oldestListing);
                log.info("UnPublish the oldest listing");
            } else {
                log.error("Tier Limit reached");
                throw new TierLimitReachedException("Tier Limit reached");
            }
        }
        listingToPublish.setState(State.PUBLISHED);
        return mapper.toListingDto(listingRepository.save(listingToPublish));
    }

    public ListingDto unPublishListing(UUID listingId) {
        Listing listingToUnPublish = listingRepository.findById(listingId).orElseThrow(EntityNotFoundException::new);
        listingToUnPublish.setState(State.DRAFT);
        return mapper.toListingDto(listingRepository.save(listingToUnPublish));
    }

    @Override
    public List<ListingDto> searchListing(UUID dealerId, State state) {
        return listingRepository.findAllByDealerIdAndState(dealerId, state)
                .stream()
                .map(mapper::toListingDto)
                .collect(Collectors.toList());

    }
}
