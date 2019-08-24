package com.byteowls.gradle.tasks.file;

import com.byteowls.gradle.tasks.PasswordAwareTask;
import org.gradle.api.tasks.options.Option;
import org.jasypt.util.text.TextEncryptor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author m.oberwasserlechner@byteowls.com
 */
public abstract class PropertiesFileAwareTask extends PasswordAwareTask {

    private static final String PROPERTIES_PATTERN = ".*\\.properties";

    private String[] excludedDirectories = new String[] {".gradle", "build", "out"};

    private boolean noBackup = false;

    private Pattern valueExtractorPattern;

    private String fileFilterPattern;

    private List<Path> getPaths() {
        Path rootPath = Paths.get(getProject().getRootDir().toURI());
        try (Stream<Path> walk = Files.walk(rootPath)) {
            return walk.filter(Files::isRegularFile)
                .filter(path -> {
                    for (String excludedDirectory : this.excludedDirectories) {
                        if (path.startsWith(rootPath.resolve(excludedDirectory))) {
                            return false;
                        }
                    }
                    return true;

                })
                .filter(p -> p.getFileName().toString().matches(PROPERTIES_PATTERN))
                .filter(p -> this.fileFilterPattern == null || p.getFileName().toString().matches(fileFilterPattern))
                .collect(Collectors.toList());

        } catch (IOException e) {
            getLogger().error("", e);
        }
        return Collections.emptyList();
    }

    public abstract String process(TextEncryptor encryptor, String extractedValue);
    public abstract String getPropertyPrefix();
    public abstract String getPropertySuffix();
    public abstract String getDefaultExtractRegex();

    @Override
    public void taskAction() {
        try {
            int processedCount = 0;
            List<Path> matchingPaths = getPaths();
            for (Path matching : matchingPaths) {
                List<String> allLines = Files.readAllLines(matching, StandardCharsets.UTF_8);

                Map<Integer, String> processedLines = new HashMap<>();

                int cnt = 0;
                for (String line : allLines) {
                    Matcher matcher = getValueExtractorPattern().matcher(line);
                    if (matcher.find()) {
                        String extractedValue = matcher.group(1);
                        if (extractedValue != null && !extractedValue.trim().isEmpty()) {
                            String matchGroup = matcher.group();
                            processedLines.put(cnt, line.replace(matchGroup, getPropertyPrefix() + process(getEncryptor(), extractedValue) + getPropertySuffix()));
                            processedCount++;
                        }
                    }
                    cnt++;
                }

                if (!processedLines.isEmpty()) {
                    if (!noBackup) {
                        File bkFile = new File(matching.toString() + ".backup");
                        if (!bkFile.exists()) {
                            bkFile.createNewFile();
                        }
                        Files.copy(matching, bkFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                    processedLines.forEach(allLines::set);
                    Files.write(matching, allLines, StandardCharsets.UTF_8);
                }
            }
            System.out.println("No of files found: " + matchingPaths.size());
            System.out.println("No of values changed: " + processedCount);
        } catch (IOException e) {
            getLogger().error("", e);
        }
    }

    @Option(option = "value-extract-pattern", description = "Regular expression to extract the plain text. Defaults to ENCRYPT((.*))")
    public void setValueRegex(String valueRegex) {
        if (valueRegex != null && !valueRegex.trim().isEmpty()) {
            this.valueExtractorPattern = Pattern.compile(valueRegex);
        }
    }

    private Pattern getValueExtractorPattern() {
        if (this.valueExtractorPattern == null) {
            this.valueExtractorPattern = Pattern.compile(getDefaultExtractRegex());
        }
        return this.valueExtractorPattern;
    }

    @Option(option = "file-filter-pattern", description = "Include only these files")
    public void setFileFilterPattern(String fileFilterPattern) {
        this.fileFilterPattern = fileFilterPattern;
    }

    @Option(option = "no-backup", description = "Create no backup of changed files")
    public void setNoBackup(boolean noBackup) {
        this.noBackup = noBackup;
    }

}
