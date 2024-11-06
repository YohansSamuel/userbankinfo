package com.yohans.userbankinfo.repository;

import com.yohans.userbankinfo.model.BankInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankInformationRepository extends JpaRepository<BankInformation, UUID> {
    BankInformation findByUserId(UUID userId);

    //Page<BankInformation> findByUserId(UUID userId, Pageable pageable);

    Page<BankInformation> getPaginatedBankInformationByUserId(UUID userId, Pageable pageable);
}

