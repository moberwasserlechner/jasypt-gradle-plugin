package com.byteowls.gradle.tasks.file;

import com.byteowls.gradle.JasyptPluginConstants;
import org.jasypt.util.text.TextEncryptor;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class EncryptPropertiesFileTask extends PropertiesFileAwareTask {

    public static final String TASK_NAME = "encryptProperties";
    private static final String TASK_DESCRIPTION = "Encrypts the property values wrapped with 'ENCRYPT(plain_text)'";
    private static final String ENCRYPT_EXTRACTION_REGEX = "ENCRYPT\\((.*)\\)";

    public EncryptPropertiesFileTask() {
        this.setGroup(JasyptPluginConstants.TASK_GROUP_NAME);
        this.setDescription(TASK_DESCRIPTION);
    }

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
