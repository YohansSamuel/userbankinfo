//package com.yohans.userbankinfo.controller;
//
//import com.yohans.userbankinfo.model.BankInformation;
//import com.yohans.userbankinfo.service.BankInformationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class BankInformationControllerTest {
//
//    @InjectMocks
//    private BankInformationController bankInformationController;
//
//    @Mock
//    private BankInformationService bankInformationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddBankInformation() {
//        BankInformation bankInformation = new BankInformation(UUID.randomUUID(), null, "12345678", "Ahaz Bank", "Checking");
//        when(bankInformationService.addBankInformation(any(UUID.class), any(BankInformation.class)))
//                .thenReturn(bankInformation);
//
//        ResponseEntity<BankInformation> response = bankInformationController.addBankInformation(UUID.randomUUID(), bankInformation);
//
//        // Updated assertion to check for the CREATED status code
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(bankInformation, response.getBody());
//        verify(bankInformationService, times(1)).addBankInformation(any(UUID.class), any(BankInformation.class));
//    }
//
//    @Test
//    void testGetBankInformation() {
//        UUID userId = UUID.randomUUID();
//        BankInformation bankInformation = new BankInformation(UUID.randomUUID(), null, "12345678", "Ahaz Bank", "Checking");
//        when(bankInformationService.getBankInformationByUserId(userId)).thenReturn(bankInformation);
//
//        ResponseEntity<BankInformation> response = bankInformationController.getBankInformation(userId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(bankInformation, response.getBody());
//        verify(bankInformationService, times(1)).getBankInformationByUserId(userId);
//    }
//
//    @Test
//    void testUpdateBankInformation() {
//        UUID userId = UUID.randomUUID();
//        BankInformation bankInformationDetails = new BankInformation(UUID.randomUUID(), null, "12345678", "Ahaz Bank", "Checking");
//        when(bankInformationService.updateBankInformation(eq(userId), any(BankInformation.class)))
//                .thenReturn(bankInformationDetails);
//
//        ResponseEntity<BankInformation> response = bankInformationController.updateBankInformation(userId, bankInformationDetails);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(bankInformationDetails, response.getBody());
//        verify(bankInformationService, times(1)).updateBankInformation(eq(userId), any(BankInformation.class));
//    }
//
//    @Test
//    void testDeleteBankInformation() {
//        UUID userId = UUID.randomUUID();
//        doNothing().when(bankInformationService).deleteBankInformation(userId);
//
//        ResponseEntity<Void> response = bankInformationController.deleteBankInformation(userId);
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        verify(bankInformationService, times(1)).deleteBankInformation(userId);
//    }
//}