package com.byteowls.gradle.tasks.file;

import org.jasypt.util.text.TextEncryptor;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class EncryptPropertiesFileTask extends PropertiesFileAwareTask {

    private static final String ENCRYPT_EXTRACTION_REGEX = "ENCRYPT\\((.*)\\)";

    public static final String TASK_NAME = "encryptProperties";
//    public static final String TASK_DESCRIPTION = "Encrypts the plain_text wrapped with 'ENCRYPT(plain_text)'";

    @Override
    public void validateOptions() {

    }

    @Override
    public String process(TextEncryptor encryptor, String extractedValue) {
        return encryptor.encrypt(extractedValue);
    }

    @Override
    public String getPropertyPrefix() {
        return "ENC(";
    }

    @Override
    public String getPropertySuffix() {
        return ")";
    }

    @Override
    public String getDefaultExtractRegex() {
        return ENCRYPT_EXTRACTION_REGEX;
    }

}
