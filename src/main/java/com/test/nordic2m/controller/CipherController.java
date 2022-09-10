package com.test.nordic2m.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.nordic2m.dto.CipherData;
import com.test.nordic2m.service.CipherService;


/**
 * CipherController to provide encrypt /decrypt API Data service to API consumers
 */
@RestController
@RequestMapping(value = "/")
public class CipherController {
    private final Logger logger = LoggerFactory.getLogger(CipherController.class);
    @Autowired
    private CipherService cipherService;

    private static final int MAX_ROTATIONS = 26;

    /**
     * POST Method For The Encryption
     * @param cipherData
     * @return
     */
    @PostMapping(value = "/encrypt")
    public ResponseEntity<CipherData> encryptData(@RequestBody CipherData cipherData) {
        // Validate input RequestData - cannot be null
        if (cipherData.getRequestData() == null || cipherData.getRequestData().isEmpty()) {

            logger.info(String.format("BAD_REQUEST -RequestData Cannot be null"));
            return new ResponseEntity("RequestData Cannot be null", HttpStatus.BAD_REQUEST);
        }
        // Validate input No Of Rotations
        if (cipherData.getNoOfRotations() <= 0 || cipherData.getNoOfRotations() > MAX_ROTATIONS) {
            logger.info(String.format("BAD_REQUEST -No Of Rotations Should be >0 && <" + (MAX_ROTATIONS + 1)));
            return new ResponseEntity("No Of Rotations Should be >0", HttpStatus.BAD_REQUEST);
        }
        try {
            StringBuffer stringBuffer = cipherService.encrypt(cipherData.getRequestData(), cipherData.getNoOfRotations());
            cipherData.setResponseData(stringBuffer.toString());
            return new ResponseEntity(cipherData, HttpStatus.OK);
        } catch (Exception e) {
            logger.info(String.format("INTERNAL_SERVER_ERROR -" + e.getMessage()));
            return new ResponseEntity("INTERNAL_SERVER_ERROR" + cipherData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST Method For The Decryption
     * @param cipherData
     * @return
     */
    @PostMapping(value = "/decrypt")
    public ResponseEntity<CipherData> decryptData(@RequestBody CipherData cipherData) {
        // Validate input RequestData - cannot be null
        if (cipherData.getRequestData() == null || cipherData.getRequestData().isEmpty()) {

            logger.info(String.format("BAD_REQUEST -RequestData Cannot be null"));
            return new ResponseEntity("RequestData Cannot be null", HttpStatus.BAD_REQUEST);
        }
        // Validate input No Of Rotations
        if (cipherData.getNoOfRotations() <= 0 || cipherData.getNoOfRotations() > MAX_ROTATIONS) {
            logger.info(String.format("BAD_REQUEST -No Of Rotations Should be >0 && <" + (MAX_ROTATIONS + 1)));
            return new ResponseEntity("No Of Rotations Should be >0", HttpStatus.BAD_REQUEST);
        }
        try {
            // Calculate Rotations  For the Decryption by (MAX_ROTATIONS - No_of_Encrypted_Rotations)
            StringBuffer stringBuffer = cipherService.encrypt(cipherData.getRequestData(), MAX_ROTATIONS - cipherData.getNoOfRotations());
            cipherData.setResponseData(stringBuffer.toString());
            return new ResponseEntity(cipherData, HttpStatus.OK);
        } catch (Exception e) {
            logger.info(String.format("INTERNAL_SERVER_ERROR -" + e.getMessage()));
            return new ResponseEntity("INTERNAL_SERVER_ERROR" + cipherData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get Method For The Get All History Data
     * @return
     */
    @GetMapping(value = "/historyData")
    public ResponseEntity<List<CipherData>> decryptData() {
        try {
            return new ResponseEntity(cipherService.getHistory(), HttpStatus.OK);
        } catch (Exception e) {
            logger.info(String.format("INTERNAL_SERVER_ERROR -" + e.getMessage()));
            return new ResponseEntity("INTERNAL_SERVER_ERROR" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}