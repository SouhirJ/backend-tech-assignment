package com.sm360.OnlineAdvertisement.repositories;

import com.sm360.OnlineAdvertisement.models.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DealerRepository  extends JpaRepository<Dealer, UUID> {
}
