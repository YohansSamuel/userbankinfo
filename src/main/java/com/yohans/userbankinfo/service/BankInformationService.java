package com.yohans.userbankinfo.service;

import com.yohans.userbankinfo.model.BankInformation;
import com.yohans.userbankinfo.repository.BankInformationRepository;
import com.yohans.userbankinfo.repository.UserRepository;
import com.yohans.userbankinfo.config.EncryptionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BankInformationService {
    @Autowired
    private BankInformationRepository bankInformationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionConfig encryptionConfig;

    public BankInformation addBankInformation(UUID userId, BankInformation bankInformation) {
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        bankInformation.setUser(user);
        bankInformation.setBankAccountNumber(encryptionConfig.encrypt(bankInformation.getBankAccountNumber()));
        return bankInformationRepository.save(bankInformation);
    }

    public BankInformation getBankInformationByUserId(UUID userId) {
        BankInformation bankInfo = bankInformationRepository.findByUserId(userId);
        bankInfo.setBankAccountNumber(encryptionConfig.decrypt(bankInfo.getBankAccountNumber()));
        return bankInfo;
    }



    public BankInformation updateBankInformation(UUID userId, BankInformation bankInformationDetails) {
        BankInformation bankInfo = bankInformationRepository.findByUserId(userId);
        bankInfo.setBankName(bankInformationDetails.getBankName());
        bankInfo.setAccountType(bankInformationDetails.getAccountType());
        bankInfo.setBankAccountNumber(encryptionConfig.encrypt(bankInformationDetails.getBankAccountNumber()));
        return bankInformationRepository.save(bankInfo);
    }

    public void deleteBankInformation(UUID userId) {
        BankInformation bankInfo = bankInformationRepository.findByUserId(userId);
        bankInformationRepository.delete(bankInfo);
    }


    public Page<BankInformation> getPaginatedBankInformationByUserId(UUID userId, Pageable pageable) {
        return bankInformationRepository.getPaginatedBankInformationByUserId(userId, pageable);
    }
}
