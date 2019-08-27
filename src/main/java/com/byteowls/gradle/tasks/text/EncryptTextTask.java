package com.byteowls.gradle.tasks.text;

import com.byteowls.gradle.JasyptPluginConstants;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class EncryptTextTask extends TextAwareTask {

    public static final String TASK_NAME = "encryptText";
    private static final String TASK_DESCRIPTION = "Encrypts the given text";

    public EncryptTextTask() {
        this.setGroup(JasyptPluginConstants.TASK_GROUP_NAME);
        this.setDescription(TASK_DESCRIPTION);
    }

    @Override
    public void taskAction() {
        String encrypted = getEncryptor().encrypt(this.text);
        System.out.println("Encrypted text: " + encrypted);
    }


}
