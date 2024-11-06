package com.yohans.userbankinfo.controller;

import com.yohans.userbankinfo.model.BankInformation;
import com.yohans.userbankinfo.service.BankInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/bank-information")
public class BankInformationController {

    @Autowired
    private BankInformationService bankInformationService;

    @PostMapping
    public ResponseEntity<BankInformation> addBankInformation(@PathVariable UUID userId, @RequestBody BankInformation bankInformation) {
        BankInformation createdBankInformation = bankInformationService.addBankInformation(userId, bankInformation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBankInformation);
    }

    @GetMapping
    public ResponseEntity<?> getBankInformation(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BankInformation> bankInformationPage = bankInformationService.getPaginatedBankInformationByUserId(userId, pageable);

        return ResponseEntity.ok(bankInformationPage);
    }


//    @GetMapping
//    public ResponseEntity<BankInformation> getBankInformation(@PathVariable UUID userId) {
//        return ResponseEntity.ok(bankInformationService.getBankInformationByUserId(userId));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<BankInformation>> getPaginatedBankInformation(
//            @PathVariable UUID userId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<BankInformation> bankInformationPage = bankInformationService.getPaginatedBankInformationByUserId(userId, pageable);
//        return ResponseEntity.ok(bankInformationPage);
//    }

    @PutMapping
    public ResponseEntity<BankInformation> updateBankInformation(@PathVariable UUID userId, @RequestBody BankInformation bankInformationDetails) {
        return ResponseEntity.ok(bankInformationService.updateBankInformation(userId, bankInformationDetails));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBankInformation(@PathVariable UUID userId) {
        bankInformationService.deleteBankInformation(userId);
        return ResponseEntity.noContent().build();
    }


}