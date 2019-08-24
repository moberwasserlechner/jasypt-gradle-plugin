package com.byteowls.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class EncryptInFilesTask extends DefaultTask {

    public static final String TASK_NAME = "encryptProperties";
    public static final String TASK_DESCRIPTION = "Encrypts the plain_text wrapped with 'ENCRYPT(plain_text)'";

    private String password;
    private String valueRegex = "ENCRYPT\\((1)\\)";
    private String filePattern = "*.properties";

    @Option(option = "password", description = "password [required]")
    public void setPassword(String password) {
        this.password = password;
    }

    @TaskAction
    public void encrypt() {
//        getProject().absoluteProjectPath()

    }

    public void setValueRegex(String valueRegex) {
        this.valueRegex = valueRegex;
    }

    @Option(option = "file-pattern", description = "Include only these files")
    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }
}
