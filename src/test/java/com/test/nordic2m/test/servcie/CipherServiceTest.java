package com.test.nordic2m.test.servcie;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.test.nordic2m.service.CipherService;

@ExtendWith(MockitoExtension.class)
public class CipherServiceTest {

    private CipherService cipherService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cipherService = new CipherService();
    }

    @Test
    @DisplayName("Test Encrypt For Valid String")
    void testEncryptForValidString() throws Exception {
        StringBuffer stringBuffer = cipherService.encrypt("abcdefghijklmnopqrstuvwxyz", 3);
        Assertions.assertNotNull(stringBuffer.toString());
        Assertions.assertEquals("defghijklmnopqrstuvwxyzabc", stringBuffer.toString());
    }

    @Test
    @DisplayName("Test Encrypt For Valid With Capitals And Numbers")
    void testEncryptForValidStringWithCapitalAndNumbers() throws Exception {
        StringBuffer stringBuffer = cipherService.encrypt("2M-Helsingborg", 2);
        Assertions.assertNotNull(stringBuffer.toString());
        Assertions.assertEquals("2O-Jgnukpidqti", stringBuffer.toString());
    }

    @Test
    @DisplayName("Test Decrypt For Valid String With Rotations EqualsTo 5")
    void testDecryptForValidStringWithRotationsEqualsTo5() throws Exception {
        // calculate No of Rotations for Decrypt by (26 - noRotations)
        StringBuffer stringBuffer = cipherService.encrypt("Xtrj-2R-\"xtkybfwj jslnsjjwx\"-bnqq gj otnsnsl ymj rnxxnts yt--Rfwx--!!!", (26 - 5));
        Assertions.assertNotNull(stringBuffer.toString());
        Assertions.assertEquals("Some-2M-\"software engineers\"-will be joining the mission to--Mars--!!!", stringBuffer.toString());
    }

    @Test
    @DisplayName("Test Decrypt For Null String")
    void testEncryptForNullString() throws Exception {
        Exception thrown = assertThrows(Exception.class, () -> cipherService.encrypt(null, 3), "Expected to throw Exception, but it didn't");
        Assertions.assertTrue(thrown.getMessage().contains("Encrypt request data is null"));
    }

    @Test
    @DisplayName("Test Decrypt For Empty String")
    void testEncryptForEmptyString() throws Exception {
        Exception thrown = assertThrows(Exception.class, () -> cipherService.encrypt("", 3), "Expected to throw Exception, but it didn't");
        Assertions.assertTrue(thrown.getMessage().contains("Encrypt request data is null"));
    }

    @Test
    @DisplayName("Test Decrypt For Zero Rotations ")
    void testEncryptForZeroRotations() throws Exception {
        Exception thrown = assertThrows(Exception.class, () -> cipherService.encrypt("AAADsddfsfs", 0), "Expected to throw Exception, but it didn't");
        Assertions.assertTrue(thrown.getMessage().contains("No Of Rotations should be > 0"));
    }
}
