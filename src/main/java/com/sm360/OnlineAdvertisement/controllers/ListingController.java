package com.sm360.OnlineAdvertisement.controllers;

import com.sm360.OnlineAdvertisement.dto.ListingDto;
import com.sm360.OnlineAdvertisement.models.State;
import com.sm360.OnlineAdvertisement.services.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/listings")
@RequiredArgsConstructor
@Tag(name = "Listings")
public class ListingController {
    private final ListingService listingService;

    @PostMapping("create")
    public ResponseEntity<ListingDto> createListing(@RequestBody ListingDto listingDto) {
        return ResponseEntity.ok(listingService.create(listingDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ListingDto> updateListing(@PathVariable UUID id, @RequestBody ListingDto listingDto) {
        return ResponseEntity.ok(listingService.update(id, listingDto));
    }

    @Operation(
            description = "Get all listings of a dealer with a given state",
            summary = "Get all listings of a dealer with a given state",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("search")
    public ResponseEntity<List<ListingDto>> searchListing(@RequestParam UUID dealerId, @RequestParam State state) {
        return ResponseEntity.ok(listingService.searchListing(dealerId, state));
    }
    @GetMapping("")
    public ResponseEntity<List<ListingDto>> getAllListings() {
        return ResponseEntity.ok(listingService.findAll());
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<ListingDto> publishListing(@PathVariable UUID id, @RequestParam(required = false) String handling) {
        return ResponseEntity.ok(listingService.publishListing(id, handling));
    }

    @PutMapping("/{id}/unPublish")
    public ResponseEntity<ListingDto> unPublishListing(@PathVariable UUID id) {
        return ResponseEntity.ok(listingService.unPublishListing(id));
    }
}
