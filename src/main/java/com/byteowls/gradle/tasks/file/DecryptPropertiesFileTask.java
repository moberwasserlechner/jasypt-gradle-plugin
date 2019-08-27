package com.byteowls.gradle.tasks.file;

import com.byteowls.gradle.JasyptPluginConstants;
import org.jasypt.util.text.TextEncryptor;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class DecryptPropertiesFileTask extends PropertiesFileAwareTask {

    public static final String TASK_NAME = "decryptProperties";
    private static final String TASK_DESCRIPTION = "Decrypts the property values wrapped with 'ENC(encrypted_text)'";
    private static final String DECRYPT_EXTRACTION_REGEX = "ENC\\((.*)\\)";

    public DecryptPropertiesFileTask() {
        this.setGroup(JasyptPluginConstants.TASK_GROUP_NAME);
        this.setDescription(TASK_DESCRIPTION);
    }

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
