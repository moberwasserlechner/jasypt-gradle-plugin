package com.byteowls.gradle.tasks.file;

import org.jasypt.util.text.TextEncryptor;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class DecryptPropertiesFileTask extends PropertiesFileAwareTask {

    private static final String DECRYPT_EXTRACTION_REGEX = "ENC\\((.*)\\)";

    public static final String TASK_NAME = "decryptProperties";
//    public static final String TASK_DESCRIPTION = "Decrypt the property values wrapped with 'ENC(encrypted_text)'";

    @Override
    public void validateOptions() {

    }

    @Override
    public String process(TextEncryptor encryptor, String extractedValue) {
        return encryptor.decrypt(extractedValue);
    }

    @Override
    public String getPropertyPrefix() {
        return "ENCRYPT(";
    }

    @Override
    public String getPropertySuffix() {
        return ")";
    }

    @Override
    public String getDefaultExtractRegex() {
        return DECRYPT_EXTRACTION_REGEX;
    }

}
