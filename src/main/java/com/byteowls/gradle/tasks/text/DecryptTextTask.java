package com.byteowls.gradle.tasks.text;

import com.byteowls.gradle.JasyptPluginConstants;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class DecryptTextTask extends TextAwareTask {

    public static final String TASK_NAME = "decryptText";
    private static final String TASK_DESCRIPTION = "Decrypts the given text";

    public DecryptTextTask() {
        this.setGroup(JasyptPluginConstants.TASK_GROUP_NAME);
        this.setDescription(TASK_DESCRIPTION);
    }

    @Override
    public void taskAction() {
        String decrypted = getEncryptor().decrypt(this.text);
        System.out.println("Decrypted text: " + decrypted);
    }

}
