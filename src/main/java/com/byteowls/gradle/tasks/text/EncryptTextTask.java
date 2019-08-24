package com.byteowls.gradle.tasks.text;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class EncryptTextTask extends TextAwareTask {

    public static final String TASK_NAME = "encryptText";
//    public static final String TASK_DESCRIPTION = "Encrypts the given text";

    @Override
    public void taskAction() {
        String encrypted = getEncryptor().encrypt(this.text);
        System.out.println("Encrypted text: " + encrypted);
    }


}
