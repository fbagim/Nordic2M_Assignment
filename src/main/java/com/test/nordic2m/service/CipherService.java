package com.test.nordic2m.service;

import java.util.ArrayList;
import java.util.List;

import com.test.nordic2m.dto.CipherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CipherService used to support to encryption and decryption
 */
@Service
public class CipherService {

    private final Logger logger = LoggerFactory.getLogger(CipherService.class);
    public static List<CipherData> historyList = new ArrayList();

    /**
     * Use to get all History Data encrypted
     *
     * @return Map<String, String>
     * @throws Exception
     */
    public List<CipherData> getHistory() throws Exception {
        return historyList;
    }

    /**
     * Use to encryption and decryption to given string based on no of Rotations
     *
     * @param text
     * @param noRotations
     * @return
     * @throws Exception
     */
    public StringBuffer encrypt(String text, int noRotations) throws Exception {
        // input Validations
        if (text == null || text.isEmpty()) {
            logger.info(String.format("CipherService -> encrypt request data is null"));
            throw new Exception("Encrypt request data is null");
        }
        // input Validations
        if (noRotations <= 0) {
            logger.info(String.format("CipherService -> No Of Rotations should be > 0 "));
            throw new Exception("No Of Rotations should be > 0");
        }

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +   noRotations - 65) % 26 + 65);
                result.append(ch);
            } else {
                // check only Alphabetical characters to find  chars by checking ASCII codes
                if (!((int) text.charAt(i) > 31 && (int) text.charAt(i) < 58)) {
                    char ch = (char) (((int) text.charAt(i) + noRotations - 97) % 26 + 97);
                    result.append(ch);
                } else {
                    result.append(text.charAt(i));
                }
            }
        }

        CipherData cipherData = new CipherData();
        cipherData.setResponseData(result.toString());
        cipherData.setRequestData(text);
        cipherData.setNoOfRotations(noRotations);
        historyList.add(cipherData);

        return result;
    }
}
