package com.byteowls.gradle.tasks.text;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class DecryptTextTask extends TextAwareTask {

    public static final String TASK_NAME = "decryptText";
//    public static final String TASK_DESCRIPTION = "Encrypts the given text";

    @Override
    public void taskAction() {
        String decrypted = getEncryptor().decrypt(this.text);
        System.out.println("Decrypted text: " + decrypted);
    }

}
