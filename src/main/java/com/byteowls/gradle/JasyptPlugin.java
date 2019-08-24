package com.byteowls.gradle;

import com.byteowls.gradle.tasks.EncryptInFilesTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public class JasyptPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(JavaPlugin.class);

        project.getTasks().create(EncryptInFilesTask.TASK_NAME, EncryptInFilesTask.class);
    }

}
