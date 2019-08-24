package com.byteowls.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.internal.tasks.options.OptionValidationException;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public abstract class PasswordAwareTask extends DefaultTask {

    private String password;
    private boolean strongEncryption;
    private TextEncryptor encryptor;

    protected TextEncryptor getEncryptor() {
        if (encryptor == null) {
            if (!strongEncryption) {
                BasicTextEncryptor e = new BasicTextEncryptor();
                e.setPassword(this.password);
                this.encryptor = e;
            } else {
                StrongTextEncryptor e = new StrongTextEncryptor();
                e.setPassword(this.password);
                this.encryptor = e;
            }
        }
        return encryptor;
    }

    @TaskAction
    public void action() {
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new OptionValidationException("--password is required!");
        }
        validateOptions();
        taskAction();
    }

    public abstract void taskAction();
    public abstract void validateOptions();

    @Option(option = "strong-encryption", description = "Make sure jce extension is installed")
    public void setStrongEncryption(boolean strongEncryption) {
        this.strongEncryption = strongEncryption;
    }

    @Option(option = "password", description = "password [required]")
    public void setPassword(String password) {
        this.password = password;
    }
}
