package com.sm360.OnlineAdvertisement.controllers;

import com.sm360.OnlineAdvertisement.dto.DealerDto;
import com.sm360.OnlineAdvertisement.services.DealerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/dealers")
@RequiredArgsConstructor
public class DealerController {
    private final DealerService dealerService;

    @PostMapping("create")
    public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto dealerDto) {
        return ResponseEntity.ok(dealerService.create(dealerDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<DealerDto> updateDealer(@PathVariable UUID id, @RequestBody DealerDto dealerDto) {
        return ResponseEntity.ok(dealerService.update(id, dealerDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DealerDto> getDealer(@PathVariable UUID id) {
        return ResponseEntity.ok(dealerService.findById(id));
    }
    @GetMapping("")
    public ResponseEntity<List<DealerDto>> getAllDealers() {
        return ResponseEntity.ok(dealerService.findAll());
    }

}
