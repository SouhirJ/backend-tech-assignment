package com.sm360.OnlineAdvertisement.services;

import com.sm360.OnlineAdvertisement.dto.DealerDto;
import com.sm360.OnlineAdvertisement.mapper.ObjectsMapper;
import com.sm360.OnlineAdvertisement.models.Dealer;
import com.sm360.OnlineAdvertisement.repositories.DealerRepository;
import com.sm360.OnlineAdvertisement.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class DealerServiceImpl implements DealerService{
    private final DealerRepository dealerRepository;
    private final ObjectsMapper mapper;
    private final ObjectsValidator<DealerDto> validator;
    @Override
    public DealerDto create(DealerDto dealerDto) {
        validator.validate(dealerDto);
        Dealer dealerToSave = mapper.toDealer(dealerDto);

        return mapper.toDealerDto(dealerRepository.save(dealerToSave));

    }

    @Override
    public DealerDto update(UUID id, DealerDto dealerDto) {
        validator.validate(dealerDto);
        Dealer existingDealer = dealerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dealer not found with id: " + id));

        existingDealer.setName(dealerDto.getName());
        existingDealer.setTierLimit(dealerDto.getTierLimit());

        return mapper.toDealerDto(dealerRepository.save(existingDealer));
    }

    @Override
    public DealerDto findById(UUID id) {
        return dealerRepository.findById(id)
                .map(mapper::toDealerDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<DealerDto> findAll() {
        return dealerRepository.findAll()
                .stream()
                .map(mapper::toDealerDto)
                .collect(Collectors.toList());
    }
}
